package com.apll.centermanagementsservice.visitingrequest.service


import com.apll.centermanagementsservice.center.model.dto.CenterDTO
import com.apll.centermanagementsservice.visitingrequest.model.RequestType
import com.apll.centermanagementsservice.visitingrequest.controller.visitrequestdto.ResolvedDTO
import com.apll.centermanagementsservice.visitingrequest.controller.visitrequestdto.VisitingRequestDTO
import com.apll.centermanagementsservice.visitingrequest.dashboard.VisReqResolvedAnalysis
import com.apll.centermanagementsservice.visitingrequest.dashboard.VisitRequestAnalysis
import io.vavr.control.Either


interface VisitingRequestService {

    fun saveVisitingRequest(dto: VisitingRequestDTO): Either<Exception, VisitingRequestDTO>

    fun getVisitingRequestById(id: String): Either<Exception, VisitingRequestDTO>

    fun updateVisitingRequest(dto: VisitingRequestDTO): Either<Exception, VisitingRequestDTO>

    fun getAllVisitingRequest(): Either<Exception, List<VisitingRequestDTO>>

    fun getVisitingRequestByRequest(requestBy: RequestType): Either<Exception, List<VisitingRequestDTO>>

    fun getVisitingRequestByRegionId(regionId: String): Either<Exception, List<VisitingRequestDTO>>

    fun getResolvedVisitingRequest(regionId: String):Either<Exception, List<VisitingRequestDTO>>

    fun getVisitingRequestByRequesterId(requesterId: String): Either<Exception, List<VisitingRequestDTO>>

    fun resolve(dto: ResolvedDTO): Either<Exception, VisitingRequestDTO>

    fun getByEmployeeId(employeeId: String):Either<Exception, List<VisitingRequestDTO>>

    fun findCenterNameByRequestId(requestId: String):Either<Exception, CenterDTO>

    fun getNotScheduledRequestByEmployeeId(employeeId: String): Either<Exception, List<VisitingRequestDTO>>

    fun visReqAnalysis():Either<Exception, VisitRequestAnalysis>

    fun visReqResolvedAnalysis():Either<Exception, VisReqResolvedAnalysis>

    fun last2MonthAnalysis():Either<Exception,VisitRequestAnalysis>

    fun getUnresolved():Either<Exception,List<VisitingRequestDTO>>


    //fun getByUnResolvedEmployeeId(employeeId: String):Either<Exception, List<VisitingRequestDTO>>

    //fun getVisitingRequestByEmployeeId(employeeId: String): Either<Exception, List<VisitingRequestDTO>>

    //fun getByResolved(): Either<Exception, List<VisitingRequestDTO>>

    //fun getAllCVRForMonth(date: String,regionId: String):Either<Exception,List<VisitingRequestDTO>>

    //fun getAllCVRForMonth(yearMonth: YearMonth,regionId: String):Either<Exception,List<VisitingRequestDTO>>

    //fun changeStatusToVisitingRequest(status: Boolean, resolverId: String): Either<Exception, VisitingRequestDTO>

/*
    fun  getVisitingRequestByCenterId(centerId:String):Either<Exception ,List<VisitingRequestDTO>>
*/


   // fun getLastTwomonthsNotVisitedVisitingRequestByRequestDate(date: LocalDate): Either<Exception, List<VisitingRequestDTO>>

    //fun  getLastTwoMonthsNotVisitedCenters(date:LocalDate,regionId: String): Either<Exception, List<CenterStateDtoWithSR>>


    //fun demo(regionId: String, yearMonth: YearMonth): Either<Exception, List<VisitingRequestDTO>>

    //fun getLastTwoMonthNotVisitedCentersAccRegion(date: LocalDate, regionId: String): Either<Exception, List<CenterStateDtoWithSR>>
}