package com.melihgencturk.rentacar.business.concretes;

import com.melihgencturk.rentacar.business.abstracts.BrandService;
import com.melihgencturk.rentacar.entities.Brand;
import com.melihgencturk.rentacar.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {

    private final BrandRepository repository;

    @Override
    public List<Brand> getAll() {
        return repository.findAll();
    }

    @Override
    public Brand getById(int id) {
        checkIfBrandExists(id);
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Brand add(Brand brand) {
        return repository.save(brand);
    }

    @Override
    public Brand update(int id, Brand brand) {
        checkIfBrandExists(id);
        brand.setId(id);
        return repository.save(brand);
    }

    @Override
    public void delete(int id) {
        checkIfBrandExists(id);
        repository.deleteById(id);
    }


    //Let's get down to Business!

    private void checkIfBrandExists(int id){
        if (!repository.existsById(id)) throw new IllegalArgumentException("There is not such a brand.");
    }



}
