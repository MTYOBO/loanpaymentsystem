package com.bx.loanpaymentsystem.loan.service;
import com.bx.loanpaymentsystem.loan.entity.Loan;
import com.bx.loanpaymentsystem.loan.repository.LoanRepository;
import org.springframework.stereotype.Service;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanPaymentRepository;

    public LoanServiceImpl(LoanRepository loanPaymentRepository) {
        this.loanPaymentRepository = loanPaymentRepository;
    }

    @Override
    public Loan saveLoanDetails(Loan loan) {
        return loanPaymentRepository.save(loan);
    }

    @Override
    public Loan retreiveLoan(Long loanId) {
        return loanPaymentRepository.findById(loanId).orElse(null);
    }
}
