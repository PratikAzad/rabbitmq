package com.apll.centermanagementsservice.centrevisitreport.model.dto

import com.apll.centermanagementsservice.config.JsonDateTimeDeSerializer
import com.apll.centermanagementsservice.config.JsonDateTimeSerializer
import com.apll.centermanagementsservice.config.LocalDateDeSerializer
import com.apll.centermanagementsservice.config.LocalDateSerializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.Range
import java.time.LocalDate
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@ApiModel("RunningBatchStatus" ,description = "RunningBatchStatus for Existing Center",parent = Any::class)

data class RunningBatchStatusDTO(
        @ApiModelProperty(name="runningBatchStatusId",notes="runningBatchStatusId for RunningBatchStatus")
        var runningBatchStatusId: String?,

        @get:NotNull(message = "batchNo can not be NULL")
        @get:NotBlank(message = "batchNo  must not be blank")
        @get:Size(min=1,max=20,message = "batch No should have min 1 to max 20 alphanumeric")
        @ApiModelProperty(name="batchNo",notes="batchNo for RunningBatchStatus")
        var batchNo:String?,


        @get:NotNull(message = "days can not be ZERO")
        @get:Range(min=1,max=100,message = "ruuning batch  min 1 day to max 100 days")
        @ApiModelProperty(name="days",notes="days for RunningBatchStatus")
        var days:Int?,

        @get:NotNull(message = "time can not be NULL/EMPTY")
        @JsonDeserialize(using=JsonDateTimeDeSerializer::class)
        @JsonSerialize(using=JsonDateTimeSerializer::class)
        @ApiModelProperty(name="time",notes="time for RunningBatchStatus")
        var time: LocalDateTime?,

        @get:NotNull(message = "start date can not be NULL/EMPTY")
        @JsonDeserialize(using = LocalDateDeSerializer::class)
        @JsonSerialize(using = LocalDateSerializer::class)
        @ApiModelProperty(name="startdate",notes="startdate for RunningBatchStatus")
        var startdate: LocalDate?,

        @get:NotNull(message = "facultyName can not be NULL")
        @get:NotBlank(message = "facultyName must not be blank")
        @get:Size(min=3,max=40,message = "facultyName should have min 3 to max 40 characters")
        @ApiModelProperty(name="faculty",notes="faculty for RunningBatchStatus")
        var facultyName:String?,


        @ApiModelProperty(name = "facultyMobNo", notes = "facultyMobNo of a Faculty for RunningBatchStatus")
        @get:NotNull(message = "faculty Mobile-Number should not be null.")
        @get:NotBlank(message = "faculty Mobile-Number should not be blank.")
        @get:Size(max = 10,min = 10,message = "faculty Mobile Number must be 10 digit only.")
        @get:Pattern(regexp = "^[6-9]\\d{9}\$",message = "Please write Mobile Number in valid format.")
        var facultyMobNo:String?,


        @ApiModelProperty(name="completedModule",notes="completed_module for RunningBatchStatus")
        @get:Size(min=3,max=40,message = "completedModule Should have minimum 3 to max 40 characters")
        var completedModule:String?,

        @get:NotNull(message = "currentModule  can not be NULL")
        @get:NotBlank(message = "currentModule must not be blank")
        @get:Size(min=3,max=40,message = "current module Should have minimum 3 to max 40 characters")
        @ApiModelProperty(name="currentModule",notes="current_module for RunningBatchStatus")
        var currentModule:String?,

        @get:NotNull(message = "noOfStudsAtBegng  can not be empty")
        @get:Range(min=1,max=100,message = "number of students at begining min 1 to max 100 students")
        @ApiModelProperty(name="noOfStudsAtBeg",notes="no_of_studs_at_begng for RunningBatchStatus")
        var noOfStudsAtBegng:Int?

     /*   @get:NotNull(message = "noOfStudsAtBegng  can not be empty")
        @ApiModelProperty(name="noOfStudsAttending",notes="no_of_studs_attending  for RunningBatchStatus  ")
        var noOfStudsAttending:Int?*/) {
}