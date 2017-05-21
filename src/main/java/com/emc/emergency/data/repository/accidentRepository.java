package com.emc.emergency.data.repository;

import com.emc.emergency.data.model.Accident;
import org.springframework.data.repository.CrudRepository;

public interface  accidentRepository extends CrudRepository<Accident, Long> {

}
