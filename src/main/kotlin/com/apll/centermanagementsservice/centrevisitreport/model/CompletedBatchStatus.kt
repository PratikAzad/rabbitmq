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
@Table(name = "completed_batch_status")
class CompletedBatchStatus{
    @EmbeddedId
    @Column(name="completed_batch_status_id")
     var completedBatchStatusId=CompletedBatchStatusId()


    @Column(name="batch_no")
   var batchNo:String?=null

    @Column(name="exam_conducted")
    var examConducted:Boolean? = false


    @JsonDeserialize(using = LocalDateDeSerializer::class)
    @JsonSerialize(using = LocalDateSerializer::class)
    @Column(name="conducted_on")
    var conductedOn:LocalDate?=null
        set(value){
            field=value
        }
        get() = field


    @Column(name="total_no_of_students")
    var totalNoOfStudents:Int? = 0

    @Column(name="no_of_students_appeared")
    var noOfStudentsAppeared:Int ?= 0

    @Column(name="remarks")
     var remarks:String?=null

    /*@JsonIgnore
    @JoinColumn(name="center_visit_report_id"*//*, nullable = false,columnDefinition = "VARCHAR(225)"*//*)
    @ManyToOne(cascade = arrayOf(CascadeType.ALL),fetch = FetchType.LAZY,optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    var centerVisitReport: CenterVisitReport? = null
        get() = field

        // setter
        set(value) {
            field = value
        }*/
    constructor()

    constructor(completedBatchStatusId: CompletedBatchStatusId, batchNo: String?, examConductedOn: Boolean?, conductedOn: LocalDate?, totalNoOfStudents: Int?, noOfStudentsAppeared: Int?, remarks: String?) {
        this.completedBatchStatusId = completedBatchStatusId
        this.batchNo = batchNo
        this.examConducted = examConductedOn
        this.conductedOn = conductedOn
        this.totalNoOfStudents = totalNoOfStudents
        this.noOfStudentsAppeared = noOfStudentsAppeared
        this.remarks = remarks
    }


    fun updateCompletedBatchStatus(completedBatchStatus:CompletedBatchStatus){
        this.batchNo=completedBatchStatus.batchNo
        this.examConducted=completedBatchStatus.examConducted
        this.conductedOn=completedBatchStatus.conductedOn
        this.totalNoOfStudents=completedBatchStatus.totalNoOfStudents
        this.noOfStudentsAppeared=completedBatchStatus.noOfStudentsAppeared
        this.remarks=completedBatchStatus.remarks
    }





}