
package com.apll.centermanagementsservice.config;
/*
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.vavr.control.Either;

import java.io.IOException;
import java.time.LocalDate;

*/

import com.apll.centermanagementsservice.config.DateTimeZoneConverters;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.vavr.control.Either;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by SYNYCS on 3/14/2017.
 *//*

public class LocalDateDeSerializer extends JsonDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        //DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        Either<Exception,LocalDate> localDate=new DateTimeZoneConverters().
                dateFromString(jp.getText());
        return localDate.isRight()?localDate.get():null;

    }
}
*/

public class LocalDateDeSerializer extends JsonDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

//DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        Either<Exception,LocalDate> localDate=new DateTimeZoneConverters().
                dateFromString(jp.getText());
        return localDate.isRight()?localDate.get():null;

    }
}
