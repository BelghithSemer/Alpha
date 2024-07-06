package com.esprit.unibackend.services;

import com.esprit.unibackend.Repos.PaymentRepository;
import com.esprit.unibackend.entities.Payment;
import com.esprit.unibackend.entities.PaymentInfoRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PaymentService {

    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, @Value("${stripe.apiKey}") String secretKey) {
        this.paymentRepository = paymentRepository;
        Stripe.apiKey = secretKey;
    }

    public PaymentIntent createPaymentIntent(PaymentInfoRequest paymentInfoRequest) throws StripeException {
        List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");

        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentInfoRequest.getAmount());
        params.put("currency", paymentInfoRequest.getCurrency());
        params.put("payment_method_types", paymentMethodTypes);

        return PaymentIntent.create(params);
    }

    public ResponseEntity<String> stripePayment(String clientSecret) throws Exception {
        // Here, use the client secret to complete the payment process
        // This could involve calling Stripe's API to capture the payment
        // For example:
        PaymentIntent paymentIntent = PaymentIntent.retrieve(clientSecret);
        PaymentIntent updatedPaymentIntent = paymentIntent.capture();

        // Save the payment details to your database if needed
        // Assuming you have a method to get user email from the client secret
        String userEmail = getUserEmailFromClientSecret(clientSecret);
        Payment payment = paymentRepository.findByUserEmail(userEmail);

        if (payment == null) {
            throw new Exception("Payment information is missing");
        }
        payment.setAmount(updatedPaymentIntent.getAmount());
        paymentRepository.save(payment);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String getUserEmailFromClientSecret(String clientSecret) {
        // Implement logic to retrieve user email based on the client secret
        // This might involve storing the mapping of client secret to user email during the payment intent creation
        return "user@example.com"; // Placeholder implementation
    }


}
