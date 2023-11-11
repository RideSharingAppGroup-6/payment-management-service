package dev.pragati.paymentmanagementservice.Repository;

import dev.pragati.paymentmanagementservice.Models.Refund;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefundRepository extends JpaRepository<Refund, Long> {


    Optional<Refund> findByRiderIdAndUserId(Long RiderId, Long UserId);
}
