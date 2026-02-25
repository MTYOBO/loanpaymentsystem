package com.bx.loanpaymentsystem;

import com.bx.loanpaymentsystem.payment.service.PaymentService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bx.loanpaymentsystem.loan.entity.Loan;
import com.bx.loanpaymentsystem.loan.entity.LoanStatus;
import com.bx.loanpaymentsystem.loan.service.LoanService;
import com.bx.loanpaymentsystem.payment.entity.Payment;
import com.bx.loanpaymentsystem.payment.repository.PaymentRepository;
import com.bx.loanpaymentsystem.payment.service.PaymentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoanSettlementTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private LoanService loanService;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    void testPayLoanFullPayment() throws Exception {
        // Given
        Long loanId = 1L;
        Integer paymentAmount = 500;
        Loan loan = Loan.builder()
                .loanId(loanId)
                .loanAmount(1000)
                .loanBalance(500)
                .term(12)
                .status(LoanStatus.ACTIVE)
                .build();

        Payment payment = Payment.builder()
                .paymentId(1L)
                .loanId(loanId)
                .paymentAmount(paymentAmount)
                .build();

        when(loanService.retreiveLoan(loanId)).thenReturn(loan);
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        // When
        Payment result = paymentService.payLoan(paymentAmount, loanId);

        // Then
        assertNotNull(result);
        assertEquals(paymentAmount, result.getPaymentAmount());
        assertEquals(loanId, result.getLoanId());
        verify(loanService, times(1)).retreiveLoan(loanId);
        verify(loanService, times(1)).saveLoanDetails(loan);
        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(0, loan.getLoanBalance()); // 500 - 500
        assertEquals(LoanStatus.SETTLED, loan.getStatus()); // Settled
    }
}
