package org.example.service;

import org.example.dto.ProductDTO;
import org.example.entity.ProductEntity;
import org.example.exp.AppBadException;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductDTO create(ProductDTO dto) {
        ProductEntity entity = new ProductEntity();
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setTypeId(dto.getTypeId());
        entity.setStatusId(dto.getStatusId());
        productRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<ProductDTO> getAll() {
        List<ProductEntity> entityList = productRepository.getAllProductsWithTypeAndStatus();
        if (!entityList.isEmpty()) {
            List<ProductDTO> dtoList = new ArrayList<>();
            for (ProductEntity entity : entityList) {
                ProductDTO dto = new ProductDTO();
                dto.setId(entity.getId());
                dto.setName(entity.getName());
                dto.setPrice(entity.getPrice());
                dto.setTypeName(entity.getTypeName());
                dto.setTypeId(entity.getTypeId());
                dto.setStatusId(entity.getStatusId());
                dto.setStatusName(entity.getStatusName());
                dtoList.add(dto);
            }
            return dtoList;
        } else {
            throw new AppBadException("Products not found");
        }
    }

    public ProductDTO getById(Integer id) {
        Optional<ProductEntity> optional = productRepository.getFullData(id);
        if (optional.isPresent()) {
            ProductEntity entity = optional.get();
            ProductDTO dto = new ProductDTO();
            dto.setId(id);
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setTypeId(entity.getTypeId());
            dto.setStatusId(entity.getStatusId());
            dto.setTypeName(entity.getTypeName());
            dto.setStatusName(entity.getStatusName());
            return dto;
        } else {
            throw new AppBadException("Product not found");
        }
    }

    public ProductDTO updateById(Integer id, ProductDTO dto) {
        Optional<ProductEntity> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            productRepository.updateById(dto.getName(), dto.getPrice(), dto.getTypeId(), dto.getStatusId(), id);
            dto.setId(id);
            return dto;
        } else {
            throw new AppBadException("Product not found");
        }
    }

    public Boolean deleteById(Integer id) {
        Optional<ProductEntity> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            productRepository.deleteById(id);
            return true;
        } else {
            throw new AppBadException("Product not found");
        }
    }
}
