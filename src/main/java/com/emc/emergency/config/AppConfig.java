package com.emc.emergency.config;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Configuration
@ComponentScan("com.emc.emergency.web.FlashMessage")
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter  {
    @Override
       public void addResourceHandlers(ResourceHandlerRegistry registry) {
           registry.addResourceHandler("/resources/**").addResourceLocations("/public-resources/");
       }
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(
            Charset.forName("UTF-8"));
        stringConverter.setSupportedMediaTypes(Arrays.asList( //
            MediaType.TEXT_PLAIN, //
            MediaType.TEXT_HTML, //
            MediaType.APPLICATION_JSON,
            MediaType.IMAGE_JPEG,
            MediaType.IMAGE_PNG,
            MediaType.APPLICATION_OCTET_STREAM
         ));
        converters.add(stringConverter);

    	 GsonHttpMessageConverter msgConverter = new GsonHttpMessageConverter();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	 msgConverter.setGson(gson);
        converters.add(msgConverter);


    }
}

