package com.apll.centermanagementsservice.schedulemanagement.model

import io.vavr.control.Either
import java.time.LocalDate



class MonthWiseSchedule {
    lateinit var yearMonth:LocalDate
    var frontEndSchedules: List<FrontEndSchedule> = ArrayList()

    constructor()


    constructor(yearMonth: LocalDate, frontEndSchedules: List<FrontEndSchedule>) {
        this.yearMonth = yearMonth
        this.frontEndSchedules = frontEndSchedules
    }

    constructor(frontEndSchedules: List<FrontEndSchedule>) {
        this.frontEndSchedules = frontEndSchedules
    }

    fun scheduleValidation(frontEndSchedule: FrontEndSchedule):Either<Exception,Boolean>{

        //Existing All Schedule of EXISTING_CENTER_REQUEST, EXISTING_CENTER
        var oldScheduleRequestId=ArrayList<String>()
        this.frontEndSchedules.forEach {

            it.schedules.filter { x ->
                x.scheduleOnWhichPurpose.equals(TypeOfRequest.EXISTING_CENTER_REQUEST) ||
                        x.scheduleOnWhichPurpose.equals(TypeOfRequest.EXISTING_CENTER)}
                    .map { x ->oldScheduleRequestId.add(x.requestId!!)}
        }

        //Schedule RequestId for Create Front-End-Schedule
        var newSchedule: List<Schedule>
        if(frontEndSchedule.frontEndScheduleId.frontEndScheduleId.equals("")) {
            newSchedule = frontEndSchedule.schedules.
                    filter { x -> (x.scheduleOnWhichPurpose.equals(TypeOfRequest.EXISTING_CENTER_REQUEST) ||
                            x.scheduleOnWhichPurpose.equals(TypeOfRequest.EXISTING_CENTER)) }.
                    map { x -> x }.toList()
        }
        else{
            //Schedule for Update Front-End-Schedule
            newSchedule = frontEndSchedule.schedules.filter {x->x.scheduleId.scheduleId.equals("")}.
                    filter { x -> (x.scheduleOnWhichPurpose.equals(TypeOfRequest.EXISTING_CENTER_REQUEST) ||
                            x.scheduleOnWhichPurpose.equals(TypeOfRequest.EXISTING_CENTER)) }.
                    map { x -> x}.toList()
        }


        var conflictSchedule=newSchedule.filter { x->oldScheduleRequestId.contains(x.requestId)}.toList()

        if(conflictSchedule.size>0){
            var errorMsg:String="Schedule on Duplicate Request:: "
            conflictSchedule.forEach{
                errorMsg=errorMsg+" Schedule-Date :: "+it.scheduleDate+" Schedule-Details :: "+it.note
            }
            return Either.left(Exception(errorMsg))
        }
        return Either.right(true)
    }

    fun existingRequestId():List<String>{
        var requestIds=ArrayList<String>()
        this.frontEndSchedules.forEach{
            it.schedules.
                    filter { x->x.scheduleOnWhichPurpose.
                            equals(TypeOfRequest.EXISTING_CENTER_REQUEST)}
                    .map{x->requestIds.add(x.requestId!!)}.toSet()
        }
        return requestIds

    }
}