package com.apll.centermanagementsservice.schedulemanagement.model.dto

import com.apll.centermanagementsservice.config.LocalDateDeSerializer
import com.apll.centermanagementsservice.config.LocalDateSerializer
import com.apll.centermanagementsservice.schedulemanagement.model.EmployeeType
import com.apll.centermanagementsservice.schedulemanagement.model.FEScheduleStatus
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate


data class FrontEndScheduleDayWiseDTO(
        @ApiModelProperty(name = "frontEndScheduleId", notes = "frontEndScheduleId of FrontEndScheduleDayWiseDTO")
        var frontEndScheduleId: String?,

        @JsonDeserialize(using = LocalDateDeSerializer::class)
        @JsonSerialize(using = LocalDateSerializer::class)
        @ApiModelProperty(name = "yearmonth", notes = "year month of FrontEndScheduleDayWiseDTO")
        var yearmonth: LocalDate,

        @ApiModelProperty(name = "frontEndId", notes = "frontEndId of FrontEndScheduleDayWiseDTO")
        var frontEndId: String,

        @ApiModelProperty(name = "frontEndName", notes = "frontEndName of FrontEndScheduleDayWiseDTO")
        var frontEndName:String?,

        @ApiModelProperty(name = "listOfDayWiseScheduleDTO", notes = "listOfDayWiseScheduleDTO of FrontEndScheduleDayWiseDTO")
        var listOfDayWiseScheduleDTO:ArrayList<DayWiseScheduleDTO>,

        @ApiModelProperty(name = "fEScheduleStatus", notes = "approving FrontEndScheduleDayWiseDTO")
        var feScheduleStatus: FEScheduleStatus,

        @ApiModelProperty(name = "employeeType", notes = "employee Type in FrontEndScheduleDayWiseDTO " )
        var employeeType: EmployeeType,

        @ApiModelProperty(name = "reasonForUpdate", notes = "reason For Update in FrontEndScheduleDayWiseDTO")
        var reasonForUpdate:String?,

        @ApiModelProperty(name = "messageForReject", notes = "messageForReject FrontEndScheduleDayWiseDTO")
        var messageForReject:String?,

        @JsonDeserialize(using = LocalDateDeSerializer::class)
        @JsonSerialize(using = LocalDateSerializer::class)
        @ApiModelProperty(name = "rejectedDate", notes = "rejectedDate FrontEndScheduleDayWiseDTO")
        var rejectedDate:LocalDate?,


        @ApiModelProperty(name = "headAdminId", notes = "headAdminId FrontEndScheduleDayWiseDTO")
        var headAdminId:String?
)