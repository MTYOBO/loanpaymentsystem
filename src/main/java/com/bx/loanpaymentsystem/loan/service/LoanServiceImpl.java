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
        if (loan == null) {
            throw new IllegalArgumentException("Loan cannot be null");
        }
        return loanPaymentRepository.save(loan);
    }

    @Override
    public Loan retreiveLoan(Long loanId) {
        if (loanId == null) {
            throw new IllegalArgumentException("Loan ID cannot be null");
        }
        return loanPaymentRepository.findById(loanId).orElse(null);
    }
}
