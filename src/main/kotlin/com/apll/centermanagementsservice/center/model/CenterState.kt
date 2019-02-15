package com.apll.centermanagementsservice.center.model

import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.time.LocalDate
import javax.persistence.*



@Entity
@Table(name = "center_state")
data class CenterState (

        @EmbeddedId
        @Column(name = "center_id",length = 32)
        var centerId:CenterId,

        @Column(name = "center_name")
        var centerName:String,


        @Column(name = "name_of_owner")
        var nameOfOwner:String,

        @Column(name = "contact_number")
        var contactNumber:String,

        @Column(name = "alternate_number")
        var alternateNumber:String,

        @Column(name = "house_no")
        var houseNumber:String,

        @Column(name = "street_name")
        var streetName:String,


        @Column(name = "area_name")
        var areaName:String,

        @Column(name = "landmark")
        var landmark:String,

        @Column(name = "city")
        var city:String,


        @Column(name = "pin_code")
        var pinCode:String="",

        @Column(name = "state")
        var state:String,

        @Column(name = "courier_service")
        var courierService:String,

        @Column(name = "email_id")
        var emailId:String,

        @Column(name = "pan_details")
        var panDetails:String,

        @Column(name = "center_establish_year")
        var centerEstablishYear: LocalDate,

        @Column(name = "service_tax_number")
        var serviceTaxNumber:String,

        @Column(name = "center_type")
        @Enumerated(EnumType.STRING)
        var centerType: CenterType,

        @Column(name = "center_status")
        @Enumerated(EnumType.STRING)
        var centerStatus: CenterStatus,

        @OneToMany(cascade = arrayOf(CascadeType.ALL),fetch = FetchType.EAGER)
        @Fetch(value = FetchMode.SUBSELECT)
        @JoinTable(name = "center_state_faculty",
                joinColumns = arrayOf(JoinColumn(name = "center_id")),
                inverseJoinColumns = arrayOf(JoinColumn(name = "faculty_id")))
        var facultyList: Set<FacultyState>,


        @JoinColumn(name="infrastructure_id")
        @OneToOne(fetch = FetchType.EAGER,cascade = arrayOf(CascadeType.ALL))
        var infrastructureDetails: InfrastructureDetails,


        @Column(name="subregion_id")
        var subregionId: String

        /*@JoinColumn(name="subregion_id")
        @OneToOne(fetch = FetchType.EAGER,cascade = arrayOf(CascadeType.ALL),targetEntity = SubRegionId::class)
        var subRegionId: SubRegionId
*/

) {

    override fun toString(): String {
        return "CenterState(centerId=$centerId)"
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    fun idInitializer() {
        this.centerId=CenterId()
        this.infrastructureDetails.infrastructureDetailsId=InfrastructureDetailsId()
        this.facultyList.forEach {
            it.facultyId=FacultyId()

        }
    }


    fun updateCenterState(centerState: CenterState){
        this.centerName=centerState.centerName
        this.contactNumber=centerState.contactNumber
        this.alternateNumber=centerState.alternateNumber
        this.houseNumber=centerState.houseNumber
        this.streetName=centerState.streetName
        this.areaName=centerState.areaName
        this.landmark=centerState.landmark
        this.city=centerState.city
        this.pinCode=centerState.pinCode
        this.state=centerState.state
        this.courierService=centerState.courierService
        this.emailId=centerState.emailId
        this.centerEstablishYear=centerState.centerEstablishYear
        this.centerType=centerState.centerType
        this.centerStatus=centerState.centerStatus
    }



}