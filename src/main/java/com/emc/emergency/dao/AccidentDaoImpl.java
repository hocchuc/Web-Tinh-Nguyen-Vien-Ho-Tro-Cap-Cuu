package com.emc.emergency.dao;

import com.emc.emergency.model.Accident;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by hocan on 21-May-17.
 */
public class AccidentDaoImpl implements AccidentDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Accident> findAll() {
        return null;
    }

    @Override
    public Accident findById(Long id) {
        return null;
    }

    @Override
    public void save(Accident accident) {

    }

    @Override
    public void delete(Accident accident) {

    }
}
