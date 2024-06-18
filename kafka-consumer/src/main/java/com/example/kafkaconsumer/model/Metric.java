package com.example.kafkaconsumer.model;

import com.example.kafkaconsumer.serialization.CustomOffsetDateTimeDeserializer;
import com.example.kafkaconsumer.serialization.CustomOffsetDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "metric")
@Data
@NoArgsConstructor
@IdClass(MetricId.class)
public class Metric {

  @Id
  @Column(name = "name")
  @JsonProperty("name")
  private String name;

  @Id
  @Column(name = "moment")
  @JsonProperty("moment")
  @JsonSerialize(using = CustomOffsetDateTimeSerializer.class)
  @JsonDeserialize(using = CustomOffsetDateTimeDeserializer.class)
  private OffsetDateTime moment;

  @Column(name = "value")
  @JsonProperty("value")
  private Double value;

  @JsonCreator
  public Metric(@JsonProperty("name") String name,
                @JsonDeserialize(using = CustomOffsetDateTimeDeserializer.class)
                @JsonProperty("moment") OffsetDateTime moment,
                @JsonProperty("value") Double value
  ) {
    this.name = name;
    this.value = value;
    this.moment = moment;
  }
}


