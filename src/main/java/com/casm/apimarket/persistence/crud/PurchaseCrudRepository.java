package com.casm.apimarket.persistence.crud;

import com.casm.apimarket.persistence.entity.PurchaseEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PurchaseCrudRepository extends CrudRepository<PurchaseEntity, Integer> {
    Optional<List<PurchaseEntity>> findByCustomerId(String customerId);
    Optional<List<PurchaseEntity>> findByDate(LocalDateTime date);
}
