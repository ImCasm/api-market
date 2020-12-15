package com.casm.apimarket.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "compras")
@Getter
@Setter
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private Integer purchaseId;

    @Column(name = "id_cliente", length = 20)
    private String customerId;

    @ManyToOne
    @JoinColumn(name = "id_cliente", insertable = false, updatable = false)
    private CustomerEntity customer;

    @Column(name = "fecha")
    private LocalDateTime date;

    @Column(name = "medio_pago")
    private Character paymentMethod;

    @Column(name = "comentario", length = 300)
    private String comment;

    @Column(name = "estado")
    private Character state;

    @OneToMany(mappedBy = "purchase", cascade = {CascadeType.ALL})
    private List<ProductPurchaseEntity> purchaseProducts;

}
