package com.example.kafkaconsumer.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class CustomOffsetDateTimeDeserializer extends JsonDeserializer<OffsetDateTime> {

  private static final DateTimeFormatter DATE_TIME_FORMATTER
      = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

  @Override
  public OffsetDateTime deserialize(JsonParser jsonParser,
                                    DeserializationContext deserializationContext)
      throws IOException {
    String dateAsString = jsonParser.getText();
    if (dateAsString == null) {
      throw new IOException("OffsetDateTime argument is null.");
    }
    return OffsetDateTime.parse(dateAsString, DATE_TIME_FORMATTER);
  }
}
