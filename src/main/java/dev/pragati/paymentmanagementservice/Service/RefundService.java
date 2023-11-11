package dev.pragati.paymentmanagementservice.Service;

import dev.pragati.paymentmanagementservice.DTO.RefundRequestDTO;
import dev.pragati.paymentmanagementservice.DTO.RefundResponseDTO;
import dev.pragati.paymentmanagementservice.Exceptions.PaymentException;
import dev.pragati.paymentmanagementservice.Models.PaymentMethods;
import dev.pragati.paymentmanagementservice.Models.PaymentStatus;
import dev.pragati.paymentmanagementservice.Models.Refund;
import dev.pragati.paymentmanagementservice.Repository.PaymentRepository;
import dev.pragati.paymentmanagementservice.Repository.RefundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RefundService {

    @Autowired
    private RefundRepository refundRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public RefundResponseDTO refundToUser(RefundRequestDTO refundDTO) throws PaymentException {
        RefundResponseDTO refundResponseDTO=null;
        Optional<PaymentMethods> paymentMethods=paymentRepository.findByRiderIDAndUserID(refundDTO.getRiderID(), refundDTO.getUserID());
        Optional<Refund> refund=refundRepository.findByRiderIdAndUserId(refundDTO.getRiderID(), refundDTO.getUserID());

        if(paymentMethods.isEmpty()){
            throw new PaymentException("Rider with ID not found");
        }
        else if (paymentMethods.get().getPaymentStatus().equals(PaymentStatus.FAIL)) {
            throw new PaymentException("Payment has failed, Please make payment");

        }
        else if (paymentMethods.get().getPaymentStatus().equals(PaymentStatus.SUCCESS)) {
            if(refund.isEmpty()){
                Refund refund1=new Refund();
                refund1.setUserId(paymentMethods.get().getUserID());
                refund1.setRiderId(paymentMethods.get().getRiderID());
                refund1.setPrice(paymentMethods.get().getPrice());
                refund1.setRefundStatus("Refunded");
                Refund refund2=refundRepository.save(refund1);
                refundResponseDTO=convertToRefundResponseDTO(refund2);
            }
            else if (refund.get().getRefundStatus().equals("refunded")) {
                throw  new PaymentException("Already refunded");
            }
        }
        return  refundResponseDTO;
    }

    private RefundResponseDTO convertToRefundResponseDTO(Refund refund2) {
        RefundResponseDTO refundResponseDTO=new RefundResponseDTO();
        refundResponseDTO.setUserID(refund2.getUserId());
        refundResponseDTO.setRiderId(refund2.getRiderId());
        refundResponseDTO.setRefundStatus(refund2.getRefundStatus());
       return refundResponseDTO;
    }
}
