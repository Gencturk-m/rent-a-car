package com.melihgencturk.rentacar.business.concretes;

import com.melihgencturk.rentacar.business.abstracts.ModelService;
import com.melihgencturk.rentacar.entities.Model;
import com.melihgencturk.rentacar.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private final ModelRepository repository;

    @Override
    public Model add(Model model) {
        return repository.save(model);
    }
}
