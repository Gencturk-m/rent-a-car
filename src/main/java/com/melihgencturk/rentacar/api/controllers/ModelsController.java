package com.melihgencturk.rentacar.api.controllers;

import com.melihgencturk.rentacar.business.abstracts.ModelService;
import com.melihgencturk.rentacar.business.dto.requests.create.CreateModelRequest;
import com.melihgencturk.rentacar.business.dto.requests.update.UpdateModelRequest;
import com.melihgencturk.rentacar.business.dto.responses.create.CreateModelResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetAllModelsResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetModelResponse;
import com.melihgencturk.rentacar.business.dto.responses.update.UpdateModelResponse;
import com.melihgencturk.rentacar.entities.Model;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/models")
public class ModelsController {
    private final ModelService service;

    @GetMapping
    public List<GetAllModelsResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetModelResponse getById(@PathVariable int id){
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateModelResponse add(@RequestBody CreateModelRequest request){
        return service.create(request);
    }

    @PutMapping("/{id}")
    public UpdateModelResponse update(@PathVariable int id, @RequestBody UpdateModelRequest request){
        return service.update(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        service.delete(id);
    }

}
