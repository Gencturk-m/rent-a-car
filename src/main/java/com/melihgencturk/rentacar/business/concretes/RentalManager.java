package com.melihgencturk.rentacar.business.concretes;

import com.melihgencturk.rentacar.business.abstracts.CarService;
import com.melihgencturk.rentacar.business.abstracts.PaymentService;
import com.melihgencturk.rentacar.business.abstracts.RentalService;
import com.melihgencturk.rentacar.business.dto.requests.create.CreateRentalRequest;
import com.melihgencturk.rentacar.business.dto.requests.update.UpdateRentalRequest;
import com.melihgencturk.rentacar.business.dto.responses.create.CreateRentalResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetAllRentalsResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetRentalResponse;
import com.melihgencturk.rentacar.business.dto.responses.update.UpdateRentalResponse;
import com.melihgencturk.rentacar.common.dto.CreateRentalPaymentRequest;
import com.melihgencturk.rentacar.entities.Enums.CarState;
import com.melihgencturk.rentacar.entities.Rental;
import com.melihgencturk.rentacar.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
    private final RentalRepository repository;
    private final ModelMapper mapper;
    private final CarService carService;
    private final PaymentService paymentService;

    @Override
    public List<GetAllRentalsResponse> getAll() {
        List<Rental> rentals = repository.findAll();
        List<GetAllRentalsResponse> response = rentals.stream()
                .map(rental -> mapper.map(rental,GetAllRentalsResponse.class)).toList();

        return response;
    }

    @Override
    public GetRentalResponse getById(int id) {
        checkIfRentalExists(id);
        Rental rental = repository.findById(id).orElseThrow();
        GetRentalResponse response = mapper.map(rental,GetRentalResponse.class);
        return response;
    }

    @Override
    public CreateRentalResponse create(CreateRentalRequest request) {

        checkIfCarAvailable(request.getCarId());
        Rental rental = mapper.map(request, Rental.class);
        rental.setId(0);
        rental.setTotalPrice(calcTotalPrice(rental.getDailyPrice(), rental.getRentedForDays()));
        rental.setStartDate(LocalDateTime.now());

        CreateRentalPaymentRequest createRentalPaymentRequest = new CreateRentalPaymentRequest();
        mapper.map(request.getPaymentRequest(), createRentalPaymentRequest);
        createRentalPaymentRequest.setPrice(rental.getTotalPrice());
        paymentService.processRentalPayment(createRentalPaymentRequest);

        Rental createdRental = repository.save(rental);
        carService.changeCarState(request.getCarId(), CarState.RENTED);
        CreateRentalResponse response = mapper.map(createdRental,CreateRentalResponse.class);

        return response;
    }

    @Override
    public UpdateRentalResponse update(int id, UpdateRentalRequest request) {
        checkIfRentalExists(id);
        Rental rental =mapper.map(request,Rental.class);
        rental.setId(id);
        Rental updatedRental = repository.save(rental);
        UpdateRentalResponse response = mapper.map(updatedRental,UpdateRentalResponse.class);
        return response;

    }

    @Override
    public void delete(int id) {
        checkIfRentalExists(id);
        int carId = repository.findById(id).get().getCar().getId();
        carService.changeCarState(carId,CarState.AVAILABLE);
        repository.deleteById(id);
    }

    private double calcTotalPrice(double dailyPrice, int days){
        return dailyPrice * days;
    }

    private void checkIfCarAvailable(int carId){
            if (!carService.getById(carId).getState().equals(CarState.AVAILABLE)){
                throw new RuntimeException("Araç Müsait Değil.");
            }
    }
    private void checkIfRentalExists(int id){
        if(!repository.existsById(id)){
            throw new RuntimeException("böyle bir kiralama mevcut değil.");
        }
    }
}
