package com.esprit.unibackend.services;

import com.esprit.unibackend.entities.StripeChargeDto;
import com.esprit.unibackend.entities.StripeTokenDto;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import com.stripe.model.Token;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class StripeService {

    @Value("${stripe.apiKey}")
    private String stripeApiKey;

    @PostConstruct
    public void init(){
        Stripe.apiKey = stripeApiKey;
    }

    public StripeTokenDto createCardToken(StripeTokenDto model){
        try{
            Map<String , Object> card = new HashMap<>();
            card.put("number", model.getCardNumber());
            card.put("exp_month", model.getExpMonth());
            card.put("exp_year", model.getExpYear());
            card.put("cvc", model.getCvc());

            Map<String, Object> params = new HashMap<>();
            params.put("card",card);

            Token token = Token.create(params);
            if (token!= null && token.getId() != null){
                model.setSuccess(true);
                model.setToken(token.getId());
            }
            return model;

        }catch( StripeException e){
            System.out.println("Stripe Service (createToken)"+e);
            throw new RuntimeException(e.getMessage());
        }
    }


    public StripeChargeDto charge(StripeChargeDto chargeRequest) {
        try {
            chargeRequest.setSuccess(false);
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", (int) (chargeRequest.getAmount() * 100));
            chargeParams.put("currency", "USD");
            chargeParams.put("description", "Payment for " + chargeRequest.getUsername());
            chargeParams.put("source", chargeRequest.getStripeToken());
            Map<String, Object> metaData = new HashMap<>();
            metaData.put("username", chargeRequest.getUsername());
            metaData.putAll(chargeRequest.getAdditionalInfo());
            chargeParams.put("metadata", metaData);
            Charge charge = Charge.create(chargeParams);
            chargeRequest.setMessage(charge.getOutcome().getSellerMessage());

            if (charge.getPaid()) {
                chargeRequest.setChargeId(charge.getId());
                chargeRequest.setSuccess(true);
            }
            return chargeRequest;
        } catch (StripeException e) {
            log.error("StripeService (charge)", e);
            throw new RuntimeException(e.getMessage());
        }
    }




}
