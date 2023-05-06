package com.melihgencturk.rentacar.api.controllers;

import com.melihgencturk.rentacar.business.abstracts.PaymentService;
import com.melihgencturk.rentacar.business.dto.requests.create.CreatePaymentRequest;
import com.melihgencturk.rentacar.business.dto.requests.update.UpdatePaymentRequest;
import com.melihgencturk.rentacar.business.dto.responses.create.CreatePaymentResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetAllPaymentsResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetPaymentResponse;
import com.melihgencturk.rentacar.business.dto.responses.update.UpdatePaymentResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService service;

    @GetMapping
    public List<GetAllPaymentsResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetPaymentResponse getById(@PathVariable int id){
        return service.getById(id);
    }

    @PostMapping
    public CreatePaymentResponse create(@Valid @RequestBody CreatePaymentRequest request){
        return service.create(request);
    }

    @PutMapping("/{id}")
    public UpdatePaymentResponse update(@PathVariable int id, @Valid @RequestBody UpdatePaymentRequest request){
        return service.update(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        service.delete(id);
    }
}
