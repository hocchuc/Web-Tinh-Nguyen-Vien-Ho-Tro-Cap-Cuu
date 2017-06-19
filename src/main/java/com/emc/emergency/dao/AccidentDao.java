package com.emc.emergency.dao;

import com.emc.emergency.model.Accident;
import com.emc.emergency.model.User;

import java.util.List;

/**
 * Created by hocan on 21-May-17.
 */
public interface AccidentDao {
    List<Accident> findAll();
    Accident findById(Long id);
    void save(Accident accident);
    void delete(Accident accident);
}
