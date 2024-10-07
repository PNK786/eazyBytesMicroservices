package com.eazybytes.Loans.service;

import com.eazybytes.Loans.dto.LoansDto;

public interface ILoansService {

    void createLoan(String mobileNumber);
    LoansDto fetchLoan(String mobileNumber);
    boolean updateLoanDetails(LoansDto loansDto);
    boolean deleteLoan(String mobileNumber);
}
