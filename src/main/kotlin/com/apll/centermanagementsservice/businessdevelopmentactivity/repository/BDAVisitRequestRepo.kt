package com.apll.centermanagementsservice.businessdevelopmentactivity.repository

import com.apll.centermanagementsservice.businessdevelopmentactivity.model.BDAVisitRequest
import com.apll.centermanagementsservice.businessdevelopmentactivity.model.BDAVisitRequestId
import com.apll.centermanagementsservice.regionAndsubRegion.model.RegionId
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDate
import java.util.*


interface BDAVisitRequestRepo:JpaRepository<BDAVisitRequest,BDAVisitRequestId> {


        //@EntityGraph(attributePaths = arrayOf("bdaReports","bdaReports.bdaVisitImageList"))
        fun findByFrontEndId(frontEndId:String): List<BDAVisitRequest>

        //@EntityGraph(attributePaths = arrayOf("bdaReports","bdaReports.bdaVisitImageList"))
        /*@Query(nativeQuery = true, value = "SELECT bdavr.* FROM `bda_visit_request` bdavr  WHERE  bdavr.`start_date`>=:srtDate AND " +
                 "bdavr.`start_date`<=:edDate AND bdavr.`front_end_id`=:frontEndId")*/
        fun findByStartDateBetweenAndFrontEndId(@Param("srtDate")srtDate:LocalDate,
                                                @Param("edDate")edDate:LocalDate,
                                                @Param("frontEndId")frontEndId:String):List<BDAVisitRequest>


        //@EntityGraph(attributePaths = arrayOf("bdaReports","bdaReports.bdaVisitImageList"))
        @Query(nativeQuery = true, value = "SELECT bdavr.* FROM `bda_visit_request` bdavr" +
                "    INNER JOIN `center_state` cs ON bdavr.`center_id`=cs.`center_id`" +
                "    INNER JOIN `sub_region` sr ON sr.`sub_region_id`=cs.`subregion_id` " +
                "    WHERE sr.region_id=:regionId AND bdavr.`start_date`>=:srtDate AND bdavr.`start_date`<= :edDate")
        fun findBybdaDateAndRegionId(@Param("regionId")regionId: String,
                                     @Param("srtDate")srtDate: LocalDate,
                                     @Param("edDate")edDate:LocalDate):List<BDAVisitRequest>


        @EntityGraph(attributePaths = arrayOf("bdaReports","bdaReports.bdaVisitImageList"))
        override fun findAll(): List<BDAVisitRequest>


        //@EntityGraph(attributePaths = arrayOf("bdaReports","bdaReports.bdaVisitImageList"))
        @Query(value = "SELECT bdavr.*,cs.`center_name`,cs.`subregion_id` FROM `bda_visit_request` bdavr\n" +
                "    INNER JOIN `center_state` cs ON bdavr.`center_id`=cs.`center_id`\n" +
                "    INNER JOIN `sub_region` sr ON sr.`sub_region_id`=cs.`subregion_id` WHERE sr.region_id=:regionId",
                nativeQuery = true)
        fun dummy(regionId: RegionId):List<BDAVisitRequest>





        /*  @Query(nativeQuery = true, value = "SELECT bdavr.* FROM `bda_visit_report` bdavr"+
              " where bdave.`bda_visit_request_id` AND bdavr.`bda_visit_report_date`>=:srtDate AND bdavr.`bda_visit_report_date`<=:edDate")
         fun  findByReportbdaVisitReportDateBetween(@Param("srtDate")srtDate: LocalDate,
                                                    @Param("edDate")edDate:LocalDate,
                                                    @Param("bdaRequestId")bdaRequestId:String):List<BDAVisitRequest>*/



        //  fun findByStartDateBetweenAndbdaVisitRequestId(@Param("srtDate")srtDate:LocalDate,@Param("edDate")edDate:LocalDate,@Param("bdaVisitRequestId")bdaVisitRequestId:String):List<BDAVisitRequest>

        //     fun findByDaysBetweenDates(from: LocalDate, to: LocalDate):List<BDAVisitRequest>

    }


