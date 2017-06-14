package com.emc.emergency.data.repository;

import com.emc.emergency.data.model.Medical_Info;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;

/**
 * Created by hocan on 18-May-17.
 */
@RepositoryRestController
public interface medical_infoRepository extends CrudRepository<Medical_Info, Long> {
}
