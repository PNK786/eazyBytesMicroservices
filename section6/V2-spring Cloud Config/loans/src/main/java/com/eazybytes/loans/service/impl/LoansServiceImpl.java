package com.eazybytes.loans.service.impl;

import com.eazybytes.loans.constants.LoansConstant;
import com.eazybytes.loans.dto.LoansDto;
import com.eazybytes.loans.entity.Loans;
import com.eazybytes.loans.exception.LoanAlreadyExistedException;
import com.eazybytes.loans.exception.ResourceNotFoundException;
import com.eazybytes.loans.mapper.LoansMapper;
import com.eazybytes.loans.repository.LoansRepository;
import com.eazybytes.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

    private LoansRepository loansRepository;

    /**
     *
     * @param mobileNumber-mobile Number Of the customer
     */
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> optionalLoans  =loansRepository.findByMobileNumber(mobileNumber);

        if(optionalLoans.isPresent())
        {
            throw new LoanAlreadyExistedException("Loan already existed with the given mobile Number"+mobileNumber);
        }
        loansRepository.save(createNewLoan(mobileNumber));
    }
    private Loans createNewLoan(String mobileNumber){

        Loans newLoan =new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstant.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstant.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstant.NEW_LOAN_LIMIT);
        newLoan.setCreatedBy("User");
        newLoan.setCreatedAt(LocalDateTime.now());
        newLoan.setUpdatedAt(LocalDateTime.now());
        newLoan.setUpdatedBy("User");

        return newLoan;
    }
    /**
     * @param mobileNumber - Input mobile Number
     * @return Loan Details based on a given mobileNumber
     */
    @Override
    public LoansDto fetchLoan(String mobileNumber) {
       Loans loans =loansRepository.findByMobileNumber(mobileNumber)
               .orElseThrow(
                       ()->new ResourceNotFoundException("Loans","MobileNumber",mobileNumber)
               );

     return LoansMapper.mapToLoansDto(loans,new LoansDto());
    }

    /**
     * @param loansDto
     * update Loan details Expect Loan Number
     */
    @Override
    public boolean updateLoanDetails(LoansDto loansDto) {

        Loans loans =loansRepository.findByLoanNumber(loansDto.getLoanNumber())
                .orElseThrow(
                        ()->new ResourceNotFoundException("Loan","loanNumber",loansDto.getLoanNumber()));

         LoansMapper.mapToLoans(loansDto,loans);
         loansRepository.save(loans);
        return true;
    }

    /**
     * @param mobileNumber
     * @return delete Loan based on true or false
     */
    @Override
    public boolean deleteLoan(String mobileNumber) {
      Loans loans=loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
              () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
      );

      loansRepository.deleteById(loans.getLoanId());
        return true;
    }


}
