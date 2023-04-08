package com.melihgencturk.rentacar.repository;

import com.melihgencturk.rentacar.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
    //custom queries
    boolean existsByNameIgnoreCase(String name);

}
