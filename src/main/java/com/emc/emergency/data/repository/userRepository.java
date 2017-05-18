package com.emc.emergency.data.repository;

import com.emc.emergency.data.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by hocan on 18-May-17.
 */
public interface userRepository extends CrudRepository<User, Long> {
    User findByUserName(@Param("username")String username);

}
