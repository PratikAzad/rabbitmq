package com.apll.centermanagementsservice.schedulemanagement.model.dto

import com.apll.centermanagementsservice.schedulemanagement.model.TypeOfRequest
import java.time.LocalDate

data class CompletedCustomScheduleDto (

    //This DTO not used in Controller.
    // @ApiModelProperty(name = "frontEndScheduleId", notes = "frontEndScheduleId of CompletedScheduleDTO")
     var frontEndScheduleId: String,
        //        @ApiModelProperty(name = "listOfScheduleId", notes = "request-Id of CompletedScheduleDTO")
     var sheduledate: LocalDate?,
     var typeOfRequest: TypeOfRequest?,
     var customScheduleId:String?)

