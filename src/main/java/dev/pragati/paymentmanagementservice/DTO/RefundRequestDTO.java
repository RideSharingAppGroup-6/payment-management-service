package dev.pragati.paymentmanagementservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefundRequestDTO {

    private Long userID;

    private Long riderID;

    private double price;

}
