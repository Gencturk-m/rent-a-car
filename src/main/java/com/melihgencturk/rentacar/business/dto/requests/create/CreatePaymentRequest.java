package com.melihgencturk.rentacar.business.dto.requests.create;

import com.melihgencturk.rentacar.business.dto.PaymentRequest;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest extends PaymentRequest {
    @Min(value = 1, message = "Balance must be bigger than 1.")
    private double balance;
}


