package com.emc.emergency.config;

import com.emc.emergency.data.model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.http.HttpStatus;

@Configuration
class CustomRestMvcConfiguration {

  @Bean
  public RepositoryRestConfigurer repositoryRestConfigurer() {

    return new RepositoryRestConfigurerAdapter() {

      @Override
      public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.setBasePath("/api");
        config.exposeIdsFor(Accident.class);
        config.exposeIdsFor(User.class);
        config.exposeIdsFor(Chat.class);
        config.exposeIdsFor(Personal_Infomation.class);
        config.exposeIdsFor(Medical_Info.class);
        config.exposeIdsFor(User_Type.class);
        config.exposeIdsFor(Image.class);


        config.setReturnBodyForPutAndPost(true);
      }
    };
  }
}