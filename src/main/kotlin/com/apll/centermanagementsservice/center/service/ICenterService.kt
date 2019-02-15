package com.apll.centermanagementsservice.center.service

import com.apll.centermanagementsservice.center.model.dto.CenterDTO
import com.apll.centermanagementsservice.center.model.dto.CenterStateDTO
import com.apll.centermanagementsservice.center.model.dto.CenterStateDtoWithSR
import com.apll.centermanagementsservice.center.model.dto.CenterUpdateDTO
import io.vavr.control.Either
import java.time.LocalDate

interface ICenterService {


    fun register(centerStateDto: CenterStateDTO): Either<Exception, CenterStateDtoWithSR>

    fun getCenterById(centerId:String): Either<Exception, CenterStateDtoWithSR>

    fun findAllCenter():Either<Exception,List<CenterStateDtoWithSR>>

    fun editCenter(dto: CenterUpdateDTO):Either<Exception,CenterStateDtoWithSR>

    fun getCenterBySubRegionId(subregionId: String):Either<Exception,Set<CenterStateDtoWithSR>>

    fun getAllCenterStatesByRegionId(regionId: String):Either<Exception,List<CenterStateDtoWithSR>>

    fun getLastTwoMonthNotVisitedCentersAccRegion(localDate: LocalDate, regionId: String): Either<Exception, List<CenterStateDtoWithSR>>

    fun getLastTwoMonthNotVisitedCentersAccEmployeeId(monthYear: String, employeeId: String): Either<Exception, List<CenterStateDtoWithSR>>

    fun getCenterName(centerId:String):Either<Exception, CenterDTO>
    }