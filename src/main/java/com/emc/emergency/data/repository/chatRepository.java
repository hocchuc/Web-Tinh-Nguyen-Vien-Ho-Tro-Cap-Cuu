package com.emc.emergency.data.repository;

import com.emc.emergency.data.model.Chat;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by hocan on 18-May-17.
 */
public interface chatRepository extends CrudRepository<Chat, Long> {
}
