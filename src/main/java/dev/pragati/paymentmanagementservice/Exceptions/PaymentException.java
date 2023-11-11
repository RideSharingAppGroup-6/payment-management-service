package dev.pragati.paymentmanagementservice.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PAYMENT_REQUIRED)
public class PaymentException extends Exception{

    public PaymentException(String message){
        super(message);
    }
    
}
