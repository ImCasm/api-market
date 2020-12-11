package com.casm.apimarket.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "compras_productos")
@Getter
@Setter
public class ProductPurchaseEntity {

    // Composite primary key
    @EmbeddedId
    private ProductPurchasePK id;

    @Column(name = "cantidad")
    private Integer cuantity;

    @Column(precision = 2)
    private BigDecimal total;

    @Column(name = "estado")
    private Boolean state;

    @ManyToOne
    @JoinColumn(name = "id_compra", insertable = false, updatable = false)
    private PurchaseEntity purchase;

    @ManyToOne
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private ProductEntity product;
}
