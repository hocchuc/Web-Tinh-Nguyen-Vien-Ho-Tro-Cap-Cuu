package com.emc.emergency.data.repository;

import com.emc.emergency.data.model.Action_Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by hocan on 16-Jul-17.
 */
@RepositoryRestResource
public interface action_typeRepository extends CrudRepository<Action_Type, Long> {
}
