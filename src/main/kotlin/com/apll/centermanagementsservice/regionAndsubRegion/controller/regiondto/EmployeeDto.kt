package com.apll.centermanagementsservice.regionAndsubRegion.controller.regiondto

import com.apll.centermanagementsservice.restTemplet.CenterEmpDto

data class EmployeeDto (

        var employeeId: EmployeeId? = null,

        var employeeFullName: String? = null,
        var username: String? = null,
        var mobileNumber: String?=null,
        var listOfCenters:List<CenterEmpDto>?=null


)
