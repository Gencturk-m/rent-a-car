package com.melihgencturk.rentacar.api.controllers;

import com.melihgencturk.rentacar.business.abstracts.CarService;
import com.melihgencturk.rentacar.business.dto.requests.create.CreateCarRequest;
import com.melihgencturk.rentacar.business.dto.requests.update.UpdateCarRequest;
import com.melihgencturk.rentacar.business.dto.responses.create.CreateCarResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetAllCarsResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetCarResponse;
import com.melihgencturk.rentacar.business.dto.responses.update.UpdateCarResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")
@AllArgsConstructor
public class CarsController {

    private final CarService service;


    @GetMapping
    public List<GetAllCarsResponse> getAll(@RequestParam(defaultValue = "true") boolean includeMaintenance){
        return service.getAll(includeMaintenance);
    }

    @GetMapping("/{id}")
    public GetCarResponse getById(@PathVariable int id){
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCarResponse create(@RequestBody CreateCarRequest request){
        return service.create(request);
    }

    @PutMapping("/{id}")
    public UpdateCarResponse update(@PathVariable int id, @RequestBody UpdateCarRequest request){
        return service.update(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        service.delete(id);
    }

}
