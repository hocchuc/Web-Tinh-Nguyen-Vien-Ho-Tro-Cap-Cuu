package com.emc.emergency.dao;

import com.emc.emergency.model.Personal_Infomation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by hocan on 21-May-17.
 */
public class Personal_InfomationDaoImpl implements Personal_InformationDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Personal_Infomation> findAll() {
        return null;
    }

    @Override
    public Personal_Infomation findById(Long id) {
        return null;
    }

    @Override
    public void save(Personal_Infomation personal_infomation) {

    }

    @Override
    public void delete(Personal_Infomation personal_infomation) {

    }
}
