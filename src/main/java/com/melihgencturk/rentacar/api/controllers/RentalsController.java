package com.melihgencturk.rentacar.api.controllers;

import com.melihgencturk.rentacar.business.abstracts.RentalService;
import com.melihgencturk.rentacar.business.dto.requests.create.CreateRentalRequest;
import com.melihgencturk.rentacar.business.dto.requests.update.UpdateRentalRequest;
import com.melihgencturk.rentacar.business.dto.responses.create.CreateRentalResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetAllRentalsResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetRentalResponse;
import com.melihgencturk.rentacar.business.dto.responses.update.UpdateRentalResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/rentals")
public class RentalsController {

    private final RentalService service;


    @GetMapping
    public List<GetAllRentalsResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetRentalResponse getById(@PathVariable int id){
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateRentalResponse create(@RequestBody CreateRentalRequest request){
        return service.create(request);
    }

    @PutMapping("/{id}")
    public UpdateRentalResponse update(@PathVariable int id, @RequestBody UpdateRentalRequest request){
        return service.update(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        service.delete(id);
    }



}
