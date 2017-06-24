package com.emc.emergency.dao;

import com.emc.emergency.model.Chat;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by hocan on 21-May-17.
 */
public class ChatDaoImpl implements ChatDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Chat> findAll() {
        return null;
    }

    @Override
    public Chat findById(Long id) {
        return null;
    }

    @Override
    public void save(Chat chat) {

    }

    @Override
    public void delete(Chat chat) {

    }
}
