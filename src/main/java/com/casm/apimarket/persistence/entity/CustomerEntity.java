package com.casm.apimarket.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clientes")
@Getter
@Setter
public class CustomerEntity {

    @Id
    @Column(length = 20)
    private String id;

    @Column(name = "nombre",length = 40)
    private String firstName;

    @Column(name = "apellidos", length = 100)
    private String lastName;

    @Column(name = "celular")
    private Long cellPhoneNumber;

    @Column(name = "direccion", length = 80)
    private String address;

    @Column(name = "correo_electronico", length = 70)
    private String email;

    @OneToMany(mappedBy = "customer")
    private List<PurchaseEntity> purchases;
}
