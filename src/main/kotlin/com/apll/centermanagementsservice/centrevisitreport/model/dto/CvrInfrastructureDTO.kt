package com.apll.centermanagementsservice.centrevisitreport.model.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@ApiModel("CvrInfrastructure" ,description = "CvrInfrastructure for Existing Center Report ",parent = Any::class)
data class CvrInfrastructureDTO(


        @ApiModelProperty(name="infrastructure Id",notes="infrastructure Id for CvrInfrastructure")
        var cvrInfrastructureId:String,

        @get:NotNull(message = "infrastructure Name can not be NULL ")
        @get:NotBlank(message = "infrastructure Name  Must not be blank")
        @get:Size(min=3,max=40,message = "infrastructure Name should have min 3 to max 40 characters")
        @ApiModelProperty(name="infrastructureName",notes="infrastructure Name for CvrInfrastructure")
        var infrastructureName:String,

        @ApiModelProperty(name="minimalValue",notes="minimalValue for CvrInfrastructure")
        @get:NotNull(message = "minimalValue can not be NULL ")
        var minimalValue:Float,

        @get:NotNull(message = "minimalRequirementSatisfies can not be NULL/Empty")
        @ApiModelProperty(name="minimal Requirement Satisfies",notes="minimalRequirementSatisfies for CvrInfrastructure")
        var minimalRequirementSatisfies:Boolean,



        @ApiModelProperty(name="current Value",notes="current Value for CvrInfrastructure")
        var currentValue:Float?
)