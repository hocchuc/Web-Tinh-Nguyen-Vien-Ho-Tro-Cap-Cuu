package com.emc.emergency.dao;

import com.emc.emergency.model.Personal_Infomation;

import java.util.List;

/**
 * Created by hocan on 21-May-17.
 */
public interface Personal_InformationDao {
    List<Personal_Infomation> findAll();
    Personal_Infomation findById(Long id);
    void save(Personal_Infomation personal_infomation);
    void delete(Personal_Infomation personal_infomation);
}
