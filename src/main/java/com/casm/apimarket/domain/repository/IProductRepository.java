package com.casm.apimarket.domain.repository;

import com.casm.apimarket.domain.Product;

import java.util.List;
import java.util.Optional;

public interface IProductRepository {
    List<Product> getAll();
    Optional<List<Product>> getByCategory(int categoryId);
    Optional<List<Product>> getScarseProducts(int quantity);
    Optional<List<Product>> getExpensiveAndUnsold(double minSalesPrice, int minStock);
    Optional<Product> getProduct(int productId);
    Product save(Product product);
    void delete(int productId);
}
