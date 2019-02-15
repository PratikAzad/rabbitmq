package com.apll.centermanagementsservice.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class YearMonthDeserializer extends JsonDeserializer<YearMonth> {

    @Override
    public YearMonth deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException{
        String s=jsonParser.readValueAs(String.class);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
        return YearMonth.parse(s, formatter);
    }
}
