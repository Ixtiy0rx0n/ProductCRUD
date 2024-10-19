package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.entity.ProductStatusEntity;
import org.example.entity.ProductTypeEntity;
@Setter
@Getter
public class ProductDTO {
    private Integer id;
    private String name;
    private double price;
    private Integer typeId;
    private Integer statusId;
}
