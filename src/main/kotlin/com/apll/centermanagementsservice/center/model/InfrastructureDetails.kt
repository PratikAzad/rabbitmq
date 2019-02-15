package com.apll.centermanagementsservice.center.model


import javax.persistence.*


@Entity
@Table(name = "infrastructure")
data class InfrastructureDetails (

        @EmbeddedId
    @Column(name = "infrastructure_details_id")
    var infrastructureDetailsId: InfrastructureDetailsId,

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


 /*   @OneToOne(mappedBy = "infrastructureDetails")
    var center: CenterState*/


) {
    override fun toString(): String {
        return "InfrastructureDetails(infrastructureDetailsId=$infrastructureDetailsId)"
    }

    fun updateInfrastructureDetails(infrastructureDetails: InfrastructureDetails) {

        this.coveredArea=infrastructureDetails.coveredArea
        this.classRooms=infrastructureDetails.classRooms
        this.noOfMachines=infrastructureDetails.noOfMachines
        this.sittingCapacityPerClass=infrastructureDetails.sittingCapacityPerClass
        this.sittingCapacityPerLabs=infrastructureDetails.sittingCapacityPerLabs
        this.noOfLab=infrastructureDetails.noOfLab


    }
}
