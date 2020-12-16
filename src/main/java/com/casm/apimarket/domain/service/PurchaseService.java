package com.casm.apimarket.domain.service;

import com.casm.apimarket.domain.Purchase;
import com.casm.apimarket.persistence.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public List<Purchase> getAll(){
        return purchaseRepository.getAll();
    }

    public Optional<List<Purchase>> getByCustomer(String customerId){
        return purchaseRepository.getByCustomer(customerId);
    }

    public Optional<List<Purchase>> getByDate(LocalDateTime date){
        return purchaseRepository.getByDate(date);
    }

    public Purchase findById(int purchaseId) {
        return purchaseRepository.getById(purchaseId);
    }

    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }
}
