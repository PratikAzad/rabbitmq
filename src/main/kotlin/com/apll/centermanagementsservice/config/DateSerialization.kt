package com.apll.centermanagementsservice.config

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateSerialization:JsonSerializer<LocalDate>() {
    @Throws(IOException::class, JsonProcessingException::class)
    override fun serialize(date: LocalDate, jsonGenerator: JsonGenerator, serializerProvider: SerializerProvider) {
        jsonGenerator.writeString(date.format(DateTimeFormatter.ISO_DATE))
    }
}