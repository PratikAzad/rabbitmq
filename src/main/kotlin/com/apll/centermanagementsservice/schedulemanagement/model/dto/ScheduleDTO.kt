
package com.apll.centermanagementsservice.schedulemanagement.model.dto

import com.apll.centermanagementsservice.config.LocalDateDeSerializer
import com.apll.centermanagementsservice.config.LocalDateSerializer
import com.apll.centermanagementsservice.schedulemanagement.model.TypeOfRequest
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull


@ApiModel(value = "Schedule", description = "Class representing Schedule", parent = Any::class)
data class  ScheduleDTO(

        @ApiModelProperty(name = "requestId", notes = "schedule_id of Schedule")
        var scheduleId: String?,


        @JsonDeserialize(using = LocalDateDeSerializer::class)
        @JsonSerialize(using = LocalDateSerializer::class)
        @ApiModelProperty(name = "scheduleDate", notes = "schedule_date of Schedule")
        @get:NotNull(message = "schedule_date cannot be null. ")
        var scheduleDate: LocalDate?,

        @ApiModelProperty(name = "regionId", notes = "region_id of Schedule")
        var regionId: String?,

        @ApiModelProperty(name = "note", notes = "note of Schedule")
        @get:NotNull(message = "note cannot be null. ")
        @get:NotBlank(message = "note cannot be blank.")
        var note: String?,

        @ApiModelProperty(name = "scheduleOnWhichPurpose", notes = "schedule_on_which_purpose of Schedule")
        @get:NotNull(message = "schedule_on_which_purpose cannot be null. ")
        var scheduleOnWhichPurpose: TypeOfRequest?,

        @ApiModelProperty(name = "isScheduleCompleted", notes = "is_schedule_completed of Schedule")
        var isScheduleCompleted: Boolean?=false,

        @ApiModelProperty(name = "requestId", notes = "visiting_request_id of Schedule")
        var visitingRequestId: String?
)

