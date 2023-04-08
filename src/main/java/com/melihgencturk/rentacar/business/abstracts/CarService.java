package com.melihgencturk.rentacar.business.abstracts;

import com.melihgencturk.rentacar.business.dto.requests.create.CreateCarRequest;
import com.melihgencturk.rentacar.business.dto.requests.update.UpdateCarRequest;
import com.melihgencturk.rentacar.business.dto.responses.create.CreateCarResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetAllCarsResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetCarResponse;
import com.melihgencturk.rentacar.business.dto.responses.update.UpdateCarResponse;
import com.melihgencturk.rentacar.entities.Enums.CarState;

import java.util.List;

public interface CarService {

    GetCarResponse getById(int id);
    List<GetAllCarsResponse> getAll(boolean includeMaintenance);

    CreateCarResponse create(CreateCarRequest request);

    UpdateCarResponse update(int id, UpdateCarRequest request);

    void changeCarState(int carId, CarState carState);

    void delete(int id);

}
