package com.example.kafkaconsumer.service;

import com.example.kafkaconsumer.aspect.DebugLogging;
import com.example.kafkaconsumer.dto.MetricsDto;
import com.example.kafkaconsumer.model.Metric;
import com.example.kafkaconsumer.repository.MetricRepository;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@DebugLogging
public class MetricService {

  private final MetricRepository metricRepository;
  @Value("${app.kafka.topic}")
  String kafkaTopic;

  public MetricService(MetricRepository metricRepository) {
    this.metricRepository = metricRepository;
  }

  public void saveMetrics(MetricsDto metricsDto) {
    if (!Objects.isNull(metricsDto) && !Objects.isNull(metricsDto.metrics())) {
      metricRepository.saveAll(metricsDto.metrics());
    }
  }

  public List<Metric> findAllLast(OffsetDateTime moment) {
    return metricRepository.findLastAll(moment);
  }

  public List<Metric> findMetricHistoryByNameAndInterval(String metricName,
                                                         OffsetDateTime momentFrom,
                                                         OffsetDateTime momentTo) {
    return metricRepository.findMetricHistoryByName(metricName, momentFrom, momentTo);
  }
}
