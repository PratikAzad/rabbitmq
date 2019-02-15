package com.apll.centermanagementsservice.centrevisitreport.model

import com.apll.centermanagementsservice.config.LocalDateDeSerializer
import com.apll.centermanagementsservice.config.LocalDateSerializer
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name="running_batch_status")
class RunningBatchStatus{
    @EmbeddedId
    @Column(name="running_batch_status_id")
    var runningBatchStatusId: RunningBatchStatusId?=null

    @Column(name="batch_no")
    var batchNo:String?=null

    @Column(name = "days")
    var days:Int ?= 0

    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @Column(name = "time")
     var time: LocalDateTime?=null

    @JsonDeserialize(using = LocalDateDeSerializer::class)
    @JsonSerialize(using = LocalDateSerializer::class)
    @Column(name = "start_date")
     var startdate:LocalDate?=null

    @Column(name = "faculty_name")
    var facultyName:String?=null

    @Column(name = "faculty_mob_no")
    var facultyMobNo:String?=null

    @Column(name = "completed_module")
    var completedModule:String?=null

    @Column(name = "current_module")
     var currentModule:String?=null

    @Column(name = "no_of_studs_at_beg")
    var noOfStudsAtBeg:Int? = 0


   /* @JsonIgnore
    @JoinColumn(name="center_visit_report_id"*//*, nullable = false,columnDefinition = "VARCHAR(225)"*//*)
    @ManyToOne(cascade = arrayOf(CascadeType.ALL),optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    var centerVisitReport: CenterVisitReport? = null
        get() = field

        // setter
        set(value) {
            field = value
        }*/
    constructor(runningBatchStatusId: RunningBatchStatusId?, batchNo: String?, days: Int?, time: LocalDateTime?, startdate: LocalDate?, facultyName: String?,facultyMobNo:String?, completedModule: String?, currentModule: String?, noOfStudsAtBegng: Int?) {
        this.runningBatchStatusId = runningBatchStatusId
        this.batchNo = batchNo
        this.days = days
        this.time = time
        this.startdate = startdate
        this.facultyName = facultyName
       this.facultyMobNo=facultyMobNo
        this.completedModule = completedModule
        this.currentModule = currentModule
        this.noOfStudsAtBeg = noOfStudsAtBegng

    }
    constructor()



    fun updateRunningBatchStatus(runningBatchStatus:RunningBatchStatus){
        this.batchNo=runningBatchStatus.batchNo
        this.days=runningBatchStatus.days
        this.time=runningBatchStatus.time
        this.startdate=runningBatchStatus.startdate
        this.facultyName=runningBatchStatus.facultyName
        this.facultyMobNo=runningBatchStatus.facultyMobNo
        this.completedModule=runningBatchStatus.completedModule
        this.currentModule=runningBatchStatus.currentModule
        this.noOfStudsAtBeg=runningBatchStatus.noOfStudsAtBeg


    }
}