package org.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.example.entity.ProductStatusEntity;
import org.example.entity.ProductTypeEntity;
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
    private Integer id;
    private String name;
    private double price;
    private Integer typeId;
    private String typeName;
    private Integer statusId;
    private String statusName;
}
