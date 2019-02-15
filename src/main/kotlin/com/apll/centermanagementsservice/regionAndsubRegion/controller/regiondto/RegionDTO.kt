package com.apll.centermanagementsservice.regionAndsubRegion.controller.regiondto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


@ApiModel(value = "Region", description = "Class representing Region", parent = Any::class)
data class RegionDTO (
        @ApiModelProperty(name = "regionId", notes = "region_Id of Region")
        var regionId: String?,

        @get:NotNull(message="Region-Name can not be NULL(Empty).")
        @get:Size(max=40,min=3, message="RegionName should have max 40 characters and min 3 characters..")
        @ApiModelProperty(name = "regionName", notes = "region_name of Region")
        var regionName:String?,

        @get:Size(max=40,min=3, message="State should have max 40 characters and min 3 characters..")
        @get:NotNull(message="State can not be NULL(Empty).")
        @ApiModelProperty(name = "state", notes = "state of Region")
        var state:String?,


        @get:Valid
        @get:NotNull(message="Sub-Region can not be NULL(Empty).")
        @ApiModelProperty(name = "sub_region", notes = "sub_region of Region")
        var subRegionDtos: Set<SubRegionDTO>?

        /*@NotNull
        @ApiModelProperty(name = "employeeDto", notes = "Employee Details")
        var employeeDtos: List<EmployeeDto>?=null
*/

)