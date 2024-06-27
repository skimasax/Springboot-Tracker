package com.springboot_crud.Service;

import com.springboot_crud.Model.Inflow;

import java.util.List;
import java.util.Optional;

public interface InflowService {
    List<Inflow> allInflows();
    Inflow createInflow(Inflow inflow);
    Optional<Inflow> singleInflow(Long id);
    boolean deleteInflow(Long id);
}
