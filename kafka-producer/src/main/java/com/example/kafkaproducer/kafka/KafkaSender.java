package com.example.kafkaproducer.kafka;

import com.example.kafkaproducer.aspect.DebugLogging;
import com.example.kafkaproducer.dto.MetricsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@DebugLogging
public class KafkaSender {

  private final KafkaTemplate kafkaTemplate;

  public KafkaSender(KafkaTemplate kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage(String topicName, MetricsDto metrics) {
    kafkaTemplate.send(topicName, metrics);
  }
}
