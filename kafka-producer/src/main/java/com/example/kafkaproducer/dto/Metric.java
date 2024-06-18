package com.example.kafkaproducer.dto;

import com.example.kafkaproducer.serialization.CustomOffsetDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;

public record Metric(@JsonProperty("name") String name,
                     @JsonSerialize(using = CustomOffsetDateTimeSerializer.class)
                     @JsonProperty("moment")
                     OffsetDateTime moment,
                     @JsonProperty("value")
                     Double value) {

}
