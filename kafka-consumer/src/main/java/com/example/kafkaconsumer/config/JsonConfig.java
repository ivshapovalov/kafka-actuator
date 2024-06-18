package com.example.kafkaconsumer.config;

import com.example.kafkaconsumer.serialization.CustomOffsetDateTimeDeserializer;
import com.example.kafkaconsumer.serialization.CustomOffsetDateTimeSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.time.OffsetDateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonConfig {

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    module.addSerializer(OffsetDateTime.class, new CustomOffsetDateTimeSerializer());
    module.addDeserializer(OffsetDateTime.class, new CustomOffsetDateTimeDeserializer());
    objectMapper.registerModule(module);
    return objectMapper;
  }
}
