package com.bx.loanpaymentsystem;

import com.bx.loanpaymentsystem.loan.entity.Loan;
import com.bx.loanpaymentsystem.loan.entity.LoanStatus;
import com.bx.loanpaymentsystem.loan.repository.LoanRepository;
import com.bx.loanpaymentsystem.loan.service.LoanServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoanCreationTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanServiceImpl loanService;

    @Test
    void testSaveLoanDetails() {
        // Given
        Loan loan = Loan.builder()
                .loanAmount(1000)
                .loanBalance(1000)
                .term(12)
                .status(LoanStatus.ACTIVE)
                .build();

        Loan savedLoan = Loan.builder()
                .loanId(1L)
                .loanAmount(1000)
                .loanBalance(1000)
                .term(12)
                .status(LoanStatus.ACTIVE)
                .build();

        when(loanRepository.save(any(Loan.class))).thenReturn(savedLoan);

        // When
        Loan result = loanService.saveLoanDetails(loan);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getLoanId());
        assertEquals(1000, result.getLoanAmount());
        assertEquals(1000, result.getLoanBalance());
        assertEquals(12, result.getTerm());
        assertEquals(LoanStatus.ACTIVE, result.getStatus());
        verify(loanRepository, times(1)).save(loan);
    }
}
