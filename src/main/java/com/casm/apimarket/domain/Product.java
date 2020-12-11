package com.casm.apimarket.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private int productId;
    private String name;
    private int categoryId;
    private float price;
    private int stock;
    private boolean active;
    private Category category;
}
