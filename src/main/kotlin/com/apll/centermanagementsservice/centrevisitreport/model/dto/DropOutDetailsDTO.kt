package com.apll.centermanagementsservice.centrevisitreport.model.dto

import com.apll.centermanagementsservice.config.LocalDateDeSerializer
import com.apll.centermanagementsservice.config.LocalDateSerializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@ApiModel("DropOutDetails" ,description = "DropOutDetails for Existing Center",parent = Any::class)
data class DropOutDetailsDTO(

        @ApiModelProperty(name="drop_out_details_id",notes="drop_out_details_id for DropOutDetails")
        var dropOutDetailsId: String,

        @get:NotNull(message = "name Of Student can not be NULL ")
        @get:NotBlank(message = "nameOfStudents  Must not be blank")
        @get:Size(min=3,max=40,message = "name of students should have min 3 to max 40 characters")
        @ApiModelProperty(name="name_of_students",notes="name_of_students for DropOutDetails")
        var nameOfStudents:String?,

        @get:NotNull(message = "id  Of Student can not be NULL ")
        @get:NotBlank(message = "id  Of Student must not be blank")
        @get:Size(min=1,max=20,message = "student id should have min 1 to 20 chars")
        @ApiModelProperty(name="id",notes="id for DropOutDetails")
        var id:String?,

        @get:NotNull(message = "batchNo   can not be NULL ")
        @get:NotBlank(message = "batchNo must Not be blank")
        @get:Size(min=1,max=20,message = "batchNo should have min 1 to 20 chars")
        @ApiModelProperty(name="batch_no",notes="batch_no for DropOutDetails")
        var batchNo:String?,

        @get:NotNull(message = "dropOutDate  can not be NULL/EMPTY ")
        @JsonDeserialize(using = LocalDateDeSerializer::class)
        @JsonSerialize(using = LocalDateSerializer::class)
        @ApiModelProperty(name="drop_out_date",notes="drop_out_date for DropOutDetails")
        var dropOutDate: LocalDate?,


        @get:NotNull(message = "remarks  can not be NULL")
        @get:NotBlank(message = "remarks must not be blank")
        @get:Size(max=500,min=2,message = "remarks should have min 2 to 500 characters ")
        @ApiModelProperty(name="remarks",notes="remarks for DropOutDetails")
        var remarks:String?)

