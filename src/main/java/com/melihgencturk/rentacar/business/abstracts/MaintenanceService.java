package com.melihgencturk.rentacar.business.abstracts;

import com.melihgencturk.rentacar.business.dto.requests.create.CreateMaintenanceRequest;
import com.melihgencturk.rentacar.business.dto.requests.update.UpdateMaintenanceRequest;
import com.melihgencturk.rentacar.business.dto.responses.create.CreateMaintenanceResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetAllMaintenancesResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetMaintenanceResponse;
import com.melihgencturk.rentacar.business.dto.responses.update.UpdateMaintenanceResponse;

import java.util.List;

public interface MaintenanceService {
    List<GetAllMaintenancesResponse> getAll();
    GetMaintenanceResponse getById(int id);

    GetMaintenanceResponse returnCarFromMaintenance(int carId);

    CreateMaintenanceResponse create(CreateMaintenanceRequest request);

    UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request);

    void delete(int id);
}
