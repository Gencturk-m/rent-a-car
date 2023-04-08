package com.melihgencturk.rentacar.entities;


import com.melihgencturk.rentacar.entities.Enums.CarState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "cars")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int modelYear;
    private String plate;
    @Enumerated(EnumType.STRING)
    private CarState state;
    private double dailyPrice;
    @OneToMany(mappedBy = "car")
    private List<Maintenance> maintenances;
    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

}
