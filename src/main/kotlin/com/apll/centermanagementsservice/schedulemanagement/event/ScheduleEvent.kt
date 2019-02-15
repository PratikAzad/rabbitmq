package com.apll.centermanagementsservice.schedulemanagement.event

import com.apll.centermanagementsservice.schedulemanagement.model.FrontEndSchedule
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import java.time.LocalDate
import javax.persistence.EnumType
import javax.persistence.Enumerated

class ScheduleEvent {
    var feScheduleId:String?=null
    var frontEndId:String?=null

    @JsonDeserialize(using = LocalDateDeserializer::class)
    @JsonSerialize(using = LocalDateSerializer::class)
    var yearMonth: LocalDate?=null

    var operationType:OperationType?=null

    constructor()
    constructor(feScheduleId: String?,
                frontEndId: String?,
                yearMonth: LocalDate?) {
        this.feScheduleId = feScheduleId
        this.frontEndId = frontEndId
        this.yearMonth = yearMonth
    }

    constructor(feSchedule: FrontEndSchedule,type:OperationType){
        this.feScheduleId=feSchedule.frontEndScheduleId.frontEndScheduleId
        this.frontEndId=feSchedule.frontEndId
        this.yearMonth= LocalDate.of(feSchedule.yearmonth.year,feSchedule.yearmonth.month,1)
        this.operationType=type
    }

    override fun toString(): String {
        return "ScheduleEvent(feScheduleId=$feScheduleId, " +
                "frontEndId=$frontEndId, yearMonth=$yearMonth, " +
                "operationType=$operationType)"
    }


}