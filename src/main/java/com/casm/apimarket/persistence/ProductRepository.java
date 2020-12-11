package com.casm.apimarket.persistence;

import com.casm.apimarket.domain.Product;
import com.casm.apimarket.domain.repository.IProductRepository;
import com.casm.apimarket.persistence.crud.ProductCrudRepository;
import com.casm.apimarket.persistence.entity.ProductEntity;
import com.casm.apimarket.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductRepository implements IProductRepository {

    private final ProductCrudRepository productCrudRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductRepository(ProductCrudRepository productCrudRepository, ProductMapper productMapper) {
        this.productCrudRepository = productCrudRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> getAll() {
        return productMapper.toProducts((List<ProductEntity>) productCrudRepository.findAll());
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        return Optional.of(productMapper.toProducts(productCrudRepository.findByCategoryIdOrderByNameAsc(categoryId)));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        return productCrudRepository.findByStockLessThanAndState(quantity, true)
                .map(productMapper::toProducts);
    }

    @Override
    public Optional<List<Product>> getExpensiveAndUnsold(double minSalesPrice, int minStock) {
        return productCrudRepository.findBySalesPriceGreaterThanAndStockGreaterThan(minSalesPrice, minStock)
                .map(productMapper::toProducts);
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productCrudRepository.findById(productId).map(productMapper::toProduct);
    }

    @Override
    public Product save(Product product) {
        return productMapper.toProduct(productCrudRepository.save(productMapper.toProductEntity(product)));
    }

    @Override
    public void delete(int productId) {
        productCrudRepository.deleteById(productId);
    }
}
