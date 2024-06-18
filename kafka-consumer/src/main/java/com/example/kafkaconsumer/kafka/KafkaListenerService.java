package com.example.kafkaconsumer.kafka;

import com.example.kafkaconsumer.aspect.DebugLogging;
import com.example.kafkaconsumer.dto.MetricsDto;
import com.example.kafkaconsumer.service.MetricService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@DebugLogging
public class KafkaListenerService {

  private final MetricService metricService;

  public KafkaListenerService(MetricService metricService) {
    this.metricService = metricService;
  }

  @KafkaListener(topics = "${app.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
  void readTopic(@Payload MetricsDto metricsDto,
                 @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                 @Header(KafkaHeaders.OFFSET) int offset) {
    log.info("Received message [{}] from partition-{} with offset-{}",
        metricsDto,
        partition,
        offset);

    metricService.saveMetrics(metricsDto);
  }

  @KafkaListener(topics = "${app.kafka.topic-dlt}", groupId = "${spring.kafka.consumer.group-id}")
  void readDlt(byte[] in) {
    log.info("Received message [{}] from dlt", new String(in));

  }
}
