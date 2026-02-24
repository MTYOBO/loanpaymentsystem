package com.bx.loanpaymentsystem.loan.repository;

import com.bx.loanpaymentsystem.loan.entity.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long>{

}
