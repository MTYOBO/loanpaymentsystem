package com.bx.loanpaymentsystem.payment.repository;

import com.bx.loanpaymentsystem.payment.entity.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long>{

}
