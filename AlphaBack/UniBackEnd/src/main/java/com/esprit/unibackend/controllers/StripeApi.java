package com.esprit.unibackend.controllers;

import com.esprit.unibackend.entities.StripeChargeDto;
import com.esprit.unibackend.entities.StripeTokenDto;
import com.esprit.unibackend.services.StripeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stripe")
@AllArgsConstructor
public class StripeApi {

    private final StripeService stripeService;


    @PostMapping("/card/token")
    @ResponseBody
    public StripeTokenDto createCardToken(@RequestBody StripeTokenDto model){
        return stripeService.createCardToken(model);
    }


    @PostMapping("/charge")
    public ResponseEntity<String> charge(@RequestBody StripeChargeDto chargeRequest) {
        try {
            StripeChargeDto response = stripeService.charge(chargeRequest);
            return ResponseEntity.ok(response.getSuccess() ? "Charge successful" : "Charge failed");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
