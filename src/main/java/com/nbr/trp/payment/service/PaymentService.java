package com.nbr.trp.payment.service;

import com.nbr.trp.payment.entity.Payment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {

    public void savePayment(Payment payment);

    public Payment getPaymentByUuid(String id) ;

    public Payment getPaymentByUsernameAndYear(String Username, String year);

    public List<Payment> getAll();
}
