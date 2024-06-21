package com.springboot_crud.Repository;

import com.springboot_crud.Model.Inflow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InflowRepository extends JpaRepository<Inflow, Long> {
}
