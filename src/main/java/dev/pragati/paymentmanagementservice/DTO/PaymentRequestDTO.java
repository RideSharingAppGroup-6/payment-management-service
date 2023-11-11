package dev.pragati.paymentmanagementservice.DTO;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import dev.pragati.paymentmanagementservice.Models.CreditCard;
import dev.pragati.paymentmanagementservice.Models.PaymentMode;
import dev.pragati.paymentmanagementservice.Models.Phonepe;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import lombok.Getter;
import lombok.Setter;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.*;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.*;

@Getter
@Setter
@JsonTypeInfo(use = Id.NAME, include = PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Phonepe.class, name = "Phonepe"),
        @JsonSubTypes.Type(value = CreditCard.class, name = "CreditCard")
})
public class PaymentRequestDTO {

    private Long userID;

    private Long riderID;

    private double price;

    private PaymentMode paymentMode;

    private String name;


    public PaymentRequestDTO(Long userID, Long riderID, double price, PaymentMode paymentMode, String name) {
        this.userID = userID;
        this.riderID = riderID;
        this.price = price;
        this.paymentMode = paymentMode;
        this.name=name;
    }
}
