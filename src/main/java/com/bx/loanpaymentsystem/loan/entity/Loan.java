package com.bx.loanpaymentsystem.loan.entity;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long loanId;
    private Integer loanAmount;
    private Integer loanBalance;
    private Integer term;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private LoanStatus status =  LoanStatus.ACTIVE;
}
