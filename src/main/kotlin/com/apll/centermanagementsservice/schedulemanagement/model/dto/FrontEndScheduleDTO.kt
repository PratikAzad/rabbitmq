package com.apll.centermanagementsservice.schedulemanagement.model.dto

import com.apll.centermanagementsservice.config.LocalDateDeSerializer
import com.apll.centermanagementsservice.config.LocalDateSerializer
import com.apll.centermanagementsservice.schedulemanagement.model.FEScheduleStatus
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull


@ApiModel(value = "FrontEndSchedule", description = "Class representing FrontEndSchedule", parent = Any::class)
data class FrontEndScheduleDTO(

        @ApiModelProperty(name = "frontEndScheduleId", notes = "front_end_schedule_id of FrontEndSchedule")
        var frontEndScheduleId: String?,

        @JsonDeserialize(using = LocalDateDeSerializer::class)
        @JsonSerialize(using = LocalDateSerializer::class)
        @ApiModelProperty(name = "year_month", notes = "year_month of FrontEndSchedule")
        @get:NotNull(message = "year Month cannot be null or Empty.")
        var yearMonth: LocalDate?,

        @ApiModelProperty(name = "frontEndId", notes = "front_end_id of FrontEndSchedule")
        @get:NotNull(message = "front-End Id cannot be null.")
        @get:NotBlank(message = "front-End Id cannot be blank.")
        var frontEndId: String?,

        @ApiModelProperty(name = "frontEndName", notes = "front_end_name of FrontEndSchedule")
        var frontEndName: String? ,

        @ApiModelProperty(name = "schedules", notes = "schedules of FrontEndSchedule")
        @get:NotNull(message = "schedules cannot be null or Empty.")
        @get:Valid
        var schedules: List<ScheduleDTO>?,

        @ApiModelProperty(name = "fEScheduleStatus", notes = "feScheduleStatus FrontEndSchedule")
        @get:NotNull(message = "feScheduleStatus enum type cannot be null or Empty.")
        var feScheduleStatus: FEScheduleStatus?,

        @ApiModelProperty(name = "reasonForUpdate", notes = "reasonForUpdate from Front End for FrontEndSchedule.")
        var reasonForUpdate:String?,

        @ApiModelProperty(name = "messageForReject", notes = "messageForReject from Front End for FrontEndSchedule.")
        var messageForReject:String?,

        @JsonDeserialize(using = LocalDateDeSerializer::class)
        @JsonSerialize(using = LocalDateSerializer::class)
        @ApiModelProperty(name = "rejectedDate", notes = "rejectedDate FrontEndSchedule")
        var rejectedDate:LocalDate?,

        @ApiModelProperty(name = "headAdminId", notes = "headAdminId FrontEndSchedule")
        var headAdminId:String?
)
