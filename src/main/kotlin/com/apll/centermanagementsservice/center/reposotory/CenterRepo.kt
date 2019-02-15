package com.apll.centermanagementsservice.center.reposotory

import com.apll.centermanagementsservice.center.model.CenterId
import com.apll.centermanagementsservice.center.model.CenterState
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CenterRepo:JpaRepository<CenterState, CenterId>{

      @EntityGraph(attributePaths = arrayOf("facultyList","infrastructureDetails"))
      fun findBySubregionId(subregionId:String):Set<CenterState>


      @EntityGraph(attributePaths = arrayOf("facultyList","infrastructureDetails"))
      override fun findAll(): List<CenterState>


    @EntityGraph(attributePaths = arrayOf("facultyList", "infrastructureDetails"))
    override fun findById(p0: CenterId): Optional<CenterState>



     //fun findByCenterIdAndCenterType(centerId:String,centerType:String):Optional<CenterState>


     /*@Query(nativeQuery = true,value = "SELECT cs.* FROM `center_state` cs\n" +
             "INNER JOIN `sub_region` sr ON cs.`subregion_id`=sr.`sub_region_id`\n" +
             "INNER JOIN `employee_state` es ON es.`region_id`=sr.`region_id` WHERE es.`id`=:employeeId")
     fun findByEmployeeId(employeeId: String):List<CenterState>*/

     /*@Query("SELECT cen.`center_id`,cen.`center_name` FROM `center_state` cen")
     fun getAllCenterDetails():Set<CenterDetails>*/
}