/*
package com.apll.centermanagementsservice.centrevisitreport.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
@Table(name = "student_feedback_report")
class StudentFeedbackReport{

    @EmbeddedId
    @Column(name = "student_feedback_Report_id")
     var studentFeedbackReportId:StudentFeedbackReportId?=null

    @Column(name = "name_of_student")
    var nameOfStudent:String?=null

    @Column(name = "id")
    var id:String?=null

    @Column(name = "batch_no")
     var batchNo:String?=null

    @Column(name ="feedback_details" )
   var feedbackDetails:String?=null
*/
/*
    @JsonIgnore
    @JoinColumn(name="center_visit_report_id"*//*
*/
/*, nullable = false,columnDefinition = "VARCHAR(225)"*//*
*/
/*)
    @ManyToOne(cascade = arrayOf(CascadeType.ALL),fetch = FetchType.LAZY,optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    var centerVisitReport: CenterVisitReport? = null
        get() = field
        set(value) {
            field = value
        }*//*

    constructor()
    constructor(studentFeedbackReportId: StudentFeedbackReportId?, nameOfStudent: String?, id: String?, batchNo: String?, feedbackDetails: String?) {
        this.studentFeedbackReportId = studentFeedbackReportId
        this.nameOfStudent = nameOfStudent
        this.id = id
        this.batchNo = batchNo
        this.feedbackDetails = feedbackDetails
    }

    fun updateStudentFeedbackReport(studentFeedbackReport:StudentFeedbackReport){
     this.nameOfStudent=studentFeedbackReport.nameOfStudent
        this.id=studentFeedbackReport.id
        this.batchNo=studentFeedbackReport.batchNo
        this.feedbackDetails=studentFeedbackReport.feedbackDetails



    }
}*/
