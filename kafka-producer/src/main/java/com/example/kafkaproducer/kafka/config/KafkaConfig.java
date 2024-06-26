package com.example.kafkaproducer.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConfig {

  @Value("${app.kafka.topic}")
  String kafkaTopic;

  @Value("${app.kafka.topic-dlt}")
  String kafkaTopicDlt;

  @Bean
  public CommonErrorHandler errorHandler(KafkaOperations<Object, Object> template) {
    return new DefaultErrorHandler(new DeadLetterPublishingRecoverer(template),
        new FixedBackOff(1000L, 2));
  }

  @Bean
  public NewTopic metricsTopic() {
    return TopicBuilder.name(kafkaTopic).partitions(1).replicas(1).build();
  }

  @Bean
  public NewTopic metricsDltTopic() {
    return TopicBuilder.name(kafkaTopicDlt).partitions(1).replicas(1).build();
  }

}
