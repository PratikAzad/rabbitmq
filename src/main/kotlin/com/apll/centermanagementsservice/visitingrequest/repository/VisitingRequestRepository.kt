package com.apll.centermanagementsservice.visitingrequest.repository

import com.apll.centermanagementsservice.center.model.CenterId
import com.apll.centermanagementsservice.visitingrequest.model.RequestType
import com.apll.centermanagementsservice.visitingrequest.model.VisitingRequest
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate

interface VisitingRequestRepository:VisitingRequestRepo {

    fun findByRequestBy(requestBy: RequestType):List<VisitingRequest>

    //fun findByResolved(resolved: Boolean):List<VisitingRequest>

    fun findByCenterId(centerId: CenterId):List<VisitingRequest>

    fun findByRequesterId(requesterId: String):List<VisitingRequest>

    @Query(nativeQuery = true, value = "SELECT vr.* FROM `visiting_request` vr " +
            "INNER JOIN `center_state` cs ON vr.`center_id`=cs.`center_id` " +
            "INNER JOIN `sub_region` sr ON sr.`sub_region_id`=cs.`subregion_id` " +
            "WHERE sr.region_id=:regionId AND vr.`resolved`=FALSE")
    fun findByRegionId(regionId: String):List<VisitingRequest>


    @Query(nativeQuery = true, value = "SELECT vr.* FROM `visiting_request` vr " +
            "INNER JOIN `center_state` cs ON vr.`center_id`=cs.`center_id` " +
            "INNER JOIN `sub_region` sr ON sr.`sub_region_id`=cs.`subregion_id` " +
            "WHERE sr.region_id=:regionId AND vr.`resolved`=TRUE ")
    fun findResolvedVisitingRequest(regionId: String):List<VisitingRequest>

    /*@Query(nativeQuery = true, value = "SELECT vr.* FROM `visiting_request` vr " +
            "INNER JOIN `center_state` cs ON vr.`center_id`=cs.`center_id` " +
            "INNER JOIN `sub_region` sr ON sr.`sub_region_id`=cs.`subregion_id` " +
            "INNER JOIN `employee_state` es ON es.`region_id`=sr.`region_id` " +
            "WHERE es.`id`=:employeeId AND vr.`resolved`=FALSE"  )
    fun findByEmployeeId(employeeId: String):List<VisitingRequest>
*/

    fun findByResolved(resolved :Boolean):List<VisitingRequest>

    //For Schedule


    fun findByRequestDateBetween(startDate: LocalDate, endDate: LocalDate):List<VisitingRequest>

    fun findByRequestDate(requestDate: LocalDate):List<VisitingRequest>


}