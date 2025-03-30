package org.example.service;

import org.example.model.Payment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {

    private final List<Payment> payments = new ArrayList<>();

    public List<Payment> getAllPayments() {
        return payments;
    }

    public Payment getPaymentById(Long id) {
        return payments.stream()
                .filter(payment -> payment.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Payment processPayment(Payment payment) {
        payment.setId((long) (payments.size() + 1));
        payments.add(payment);
        return payment;
    }

    public Payment cancelPayment(Long id, Payment updatedPayment) {
        Payment payment = getPaymentById(id);
        if (payment != null) {
            payment.setStatus("Cancelled");
            payment.setCancellationReason(updatedPayment.getCancellationReason());
        }
        return payment;
    }
}