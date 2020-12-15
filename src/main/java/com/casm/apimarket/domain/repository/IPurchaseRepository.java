package com.casm.apimarket.domain.repository;

import com.casm.apimarket.domain.Purchase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IPurchaseRepository {
    List<Purchase> getAll();
    Optional<List<Purchase>> getByCustomer(String customerId);
    Optional<List<Purchase>> getByDate(LocalDateTime date);
    Purchase save(Purchase purchase);
}
