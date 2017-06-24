package com.emc.emergency.dao;

import com.emc.emergency.model.Image;

import java.util.List;

/**
 * Created by hocan on 21-May-17.
 */
public interface ImageDao {
    List<Image> findAll();
    Image findById(Long id);
    void save(Image image);
    void delete(Image image);
}
