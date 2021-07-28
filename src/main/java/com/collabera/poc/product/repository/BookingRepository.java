package com.collabera.poc.product.repository;

import com.collabera.poc.product.entity.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {
    Optional<Booking> findByReferenceNumber(String referenceNumber);

    Optional<Booking> findByRequestId(String requestId);
}
