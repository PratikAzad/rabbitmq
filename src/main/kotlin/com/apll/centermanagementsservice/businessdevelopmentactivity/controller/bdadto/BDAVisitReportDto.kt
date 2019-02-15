package com.apll.centermanagementsservice.businessdevelopmentactivity.controller.bdadto

import com.apll.centermanagementsservice.businessdevelopmentactivity.controller.imagedto.BDAVisitImageDTO
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
import javax.validation.constraints.Size

@ApiModel("Business Development Activity Visit Report" ,description = "Visit Report  for Business Development Activity",parent = Any::class)
data class BDAVisitReportDto (

        @ApiModelProperty(name="bda_Visit_Report_Id",notes="bda_Visit_Report_Id for Business Development Activity")
        var  bdaVisitReportId: String?,

        @get:NotNull(message = "bda Visit Request Id can not be NULL")
        @get:NotBlank(message="bda Visit Request must not be blank")
        @ApiModelProperty(name="bda_visit_request_id",notes="bda_visit_request_id for Business Development Activity")
        var  bdaVisitRequestId: String?,

        @get:Valid
        @ApiModelProperty(name="bdaVisitImageList",notes="bda Visit Image List for Business Development Activity")
        var  bdaVisitImageList:List<BDAVisitImageDTO>?,

        @get:NotNull(message = "description can not be NULL")
        @get:NotBlank(message = "description must not be blank")
        @get:Size(max=500,min=5,message = "description should have min 5 characters  and  max 500 characters")
        @ApiModelProperty(name="description",notes="description for Business Development Activity")
        var  description:String?,


        @get:NotNull(message = "bda Visit Report Date can not be NULL")
        @ApiModelProperty(name="bda_visit_report_date",notes="bda_visit_report_date for Business Development Activity")
        @JsonDeserialize(using = LocalDateDeSerializer::class)
        @JsonSerialize(using = LocalDateSerializer::class)
        var bdaVisitReportDate: LocalDate?,

        //For Schedule Comletion
        var typeOfReq: TypeOfRequest?,

        @get:NotNull(message = "Front-End-Schedule-Id can not be NULL")
        var feScheduleId: String?,

        @get:NotNull(message = "Request-Id can not be NULL")
        var requestId: String?,

        @get:NotNull(message = "schedule Date can not be NULL")
        @JsonDeserialize(using = LocalDateDeSerializer::class)
        @JsonSerialize(using = LocalDateSerializer::class)
        var scheduleDate:LocalDate?
)