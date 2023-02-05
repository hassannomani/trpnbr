package com.nbr.trp.payment.service;

import com.nbr.trp.payment.entity.Payment;
import com.nbr.trp.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PaymentServiceImpl implements PaymentService{

    @Autowired

    private PaymentRepository paymentRepository;

    @Autowired
    public void PaymentServiceImp( PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void savePayment(Payment payment) {
        paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentByUuid(String id) {

        Payment payment = paymentRepository.findByUuid(id);

        return payment;
    }

    @Override
    public Payment getPaymentByUsernameAndYear(String username, String year){

        Payment payment = paymentRepository.findByUsernameAndAssessmentYear(username,year);
        return payment;
    }

    @Override
    public List<Payment> getAll(){
        return paymentRepository.findAll();
    }

}

