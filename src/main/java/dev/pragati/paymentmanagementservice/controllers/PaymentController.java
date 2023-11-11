package dev.pragati.paymentmanagementservice.controllers;

import dev.pragati.paymentmanagementservice.DTO.PaymentResponseDTO;
import dev.pragati.paymentmanagementservice.DTO.PaymentRequestDTO;
import dev.pragati.paymentmanagementservice.DTO.RefundRequestDTO;
import dev.pragati.paymentmanagementservice.DTO.RefundResponseDTO;
import dev.pragati.paymentmanagementservice.Exceptions.PaymentException;
import dev.pragati.paymentmanagementservice.Service.PaymentService;
import dev.pragati.paymentmanagementservice.Service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private RefundService refundService;

    @PostMapping("/process")
    public PaymentResponseDTO processPayment(@RequestBody PaymentRequestDTO PaymentRequestDTO){
        return paymentService.savePayment(PaymentRequestDTO);
    }

    @PostMapping("/refund")
    public RefundResponseDTO refundProcess(@RequestBody RefundRequestDTO refundDTO) throws PaymentException {
       return refundService.refundToUser(refundDTO);

    }

}
