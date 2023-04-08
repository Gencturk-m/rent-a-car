package com.melihgencturk.rentacar.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "maintenances")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String information;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isCompleted;
    private double maintenancePrice;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
}
