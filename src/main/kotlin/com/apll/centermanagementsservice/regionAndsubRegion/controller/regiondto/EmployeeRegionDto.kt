package com.apll.centermanagementsservice.regionAndsubRegion.controller.regiondto

import io.swagger.annotations.ApiModelProperty

import javax.validation.constraints.NotNull

data class EmployeeRegionDto (
        @ApiModelProperty(name = "regionDTO", notes = "Region Dto")
     var regionDTO: RegionDTO? = null,
        @NotNull
    @ApiModelProperty(name = "employeeDtos", notes = "List Employee Dtos")
     var employeeDtos: List<EmployeeDto>? = null
)
