package com.apll.centermanagementsservice.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class YearMonthSerializer extends JsonSerializer<YearMonth> {
    @Override
    public void serialize(YearMonth yearMonth, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MM-yyyy");

        jsonGenerator.writeString(yearMonth.format(f));
    }
}
