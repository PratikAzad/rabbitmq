package com.apll.centermanagementsservice.schedulemanagement.model.dto

import com.apll.centermanagementsservice.schedulemanagement.model.TypeOfRequest
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate

data class CompletedScheduleDTO(

        //This DTO not used in Controller.
//        @ApiModelProperty(name = "frontEndScheduleId", notes = "frontEndScheduleId of CompletedScheduleDTO")
        var frontEndScheduleId: String,

//        @ApiModelProperty(name = "listOfScheduleId", notes = "request-Id of CompletedScheduleDTO")
        var requestId:String,

//        @ApiModelProperty(name = "listOfScheduleId", notes = "request-Id of CompletedScheduleDTO")
        var sheduledate:LocalDate?,

        var typeOfRequest: TypeOfRequest?

)