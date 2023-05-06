package com.melihgencturk.rentacar.business.concretes;

import com.melihgencturk.rentacar.business.abstracts.BrandService;
import com.melihgencturk.rentacar.business.dto.requests.create.CreateBrandRequest;
import com.melihgencturk.rentacar.business.dto.requests.update.UpdateBrandRequest;
import com.melihgencturk.rentacar.business.dto.responses.create.CreateBrandResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetAllBrandsResponse;
import com.melihgencturk.rentacar.business.dto.responses.get.GetBrandResponse;
import com.melihgencturk.rentacar.business.dto.responses.update.UpdateBrandResponse;
import com.melihgencturk.rentacar.business.rules.BrandBusinessRules;
import com.melihgencturk.rentacar.entities.Brand;
import com.melihgencturk.rentacar.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {

    private final BrandRepository repository;
    private final ModelMapper mapper;

    private final BrandBusinessRules rules;

    @Override
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands = repository.findAll();
        List<GetAllBrandsResponse> response = brands.stream()
                .map(brand -> mapper.map(brand,GetAllBrandsResponse.class))
                .toList();
        return response;
    }

    @Override
    public GetBrandResponse getById(int id) {
        rules.checkIfBrandExists(id);
        Brand brand = repository.findById(id).orElseThrow();
        GetBrandResponse response = mapper.map(brand,GetBrandResponse.class);
        return response;
    }

    @Override
    public CreateBrandResponse add(CreateBrandRequest request)
    {
        rules.checkIfBrandExistsByName(request.getName());

        Brand brand = mapper.map(request, Brand.class);
        brand.setId(0);
        Brand createdBrand = repository.save(brand);
        CreateBrandResponse response= mapper.map(createdBrand, CreateBrandResponse.class);
        return response;
    }

    @Override
    public UpdateBrandResponse update(int id, UpdateBrandRequest request) {
        rules.checkIfBrandExists(id);
        Brand brand = mapper.map(request, Brand.class);
        brand.setId(id);
        Brand updatedBrand = repository.save(brand);
        UpdateBrandResponse response = mapper.map(updatedBrand, UpdateBrandResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfBrandExists(id);
        repository.deleteById(id);
    }

}
