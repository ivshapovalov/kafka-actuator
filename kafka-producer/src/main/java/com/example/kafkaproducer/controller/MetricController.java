package com.example.kafkaproducer.controller;

import com.example.kafkaproducer.aspect.DebugLogging;
import com.example.kafkaproducer.service.MetricService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@DebugLogging
public class MetricController {

  private final MetricService metricService;

  public MetricController(MetricService metricService) {
    this.metricService = metricService;
  }

  @Operation(summary = "Отправка в Kafka метрик приложения, полученных из Actuator",
      description = "Отправка в Kafka метрик приложения, полученных из Actuator")
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/metrics"
  )
  ResponseEntity<Object> sendMetrics() {
    metricService.sendMetrics();
    return ResponseEntity.ok().build();
  }

}
