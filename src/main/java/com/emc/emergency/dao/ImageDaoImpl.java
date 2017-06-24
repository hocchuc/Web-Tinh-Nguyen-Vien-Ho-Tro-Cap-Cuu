package com.emc.emergency.dao;

import com.emc.emergency.model.Image;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by hocan on 21-May-17.
 */
public class ImageDaoImpl implements ImageDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Image> findAll() {
        return null;
    }

    @Override
    public Image findById(Long id) {
        return null;
    }

    @Override
    public void save(Image image) {

    }

    @Override
    public void delete(Image image) {

    }
}
