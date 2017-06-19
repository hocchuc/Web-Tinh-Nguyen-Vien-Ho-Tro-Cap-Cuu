package com.emc.emergency.dao;

import com.emc.emergency.model.Chat;

import java.util.List;

/**
 * Created by hocan on 21-May-17.
 */
public interface ChatDao {
    List<Chat> findAll();
    Chat findById(Long id);
    void save(Chat chat);
    void delete(Chat chat);
}
