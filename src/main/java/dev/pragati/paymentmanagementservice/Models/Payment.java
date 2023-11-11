package dev.pragati.paymentmanagementservice.Models;

import jakarta.persistence.Id;

public class Payment {

    private String userID;

    private String riderID;

    private double price;

   private PaymentMethods paymentMethods;


}
