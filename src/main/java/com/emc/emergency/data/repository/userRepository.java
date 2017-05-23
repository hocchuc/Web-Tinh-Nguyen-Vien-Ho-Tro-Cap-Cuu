package com.emc.emergency.data.repository;

import com.emc.emergency.data.model.User;
import com.emc.emergency.data.model.User_Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.List;

/**
 * Created by hocan on 18-May-17.
 */
@RepositoryRestController
public interface userRepository extends CrudRepository<User, Long> {

    List<User> findByUsername(@Param("username")String username);

    List<User> findByUsernameAndPassword(@Param("username")String username,@Param("password")String password);

  //  List<User> findById_user_type(@Param("id_user_type")Long id_user_type);





}
