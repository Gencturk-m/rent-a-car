package com.melihgencturk.rentacar.business.dto.responses.get;

import com.melihgencturk.rentacar.entities.Enums.CarState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetCarResponse {

    private int id;
    private int modelYear;
    private String plate;
    private CarState state;
    private int modelId;
    private double dailyPrice;

    //if we want to show brand name with customized field name check car manager getbyid
    private String brandName;
}