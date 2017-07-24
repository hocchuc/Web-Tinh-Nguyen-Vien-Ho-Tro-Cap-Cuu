package com.emc.emergency.data.repository;

import com.emc.emergency.data.model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.List;


/**
 * Created by hocan on 18-May-17.
 */
@RepositoryRestController
public interface userRepository extends CrudRepository<User, Long> {

   // List<User> findByUsername(@Param("username")String username);

    List<User> findByUsernameAndPassword(@Param("username")String username,@Param("password")String password);

    User findByUsername(@Param("username")String username);

    @Override
    User findOne(Long aLong);

    @Override
    boolean exists(Long aLong);

    @Override
    List<User> findAll();

    @Override
    List<User> findAll(Iterable<Long> longs);

    @Override
    long count();

    @Override
    void delete(Long aLong);

    @Override
    void delete(User entity);



}
