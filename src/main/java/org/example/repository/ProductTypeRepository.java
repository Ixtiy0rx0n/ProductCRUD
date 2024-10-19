package org.example.repository;

import jakarta.transaction.Transactional;
import org.example.entity.ProductTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, Integer> {


    @Transactional
    @Modifying
    @Query("update ProductTypeEntity s set s.name=?1 where s.id=?2")
    Integer updateById(String name, Integer id);
}
