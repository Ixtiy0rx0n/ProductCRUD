package org.example.service;

import org.example.dto.StatusDTO;
import org.example.entity.ProductStatusEntity;
import org.example.exp.AppBadException;
import org.example.repository.ProductStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductStatusService {

    @Autowired
    private ProductStatusRepository statusRepository;

    public StatusDTO create(StatusDTO dto) {
        ProductStatusEntity entity = new ProductStatusEntity();
        entity.setName(dto.getName());
        statusRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<StatusDTO> getAll() {
        List<ProductStatusEntity> entityList = statusRepository.findAll();
        if (!entityList.isEmpty()) {
            List<StatusDTO> dtoList = new ArrayList<>();
            for (ProductStatusEntity entity : entityList) {
                StatusDTO dto = new StatusDTO();
                dto.setId(entity.getId());
                dto.setName(entity.getName());
                dtoList.add(dto);
            }
            return dtoList;
        } else {
            throw new AppBadException("Statuses not found");
        }
    }

    public StatusDTO getById(Integer id) {
        Optional<ProductStatusEntity> optional = statusRepository.findById(id);
        if (optional.isPresent()) {
            ProductStatusEntity entity = optional.get();
            StatusDTO dto = new StatusDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            return dto;
        } else {
            throw new AppBadException("Status not found");
        }
    }

    public StatusDTO updateById(Integer id, StatusDTO dto) {
        Optional<ProductStatusEntity> optional = statusRepository.findById(id);
        if (optional.isPresent()) {
            statusRepository.updateById(dto.getName(), id);
            dto.setId(id);
            return dto;
        } else {
            throw new AppBadException("Status not found");
        }
    }

    public Boolean deleteById(Integer id) {
        Optional<ProductStatusEntity> optional = statusRepository.findById(id);
        if (optional.isPresent()) {
            statusRepository.deleteById(id);
            return true;
        } else {
            throw new AppBadException("Status not found");
        }
    }

}
