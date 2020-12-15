package com.casm.apimarket.web.controller;

import com.casm.apimarket.domain.Product;
import com.casm.apimarket.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    @ApiOperation("Get all market products")
    @ApiResponse(code = 200,message = "OK")
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Search product by id")
    @ApiResponses({
            @ApiResponse(code = 200,message = "OK"),
            @ApiResponse(code = 404,message = "Product not found")
    })
    public ResponseEntity<Product> getProduct(@PathVariable("id") int productId) {
        return productService.getProduct(productId)
                .map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{id}")
    @ApiOperation("Search products by category")
    @ApiResponses({
            @ApiResponse(code = 200,message = "OK"),
            @ApiResponse(code = 404,message = "Products by category not found")
    })
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable("id") int categoryId) {
        return productService.getProductsByCategory(categoryId)
                .map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    @ApiOperation("Save a product")
    @ApiResponse(code = 201,message = "CREATED")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete product by id")
    @ApiResponses({
            @ApiResponse(code = 204,message = "DELETED"),
            @ApiResponse(code = 404,message = "Product not found")
    })
    public ResponseEntity delete(@PathVariable("id") int productId) {
        return productService.delete(productId) ?
                new ResponseEntity(HttpStatus.NO_CONTENT) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
