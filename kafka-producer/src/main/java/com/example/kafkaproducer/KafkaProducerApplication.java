package com.example.kafkaproducer;

import com.example.kafkaproducer.service.MetricService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class KafkaProducerApplication {

  private final MetricService metricService;

  public KafkaProducerApplication(MetricService metricService) {
    this.metricService = metricService;
  }

  public static void main(String[] args) {
    SpringApplication.run(KafkaProducerApplication.class, args);
  }

  @EventListener(ApplicationReadyEvent.class)
  public void doSomethingAfterStartup() {
//		mainService.sendMetrics();
  }
}
