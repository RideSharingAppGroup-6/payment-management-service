package dev.pragati.paymentmanagementservice.Repository;

import dev.pragati.paymentmanagementservice.Models.PaymentMethods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PaymentRepository extends JpaRepository<PaymentMethods, Long> {

    Optional<PaymentMethods> findByRiderID(Long aLong);


    Optional<PaymentMethods> findByRiderIDAndUserID(Long RiderId, Long UserId);
}
