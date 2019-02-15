package com.apll.centermanagementsservice.schedulemanagement.frontendperformance.service

import com.apll.centermanagementsservice.regionAndsubRegion.controller.regiondto.EmployeeDto
import com.apll.centermanagementsservice.regionAndsubRegion.controller.regiondto.EmployeeId
import com.apll.centermanagementsservice.restTemplet.EmployeeService
import com.apll.centermanagementsservice.schedulemanagement.frontendperformance.dto.*
import com.apll.centermanagementsservice.schedulemanagement.model.FrontEndSchedule
import com.apll.centermanagementsservice.schedulemanagement.model.TypeOfRequest

import com.apll.centermanagementsservice.schedulemanagement.repository.FrontEndScheduleRepo

import io.vavr.control.Either
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Service
class FrontEndPerformanceService {

    @Autowired
    lateinit var frontEndScheduleRepo: FrontEndScheduleRepo

    @Autowired
    lateinit var empService:EmployeeService

    fun getAllFrontEnd():List<EmployeeDto>{
        var allFEnd=empService.getAllFrontEndEmployee()
        if(allFEnd!!.size<1){
            return ArrayList<EmployeeDto>()
        }
        return allFEnd
    }

     fun findFrontEndScheduleByIsScheduleCompletedAndDate(yearmonth:String):Either<Exception,List<FrontEndPerformance>> {

         var allFEnd=getAllFrontEnd()
         var feMap=allFEnd.map { x->x.employeeId!!.id to x.employeeFullName }.toMap()

        val formatter = DateTimeFormatter.ofPattern("MM-yyyy")
        val ym = YearMonth.parse(yearmonth, formatter)

        var startDate: LocalDate
        var endDate: LocalDate

        startDate = LocalDate.of(ym.year, ym.month, 1)
        endDate = LocalDate.of(ym.year, ym.month, ym.lengthOfMonth())
         var days=ym.lengthOfMonth()

        var frontEndScheduleList = frontEndScheduleRepo.findByYearmonthBetween(startDate, endDate)
         var map = frontEndScheduleList.groupBy { x -> x.frontEndScheduleId.frontEndScheduleId }
         var fESchedules = ArrayList<FrontEndSchedule>()
         map.forEach {
             var version = it.value.map { x -> x.version }.toSet().max()
             var fESchedule = it.value.stream().filter { x -> x.version == version }.findAny()
             if (fESchedule.isPresent) {
                 fESchedules.add(fESchedule.get())
             }
         }
        if (fESchedules.size > 0) {
            var fEPerformanceList = ArrayList<FrontEndPerformance>()

             fESchedules.forEach {
                var allReq=it.schedules.size
                var custom = CustomRequest(0, 0, 0.0F)
                var bda = BDARequest(0, 0, 0.0F)
                var ec = ExistingCenter(0, 0, 0.0F)
                var ecr = ExistingCenterRequest(0, 0, 0.0F)
                var nc = NewCenterRequest(0, 0, 0.0F)
                var  allVisitedReq=it.schedules.filter { x->x.isScheduleCompleted.equals(true) }.count()
                 var overallPercen=0.0F

                var fePerformance = FrontEndPerformance(it.frontEndId,bda,custom,ec,nc,ecr,feMap.get(it.frontEndId),allReq,allVisitedReq,overallPercen,days)


                it.schedules.forEach {
                    if (it.scheduleOnWhichPurpose.equals(TypeOfRequest.CUSTOM_REQUEST)) {
                        custom.customRequests++
                        if (it.isScheduleCompleted) {
                            custom.visitedCustomRequests++
                        }
                    } else if (it.scheduleOnWhichPurpose.equals(TypeOfRequest.BUSSINESS_DEVELOPEMENT_ACTIVITY)) {
                        bda.bdaRequests++
                        if (it.isScheduleCompleted) {
                            bda.visitedRequests++
                        }
                    } else if (it.scheduleOnWhichPurpose.equals(TypeOfRequest.EXISTING_CENTER)) {
                        ec.ecRequests++
                        if (it.isScheduleCompleted) {
                            ec.visitedExistingRequests++
                        }
                    } else if (it.scheduleOnWhichPurpose.equals(TypeOfRequest.EXISTING_CENTER_REQUEST)) {
                        ecr.existingRequests++
                        if (it.isScheduleCompleted) {
                            ecr.visitedExistingRequests++
                        }
                    } else if (it.scheduleOnWhichPurpose.equals(TypeOfRequest.NEW_CENTER_REQUEST)) {
                        nc.newRequests++
                        if (it.isScheduleCompleted) {
                            nc.visitedNewRequests++
                        }
                    }
                }
                 fePerformance.calculatePerformance()
                 fePerformance.calculateOverRallPerformance()
                fEPerformanceList.add(fePerformance)
            }
            return Either.right(fEPerformanceList)
        }
        return Either.left(Exception("Front-End Schedule not found for this month."))
    }

