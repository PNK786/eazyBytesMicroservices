package com.easybit.accounts.Mapper;

import com.easybit.accounts.Dto.CustomerDTO;
import com.easybit.accounts.entity.Customer;

public class CustomerMapper {
    public static CustomerDTO mapToCustomerDto(CustomerDTO customerDTO,Customer customer) {
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setMobileNumber(customer.getMobileNumber());

        return customerDTO;
    }

    public static Customer mapToCustomer(CustomerDTO customerDTO,Customer customer) {
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setMobileNumber(customerDTO.getMobileNumber());
        return customer;
    }
}
