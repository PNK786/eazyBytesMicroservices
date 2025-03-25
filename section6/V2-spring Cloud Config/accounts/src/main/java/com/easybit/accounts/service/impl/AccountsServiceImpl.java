package com.easybit.accounts.service.impl;

import com.easybit.accounts.Dto.AccountsDto;
import com.easybit.accounts.Dto.CustomerDTO;
import com.easybit.accounts.Exception.CustomerAlreadyExistingException;
import com.easybit.accounts.Exception.ResourceNotFoundException;
import com.easybit.accounts.Mapper.AccountsMapper;
import com.easybit.accounts.Mapper.CustomerMapper;
import com.easybit.accounts.constants.AccountsContants;
import com.easybit.accounts.entity.Accounts;
import com.easybit.accounts.entity.Customer;
import com.easybit.accounts.repository.AccountsRepository;
import com.easybit.accounts.repository.CustomerRepository;
import com.easybit.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDTO customerDto) {

        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());

        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistingException("Customer already registered with given Mobile Number" + customerDto.getMobileNumber());
        }
//        customer.setCreatedBy("Myself");
//        customer.setCreatedAt(LocalDateTime.now());
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    private Accounts createNewAccount(Customer customer) {

        Accounts newAccount = new Accounts();

        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsContants.SAVINGS);
        newAccount.setBranchAddress(AccountsContants.ADDRESS);
//        newAccount.setCreatedBy("Myself");
//        newAccount.setCreatedAt(LocalDateTime.now());
        return newAccount;
    }

    @Override
    public CustomerDTO fetchDetails(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString()));

        CustomerDTO customerDTO = CustomerMapper.mapToCustomerDto(new CustomerDTO(), customer);

        customerDTO.setAccountsDto(AccountsMapper.marToAccountsDto(accounts, new AccountsDto()));
        return customerDTO;
    }

    /**
     * @param customerDTO -customerDto object
     * @return boolean indicating if the update of Account details is successful or not
     */
    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {
        boolean isUpdated = false;

        AccountsDto accountsDto = customerDTO.getAccountsDto();


        if (accountsDto != null) {
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber())
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString()));
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);
            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerId", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDTO, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    /**
     * @param mobileNumber -Input Mobile Number
     * @return boolean indicating if the delete account details success or not
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {

       Customer customer= customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()->new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
                );

        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }


}