    /*private fun getBdaRequestByTypeOfRequest(typeofReq:TypeOfRequest,yearMonth:String): Either<Exception,Int>{

        val formatter = DateTimeFormatter.ofPattern("MM-yyyy")
        val ym = YearMonth.parse(yearMonth, formatter)

        var startDate: LocalDate
        var endDate: LocalDate

        startDate = LocalDate.of(ym.year, ym.month, 1)
        endDate = LocalDate.of(ym.year, ym.month, ym.month.length(ym.isLeapYear))
        var requestCount=0
        var visitedCount=0
        var requests=frontEndScheduleRepo.findAll()
        var fEschduleRequestList=ArrayList<FrontEndSchedule>()
        var fEschduleVisRequestList=ArrayList<FrontEndSchedule>()
      for(request in requests){
            if(request.schedules.filter { x->x.scheduleOnWhichPurpose.equals(TypeOfRequest.BUSSINESS_DEVELOPEMENT_ACTIVITY)&& x.scheduleDate!!.isEqual(ChronoUnit.DAYS.between(startDate, endDate). }.any()){
                fEschduleRequestList.add(request)
                requestCount++
            }
      }
        for(visRequests in fEschduleRequestList){
            if(visRequests.schedules.filter { x->x.isScheduleCompleted.equals(true) }.any()){
                fEschduleVisRequestList.add(visRequests)
                visitedCount++
            }
        }

        var per=performancePercentage(requestCount,visitedCount)
        if(per!=0){
            return Either.right(per)
        }


        return Either.left(Exception("performance is bad.."))





    }


    private fun performancePercentage(requestcount:Int,visitedRequestCount:Int):Int{


        if(requestcount!=0&&visitedRequestCount!=0) {


            var performance =  visitedRequestCount/requestcount
           return performance

        }
       return 0

    }




     private fun findFrontEndScheduleByTypeofRequestsAndDate(typeOfReq:TypeOfRequest,yearmonth:String):Either<Exception,FrontEndSchedule> {
        val formatter = DateTimeFormatter.ofPattern("MM-yyyy")
        val ym = YearMonth.parse(yearmonth, formatter)

        var startDate: LocalDate
        var endDate: LocalDate

        startDate = LocalDate.of(ym.year, ym.month, 1)
        endDate = LocalDate.of(ym.year, ym.month, ym.month.length(ym.isLeapYear))

        var frontEndScheduleList = frontEndScheduleRepo.findByYearmonthBetween(startDate, endDate)
        if (frontEndScheduleList.size > 0) {
            var frontEndSchedule = frontEndScheduleList.stream().filter {
                it.schedules.filter { x -> x.scheduleOnWhichPurpose.equals(typeOfReq) }.any()
                        && it.yearmonth.month.equals(ym.month)
            }.findAny()

            if (frontEndSchedule.isPresent) {
                var fESchedule=frontEndSchedule.get()
                return  Either.right(fESchedule)
            }
        }
       return Either.left(Exception("Schedules are not found"))
    }
*/
}
