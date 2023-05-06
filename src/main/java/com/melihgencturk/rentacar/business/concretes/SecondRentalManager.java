package com.melihgencturk.rentacar.business.concretes;

import com.melihgencturk.rentacar.business.abstracts.RentalService;
import com.melihgencturk.rentacar.business.dto.requests.create.CreateRentalRequest;
import com.melihgencturk.rentacar.business.dto.requests.update.UpdateRentalRequest;
import com.melihgencturk.rentacar.business.dto.responses.create.CreateRentalResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetAllRentalsResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetRentalResponse;
import com.melihgencturk.rentacar.business.dto.responses.update.UpdateRentalResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecondRentalManager implements RentalService {
    @Override
    public List<GetAllRentalsResponse> getAll() {
        return null;
    }

    @Override
    public GetRentalResponse getById(int id) {
        return null;
    }

    @Override
    public CreateRentalResponse create(CreateRentalRequest request) {
        return null;
    }

    @Override
    public UpdateRentalResponse update(int id, UpdateRentalRequest request) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
