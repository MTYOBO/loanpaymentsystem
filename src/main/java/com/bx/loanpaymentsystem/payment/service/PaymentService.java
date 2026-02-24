package com.bx.loanpaymentsystem.payment.service;

import com.bx.loanpaymentsystem.payment.entity.Payment;

public interface PaymentService {
    Payment payLoan(Integer paymentAmount,Long loanId) throws Exception;
}
