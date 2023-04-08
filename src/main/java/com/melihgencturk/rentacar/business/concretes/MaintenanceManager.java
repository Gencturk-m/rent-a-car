package com.melihgencturk.rentacar.business.concretes;

import com.melihgencturk.rentacar.business.abstracts.CarService;
import com.melihgencturk.rentacar.business.abstracts.MaintenanceService;
import com.melihgencturk.rentacar.business.dto.requests.create.CreateMaintenanceRequest;
import com.melihgencturk.rentacar.business.dto.requests.update.UpdateMaintenanceRequest;
import com.melihgencturk.rentacar.business.dto.responses.create.CreateMaintenanceResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetAllMaintenancesResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetMaintenanceResponse;
import com.melihgencturk.rentacar.business.dto.responses.update.UpdateMaintenanceResponse;
import com.melihgencturk.rentacar.entities.Enums.CarState;
import com.melihgencturk.rentacar.entities.Maintenance;
import com.melihgencturk.rentacar.repository.MaintenanceRepository;
import com.sun.tools.javac.Main;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class MaintenanceManager implements MaintenanceService {

    private final MaintenanceRepository repository;
    private final ModelMapper mapper;
    private final CarService carService;


    @Override
    public List<GetAllMaintenancesResponse> getAll() {

        List<Maintenance> maintenances = repository.findAll();
        List<GetAllMaintenancesResponse> response = maintenances.stream()
                .map(maintenance -> mapper.map(maintenance, GetAllMaintenancesResponse.class)).toList();
        return response;
    }

    @Override
    public GetMaintenanceResponse getById(int id) {
        checkIfMaintenanceExists(id);
        Maintenance maintenance = repository.findById(id).orElseThrow();
        GetMaintenanceResponse response = mapper.map(maintenance,GetMaintenanceResponse.class);
        return response;
    }

    @Override
    public GetMaintenanceResponse returnCarFromMaintenance(int carId, double maintenancePrice) {

        checkIfCarIsNotInMaintenance(carId);

        Maintenance maintenance = repository.findByCarIdAndIsCompletedIsFalse(carId);
        maintenance.setCompleted(true);
        maintenance.setEndDate(LocalDate.now());
        maintenance.setMaintenancePrice(maintenancePrice);
        Maintenance endedMaintenance = repository.save(maintenance);
        carService.changeCarState(carId,CarState.AVAILABLE);
        GetMaintenanceResponse response = mapper.map(endedMaintenance,GetMaintenanceResponse.class);
        return response;
    }



    @Override
    public CreateMaintenanceResponse create(CreateMaintenanceRequest request) {

        checkIfCarIsInMaintenance(request);
        checkIfCarIsRented(request);

        Maintenance maintenance = mapper.map(request,Maintenance.class);
        maintenance.setId(0);
        maintenance.setCompleted(false);
        maintenance.setStartDate(LocalDate.now());
        maintenance.setEndDate(null);
        maintenance.setMaintenancePrice(0);
        Maintenance createdMaintenance = repository.save(maintenance);
        carService.changeCarState(request.getCarId(), CarState.MAINTENANCE);
        CreateMaintenanceResponse response = mapper.map(createdMaintenance,CreateMaintenanceResponse.class);
        return response;
    }




    @Override
    public UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request) {
        checkIfMaintenanceExists(id);
        Maintenance maintenance=  mapper.map(request,Maintenance.class);
        maintenance.setId(id);
        Maintenance updatedMaintenance = repository.save(maintenance);
        UpdateMaintenanceResponse response = mapper.map(updatedMaintenance,UpdateMaintenanceResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        checkIfMaintenanceExists(id);
        repository.deleteById(id);
    }

    //Let's get down to business!

    private void checkIfCarIsRented(CreateMaintenanceRequest request) {
        if (carService.getById(request.getCarId()).getState().equals(CarState.RENTED)){
            throw new RuntimeException("Car is rented!");
        }
    }

    private void checkIfMaintenanceExists(int maintenanceId){
        if (!repository.existsById(maintenanceId)){
            throw new RuntimeException("There is not such a maintenance.");
        }
    }

    private void checkIfCarIsNotInMaintenance(int carId) {
        if (!repository.existsByCarIdAndIsCompletedIsFalse(carId)){
            throw new RuntimeException("This car is not in maintenance.");
        }
    }

    private void checkIfCarIsInMaintenance(CreateMaintenanceRequest request) {
        if (repository.existsByCarIdAndIsCompletedIsFalse(request.getCarId())){
            throw new RuntimeException("This car is already in maintenance.");
        }
    }
}
