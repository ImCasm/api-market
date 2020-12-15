package com.casm.apimarket.persistence.mapper;

import com.casm.apimarket.domain.PurchaseItem;
import com.casm.apimarket.persistence.entity.ProductPurchaseEntity;
import com.casm.apimarket.persistence.entity.PurchaseEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PurchaseItemMapper {

    @Mappings({
        @Mapping(source = "id.productId", target = "productId"),
        @Mapping(source = "state", target = "active"),
        @Mapping(source = "quantity", target = "quantity"),
    })
    PurchaseItem toPurchaseItem(ProductPurchaseEntity purchase);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "purchase", ignore = true),
            @Mapping(target = "product", ignore = true),
            @Mapping(target = "id.purchaseId", ignore = true),
    })
    ProductPurchaseEntity toProductPurchaseEntity(PurchaseItem purchaseItem);
}
