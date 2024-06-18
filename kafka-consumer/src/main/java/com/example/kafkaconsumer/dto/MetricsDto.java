package com.example.kafkaconsumer.dto;

import com.example.kafkaconsumer.model.Metric;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record MetricsDto(@JsonProperty("metrics") List<Metric> metrics) {

}
