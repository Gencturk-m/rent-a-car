package com.melihgencturk.rentacar.business.dto.requests.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMaintenanceRequest {
    private String information;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isCompleted;
    private double maintenancePrice;
    private int carId;
}
