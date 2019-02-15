package com.apll.centermanagementsservice.centrevisitreport.service

import com.apll.centermanagementsservice.center.model.dto.CenterDTO
import com.apll.centermanagementsservice.centrevisitreport.model.CenterVisitReport
import com.apll.centermanagementsservice.centrevisitreport.model.CenterVisitReportId
import com.apll.centermanagementsservice.centrevisitreport.model.CenterVisitReportStatus
import com.apll.centermanagementsservice.centrevisitreport.model.ReasonStatus
import com.apll.centermanagementsservice.centrevisitreport.model.dto.CenterVisitReportDto
import com.apll.centermanagementsservice.centrevisitreport.model.dto.CenterVisitReportUpdateDTO
import com.apll.centermanagementsservice.centrevisitreport.model.dto.TodoListApllResolvedDto
import com.apll.centermanagementsservice.centrevisitreport.model.dto.TodoListCenterResolvedDto
import io.vavr.control.Either
import java.time.LocalDate
import java.time.YearMonth

interface CenterVisitReportService {
    fun insertCenterVisitReport(centerVisitReportDto: CenterVisitReportDto): Either<Exception, CenterVisitReportDto>
    fun findByCenterVisitReportId(id: String): Either<Exception, CenterVisitReportDto>
    fun findAllCenterVisitReport(): Either<Exception, List<CenterVisitReportDto>>
  //  fun updateCenterVisitReport(centerVisitReportDto: CenterVisitReportDto): Either<Exception, CenterVisitReportDto>
    fun updateCVR(centerVisitReportUpdateDTO: CenterVisitReportUpdateDTO):Either<Exception, CenterVisitReportDto>

    fun getCenterVisitReportByFrontEndId(frontEndId: String): Either<Exception, List<CenterVisitReportDto>>
    fun changeStatus(centerVisitReportStatus: CenterVisitReportStatus, centerVisRepoId:String):Either<Exception,CenterVisitReportDto>
    fun getMonthWiseCenterVisitReportByCenterId(centerId:String,centerVisitReportmonth: String):Either<Exception,List<CenterVisitReportDto>>
    fun getMonthWiseCenterVisitReportByYearMonthAndFrontEndId(yearMonth: String,frontEndId:String):Either<Exception,List<CenterVisitReportDto>>
    fun resolvedTodoForCenterByCvrId(cvrId:String,resolvedCenterdtoList:List<TodoListCenterResolvedDto>):Either<Exception,CenterVisitReportDto>
    fun resolvedTodoForApllByCvrId(cvrId:String,resolvedApllDtoList:List<TodoListApllResolvedDto>):Either<Exception,CenterVisitReportDto>
    fun getCenterName(requestTypeId:String,centerType:String):Either<Exception,CenterDTO>

  //fun changeReasonStatusForTodoForCenter(cvrId:String,todoforcenterId:String, reasonStatus:ReasonStatus):Either<Exception,CenterVisitReportDto>
}

