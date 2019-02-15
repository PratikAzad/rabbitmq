package com.apll.centermanagementsservice.centerMinimalRequirment.model

import io.vavr.control.Either
import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "center_minimal_req")
data class CenterMinimalReq(

        @EmbeddedId
        @Column(name = "center_minimal_req_id")
        var centerMinimalReqId: CenterMinimalReqId,

        @Column(name = "covered_area")
        var coveredArea:Float,

        @Column(name = "class_rooms")
        var classRooms: Int,

        @Column(name = "sitting_capacity_per_class")
        var sittingCapacityPerClass: Int,

        @Column(name = "no_of_machines")
        var noOfMachines: Int,

        @Column(name = "sitting_capacity_per_labs")
        var sittingCapacityPerLabs: Int,

        @Column(name = "no_of_lab")
        var noOfLab:Int
){
        fun idInitializer(){
                this.centerMinimalReqId=CenterMinimalReqId()
        }

}