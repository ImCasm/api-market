package com.casm.apimarket.persistence;

import com.casm.apimarket.domain.Purchase;
import com.casm.apimarket.domain.repository.IPurchaseRepository;
import com.casm.apimarket.persistence.crud.PurchaseCrudRepository;
import com.casm.apimarket.persistence.entity.PurchaseEntity;
import com.casm.apimarket.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class PurchaseRepository implements IPurchaseRepository {

    private final PurchaseCrudRepository purchaseCrudRepository;
    private final PurchaseMapper purchaseMapper;

    @Autowired
    public PurchaseRepository(PurchaseCrudRepository purchaseCrudRepository, PurchaseMapper purchaseMapper) {
        this.purchaseCrudRepository = purchaseCrudRepository;
        this.purchaseMapper = purchaseMapper;
    }

    @Override
    public List<Purchase> getAll() {
        return purchaseMapper.toPurchases((List<PurchaseEntity>) purchaseCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByCustomer(String customerId) {
        return purchaseCrudRepository.findByCustomerId(customerId)
                .map(purchasesEntities -> purchaseMapper.toPurchases(purchasesEntities));
    }

    @Override
    public Optional<List<Purchase>> getByDate(LocalDateTime date) {
        return purchaseCrudRepository.findByDate(date)
                .map(purchases -> purchaseMapper.toPurchases(purchases));
    }

    @Override
    public Purchase save(Purchase purchase) {
        System.out.println(purchase.getState());
        PurchaseEntity purchaseEntity = purchaseMapper.toPurchaseEntity(purchase);
        purchaseEntity.getPurchaseProducts().forEach(product -> product.setPurchase(purchaseEntity));
        return purchaseMapper.toPurchase(purchaseCrudRepository.save(purchaseEntity));
    }
}
