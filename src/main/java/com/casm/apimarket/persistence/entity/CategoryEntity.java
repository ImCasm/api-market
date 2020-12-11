package com.casm.apimarket.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categorias")
@Getter
@Setter
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer categoryId;

    @Column(name = "descripcion", length = 45)
    private String description;

    @Column(name = "estado")
    private Boolean state;

    @OneToMany(mappedBy = "category")
    private List<ProductEntity> products;
}
