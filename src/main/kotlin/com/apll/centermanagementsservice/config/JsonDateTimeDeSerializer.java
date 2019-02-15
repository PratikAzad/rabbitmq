package com.apll.centermanagementsservice.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.val;

import java.io.IOException;
import java.time.LocalDateTime;

public class JsonDateTimeDeSerializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        val dateTime=new DateTimeZoneConverters().dateTimeFromString(jsonParser.getText());

        return dateTime.isRight()?dateTime.get():null;
    }
}
