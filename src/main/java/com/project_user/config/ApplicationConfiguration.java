package com.project_user.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.DateFormat;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public ObjectMapper objectMapper(){

        final Jackson2ObjectMapperBuilder mapperBuilder = new Jackson2ObjectMapperBuilder();
        mapperBuilder.dateFormat(DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL));

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        mapperBuilder.configure(mapper);

        return mapperBuilder.build();
    }
}
