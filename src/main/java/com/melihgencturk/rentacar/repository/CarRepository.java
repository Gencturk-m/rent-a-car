package com.melihgencturk.rentacar.repository;

import com.melihgencturk.rentacar.entities.Car;
import com.melihgencturk.rentacar.entities.Enums.CarState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {

    List<Car> findAllByStateIsNot(CarState state);

}
