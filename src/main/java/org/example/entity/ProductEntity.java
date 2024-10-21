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

    @Column(name = "type")
    private Integer typeId;

    @Column(name = "typeName")
    private String typeName;

    @Column(name = "status")
    private Integer statusId;

    @Column(name = "statusName")
    private String statusName;

    public ProductEntity(Integer id, String name, double price, Integer typeId, String typeName, Integer statusId, String statusName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.typeId = typeId;
        this.typeName = typeName;
        this.statusId = statusId;
        this.statusName = statusName;
    }

    public ProductEntity() {
    }
}
