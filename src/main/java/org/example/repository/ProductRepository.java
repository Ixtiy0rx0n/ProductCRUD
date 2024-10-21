package org.example.repository;

import jakarta.transaction.Transactional;
import org.example.dto.ProductDTO;
import org.example.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    Optional<ProductEntity> findByName(String name);


    @Transactional
    @Modifying
    @Query("update ProductEntity u set u.name=?1, u.price=?2, u.typeId=?3, u.statusId=?4 where u.id=?5")
    Integer updateById(String name, double price, Integer typeId, Integer statusId, Integer id);

    @Query("SELECT new ProductEntity (p.id, p.name, p.price, p.typeId, pt.name, p.statusId, ps.name) " +
            "FROM ProductEntity p " +
            "JOIN ProductTypeEntity pt ON p.typeId = pt.id " +
            "JOIN ProductStatusEntity ps ON p.statusId = ps.id order by p.id asc")
    List<ProductEntity> getAllProductsWithTypeAndStatus();

    @Query("SELECT new ProductEntity (p.id, p.name, p.price, p.typeId, pt.name, p.statusId, ps.name) " +
            "FROM ProductEntity p " +
            "JOIN ProductTypeEntity pt ON p.typeId = pt.id " +
            "JOIN ProductStatusEntity ps ON p.statusId = ps.id where p.id=?1 order by p.id asc")
   Optional<ProductEntity> getFullData(Integer id);
}
