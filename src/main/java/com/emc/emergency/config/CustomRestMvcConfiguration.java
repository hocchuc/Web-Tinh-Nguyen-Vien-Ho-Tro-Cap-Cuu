package com.emc.emergency.config;

import com.emc.emergency.data.EventListener.AccidentEventHandler;
import com.emc.emergency.data.model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
class CustomRestMvcConfiguration {

  @Bean
  public RepositoryRestConfigurer repositoryRestConfigurer() {

    return new RepositoryRestConfigurerAdapter() {

      @Override
      public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.setBasePath("/api");
        config.setReturnBodyForPutAndPost(true);
        config.exposeIdsFor(Accident.class);
        config.exposeIdsFor(User.class);
        config.exposeIdsFor(Personal_Infomation.class);
        config.exposeIdsFor(Medical_Info.class);
        config.exposeIdsFor(User_Type.class);
        config.exposeIdsFor(Accident_Detail.class);
        config.exposeIdsFor(Action_Type.class);


      }
    };
  }
  @Bean
  AccidentEventHandler accidentEventHandler() {
    return new AccidentEventHandler();
  }
}