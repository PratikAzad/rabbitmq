package com.apll.centermanagementsservice.schedulemanagement.repository

import com.apll.centermanagementsservice.schedulemanagement.model.FrontEndSchedule
import com.apll.centermanagementsservice.schedulemanagement.model.FrontEndScheduleEventId
import com.apll.centermanagementsservice.schedulemanagement.model.FrontEndScheduleId
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate
import java.util.*

interface FrontEndScheduleRepo:JpaRepository<FrontEndSchedule,FrontEndScheduleEventId>{


    @EntityGraph(attributePaths = arrayOf("schedules"))
    fun findByYearmonthBetween(startDate :LocalDate,endDate :LocalDate): List<FrontEndSchedule>

    @EntityGraph(attributePaths = arrayOf("schedules"))
    fun findByYearmonthBetweenAndFrontEndId(startDate :LocalDate,endDate :LocalDate,frontEndId:String): List<FrontEndSchedule>

    @EntityGraph(attributePaths = arrayOf("schedules"))
    fun findByFrontEndScheduleId(frontEndScheduleId: FrontEndScheduleId): List<FrontEndSchedule>

    @Query("SELECT MAX(version) FROM FrontEndSchedule WHERE " +
            "frontEndScheduleId=:frontEndScheduleId")
    fun maxVersion(frontEndScheduleId:FrontEndScheduleId):Int

    @Query("SELECT fes FROM FrontEndSchedule fes WHERE frontEndId=:frontEndId")
    @EntityGraph(attributePaths = arrayOf("schedules"))
    fun byFrontEndId(frontEndId: String):List<FrontEndSchedule>

    @EntityGraph(attributePaths = arrayOf("schedules"))
    fun findByVersionAndFrontEndScheduleId(version:Int,frontEndScheduleId: FrontEndScheduleId):Optional<FrontEndSchedule>

    @EntityGraph(attributePaths = arrayOf("schedules"))
    override fun findAll():List<FrontEndSchedule>
}




