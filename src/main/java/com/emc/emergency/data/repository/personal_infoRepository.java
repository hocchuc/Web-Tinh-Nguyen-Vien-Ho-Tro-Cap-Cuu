package com.emc.emergency.data.repository;

import com.emc.emergency.data.entity.Personal_Infomation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by hocan on 18-May-17.
 */
public interface personal_infoRepository extends CrudRepository<Personal_Infomation, Long> {
    //Personal_Infomation findByName(@Param(""))
}
