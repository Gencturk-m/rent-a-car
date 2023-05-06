package com.melihgencturk.rentacar.business.abstracts;

import com.melihgencturk.rentacar.business.dto.requests.create.CreateRentalRequest;
import com.melihgencturk.rentacar.business.dto.requests.update.UpdateRentalRequest;
import com.melihgencturk.rentacar.business.dto.responses.create.CreateRentalResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetAllRentalsResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetRentalResponse;
import com.melihgencturk.rentacar.business.dto.responses.update.UpdateRentalResponse;

import java.util.List;

public interface RentalService {

    List<GetAllRentalsResponse> getAll();
    GetRentalResponse getById(int id);

    CreateRentalResponse create(CreateRentalRequest request);

    UpdateRentalResponse update(int id, UpdateRentalRequest request);

    void delete(int id);

}
