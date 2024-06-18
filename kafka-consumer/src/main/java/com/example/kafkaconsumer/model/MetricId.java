package com.example.kafkaconsumer.model;

import com.example.kafkaconsumer.serialization.CustomOffsetDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class MetricId implements Serializable {

  @JsonProperty("name")
  private String name;
  @JsonProperty("moment")
  @JsonDeserialize(using = CustomOffsetDateTimeDeserializer.class)
  private OffsetDateTime moment;
}
