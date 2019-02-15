package com.apll.centermanagementsservice.centrevisitreport.model

import com.apll.centermanagementsservice.config.LocalDateDeSerializer
import com.apll.centermanagementsservice.config.LocalDateSerializer
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name="drop_out_details")
class DropOutDetails{
    @EmbeddedId
    @Column(name="drop_out_details_id")
    var dropOutDetailsId=DropOutDetailsId()

    @Column(name="name_of_students")
     var nameOfStudents:String?=null

    /*  drop out student Id */
    @Column(name="id")
    var id:String?=null

    @Column(name="batch_no")
    var batchNo:String?=null

    @JsonDeserialize(using = LocalDateDeSerializer::class)
    @JsonSerialize(using = LocalDateSerializer::class)
    @Column(name="drop_out_date")
   var dropOutDate:LocalDate?=null
   /* get()=field
    set(value){
        field=value
    }*/

    @Column(name="remarks")
     var remarks:String?=null

  /*  @JsonIgnore
    @JoinColumn(name="center_visit_report_id"*//*, nullable = false,columnDefinition = "VARCHAR(225)"*//*)
    @ManyToOne(cascade = arrayOf(CascadeType.ALL),fetch = FetchType.LAZY,optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    var centerVisitReport: CenterVisitReport? = null
        get() = field

        // setter
        set(value) {
            field = value
        }*/

    constructor(dropOutDetailsId: DropOutDetailsId, nameOfStudents: String?, id: String?, batchNo: String?, dropOutDate: LocalDate?, remarks: String?) {
        this.dropOutDetailsId = dropOutDetailsId
        this.nameOfStudents = nameOfStudents
        this.id = id
        this.batchNo = batchNo
        this.dropOutDate = dropOutDate
        this.remarks = remarks
    }


    fun updateDropOutDetails(dropOutDetails:DropOutDetails){

        this.nameOfStudents=dropOutDetails.nameOfStudents
        this.id=dropOutDetails.id
        this.batchNo=dropOutDetails.batchNo
        this.dropOutDate=dropOutDetails.dropOutDate
        this.remarks=dropOutDetails.remarks
    }


    constructor()
}