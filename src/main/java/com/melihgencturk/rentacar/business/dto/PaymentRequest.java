package com.melihgencturk.rentacar.business.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    @NotBlank(message = "Kart numarası boş bırakılamaz")
    @Length(min = 16,max = 16,message = "Kart numarası 16 haneli olmalıdır.")
    private String cardNumber;

    @NotBlank(message = "Card holder can not be blank.")
    @Length(min = 5,message = "Kart sahibi bilgisi en az 5 haneli olmalı")
    private String cardHolder;

    @Min(value = 2024,message = "son kullanma yılı gelecekteki bir yıl olmalı.")
    private int cardExpirationYear;

    @Max(value = 12)
    @Min(value = 1)
    private int cardExpirationMonth;

    @NotBlank(message = "cvv bilgisi boş bırakılamaz.")
    @Length(min = 3,max = 3,message = "cvv bilgisi 3 karakterli olmalıdır.")
    private String cardCvv;
}
