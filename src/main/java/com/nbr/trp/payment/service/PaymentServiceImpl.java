package com.nbr.trp.payment.service;

import com.nbr.trp.payment.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PaymentServiceImp implements PaymentService{

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
    public Payment getPaymentByUUID(String id) throws PaymentNotFoundException {

        Payment payment = paymentRepository.findByUuid(id);

        if(payment == null){
            throw new PaymentNotFoundException("Invalid payment id");
        }
        return payment;
    }

    @Override
    public Payment getPaymentByTinNoAndYear(String id, String year) throws PaymentNotFoundException {

        Payment payment = paymentRepository.findByTinNoAndAssessmentYear(id,year);

        if(payment == null){
            throw new PaymentNotFoundException("Invalid payment id");
        }
        return payment;
    }

    @Override
    public List<Payment> getAll(){
        return paymentRepository.findAll();
    }

}

