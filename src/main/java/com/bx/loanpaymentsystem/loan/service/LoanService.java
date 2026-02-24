package com.bx.loanpaymentsystem.loan.service;

import com.bx.loanpaymentsystem.loan.entity.Loan;

public interface LoanService {
    Loan saveLoanDetails(Loan loan);
    Loan retreiveLoan(Long loanId);
}
