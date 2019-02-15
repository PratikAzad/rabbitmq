package com.apll.centermanagementsservice.schedulemanagement.model.dto

import com.apll.centermanagementsservice.schedulemanagement.model.EmployeeType
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@ApiModel(value = "ScheduleUpdateDTO", description = "Class representing ScheduleUpdateDTO", parent = Any::class)
data class ScheduleUpdateDTO(


        @ApiModelProperty(name = "frontEndScheduleId", notes = "frontEndScheduleId of ScheduleUpdateDTO")
        @get:NotNull(message = "front-End-Schedule Id cannot be null.")
        var frontEndScheduleId: String?,

        @ApiModelProperty(name = "updatedBy", notes = "Who is updating the Front-End-Schedule")
        @get:NotNull(message = "updated By cannot be null.")
        @get:NotBlank(message = "updated By cannot be blank.")
        var updatedBy: String?,

        @ApiModelProperty(name = "listOfScheduleDto", notes = "listOfScheduleDto of ScheduleUpdateDTO")
        @get:NotNull(message = "list Of Schedule cannot be null.")
        @get:Valid
        var listOfScheduleDto:List<ScheduleDTO>?,

        @ApiModelProperty(name = "employeeType", notes = "Type of employee, who is updating")
        @get:NotNull(message = "employee Type cannot be null.")
        var employeeType: EmployeeType?,

        @ApiModelProperty(name = "reason", notes = "reason to update after approval of front-End-Schedule")
        @get:NotNull(message = "reason For Update schedule can not be null")
        @get:NotBlank(message = "reason For Update schedule can not be null" )
        @get:Size(min=5,max = 250,message = "reason For Update schedule min=5,max=250.")
        var reasonForUpdate:String?
)