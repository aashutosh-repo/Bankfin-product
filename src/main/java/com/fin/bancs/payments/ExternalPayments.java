package com.fin.bancs.payments;

import com.fin.bancs.dto.PaymentDetails;
import lombok.Data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Service
@Data
public class ExternalPayments {
    private static final Logger log= LogManager.getLogger(ExternalPayments.class);
    PaymentDetails paymentDetails;
    @Autowired
    private final WebClient webClient;


    public boolean upiPayment(PaymentDetails paymentDetails){
        return !paymentDetails.getUpiId().isEmpty();
    }

    public boolean cardPayment(PaymentDetails paymentDetails){
       String deToken= webClient.get()
                .uri("/api/token/detokenize")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        if(!paymentDetails.getCardNumber().isEmpty() || Objects.requireNonNull(deToken).isEmpty()){
            log.info("calling Card Vendor");
            return true;
        }else {
            return false;
        }
    }
}
