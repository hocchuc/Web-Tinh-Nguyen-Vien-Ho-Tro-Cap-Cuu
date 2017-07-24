package com.emc.emergency.data.repository;

import com.emc.emergency.data.model.Accident;
import com.emc.emergency.data.model.Accident_Detail;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by hocan on 16-Jul-17.
 */
@RepositoryRestResource
public interface accident_detailRepository extends CrudRepository<Accident_Detail, Long> {

  @Override
  List<Accident_Detail> findAll();
}
