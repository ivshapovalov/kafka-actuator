package com.example.kafkaconsumer.controller;

import com.example.kafkaconsumer.model.Metric;
import com.example.kafkaconsumer.service.MetricService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class MetricController {

  private final MetricService metricService;

  public MetricController(MetricService metricService) {
    this.metricService = metricService;
  }

  @Operation(summary = "Получение списка всех метрик с их последними на дату значениями",
      description = "Получение списка всех метрик")
  @GetMapping(
      value = "/metrics",
      produces = {"application/json"}
  )
  ResponseEntity<List<Metric>> findAllMetrics(
      @Schema(description = "Момент среза метрик (в UTC)", example = "2025-06-13T16:54:30Z")
      @RequestParam(value = "moment", required = false)
      OffsetDateTime moment) {
    List<Metric> metrics = metricService.findAllLast(moment);
    return ResponseEntity.ok().body(metrics);
  }

  @Operation(summary = "Получение истории выбранной метрики",
      description = "Получение истории выбранной метрики в выбранном интервале")
  @GetMapping(
      value = "/metrics/{metricName}",
      produces = {"application/json"}
  )
  ResponseEntity<List<Metric>> findMetricHistoryByName(
      @Schema(description = "Имя метрики", example = "application.ready.time")
      @PathVariable(value = "metricName")
      @NotBlank
      String metricName,
      @Schema(description = "Дата начала истории метрики (в UTC)", example = "2023-06-13T16:54:30Z")
      @RequestParam(value = "momentFrom", required = false)
      OffsetDateTime momentFrom,
      @Schema(description = "Дата окончания истории метрики (в UTC)", example = "2025-06-13T16:54:30Z")
      @RequestParam(value = "momentTo", required = false)
      OffsetDateTime momentTo
  ) {
    List<Metric> metrics = metricService.findMetricHistoryByNameAndInterval(metricName, momentFrom,
        momentTo);
    return ResponseEntity.ok().body(metrics);
  }
}
