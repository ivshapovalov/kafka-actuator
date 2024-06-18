package com.example.kafkaconsumer.exception;

public class MetricNotFoundException extends RuntimeException {

  public MetricNotFoundException(String metricName) {
    super("Could not find metric with id '%s'".formatted(metricName));
  }
}
