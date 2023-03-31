package com.melihgencturk.rentacar.api.controllers;

import com.melihgencturk.rentacar.business.abstracts.ModelService;
import com.melihgencturk.rentacar.entities.Model;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/models")
public class ModelsController {
    private final ModelService service;

    @PostMapping
    public Model add(@RequestBody Model model){
        return service.add(model);
    }
}
