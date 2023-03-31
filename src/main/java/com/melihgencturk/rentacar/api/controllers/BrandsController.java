package com.melihgencturk.rentacar.api.controllers;

import com.melihgencturk.rentacar.business.abstracts.BrandService;
import com.melihgencturk.rentacar.business.dto.requests.create.CreateBrandRequest;
import com.melihgencturk.rentacar.business.dto.responses.create.CreateBrandResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetAllBrandsResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetBrandResponse;
import com.melihgencturk.rentacar.entities.Brand;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/brands")
public class BrandsController {

    private final BrandService service;

    @GetMapping
    public List<GetAllBrandsResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetBrandResponse getById(@PathVariable int id){
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBrandResponse add(@RequestBody CreateBrandRequest request){
        return service.add(request);
    }

    @PutMapping("/{id}")
    public Brand update(@PathVariable int id, @RequestBody Brand brand){
        return service.update(id,brand);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        service.delete(id);
    }
}
