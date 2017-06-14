package com.emc.emergency.data.repository;

import com.emc.emergency.data.model.Chat;
import com.emc.emergency.data.projection.ChatExcerpt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;

/**
 * Created by hocan on 18-May-17.
 */
@RepositoryRestController
public interface chatRepository extends CrudRepository<Chat, Long> {


    @Override
    Chat findOne(Long aLong);

    @Override
    boolean exists(Long aLong);

    @Override
    Iterable<Chat> findAll();

    @Override
    Iterable<Chat> findAll(Iterable<Long> longs);

    @Override
    long count();

    @Override
    void delete(Long aLong);

    @Override
    void delete(Chat entity);

    @Override
    void delete(Iterable<? extends Chat> entities);

    @Override
    void deleteAll();
}

