package com.example.javahealthrisks.services.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.javahealthrisks.dtos.CustomerDto;
import com.example.javahealthrisks.models.CustomerModel;
import com.example.javahealthrisks.repositories.CustomerRepository;
import com.example.javahealthrisks.services.CustomerService;
import com.example.javahealthrisks.services.exceptions.NotFoundException;

@Service
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository repository;

    public CustomerServiceImp(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public CustomerModel create(CustomerDto customerDto) {
        Character gender = Character.toUpperCase(customerDto.gender());

        if (!gender.equals('M') && !gender.equals('F')) {
            throw new NotFoundException("Invalid gender");
        }
        
        var customerModel = new CustomerModel();
        BeanUtils.copyProperties(customerDto, customerModel);
        customerModel.setGender(gender);
        
        return repository.save(customerModel);
    }

    @Override
    public CustomerModel getOneById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOneById'");
    }

    @Override
    public List<CustomerModel> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public void updateOneById(String id, CustomerDto customerDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateOneById'");
    }

    @Override
    public void removeOneById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeOneById'");
    }

}
