package com.apll.centermanagementsservice.centrevisitreport.model.dto

import com.apll.centermanagementsservice.centrevisitreport.model.*
import com.apll.centermanagementsservice.config.LocalDateDeSerializer
import com.apll.centermanagementsservice.config.LocalDateSerializer
import com.apll.centermanagementsservice.schedulemanagement.model.TypeOfRequest
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@ApiModel(value = "CenterVisitReportDto", description = "Class representing Center Visit Request", parent = Any::class)
data class CenterVisitReportDto (

        @ApiModelProperty(name="centerVisitReportId",notes="centerVisitReportId for CenterVisitReportDto")
        var centerVisitReportId:String?,


        @get:NotNull(message = "center Id can not be NULL")
        @get:NotBlank(message = "center Id must not be blank")
        @ApiModelProperty(name="centerId",notes="centerVisitReportId for CenterVisitReportDto")
        var centerId: String?,

        @get:NotNull(message = "front End Id can not be NULL")
        @get:NotBlank(message = "front End Id must not be blank")
        @ApiModelProperty(name="front End Id",notes="frontEndId for CenterVisitReportDto")
        var  frontEndId:String?,

        @get:NotNull(message = "request Id can not be NULL")
        @get:NotBlank(message = "request Id must not be blank")
        @ApiModelProperty(name="request Id",notes=" requestId for CenterVisitReportDto")
        var requestId:String?,

        @get:NotNull(message = "frontEndScheduleId can not be NULL")
        @get:NotBlank(message = "frontEndScheduleId must not be blank")
        @ApiModelProperty(name="frontEndScheduleId",notes="frontEndScheduleId for CenterVisitReportDto")
        var frontEndScheduleId:String?,

        @get:NotNull(message = "scheduleDate can not be NULL")
        @ApiModelProperty(name="scheduleDate",notes="scheduleDate for CenterVisitReportDto")
        @JsonDeserialize(using = LocalDateDeSerializer::class)
        @JsonSerialize(using = LocalDateSerializer::class)
        var scheduleDate:LocalDate?=null,

        @get:NotNull(message = "center Visit Report Date can not be NULL/EMPTY")
        @ApiModelProperty(name="centerVisitReportDate",notes=" centerVisitReportDate for CenterVisitReportDto")
        @JsonDeserialize(using = LocalDateDeSerializer::class)
        @JsonSerialize(using = LocalDateSerializer::class)
        var centerVisitReportDate: LocalDate?,


        @get:Valid
        @ApiModelProperty(name="dropOutDetails",notes=" dropOutDetails for CenterVisitReportDto")
        var dropOutDetails:List<DropOutDetailsDTO>?,

        @get:Valid
        @ApiModelProperty(name="runningBatchStatus",notes=" runningBatchStatus for CenterVisitReportDto")
        var runningBatchStatus:List<RunningBatchStatusDTO>?,

        @get:Valid
        @ApiModelProperty(name="stockStatus",notes=" stockStatus for CenterVisitReportDto")
        var stockStatus:List<StockStatusDTO>?,

        @get:Valid
        @ApiModelProperty(name="completedBatchStatus",notes="completedBatchStatus for CenterVisitReportDto")
        var completedBatchStatus:List<CompletedBatchStatusDTO>?,


      /*  @get:Valid
        @ApiModelProperty(name="studentFeedbackReport",notes="studentFeedbackReport for CenterVisitReportDto")
        var studentFeedbackReport:List<StudentFeedbackReportDTO>?,*/

        @get:Valid
        @ApiModelProperty(name="todoForCenterdto",notes="todoForCenterdto for CenterVisitReportDto")
        var todoForCenterdto:TodoForCenterDTO?,

        @get:Valid
        @ApiModelProperty(name="todoForAplldto",notes="todoForAplldto for CenterVisitReportDto")
        var todoForAplldto:TodoForApllDTO?,

        @get:Valid
        @ApiModelProperty(name="infrastructureDTO",notes="infrastructureDTO for CenterVisitReportDto")
        var infrastructureDTO: List<CvrInfrastructureDTO>?,

        @get:NotNull(message = "centerVisitReportStatus can not be NULL/EMPTY")
        @ApiModelProperty(name="centerVisitReportStatus",notes=" centerVisitReportStatus for CenterVisitReportDto")
        var centerVisitReportStatus:CenterVisitReportStatus?,

        @get:NotNull(message = "Type Of Request can not be NULL/EMPTY")
        @ApiModelProperty(name="centerVisitReportStatus",notes=" centerVisitReportStatus for CenterVisitReportDto")
        var typeOfReq:TypeOfRequest?
)