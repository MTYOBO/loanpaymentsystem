package com.bx.loanpaymentsystem.payment.controller;

import com.bx.loanpaymentsystem.payment.entity.Payment;
import com.bx.loanpaymentsystem.payment.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/")
    public ResponseEntity<Payment> updateLoanBalance(@RequestBody Payment payment) throws Exception {
        return ResponseEntity.ok(paymentService.payLoan(payment.getPaymentAmount(),payment.getLoanId()));
    }
}
