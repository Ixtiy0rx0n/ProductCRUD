package org.example.repository;

import jakarta.transaction.Transactional;
import org.example.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    Optional<ProductEntity> findByName(String name);


    @Transactional
    @Modifying
    @Query("update ProductEntity u set u.name=?1, u.price=?2, u.typeId=?3, u.statusId=?4 where u.id=?5")
    Integer updateById(String name, double price, Integer typeId, Integer statusId, Integer id);
}
