package com.melihgencturk.rentacar.business.abstracts;

import com.melihgencturk.rentacar.business.dto.requests.create.CreatePaymentRequest;
import com.melihgencturk.rentacar.business.dto.requests.update.UpdatePaymentRequest;
import com.melihgencturk.rentacar.business.dto.responses.create.CreatePaymentResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetAllPaymentsResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetPaymentResponse;
import com.melihgencturk.rentacar.business.dto.responses.update.UpdatePaymentResponse;
import com.melihgencturk.rentacar.common.dto.CreateRentalPaymentRequest;

import java.util.List;

public interface PaymentService {
    List<GetAllPaymentsResponse> getAll();
    GetPaymentResponse getById(int id);

    CreatePaymentResponse create(CreatePaymentRequest request);

    UpdatePaymentResponse update(int id, UpdatePaymentRequest request);

    void processRentalPayment(CreateRentalPaymentRequest request);

    void delete(int id);
}
