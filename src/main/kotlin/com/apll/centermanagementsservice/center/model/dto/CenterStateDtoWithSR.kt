package com.apll.centermanagementsservice.center.model.dto

import com.apll.centermanagementsservice.center.model.CenterStatus
import com.apll.centermanagementsservice.center.model.CenterType
import com.apll.centermanagementsservice.config.LocalDateDeSerializer
import com.apll.centermanagementsservice.config.LocalDateSerializer
import com.apll.centermanagementsservice.regionAndsubRegion.controller.regiondto.SubRegionDTO
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate
import java.util.*

@ApiModel(value = "CenterStateDtoWithSR", description = "Class representing CenterStateDtoWithSR", parent = Any::class)
data class CenterStateDtoWithSR(
        @ApiModelProperty(name = "centerId", notes = "centerId-Id of a center with SubRegion")
        var centerId: String?,

        @ApiModelProperty(name = "centerName", notes = "centerName of a center with SubRegion")
        var centerName:String="",

        @ApiModelProperty(name = "nameOfOwner", notes = "nameOfOwner of a center with SubRegion")
        var nameOfOwner:String="",

        @ApiModelProperty(name = "contactNumber", notes = "contactNumber of a center with SubRegion")
        var contactNumber:String="",

        @ApiModelProperty(name = "alternateNumber", notes = "alternateNumber of a center with SubRegion")
        var alternateNumber:String="",

        @ApiModelProperty(name = "houseNumber", notes = "houseNumber of a center with SubRegion")
        var houseNumber:String="",

        @ApiModelProperty(name = "streetName", notes = "streetName of a center with SubRegion")
        var streetName:String="",

        @ApiModelProperty(name = "areaName", notes = "areaName of a center with SubRegion")
        var areaName:String="",

        @ApiModelProperty(name = "landmark", notes = "landmark of a center with SubRegion")
        var landmark:String="",

        @ApiModelProperty(name = "city", notes = "city of a center with SubRegion")
        var city :String="",

        @ApiModelProperty(name = "pinCode", notes = "pinCode of a center with SubRegion")
        var pinCode:String="",

        @ApiModelProperty(name = "state", notes = "state of a center with SubRegion")
        var state:String="",

        @ApiModelProperty(name = "courierService", notes = "courierService of a center with SubRegion")
        var courierService:String="",

        @ApiModelProperty(name = "emailId", notes = "emailId of a center with SubRegion")
        var emailId:String="",

        @ApiModelProperty(name = "panDetails", notes = "panDetails of a center with SubRegion")
        var panDetails:String="",


        @JsonDeserialize(using = LocalDateDeSerializer::class)
        @JsonSerialize(using = LocalDateSerializer::class)
        @ApiModelProperty(name = "centerEstablishYear", notes = "centerEstablishYear of a center with SubRegion")
        var centerEstablishYear: LocalDate,

        @ApiModelProperty(name = "serviceTaxNumber", notes = "serviceTaxNumber of a center with SubRegion")
        var serviceTaxNumber:String,

        @ApiModelProperty(name = "centerType", notes = "centerType of a center with SubRegion")
        var centerType: CenterType,

        @ApiModelProperty(name = "centerStatus", notes = "centerStatus of a center with SubRegion")
        var centerStatus: CenterStatus,

        @ApiModelProperty(name = "facultyStateDtoList", notes = "facultyStateDtoList of a center with SubRegion")
        var facultyStateDtoList: Set<FacultyStateDto>,

        @ApiModelProperty(name = "infrastructureDetailsDTO", notes = "infrastructureDetailsDTO of a center with SubRegion")
        var infrastructureDetailsDTO: InfrastructureDetailsDTO,

        @ApiModelProperty(name = "subRegionDTO", notes = "subRegionDTO of a center with SubRegion")
        var subRegionDTO: SubRegionDTO
){
        override fun equals(other: Any?): Boolean {
                return super.equals(other)
        }

        override fun hashCode(): Int {
            return Objects.hashCode(this.centerId)
                //return super.hashCode()
        }

        override fun toString(): String {
                return super.toString()
        }
}