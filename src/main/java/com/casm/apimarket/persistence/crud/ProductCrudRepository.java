package com.casm.apimarket.persistence.crud;

import com.casm.apimarket.persistence.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCrudRepository extends CrudRepository<ProductEntity, Integer> {

    //@Query(value = "SELECT * FROM productos WHERE id_categoria = ?",nativeQuery = true)
    public List<ProductEntity> findByCategoryIdOrderByNameAsc(int categoryId);
    public Optional<List<ProductEntity>> findByStockLessThanAndState(int stock, boolean state);
    public Optional<List<ProductEntity>> findBySalesPriceGreaterThanAndStockGreaterThan(double salesPrice, int stock);
}
