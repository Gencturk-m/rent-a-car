package com.melihgencturk.rentacar.business.dto.responses.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllRentalsResponse {

    private int id;
    private int carId;
    private double dailyPrice;
    private double totalPrice;
    private int rentedForDays;
    private LocalDateTime startDate;
}
