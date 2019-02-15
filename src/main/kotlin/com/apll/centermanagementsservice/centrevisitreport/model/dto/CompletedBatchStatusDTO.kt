package com.apll.centermanagementsservice.centrevisitreport.model.dto

import com.apll.centermanagementsservice.config.LocalDateDeSerializer
import com.apll.centermanagementsservice.config.LocalDateSerializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.Range
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@ApiModel("CompletedBatchStatus" ,description = "CompletedBatchStatus  for Existing Center",parent = Any::class)

data class CompletedBatchStatusDTO(
        @ApiModelProperty(name="completedBatchStatusId",notes="completedBatchStatusId for CompletedBatchStatus")
        var completedBatchStatusId: String,

        @get:NotNull(message = "batchNo can not be NULL")
        @get:NotBlank(message="batchNo must not be blank")
        @get:Size(min=1,max=20,message = "batchNo should have min 1 to 20 chars")
        @ApiModelProperty(name="batch_no",notes="batch_no for CompletedBatchStatus")
        var batchNo:String?,

        @get:NotNull(message = "examConductedOn can not  be empty")
        @ApiModelProperty(name="exam_conducted_on",notes="exam_conducted_on for CompletedBatchStatus")
        var examConductedOn:Boolean?,

        @JsonDeserialize(using = LocalDateDeSerializer::class)
        @JsonSerialize(using = LocalDateSerializer::class)
        @ApiModelProperty(name="conducted_on",notes="conducted_on for CompletedBatchStatus")
        var conductedOn: LocalDate?,

        @get:NotNull(message = "totalNoOfStudents can not be ZERO")
        @get:Range(min=1,max=100,message = "totalNumber of Students should have min 1 to 100 students")
        @ApiModelProperty(name="total_no_of_students",notes="total_no_of_students for CompletedBatchStatus")
        var totalNoOfStudents:Int?,


        @ApiModelProperty(name="no_of_tudents_appeared",notes="no_of_students_appeared for CompletedBatchStatus")
        @get:Range(min=1,max=100,message = "number of Students Appeared should have min 1 to 100 students")
        var noOfStudentsAppeared:Int?,

        @get:NotNull(message = "remarks can not be NULL")
        @get:NotBlank(message = "remarks must not be blank")
        @get:Size(max=500,min=2,message = "remarks should have  min  2  to max 500 characters ")
        @ApiModelProperty(name=" remarks",notes=" remarks for CompletedBatchStatus")
        var remarks:String?) {
}