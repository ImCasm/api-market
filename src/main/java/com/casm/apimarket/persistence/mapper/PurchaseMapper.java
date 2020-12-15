package com.casm.apimarket.persistence.mapper;

import com.casm.apimarket.domain.Purchase;
import com.casm.apimarket.persistence.entity.PurchaseEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PurchaseItemMapper.class})
public interface PurchaseMapper {

    @Mappings({
        @Mapping(source = "purchaseProducts", target = "items")
    })
    Purchase toPurchase(PurchaseEntity purchase);
    List<Purchase> toPurchases(List<PurchaseEntity> purchases);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "customer", ignore = true)
    })
    PurchaseEntity toPurchaseEntity(Purchase purchase);
}
