package com.emc.emergency.data.repository;

import com.emc.emergency.data.model.Personal_Infomation;
import com.emc.emergency.data.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;

/**
 * Created by hocan on 18-May-17.
 */
@RepositoryRestController
public interface personal_infoRepository extends CrudRepository<Personal_Infomation, Long> {
    @Override
    Personal_Infomation findOne(Long aLong);
}
