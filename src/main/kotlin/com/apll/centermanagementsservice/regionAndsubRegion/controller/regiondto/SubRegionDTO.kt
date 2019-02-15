package com.apll.centermanagementsservice.regionAndsubRegion.controller.regiondto


import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


@ApiModel(value = "SubRegion", description = "Class representing SubRegion", parent = Any::class)
data class SubRegionDTO (

    @ApiModelProperty(name = "subRegionId", notes = "sub_region_id of Region")
    var subRegionId:String?="",

    @ApiModelProperty(name = "regionId", notes = "region_Id of Region")
    var regionId:String?="",

    @get:NotNull(message="Sub-Region-Name can not be NULL(Empty).")
    @get:Size(max=40,min=3, message="SubRegion-Name should have max 40 characters and min 3 characters..")
    @ApiModelProperty(name = "subRegionName", notes = "sub_region_name of Region")
    var subRegionName:String?,

    @ApiModelProperty(name = "regionName", notes = "region_name of Region")
    var regionName:String?="",

    @get:Size(max=40,min=3, message="City should have max 40 characters and min 3 characters..")
    @get:NotNull(message="City can not be NULL(Empty).")
    @ApiModelProperty(name = "city", notes = "city of Region")
    var city:String?
){
    constructor():this("","",null,"",null)
}





    /*fun setsubRegionId(subRegionId:String){
         this.subRegionId=subRegionId
    }
    fun getsubRegionId():String{
        return this.subRegionId
    }
    fun  setregionId(regionId :String){
        this.regionId=regionId
    }
    fun getregionId():String
    {
        return regionId
    }

    fun setsubRegionName(subRegionName:String){
        this.subRegionName=subRegionName
    }
    fun getsubRegionName():String{
        return this.subRegionName
    }

    fun setregionName(regionName:String){
        this.regionName=regionName
    }
    fun getregionName():String{
        return this.regionName
    }

    fun setcity(    city:String){
        this.city=city
    }
    fun getcity():String
    {
        return this.city
    }*/
