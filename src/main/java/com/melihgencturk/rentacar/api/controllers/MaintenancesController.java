package com.melihgencturk.rentacar.api.controllers;

import com.melihgencturk.rentacar.business.abstracts.MaintenanceService;
import com.melihgencturk.rentacar.business.dto.requests.create.CreateMaintenanceRequest;
import com.melihgencturk.rentacar.business.dto.requests.update.UpdateMaintenanceRequest;
import com.melihgencturk.rentacar.business.dto.responses.create.CreateMaintenanceResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetAllMaintenancesResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetMaintenanceResponse;
import com.melihgencturk.rentacar.business.dto.responses.update.UpdateMaintenanceResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/maintenances")
@AllArgsConstructor
public class MaintenancesController {
    private final MaintenanceService service;

    @GetMapping
    public List<GetAllMaintenancesResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetMaintenanceResponse getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateMaintenanceResponse create(@RequestBody CreateMaintenanceRequest request) {
        return service.create(request);
    }

    @PutMapping("/returnCar/{carId}")
    public GetMaintenanceResponse returnCarFromMaintenance(@PathVariable int carId,@RequestBody double maintenancePrice){
        return service.returnCarFromMaintenance(carId,maintenancePrice);
    }

    @PutMapping("/{id}")
    public UpdateMaintenanceResponse update(@PathVariable int id, @RequestBody UpdateMaintenanceRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        service.delete(id);
    }


}