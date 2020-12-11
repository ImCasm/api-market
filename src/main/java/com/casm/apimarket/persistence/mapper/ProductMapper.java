package com.casm.apimarket.persistence.mapper;

import com.casm.apimarket.domain.Product;
import com.casm.apimarket.persistence.entity.ProductEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface ProductMapper {

    @Mappings({
            @Mapping(source = "salesPrice", target = "price"),
            @Mapping(source = "state", target = "active"),
            @Mapping(source = "category", target = "category")
    })
    Product toProduct(ProductEntity product);
    List<Product> toProducts(List<ProductEntity> products);

    @InheritInverseConfiguration
    @Mapping(target = "barcode", ignore = true)
    ProductEntity toProductEntity(Product product);
}
