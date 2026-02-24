package com.bx.loanpaymentsystem.loan.controller;

import com.bx.loanpaymentsystem.loan.entity.Loan;
import com.bx.loanpaymentsystem.loan.entity.LoanStatus;
import com.bx.loanpaymentsystem.loan.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoanController {
    private final LoanService loanService;
    public LoanController(LoanService loanService) {

        this.loanService = loanService;
    }

    @PostMapping("/")
    public ResponseEntity<Loan> createNewLoan(@RequestBody Loan loan) {
        loan.setStatus(LoanStatus.ACTIVE);
        return ResponseEntity.ok(loanService.saveLoanDetails(loan));
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<Loan> getLoanDetails(@PathVariable Long loanId)
    {
        return ResponseEntity.ok(loanService.retreiveLoan(loanId));
    }
}
