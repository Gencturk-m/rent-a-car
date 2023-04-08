package com.melihgencturk.rentacar.business.abstracts;

import com.melihgencturk.rentacar.business.dto.requests.create.CreateModelRequest;
import com.melihgencturk.rentacar.business.dto.requests.update.UpdateModelRequest;
import com.melihgencturk.rentacar.business.dto.responses.create.CreateModelResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetAllModelsResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetModelResponse;
import com.melihgencturk.rentacar.business.dto.responses.update.UpdateModelResponse;
import com.melihgencturk.rentacar.entities.Model;

import java.util.List;

public interface ModelService {

    List<GetAllModelsResponse> getAll();
    GetModelResponse getById(int id);
    CreateModelResponse create(CreateModelRequest request);

    UpdateModelResponse update(int id, UpdateModelRequest request);

    void delete(int id);
}
