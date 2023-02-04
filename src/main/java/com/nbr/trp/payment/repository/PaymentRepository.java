package com.nbr.trp.payment.repository;

import com.nbr.trp.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {


    public Payment findByUuid(String id);

    //public void savePayment(Payment payment);

    public Payment findByTinNoAndAssessmentYear(String tin, String assessmentYear);

}