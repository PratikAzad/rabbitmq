package com.apll.centermanagementsservice.businessdevelopmentactivity.service

import com.apll.centermanagementsservice.businessdevelopmentactivity.controller.bdadto.BDAVisitReportDto
import com.apll.centermanagementsservice.businessdevelopmentactivity.controller.bdadto.BDAVisitRequestDto
import com.apll.centermanagementsservice.businessdevelopmentactivity.controller.bdadto.BDARequestDto
import io.vavr.control.Either

interface BDAVisitRequestService {


    //fun insertBusinessDevelopmentActivity(dto: BDAVisitDTO): Either<Exception, List<BDARequestDto>>

    fun insertBusinessDevelopmentActivity(businessDevelopmentActivitydto: BDARequestDto): Either<Exception, BDARequestDto>
    fun getBusinessDevelopmentActivityById(businessDevelopementActivityId:String):Either<Exception, BDARequestDto>

    fun getAllBDAVisitRequestWithBDAReports():Either<Exception,List<BDAVisitRequestDto>>
    fun getAllByFrontEndId(frontEndId:String):Either<Exception,List<BDAVisitRequestDto>>

    fun getOneMonthBdaVisitRequestsByregionIdAndDate(regionId:String,yearMonth: String):Either<Exception,List<BDAVisitRequestDto>>
    fun getMonthWiseBdaVisitRequestByFrontEndIdAndYearMonth(yearMonth:String,frontEndId:String):Either<Exception,List<BDAVisitRequestDto>>
    fun getBDAVisitRequestWithReportById(bdaRequestId:String):Either<Exception, BDAVisitRequestDto>

    fun updateBusinessDevelopmentActivity(businessDevelopmentActivitydto: BDARequestDto):Either<Exception, BDARequestDto>
    fun fileBdaReport(bdaVisitReportDto: BDAVisitReportDto):Either<Exception, BDAVisitRequestDto>
}