package com.melihgencturk.rentacar.repository;

import com.melihgencturk.rentacar.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
