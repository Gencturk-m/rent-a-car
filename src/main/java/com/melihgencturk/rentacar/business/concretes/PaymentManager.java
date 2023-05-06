package com.melihgencturk.rentacar.business.concretes;

import com.melihgencturk.rentacar.business.abstracts.PaymentService;
import com.melihgencturk.rentacar.business.abstracts.PosService;
import com.melihgencturk.rentacar.business.dto.requests.create.CreatePaymentRequest;
import com.melihgencturk.rentacar.business.dto.requests.update.UpdatePaymentRequest;
import com.melihgencturk.rentacar.business.dto.responses.create.CreatePaymentResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetAllPaymentsResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetPaymentResponse;
import com.melihgencturk.rentacar.business.dto.responses.update.UpdatePaymentResponse;
import com.melihgencturk.rentacar.common.dto.CreateRentalPaymentRequest;
import com.melihgencturk.rentacar.core.exceptions.BusinessException;
import com.melihgencturk.rentacar.entities.Payment;
import com.melihgencturk.rentacar.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {

    private final PaymentRepository repository;
    private final ModelMapper mapper;
    private final PosService posService;

    @Override
    public List<GetAllPaymentsResponse> getAll() {
        List<Payment> payments = repository.findAll();
        List<GetAllPaymentsResponse> response = payments.stream()
                .map(payment -> mapper.map(payment, GetAllPaymentsResponse.class)).toList();
        return response;
    }

    @Override
    public GetPaymentResponse getById(int id) {
        checkIfPaymentExist(id);
        Payment payment = repository.findById(id).orElseThrow();
        GetPaymentResponse response = mapper.map(payment,GetPaymentResponse.class);
        return response;
    }

    @Override
    public CreatePaymentResponse create(CreatePaymentRequest request) {

        checkIfCardNumberExist(request);
        Payment payment = mapper.map(request,Payment.class);
        payment.setId(0);
        Payment createdPayment = repository.save(payment);
        CreatePaymentResponse response = mapper.map(createdPayment, CreatePaymentResponse.class);
        return response;
    }

    @Override
    public UpdatePaymentResponse update(int id, UpdatePaymentRequest request) {
        checkIfPaymentExist(id);
        Payment payment = mapper.map(request,Payment.class);
        payment.setId(id);
        Payment updatedPayment = repository.save(payment);
        UpdatePaymentResponse response = mapper.map(updatedPayment,UpdatePaymentResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        checkIfPaymentExist(id);
        repository.deleteById(id);
    }

    @Override
    public void processRentalPayment(CreateRentalPaymentRequest request) {
        checkIfPaymentIsValid(request);

        Payment payment = repository.findByCardNumber(request.getCardNumber());
        checkIfBalanceIsEnough(request.getPrice(), payment.getBalance());
        // posService.pay();//fake pos service
        payment.setBalance(payment.getBalance() - request.getPrice());
        repository.save(payment);
    }

    private void checkIfPaymentExist(int id){
        if(!repository.existsById(id)){
            throw new BusinessException("There is not such payment.");
        }
    }

    private void checkIfPaymentIsValid(CreateRentalPaymentRequest request) {
        if(!repository.existsByCardNumberAndCardHolderAndCardExpirationYearAndCardExpirationMonthAndCardCvv(
                request.getCardNumber(),
                request.getCardHolder(),
                request.getCardExpirationYear(),
                request.getCardExpirationMonth(),
                request.getCardCvv()
        )){
            throw new BusinessException("Wrong card information!");
        }
    }

    private void checkIfBalanceIsEnough(double price, double balance){
        if (price > balance){
            throw new BusinessException("Balance is not enough");
        }
    }

    private void checkIfCardNumberExist(CreatePaymentRequest request) {
        if (repository.existsByCardNumber(request.getCardNumber())){
            throw new BusinessException("This card is already exists.");
        }
    }

}
