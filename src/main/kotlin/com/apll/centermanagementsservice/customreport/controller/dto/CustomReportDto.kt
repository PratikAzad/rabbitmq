package com.apll.centermanagementsservice.customreport.controller.dto

import com.apll.centermanagementsservice.config.LocalDateDeSerializer
import com.apll.centermanagementsservice.config.LocalDateSerializer
import com.apll.centermanagementsservice.customreport.model.CustomReportId
import com.apll.centermanagementsservice.schedulemanagement.model.TypeOfRequest
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@ApiModel(value = "CustomReportDto", description = "Class representing  CustomReportDto", parent = Any::class)
data class CustomReportDto(

                        @ApiModelProperty(name = "customReportId", notes = "customReportId of a CustomReportDto")
                        var customReportId: String?,


                        @get:NotNull(message="custom Schedule Id can not be NULL")
                        @get:NotBlank(message="custom Schedule Id must not be blank")
                        @ApiModelProperty(name = "customScheduleId", notes = "custom Schedul eId of a CustomReportDto")
                        var customScheduleId:String?,

                        @get:NotNull(message="description can not be NULL")
                        @get:Size(max=500,min=2, message="description should have min 2 characters and Max 500 characters")
                        @get:NotBlank(message="description must not be blank")
                        @ApiModelProperty(name="description", notes = "description of a CustomReportDto")
                        var description:String?,

                        @get:NotNull(message="description can not be NULL/EMPTY")
                        @ApiModelProperty(name = "customReportDate", notes = "custom Report Date of a CustomReportDto")
                        @JsonDeserialize(using = LocalDateDeSerializer::class)
                        @JsonSerialize(using = LocalDateSerializer::class)
                        var customReportDate: LocalDate?,

                        @get:NotNull(message="frontEnd Schedule Id can not be NULL/EMPTY")
                        @get:NotBlank(message="front End Schedule Id must not be blank")
                        @ApiModelProperty(name = "frontEndScheduleId", notes = "front End Schedule Id of a CustomReportDto")
                        var frontEndScheduleId:String?,


                        @get:NotNull(message="frontEndName  can not be NULL/EMPTY")
                        @get:NotBlank(message="frontEndName  must not be blank")
                        @get:Size(max=40,min=5, message="frontEndName should have min 5 to  40 characters")
                        @ApiModelProperty(name = "frontEndName", notes = "frontEndName of a CustomReportDto")
                        var frontEndName:String?,


                        //For Schedule Comletion
                        var typeOfReq: TypeOfRequest?




                     ) {
}