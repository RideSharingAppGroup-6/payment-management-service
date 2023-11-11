package dev.pragati.paymentmanagementservice.Service;

import dev.pragati.paymentmanagementservice.DTO.PaymentResponseDTO;
import dev.pragati.paymentmanagementservice.DTO.PaymentRequestDTO;
import dev.pragati.paymentmanagementservice.Models.*;
import dev.pragati.paymentmanagementservice.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public PaymentResponseDTO savePayment(PaymentRequestDTO paymentRequestDTO) {
        PaymentMethods paymentMethods = processPaymentAndupdatePaymentStatus(paymentRequestDTO);
        PaymentMethods paymentMethods1 = paymentRepository.save(paymentMethods);

        PaymentResponseDTO paymentDTO = convertPaymentRequestDtoToPaymentDTO(paymentMethods1);
        return paymentDTO;
    }

    private PaymentResponseDTO convertPaymentRequestDtoToPaymentDTO(PaymentMethods paymentMethods) {
        PaymentResponseDTO paymentDTO = new PaymentResponseDTO();
        paymentDTO.setId(paymentMethods.getId());
        paymentDTO.setUserID(paymentMethods.getUserID());
        paymentDTO.setRiderID(paymentMethods.getRiderID());
        paymentDTO.setPrice(paymentMethods.getPrice());
        paymentDTO.setStatus(paymentMethods.getPaymentStatus().name());
        return paymentDTO;
    }

    private PaymentMethods validatePhonePeAccount(PaymentRequestDTO paymentRequestDTO) {
        Phonepe phonepe = (Phonepe) paymentRequestDTO;
        PaymentMethods paymentMethods = new PaymentMethods();
        if (phonepe.getUPIID() != null) {
            paymentMethods.setPaymentStatus(PaymentStatus.SUCCESS);
            paymentMethods.setUserID(paymentRequestDTO.getUserID());
            paymentMethods.setRiderID(paymentRequestDTO.getRiderID());
            paymentMethods.setPrice(paymentRequestDTO.getPrice());
            paymentMethods.setPaymentMode(paymentRequestDTO.getPaymentMode());
        } else if (phonepe.getUPIID() == null) {
            paymentMethods.setPaymentStatus(PaymentStatus.FAIL);
            paymentMethods.setUserID(paymentRequestDTO.getUserID());
            paymentMethods.setRiderID(paymentRequestDTO.getRiderID());
            paymentMethods.setPrice(paymentRequestDTO.getPrice());
            paymentMethods.setPaymentMode(paymentRequestDTO.getPaymentMode());
        }
        return paymentMethods;
    }

    private PaymentMethods validateCreditCardDetails(PaymentRequestDTO paymentRequestDTO) {
        CreditCard creditCard=(CreditCard) paymentRequestDTO;
        PaymentMethods paymentMethods = new PaymentMethods();
        if (creditCard.getCardNumber() != null && creditCard.getExpiryDate()!=null &&
            creditCard.getCVV()!=0 && creditCard.getCountry()!=null) {
            paymentMethods.setPaymentStatus(PaymentStatus.SUCCESS);
            paymentMethods.setUserID(paymentRequestDTO.getUserID());
            paymentMethods.setRiderID(paymentRequestDTO.getRiderID());
            paymentMethods.setPrice(paymentRequestDTO.getPrice());
            paymentMethods.setPaymentMode(paymentRequestDTO.getPaymentMode());
        } else if (creditCard.getCardNumber() == null || creditCard.getExpiryDate()==null ||
                creditCard.getCVV()==0 || creditCard.getCountry()==null) {
            paymentMethods.setPaymentStatus(PaymentStatus.FAIL);
            paymentMethods.setUserID(paymentRequestDTO.getUserID());
            paymentMethods.setRiderID(paymentRequestDTO.getRiderID());
            paymentMethods.setPrice(paymentRequestDTO.getPrice());
            paymentMethods.setPaymentMode(paymentRequestDTO.getPaymentMode());
        }
        return paymentMethods;

    }

    private PaymentMethods processPaymentAndupdatePaymentStatus(PaymentRequestDTO paymentRequestDTO) {
        PaymentMethods paymentMethods = null;
        if (paymentRequestDTO.getUserID() != null && paymentRequestDTO.getRiderID() != null &&
                paymentRequestDTO.getPaymentMode().equals(PaymentMode.PHONEPe)) {
             paymentMethods=validatePhonePeAccount(paymentRequestDTO);

        } else if (paymentRequestDTO.getUserID() != null && paymentRequestDTO.getRiderID() != null &&
                paymentRequestDTO.getPaymentMode().equals(PaymentMode.Creditcard)) {
            paymentMethods=validateCreditCardDetails(paymentRequestDTO);
        }
        return paymentMethods;
    }
}
