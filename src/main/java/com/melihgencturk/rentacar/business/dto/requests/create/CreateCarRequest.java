package com.melihgencturk.rentacar.business.dto.requests.create;

import com.melihgencturk.rentacar.entities.Enums.CarState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
    private int modelYear;
    private String plate;
    private int modelId;
    private double dailyPrice;
}
