package com.emc.emergency.data.repository;

import com.emc.emergency.data.entity.Accident;
import org.springframework.data.repository.CrudRepository;

public interface  accidentRepository extends CrudRepository<Accident, Long> {

}
