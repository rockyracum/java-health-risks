package com.example.javahealthrisks.controllers;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.javahealthrisks.dtos.DiseaseDto;
import com.example.javahealthrisks.dtos.UpdateDiseaseDto;
import com.example.javahealthrisks.models.DiseaseModel;
import com.example.javahealthrisks.services.DiseaseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/disease")
public class DiseaseController {

    private final DiseaseService service;

    public DiseaseController(DiseaseService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid DiseaseDto requestBody) {
        var disease = service.create(requestBody);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(disease.getName()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable String id) {
        DiseaseModel diseaseOpt = service.getById(id);

        return ResponseEntity.status(HttpStatus.OK).body(diseaseOpt);
    }

    @GetMapping
    public ResponseEntity<Page<DiseaseModel>> getAll(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll(pageable));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateById(@PathVariable String id, @RequestBody UpdateDiseaseDto requestBody) {
        service.updateById(id, requestBody);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeById(@PathVariable String id) {
        service.removeById(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
