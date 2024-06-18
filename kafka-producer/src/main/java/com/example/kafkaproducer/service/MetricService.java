package com.example.kafkaproducer.service;

import com.example.kafkaproducer.aspect.DebugLogging;
import com.example.kafkaproducer.dto.Metric;
import com.example.kafkaproducer.dto.MetricsDto;
import com.example.kafkaproducer.kafka.KafkaSender;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.stereotype.Service;

@Service
@DebugLogging
public class MetricService {

  private final MetricsEndpoint metricsEndpoint;
  private final KafkaSender kafkaSender;

  @Value("${app.kafka.topic}")
  String kafkaTopic;

  public MetricService(MetricsEndpoint metricsEndpoint, KafkaSender kafkaSender) {
    this.metricsEndpoint = metricsEndpoint;
    this.kafkaSender = kafkaSender;
  }

  public void sendMetrics() {
    OffsetDateTime moment = OffsetDateTime.now();
    Set<String> metricsNames = metricsEndpoint.listNames().getNames();
    List<Metric> metrics = metricsNames.stream().map(
        metricName -> new Metric(metricName, moment,
            metricsEndpoint.metric(metricName, null)
                .getMeasurements()
                .stream()
                .filter(Objects::nonNull)
                .findFirst()
                .map(MetricsEndpoint.Sample::getValue)
                .filter(Double::isFinite)
                .orElse(0.0D)
        )).collect(Collectors.toList());
    kafkaSender.sendMessage(kafkaTopic, new MetricsDto(metrics));
  }
}
