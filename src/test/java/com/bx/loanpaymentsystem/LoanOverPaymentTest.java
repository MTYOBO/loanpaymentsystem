package com.bx.loanpaymentsystem;

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
public class LoanOverPaymentTest {
    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private LoanService loanService;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    void testPayLoanSuccessful() throws Exception {

        // Given
        Long loanId = 1L;
        Integer paymentAmount = 600;
        Loan loan = Loan.builder()
                .loanId(loanId)
                .loanAmount(1000)
                .loanBalance(500)
                .term(12)
                .status(LoanStatus.ACTIVE)
                .build();

        when(loanService.retreiveLoan(loanId)).thenReturn(loan);

        // When & Then
        Exception exception = assertThrows(Exception.class, () -> {
            paymentService.payLoan(paymentAmount, loanId);
        });
        assertEquals("Payment amount of 600 is greater than balance of 500", exception.getMessage());
        verify(loanService, times(1)).retreiveLoan(loanId);
        verify(loanService, never()).saveLoanDetails(any(Loan.class));
        verify(paymentRepository, never()).save(any(Payment.class));

    }
}
