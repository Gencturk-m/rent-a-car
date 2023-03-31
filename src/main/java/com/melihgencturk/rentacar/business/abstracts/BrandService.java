package com.melihgencturk.rentacar.business.abstracts;

import com.melihgencturk.rentacar.business.dto.requests.create.CreateBrandRequest;
import com.melihgencturk.rentacar.business.dto.responses.create.CreateBrandResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetBrandResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetAllBrandsResponse;
import com.melihgencturk.rentacar.entities.Brand;

import java.util.List;

public interface BrandService {

    List<GetAllBrandsResponse> getAll();
    GetBrandResponse getById(int id);

    CreateBrandResponse add(CreateBrandRequest request);
    Brand update(int id,Brand brand);
    void delete(int id);
}
