package com.emc.emergency.data.repository;

import com.emc.emergency.data.model.Accident;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface  accidentRepository extends CrudRepository<Accident, Long> {
    @Override
    List<Accident> findAll();


}
