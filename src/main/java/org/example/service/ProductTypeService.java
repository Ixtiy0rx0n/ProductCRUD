package org.example.service;

import org.example.dto.TypeDTO;
import org.example.entity.ProductTypeEntity;
import org.example.exp.AppBadException;
import org.example.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductTypeService {
    @Autowired
    private ProductTypeRepository typeRepository;

    public TypeDTO create(TypeDTO dto) {
        ProductTypeEntity entity = new ProductTypeEntity();
        entity.setName(dto.getName());
        typeRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<TypeDTO> getAll() {
        List<ProductTypeEntity> entityList = typeRepository.findAll();
        if (!entityList.isEmpty()) {
            List<TypeDTO> dtoList = new ArrayList<>();
            for (ProductTypeEntity entity : entityList) {
                TypeDTO dto = new TypeDTO();
                dto.setId(entity.getId());
                dto.setName(entity.getName());
                dtoList.add(dto);
            }
            return dtoList;
        } else {
            throw new AppBadException("Types not found");
        }
    }

    public TypeDTO getById(Integer id) {
        Optional<ProductTypeEntity> optional = typeRepository.findById(id);
        if (optional.isPresent()) {
            ProductTypeEntity entity = optional.get();
            TypeDTO dto = new TypeDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            return dto;
        } else {
            throw new AppBadException("Type not found");
        }
    }

    public TypeDTO updateById(Integer id, TypeDTO dto) {
        Optional<ProductTypeEntity> optional = typeRepository.findById(id);
        if (optional.isPresent()) {
            typeRepository.updateById(dto.getName(), id);
            dto.setId(id);
            return dto;
        } else {
            throw new AppBadException("Type not found");
        }
    }

    public Boolean deleteById(Integer id) {
        Optional<ProductTypeEntity> optional = typeRepository.findById(id);
        if (optional.isPresent()) {
            typeRepository.deleteById(id);
            return true;
        } else {
            throw new AppBadException("Type not found");
        }
    }
}
