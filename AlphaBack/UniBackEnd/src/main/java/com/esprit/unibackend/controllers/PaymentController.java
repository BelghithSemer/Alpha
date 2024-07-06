package com.esprit.unibackend.controllers;


import com.esprit.unibackend.entities.PaymentInfoRequest;
import com.esprit.unibackend.services.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment/secure")
@AllArgsConstructor
public class PaymentController {

    private PaymentService paymentService;



    @PostMapping("/payment-intent")
    public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfoRequest paymentInfoRequest)
            throws StripeException {

        PaymentIntent paymentIntent = paymentService.createPaymentIntent(paymentInfoRequest);
        String paymentStr = paymentIntent.toJson();

        return new ResponseEntity<>(paymentStr, HttpStatus.OK);
    }

    @PutMapping("/payment-complete")
    public ResponseEntity<String> stripePaymentComplete(@RequestHeader("Authorization") String authorizationHeader) throws Exception {
        // Remove "Bearer " from the authorization header to get the actual client secret
        String clientSecret = authorizationHeader.replace("Bearer ", "");

        // Assuming the client secret is passed to the payment service for processing
        // You may need to adjust the payment service to accept the client secret
        return paymentService.stripePayment(clientSecret);
    }

}
