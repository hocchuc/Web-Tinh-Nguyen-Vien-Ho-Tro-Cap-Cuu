package com.emc.emergency.data.repository;

import com.emc.emergency.data.model.Accident;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.Date;
import java.util.List;
@RepositoryRestController
public interface  accidentRepository extends CrudRepository<Accident, Long> {
    @Override
    List<Accident> findAll();

    @Query("select u from Accident u where u.date_AC = ?1 and u.lat_AC = ?2 and u.long_AC = ?3")
    Accident findbyDate_ACAndLat_ACAndLong_AC(@Param("date_AC")Date date,
                                              @Param("lat_AC")Float lat_AC,
                                              @Param("long_AC")Float long_AC);

    @Override
    Accident findOne(Long aLong);

    @Override
    boolean exists(Long aLong);

    @Override
    long count();

    @Override
    void delete(Long aLong);

    @Override
    void delete(Accident entity);

    @Override
    void delete(Iterable<? extends Accident> entities);

    @Override
    void deleteAll();
}
