package com.melihgencturk.rentacar.repository;

import com.melihgencturk.rentacar.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Integer> {
}
