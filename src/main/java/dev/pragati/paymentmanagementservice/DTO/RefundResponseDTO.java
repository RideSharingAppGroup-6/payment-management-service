package dev.pragati.paymentmanagementservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefundResponseDTO {

    private Long UserID;
    private Long riderId;
    private String refundStatus;
}
