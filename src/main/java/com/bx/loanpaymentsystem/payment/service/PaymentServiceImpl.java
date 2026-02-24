package com.bx.loanpaymentsystem.payment.service;

import com.bx.loanpaymentsystem.loan.entity.Loan;
import com.bx.loanpaymentsystem.loan.entity.LoanStatus;
import com.bx.loanpaymentsystem.loan.service.LoanService;
import com.bx.loanpaymentsystem.payment.entity.Payment;
import com.bx.loanpaymentsystem.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final LoanService  loanService;

    public PaymentServiceImpl(PaymentRepository paymentRepository, LoanService loanService) {
        this.paymentRepository = paymentRepository;
        this.loanService = loanService;
    }


    @Override
    public Payment payLoan(Integer paymentAmount, Long loanId) throws Exception {
        Loan loan = loanService.retreiveLoan(loanId);
        Payment payment = new Payment();
        payment.setPaymentAmount(paymentAmount);
        payment.setLoanId(loanId);

        if(loan.getStatus() == LoanStatus.SETTLED){
            throw new Exception("The loan has already been settled ");
        }else {
            int newBalance = loan.getLoanBalance() - paymentAmount;
            if (newBalance < 0) {
                throw new Exception("Payment amount of " + paymentAmount + " is greater than balance of " + loan.getLoanBalance());
            } else if (newBalance == 0) {
                loan.setStatus(LoanStatus.SETTLED);
            }
            loan.setLoanBalance(newBalance);
            loanService.saveLoanDetails(loan);
            return paymentRepository.save(payment);
        }
    }
}
