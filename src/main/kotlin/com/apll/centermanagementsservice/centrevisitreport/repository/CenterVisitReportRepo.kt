package com.apll.centermanagementsservice.centrevisitreport.repository

import com.apll.centermanagementsservice.centrevisitreport.model.CenterVisitReport
import com.apll.centermanagementsservice.centrevisitreport.model.CenterVisitReportId
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import java.time.LocalDate
import java.util.*

interface CenterVisitReportRepo:JpaRepository<CenterVisitReport,CenterVisitReportId>{

    @EntityGraph(attributePaths = arrayOf("dropOutDetails",
            "runningBatchStatus",
            "stockStatus",
            "completedBatchStatus",
            "todoForCenter.todoListCenterList",
            "todoForApll.todoListApllList",
            "infrastructure"))
    fun findByFrontEndId(frontEndId:String):List<CenterVisitReport>

    /* @Query( nativeQuery = true,value = "SELECT cvr.* FROM `center_visit_report` cvr" +
         " WHERE cvr.`center_visit_report_date`>=:startDate   AND  cvr.`center_visit_report_date`<=:endDate AND cvr.`center_id`=:centerId")*/

    @EntityGraph(attributePaths = arrayOf("dropOutDetails",
            "runningBatchStatus",
            "stockStatus",
            "completedBatchStatus",
            "todoForCenter.todoListCenterList",
            "todoForApll.todoListApllList",
            "infrastructure"))

    fun findByCenterIdAndCenterVisitReportDateBetween (@Param("centerId")centerId:String,
                                                       @Param("startDate")startDate:LocalDate,
                                                       @Param("endDate")endDate:LocalDate):List<CenterVisitReport>

    /* @Query( nativeQuery = true,value = "SELECT cvr.* FROM `center_visit_report` cvr" +
          " WHERE cvr.`center_visit_report_date`>=:startDate   AND  cvr.`center_visit_report_date`<=:endDate AND cvr.`front_end_id`=:frontEndId")
    fun findMonthReportByCenterVisitReportDateBetween(
                              @Param("startDate")startDate:LocalDate,
                              @Param("endDate")endDate:LocalDate,
                              @Param("frontEndId")frontEndId:String):List<CenterVisitReport>*/


    @EntityGraph(attributePaths = arrayOf("dropOutDetails",
            "runningBatchStatus",
            "stockStatus",
            "completedBatchStatus",
            "todoForCenter.todoListCenterList",
            "todoForApll.todoListApllList",
            "infrastructure"))

    fun findByCenterVisitReportDateBetweenAndFrontEndId(@Param("startDate")startDate:LocalDate,
                                                        @Param("endDate")endDate:LocalDate,
                                                        @Param("frontEndId")frontEndId:String):List<CenterVisitReport>

    @EntityGraph(attributePaths = arrayOf("dropOutDetails",
            "runningBatchStatus",
            "stockStatus",
            "completedBatchStatus",
            "todoForCenter.todoListCenterList",
            "todoForApll.todoListApllList",
            "infrastructure"))
    override fun findAll(): List<CenterVisitReport>


    @EntityGraph(attributePaths = arrayOf("dropOutDetails",
            "runningBatchStatus",
            "stockStatus",
            "completedBatchStatus",
            "todoForCenter.todoListCenterList",
            "todoForApll.todoListApllList",
            "infrastructure"))

    override fun findById(p0: CenterVisitReportId): Optional<CenterVisitReport>

    /* @Query(nativeQuery = true,value = "SELECT cvr.* FROM `center_visit_report` cvr\n" +
             "\n" +
             "WHERE cvr.`todo_for_center_id`=:todoforCenterId AND cvr.'center_visit_report_id'=:cvrId")
     fun editStatusForTodoForcenter(@Param("todoforCenterId")todoforCenterId:String,
                                    @Param("cvrId")cvrId:String):CenterVisitReport
 */
}
