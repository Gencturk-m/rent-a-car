package com.melihgencturk.rentacar.business.rules;

import com.melihgencturk.rentacar.core.exceptions.BusinessException;
import com.melihgencturk.rentacar.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrandBusinessRules {
    private final BrandRepository repository;
    public void checkIfBrandExists(int id){
        if (!repository.existsById(id)) throw new BusinessException("There is not such a brand.");
    }

    public void checkIfBrandExistsByName(String name){
        if (repository.existsByNameIgnoreCase(name)){
            throw new BusinessException("There is a brand with this name already.");
        }
    }

}
