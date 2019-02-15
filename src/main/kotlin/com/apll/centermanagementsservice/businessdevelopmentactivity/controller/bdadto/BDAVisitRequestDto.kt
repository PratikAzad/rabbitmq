package com.apll.centermanagementsservice.businessdevelopmentactivity.controller.bdadto

import com.apll.centermanagementsservice.businessdevelopmentactivity.model.BDARequestType
import com.apll.centermanagementsservice.businessdevelopmentactivity.model.BDAType
import com.apll.centermanagementsservice.config.LocalDateDeSerializer
import com.apll.centermanagementsservice.config.LocalDateSerializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class BDAVisitRequestDto
(


                        @ApiModelProperty(name="bda_Visit_Request_Id",notes="bdaVisitRequestId for Business Development Activity")
                        var bdaVisitRequestId: String?,

                        @get:NotNull(message = "activity Type can  not be NULL")
                        @ApiModelProperty(name="activity_Type",notes="activityType for Business Development Activity")
                        var activityType: BDAType?,

                        @get:NotNull(message = " can  not be NULL")
                        @get:NotBlank(message = "frontEndId  must not be blank")
                        @ApiModelProperty(name="front_End_Id",notes="frontEndId for Business Development Activity")
                        var frontEndId:String?,


                        @ApiModelProperty(name="front_End_Name",notes="frontEndName for Business Development Activity")
                        var frontEndName:String?,


                        @ApiModelProperty(name="center_Id",notes="centerId for Business Development Activity")
                        var centerId:String?,

                        @ApiModelProperty(name="center_Name",notes="centerName for Business Development Activity")
                         var centerName:String?,

                        @ApiModelProperty(name="area",notes="area for Business Development Activity")
                        @get:Size(min=3,max=40,message="area should have min 3 to max 40 characters")
                        var  area:String?,

                        @ApiModelProperty(name="city",notes="city for Business Development Activity")
                        @get:Size(min=3,max=40,message="city should have min 3 to max 40 characters")
                        var city:String?,

                        @ApiModelProperty(name="placeName",notes="placeName for Business Development Activity")
                        @get:Size(min=3,max=40,message="place Name should have min 3 to max 40 characters")
                        var placeName:String?,

                        @get:NotNull(message = "description can not be NULL ")
                        @get:NotBlank(message="description must not be blank ")
                        @get:Size(max=500,min=5,message = "description should have min 5 characters and max 500 characters")
                        @ApiModelProperty(name="description",notes="description for Business Development Activity")
                        var description:String,


                        @get:NotNull(message = "startDate  can not be NULL/EMPTY")
                        @ApiModelProperty(name="start_Date",notes="startDate for Business Development Activity")
                        @JsonDeserialize(using = LocalDateDeSerializer::class)
                        @JsonSerialize(using = LocalDateSerializer::class)
                        var startDate: LocalDate?,

                        @get:NotNull(message = "endDate  can not be NULL/EMPTY")
                        @ApiModelProperty(name="end_Date",notes="endDate for Business Development Activity")
                        @JsonDeserialize(using = LocalDateDeSerializer::class)
                        @JsonSerialize(using = LocalDateSerializer::class)
                        var endDate: LocalDate?,


                        @get:NotNull(message = "bda Request Type can  not be NULL")
                        @ApiModelProperty(name="bda_request_type",notes="bda_request_type for Business Development Activity")
                        var bdaRequestType: BDARequestType?,

                        @get:Valid
                        @ApiModelProperty(name="bdaReports",notes="bdaReports for Business Development Activity")
                        var bdaReports:List<BDAVisitReportDto>?,


                        @get:NotNull(message = "reportFiled can  not be NULL")
                        @ApiModelProperty(name="report_filed",notes="reportFiled for Business Development Activity")
                        var reportFiled:Boolean?){
}