package dev.pragati.paymentmanagementservice.Models;

import com.fasterxml.jackson.annotation.JsonTypeName;
import dev.pragati.paymentmanagementservice.DTO.PaymentRequestDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonTypeName("Phonepe")
public class Phonepe extends PaymentRequestDTO {

    private String UPIID;


    public Phonepe(Long userID, Long riderID, double price, PaymentMode paymentMode,String UPIID, String name) {
        super(userID, riderID, price, paymentMode,name);
        this.UPIID=UPIID;
    }
}
