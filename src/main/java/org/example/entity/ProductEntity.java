package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "type", insertable = false, updatable = false)
    private ProductTypeEntity type;

    @Column(name = "type")
    private Integer typeId;

    @ManyToOne
    @JoinColumn(name = "status", insertable = false, updatable = false)
    private ProductStatusEntity status;

    @Column(name = "status")
    private Integer statusId;

}
