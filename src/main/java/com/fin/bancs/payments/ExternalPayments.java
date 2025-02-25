package com.fin.bancs.payments;

import com.fin.bancs.dto.PaymentDetails;
import lombok.Data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
@Data
public class ExternalPayments {
    private static final Logger log= LogManager.getLogger(ExternalPayments.class);
    PaymentDetails paymentDetails;

    public boolean upiPayment(PaymentDetails paymentDetails){
        return !paymentDetails.getUpiId().isEmpty();
    }

    public boolean cardPayment(PaymentDetails paymentDetails){
        if(!paymentDetails.getCardNumber().isEmpty()){
            log.info("calling Card Vendor");
            return true;
        }else {
            return false;
        }
    }
}
