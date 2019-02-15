package com.apll.centermanagementsservice.schedulemanagement.model.dto

import com.apll.centermanagementsservice.config.LocalDateDeSerializer
import com.apll.centermanagementsservice.config.LocalDateSerializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate


data class DayWiseScheduleDTO(

        @ApiModelProperty(name = "listOfScheduleDTO", notes = "listOfScheduleDTO of DayWiseScheduleDTO")
        var listOfScheduleDTO: List<ScheduleDTO>,

        @JsonDeserialize(using = LocalDateDeSerializer::class)
        @JsonSerialize(using = LocalDateSerializer::class)
        @ApiModelProperty(name = "dayOfMonth", notes = "dayOfMonth of DayWiseScheduleDTO")
        var dayOfMonth: LocalDate
)