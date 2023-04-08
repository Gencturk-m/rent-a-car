package com.melihgencturk.rentacar.repository;

import com.melihgencturk.rentacar.entities.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer> {

    Maintenance findByCarIdAndIsCompletedIsFalse(int carId);
    boolean existsByCarIdAndIsCompletedIsFalse(int carId);

    boolean existsByCarId(int carId);
}
