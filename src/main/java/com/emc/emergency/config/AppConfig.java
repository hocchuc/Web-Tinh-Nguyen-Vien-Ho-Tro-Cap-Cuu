package com.emc.emergency.config;
import java.util.List;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Configuration
@ComponentScan("com.emc.emergency.web.FlashMessage")
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter  {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    	GsonHttpMessageConverter msgConverter = new GsonHttpMessageConverter();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	msgConverter.setGson(gson);
        converters.add(msgConverter);
    }
}

