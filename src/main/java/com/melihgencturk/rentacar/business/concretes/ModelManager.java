package com.melihgencturk.rentacar.business.concretes;

import com.melihgencturk.rentacar.business.abstracts.ModelService;
import com.melihgencturk.rentacar.business.dto.requests.create.CreateModelRequest;
import com.melihgencturk.rentacar.business.dto.requests.update.UpdateModelRequest;
import com.melihgencturk.rentacar.business.dto.responses.create.CreateModelResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetAllModelsResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetModelResponse;
import com.melihgencturk.rentacar.business.dto.responses.update.UpdateModelResponse;
import com.melihgencturk.rentacar.entities.Model;
import com.melihgencturk.rentacar.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private final ModelRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllModelsResponse> getAll() {
        List<Model> models = repository.findAll();
        List<GetAllModelsResponse> response = models.stream()
                .map(model -> mapper.map(model, GetAllModelsResponse.class))
                .toList();
        return response;
    }

    @Override
    public GetModelResponse getById(int id) {
        Model model = repository.findById(id).orElseThrow();
        GetModelResponse response= mapper.map(model,GetModelResponse.class);
        return response;
    }

    @Override
    public CreateModelResponse create(CreateModelRequest request) {
        checkIfModelExistsByName(request.getName());
        Model model = mapper.map(request, Model.class);
        model.setId(0);
        Model createdModel = repository.save(model);
        CreateModelResponse response = mapper.map(createdModel,CreateModelResponse.class);
        return response;
    }

    @Override
    public UpdateModelResponse update(int id, UpdateModelRequest request) {
        checkIfModelExistsById(id);
        checkIfModelExistsByName(request.getName());
        Model model = mapper.map(request, Model.class);
        model.setId(id);
        Model updatedModel = repository.save(model);

        UpdateModelResponse response = mapper.map(updatedModel, UpdateModelResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        checkIfModelExistsById(id);
        repository.deleteById(id);
    }

    private void checkIfModelExistsById(int id){
        if(!repository.existsById(id)){
            throw new RuntimeException("There is not such a model.");
        }
    }

    private void checkIfModelExistsByName(String name){
        if (repository.existsByNameIgnoreCase(name)){
            throw new RuntimeException("There is a model with this name already.");
        }
    }

}
