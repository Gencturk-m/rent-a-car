package com.melihgencturk.rentacar.business.dto.requests.update;

import com.melihgencturk.rentacar.entities.Enums.CarState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarRequest {
    private int modelYear;
    private String plate;
    private CarState state;
    private int modelId;
    private double dailyPrice;
}
