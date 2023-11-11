package dev.pragati.paymentmanagementservice.Models;

import com.fasterxml.jackson.annotation.JsonTypeName;
import dev.pragati.paymentmanagementservice.DTO.PaymentRequestDTO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonTypeName("CreditCard")
public class CreditCard extends PaymentRequestDTO {
    private String cardNumber;

    private String expiryDate;

    private int CVV;

    private String country;

    private String cardHolderName;


    public CreditCard(Long userID, Long riderID, double price, PaymentMode paymentMode,String name,
                      String cardNumber, String expiryDate, int CVV,String country,  String cardHolderName) {
        super(userID, riderID, price, paymentMode,name);
        this.cardNumber=cardNumber;
        this.expiryDate=expiryDate;
        this.CVV=CVV;
        this.country=country;
        this.cardHolderName=cardHolderName;
    }
}
