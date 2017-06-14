package com.emc.emergency.data.projection;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by hocan on 14-Jun-17.
 */
//@Projection(name = "ChatExcerpt", types = ChatExcerpt.class)
public interface ChatExcerpt {
    Long getId_chat();
    @Value("#{target.id_AC}")

    String  getId_AC();
    @Value("#{target.id_user}")

    String getId_user();

    String getComment();

    java.util.Date getDate_chat();



}
