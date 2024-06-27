package com.springboot_crud.Service.impl;

import com.springboot_crud.DTO.InflowDTO;
import com.springboot_crud.Exception.CustomException;
import com.springboot_crud.Model.Inflow;
import com.springboot_crud.Repository.InflowRepository;
import com.springboot_crud.Service.InflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InflowServiceImpl implements InflowService {

    @Autowired
    private InflowRepository inflowRepository;

    @Override
    public List<Inflow> allInflows() {
        return inflowRepository.findAll();
    }

    @Override
    public Inflow createInflow(Inflow inflow) {
        return inflowRepository.save(inflow);
    }

    @Override
    public Optional<Inflow> singleInflow(Long id) {
        Optional<Inflow> existingInflow = inflowRepository.findById(id);
        if (existingInflow.isEmpty()) {
            throw new CustomException("Inflow does not exist");
        }
        return existingInflow;
    }

    @Override
    public boolean deleteInflow(Long id) {
        if (inflowRepository.existsById(id)) {
            inflowRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
