package com.fin.bancs.controller;

import com.fin.bancs.payments.ExternalPayments;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final ExternalPayments externalPayments;
    private final WebClient webClient;

    public PaymentController(WebClient webClient, ExternalPayments externalPayments) {
        this.webClient = webClient;
        this.externalPayments = externalPayments;
    }


    @PostMapping("/process-payment")
    public ResponseEntity<String> processPayment(@RequestBody Map<String, String> request){

        if(request.get("token").isEmpty() || request.get("amount").isEmpty()){
            return ResponseEntity.badRequest().body("Not correct Request");
        }


        String deToken= webClient.get()
                .uri(
        uriBuilder -> uriBuilder.path("api/token/detokenize")
                .queryParam("token", request.get("token"))
                .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println("Your Card Number is : "+deToken);

        return ResponseEntity.ok("payment Successful");
    }



}
