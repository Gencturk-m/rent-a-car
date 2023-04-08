package com.melihgencturk.rentacar.business.concretes;

import com.melihgencturk.rentacar.business.abstracts.CarService;
import com.melihgencturk.rentacar.business.dto.requests.create.CreateCarRequest;
import com.melihgencturk.rentacar.business.dto.requests.update.UpdateCarRequest;
import com.melihgencturk.rentacar.business.dto.responses.create.CreateCarResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetAllCarsResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetCarResponse;
import com.melihgencturk.rentacar.business.dto.responses.update.UpdateCarResponse;
import com.melihgencturk.rentacar.entities.Car;
import com.melihgencturk.rentacar.entities.Enums.CarState;
import com.melihgencturk.rentacar.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarManager implements CarService {

    private final CarRepository repository;
    private final ModelMapper mapper;


    @Override
    public GetCarResponse getById(int id) {
        checkIfCarExistsById(id);
        Car car = repository.findById(id).orElseThrow();
        GetCarResponse response = mapper.map(car, GetCarResponse.class);
        //to show brand name in response
        //response.setBrandName(car.getModel().getBrand().getName());
        return response;
    }

    @Override
    public List<GetAllCarsResponse> getAll(boolean includeMaintenance) {

        List<Car> cars = filterCarsByMaintenanceState(includeMaintenance);
        List<GetAllCarsResponse> response = cars.stream()
                .map(car -> mapper.map(car, GetAllCarsResponse.class)).toList();
        return response;
    }

    @Override
    public CreateCarResponse create(CreateCarRequest request) {
        Car car = mapper.map(request, Car.class);
        car.setId(0);
        car.setState(CarState.AVAILABLE);
        Car createdCar = repository.save(car);
        CreateCarResponse response = mapper.map(createdCar, CreateCarResponse.class);
        return response;
    }

    @Override
    public UpdateCarResponse update(int id, UpdateCarRequest request) {
        checkIfCarExistsById(id);
        Car car = mapper.map(request, Car.class);
        car.setId(id);
        Car updatedCar = repository.save(car);
        UpdateCarResponse response = mapper.map(updatedCar, UpdateCarResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        checkIfCarExistsById(id);
        repository.deleteById(id);
    }

    @Override
    public void changeCarState(int carId, CarState carState) {
        Car car = repository.findById(carId).orElseThrow();
        car.setState(carState);
        repository.save(car);
    }

    private void checkIfCarExistsById(int id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("There is not such a car!");
        }
    }

    private List<Car> filterCarsByMaintenanceState(boolean includeMaintenance) {
        if (includeMaintenance) {
            return repository.findAll();
        }
        return repository.findAllByStateIsNot(CarState.MAINTENANCE);
    }
}
