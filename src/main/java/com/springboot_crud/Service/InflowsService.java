package com.springboot_crud.Service;

import com.springboot_crud.DTO.InflowDTO;
import com.springboot_crud.Exception.CustomException;
import com.springboot_crud.Model.Inflow;
import com.springboot_crud.Repository.InflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InflowsService {
    @Autowired
    private final InflowRepository inflowRepository;

    public InflowsService(InflowRepository inflowRepository) {
        this.inflowRepository = inflowRepository;
    }

    public List<Inflow> allInflows()
    {
        return inflowRepository.findAll();
    }
    public Inflow createInflow(Inflow inflow)
    {
        return inflowRepository.save(inflow);
    }

    public Optional<Inflow> singleInflow(Long id)
    {
        Optional<Inflow> existingInflow = inflowRepository.findById(id);
       if(existingInflow.isEmpty())
       {
         throw new CustomException("Inflow does not exist");
       }
        return existingInflow;
    }


    public boolean deleteInflow(Long id) {
        if (inflowRepository.existsById(id)) {
            inflowRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
