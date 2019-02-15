/*
package com.apll.centermanagementsservice.centrevisitreport.model.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@ApiModel(value = "StudentFeedbackReportDTO", description = "Class representing StudentFeedbackReportDTO", parent = Any::class)
data class StudentFeedbackReportDTO (

    @ApiModelProperty(name = "studentFeedbackReportId", notes = "studentFeedbackReportId of a student Feedback Report")
    var studentFeedbackReportId:String?,

    @ApiModelProperty(name = "nameOfStudent", notes = "name Of Student in  student Feedback Report")
    @get:NotNull(message = "name Of Student can not be NULL ")
    @get:Size(min=3,message = "name Of Student should have min 3 characters")
    @get:NotBlank(message = "nameOfStudent must not be blank")
    var nameOfStudent:String?,

    @ApiModelProperty(name = "id", notes = "id  of a student Feedback Report")
    @get:NotNull(message = "id Of Student can not be NULL ")
    @get:NotBlank(message = "id Of Student must not be blank")
    var id:String?,

    @ApiModelProperty(name = "batchNo", notes = "batch number  of a student Feedback Report")
    @get:NotNull(message = "batchNo can not be NULL ")
    @get:NotBlank(message = "batchNo must not be blank")
    var batchNo:String?,

    @ApiModelProperty(name = "feedbackDetails", notes = "feedback Details from  student ")
    @get:NotNull(message = "feedback Details can not be NULL ")
    @get:Size(max=250,min=2,message = "feedbackDetails  should have atleast min 2 characters")
    @get:NotBlank(message = "feedbackDetails must not be blank")
    var feedbackDetails:String?
)*/
