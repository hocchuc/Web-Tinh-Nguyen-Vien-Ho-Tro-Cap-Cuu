//package com.emc.emergency.config;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.GsonHttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//import java.util.List;
//
//@Configuration
//@ComponentScan("com.emc.emergency.data.model")
//@EnableWebMvc
//public class AppConfig2 extends WebMvcConfigurerAdapter {
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        GsonHttpMessageConverter msgConverter = new GsonHttpMessageConverter();
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        msgConverter.setGson(gson);
//        converters.add(msgConverter);
//    }
//}