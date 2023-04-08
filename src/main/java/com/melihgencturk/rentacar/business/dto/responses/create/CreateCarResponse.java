package com.melihgencturk.rentacar.business.dto.responses.create;

import com.melihgencturk.rentacar.entities.Enums.CarState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarResponse {

    private int id;
    private int modelYear;
    private String plate;
    private CarState state;
    private int modelId;
    private double dailyPrice;
}
