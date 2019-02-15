package com.apll.centermanagementsservice.schedulemanagement.service

import com.apll.centermanagementsservice.schedulemanagement.model.ScheduleId
import com.apll.centermanagementsservice.schedulemanagement.model.dto.*
import io.vavr.control.Either
import java.time.LocalDate

interface IFrontEndScheduleService {

    fun insertFrontEndSchedule(frontEndScheduleDTO: FrontEndScheduleDTO):Either<Exception, String>

    fun getAllFrontEndSchedule():Either<Exception,List<FrontEndScheduleDayWiseDTO>>

    fun getByFrontEndScheduleId(frontEndScheduleId: String):Either<Exception,FrontEndScheduleDayWiseDTO>

    //fun editFrontEndSchedule(frontEndScheduleDTO: FrontEndScheduleDTO):Either<Exception,FrontEndScheduleDayWiseDTO>

    fun getByFrontEndId(frontEndId:String):Either<Exception,List<FrontEndScheduleDayWiseDTO>>

    fun updateSchedule(dto: ScheduleUpdateDTO):Either<Exception,String>

    //fun getFrontEndScheduleByIdAndDate(yearMonth:String,frontEndId: String):Either<Exception, FrontEndScheduleDayWiseDTO>

    fun approveFrontEndSchedule(frontEndScheduleId: String):Either<Exception, String>

    fun last2ScheduleByFrontEndScheduleId(frontEndScheduleId:String): Either<Exception, List<FrontEndScheduleDayWiseDTO>>

    fun getFrontEndScheduleByYearMonth(frontEndId: String,yearmonth :String): Either<Exception, FrontEndScheduleDayWiseDTO>

    fun latestAndApprovedByYearMonth(frontEndId: String,yearmonth :String):
            Either<Exception,List<FrontEndScheduleDayWiseDTO>>

    fun latestForFrontEndAndHeadAdmin(frontEndId: String,yearmonth: String):Either<Exception,List<FrontEndScheduleDayWiseDTO>>

    fun getExitingVisitRequestId():Either<Exception,List<String>>

    fun rejectFrontEndSchedule(frontEndScheduleId: String,rejectedDto: RejectedDto):Either<Exception, String>

    fun findFrontEndScheduleByDates(startDate: LocalDate,endDate: LocalDate): List<String>

    fun bdaRequestIdByFEId(fEId:String):List<String>

    fun scheduleCompleted(completedScheduleDTO: CompletedScheduleDTO):Either<Exception,String>
    fun bdaScheduleCompleted(completedScheduleDTO: CompletedScheduleDTO):Either<Exception,String>
    fun customScheduleCompleted(completedCustomScheduleDTO: CompletedCustomScheduleDto):Either<Exception,String>

    }