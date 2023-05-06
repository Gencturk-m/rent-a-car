package com.melihgencturk.rentacar.common.constants;

import jakarta.servlet.http.PushBuilder;

import javax.swing.plaf.PanelUI;

public class Messages {
    public static class Car{
        public static final String NotExists = "CAR_NOT_EXISTS";
        public static final String NotAvailable = "CAR_NOT_AVAILABLE";
    }
    public static class Model{
        public static final String NotExists = "MODEL_NOT_EXISTS";
        public static final String Exists = "MODEL_ALREADY_EXISTS";
    }
    public static class Brand{
        public static final String NotExists = "BRAND_NOT_EXISTS";
        public static final String Exists = "BRAND_ALREADY_EXISTS";
    }

    public static class Maintenance{
        public static final String NotExists = "MAINTENANCE_NOT_EXISTS";
        public static final String CarExists = " CAR_IS_CURRENTLY_UNDER_MAINTENANCE";
        public static final String CarNotExists = " CAR_NOT_REGISTERED_FOR_MAINTENANCE";
        public static final String CarIsRented = " CAR_IS_RENTED_AND_CAN_NOT_BE_SENT_TO_MAINTENANCE";

    }
    public static class Rental{
        public static final String NotExists ="RENTAL_NOT_EXISTS";
    }
}
