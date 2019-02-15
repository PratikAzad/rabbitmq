package com.apll.centermanagementsservice.centrevisitreport.model

import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "center_visit_report")
class CenterVisitReport {

    @EmbeddedId
    @Column(name="center_visit_report_id")
    var centerVisitReportId: CenterVisitReportId?=null

    @Column(name = "center_id")
    var centerId: String?=null

    @Column(name = "front_end_id")
    var  frontEndId:String?=null

    @Column(name="request_id")
    var requestId:String?=null

    @Column(name="front_end_schedule_id")
    var frontEndScheduleId:String?=null

    @Column(name="schedule_Date")
    var scheduleDate:LocalDate?=null


    @Column(name="center_visit_report_date")
    var centerVisitReportDate: LocalDate?=null



    @OrderColumn(name = "drop_out_details_order")
    @OneToMany(cascade = arrayOf(CascadeType.ALL),fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "center_visit_report_drop_out_details",
            joinColumns = arrayOf(JoinColumn(name = "center_visit_report_id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "drop_out_details_id")))
    var dropOutDetails:List<DropOutDetails>?=null



    @OrderColumn(name = "running_batch_status_order")
    @OneToMany(cascade = arrayOf(CascadeType.ALL),fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "center_visit_report_running_batch_status",
            joinColumns = arrayOf(JoinColumn(name = "center_visit_report_id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "running_batch_status_id")))
    var runningBatchStatus:List<RunningBatchStatus>?=null


    @OrderColumn(name = "stock_status_order")
    @OneToMany(cascade = arrayOf(CascadeType.ALL),fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "center_visit_report_stock_status",
            joinColumns = arrayOf(JoinColumn(name = "center_visit_report_id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "stock_status_id")))
    var stockStatus:List<StockStatus>?=null


    @OrderColumn(name = "completed_batch_status_order")
    @OneToMany(cascade = arrayOf(CascadeType.ALL),fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "center_visit_report_completed_batch_status",
            joinColumns = arrayOf(JoinColumn(name = "center_visit_report_id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "completed_batch_status_id")))
    var completedBatchStatus:List<CompletedBatchStatus>?=null

    /*@OrderColumn(name = "student_feedback_report_order")
    @OneToMany(cascade = arrayOf(CascadeType.ALL),fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "center_visit_report_student_feedback_report",
            joinColumns = arrayOf(JoinColumn(name = "center_visit_report_id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "student_feedback_Report_id")))
    var studentFeedbackReport:List<StudentFeedbackReport>?=null*/


    @OneToOne(cascade = arrayOf(CascadeType.ALL),fetch = FetchType.EAGER)
    @OrderColumn(name = "todo_center_order")
    @JoinTable(name = "center_visit_report_todo_for_center",
            joinColumns = arrayOf(JoinColumn(name = "center_visit_report_id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "todo_for_center_id")))
    var todoForCenter:TodoForCenter?=null

    @OrderColumn(name = "todo_apll_order")
    @OneToOne(cascade = arrayOf(CascadeType.ALL),fetch = FetchType.EAGER)
    @JoinTable(name = "center_visit_report_todo_for_apll",
            joinColumns = arrayOf(JoinColumn(name = "center_visit_report_id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "todo_for_apll_id")))
    var todoForApll:TodoForApll?=null



    @OrderColumn(name = "cvr_infrastructure_order")
    @OneToMany(cascade = arrayOf(CascadeType.ALL),fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "center_visit_report_cvr_infrastructure",
            joinColumns = arrayOf(JoinColumn(name = "center_visit_report_id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "cvr_infrastructure_id")))
    var infrastructure:List<CvrInfrastructure>?=null

    /*@JoinColumn(name="center_feedback_form_id")
    @OneToOne(fetch = FetchType.EAGER,cascade = arrayOf(CascadeType.ALL))
    var centerFeedbackForm: CenterFeedbackForm,
*/

    @Enumerated(EnumType.STRING)
    @Column(name="center_visit_report_status")
    var centerVisitReportStatus:CenterVisitReportStatus=CenterVisitReportStatus.DEFAULT


    constructor()

    constructor(centerVisitReportId: CenterVisitReportId?, centerId: String?, frontEndId: String?, requestId: String?, frontEndScheduleId: String?, scheduleDate:LocalDate?, centerVisitReportDate: LocalDate?, dropOutDetails: List<DropOutDetails>?, runningBatchStatus: List<RunningBatchStatus>?, stockStatus: List<StockStatus>?, completedBatchStatus: List<CompletedBatchStatus>?, todoForCenter: TodoForCenter?, todoForApll: TodoForApll?, infrastructure:List<CvrInfrastructure>?, centerVisitReportStatus: CenterVisitReportStatus) {
        this.centerVisitReportId = centerVisitReportId
        this.centerId = centerId
        this.frontEndId = frontEndId
        this.requestId = requestId
        this.frontEndScheduleId = frontEndScheduleId
        this.scheduleDate=scheduleDate
        this.centerVisitReportDate = centerVisitReportDate
        this.dropOutDetails = dropOutDetails
        this.runningBatchStatus = runningBatchStatus
        this.stockStatus = stockStatus
        this.completedBatchStatus = completedBatchStatus

        this.todoForCenter = todoForCenter
        this.todoForApll = todoForApll
        this.infrastructure=infrastructure
        this.centerVisitReportStatus = centerVisitReportStatus
    }
    constructor(centerVisitReportId: CenterVisitReportId?, dropOutDetails: List<DropOutDetails>?, runningBatchStatus: List<RunningBatchStatus>?, stockStatus: List<StockStatus>?, completedBatchStatus:List<CompletedBatchStatus>?, todoForCenter: TodoForCenter?, todoForApll: TodoForApll?, infrastructure:List<CvrInfrastructure>?, centerVisitReportStatus: CenterVisitReportStatus) {
        this.centerVisitReportId = centerVisitReportId
        this.dropOutDetails = dropOutDetails
        this.runningBatchStatus = runningBatchStatus
        this.stockStatus = stockStatus
        this.completedBatchStatus = completedBatchStatus

        this.todoForCenter = todoForCenter
        this.todoForApll = todoForApll
        this.infrastructure=infrastructure

        this.centerVisitReportStatus = centerVisitReportStatus
    }




    fun idInitializer() {
        this.centerVisitReportId = CenterVisitReportId()


        this.dropOutDetails?.forEach {

            it.dropOutDetailsId = DropOutDetailsId()

        }

        this.runningBatchStatus!!.forEach {
            it.runningBatchStatusId=RunningBatchStatusId()
        }

        this.stockStatus?.forEach {
            it.stockStatusId = StockStatusId()

        }
        this.completedBatchStatus?.forEach {
            it.completedBatchStatusId = CompletedBatchStatusId()

        }

        this.todoForCenter?.idInitializer()


        this.todoForApll?.idInitializer()

        this.infrastructure!!.forEach {
            it.cvrInfrastructureId=CvrInfrastructureId()

        }

    }

    private fun dodUpdate(dropOutDetailsList:List<DropOutDetails>?):Boolean?{
        if(dropOutDetailsList!=null) {


            var updateDropOutDetailsIds = HashSet<String>()

            //Create Drop-Out-Details
            var newCreateDropOutDetails = ArrayList<DropOutDetails>()
            for (dob in dropOutDetailsList) {
                if (dob.dropOutDetailsId.dropOutDetailsId != null && dob.dropOutDetailsId.dropOutDetailsId.equals("")) {
                    dob.dropOutDetailsId = DropOutDetailsId()
                    newCreateDropOutDetails.add(dob)
                }

            }

            var mapDropOutDetails: Map<String, DropOutDetails> = dropOutDetailsList.map { x -> x.dropOutDetailsId.dropOutDetailsId to x }.toMap()

            //Update Drop-Out-Details
            this.dropOutDetails!!.filter { x -> mapDropOutDetails.containsKey(x.dropOutDetailsId.dropOutDetailsId) }.toList().forEach { x ->
                updateDropOutDetailsIds.add(x.dropOutDetailsId.dropOutDetailsId)
                x.updateDropOutDetails(mapDropOutDetails.get(x.dropOutDetailsId.dropOutDetailsId)!!)
            }


            //Delete Drop-Out-Details
            this.dropOutDetails = this.dropOutDetails!!.filter { x -> updateDropOutDetailsIds.contains(x.dropOutDetailsId!!.dropOutDetailsId) }

            if (newCreateDropOutDetails != null && newCreateDropOutDetails.size > 0) {

                var list = ArrayList(this.dropOutDetails)
                list.addAll(newCreateDropOutDetails)
                this.dropOutDetails = list
            }

            return true
        }
        return null
    }

    private fun rbsUpdate(runningBatchStatusList:List<RunningBatchStatus>):Boolean{

        var updateRunningBatchStatusIds=HashSet<String>()
        var newRunningBatchStatus=ArrayList<RunningBatchStatus>()
        //Create Running-Batch-Status
        for(rbs in runningBatchStatusList){
            if(rbs.runningBatchStatusId!!.runningBatchStatusId!=null&&rbs.runningBatchStatusId!!.runningBatchStatusId.equals("")){
                rbs.runningBatchStatusId= RunningBatchStatusId()
                newRunningBatchStatus.add(rbs)
            }
        }
        var runningBatchStatusMap:Map<String,RunningBatchStatus> = runningBatchStatusList.map { x->x.runningBatchStatusId!!.runningBatchStatusId to x }.toMap()
        //Update  Running-Batch-Status
        this.runningBatchStatus!!.filter { x->runningBatchStatusMap.containsKey(x.runningBatchStatusId!!.runningBatchStatusId) }.toList().
                forEach { x ->
                    updateRunningBatchStatusIds.add(x.runningBatchStatusId!!.runningBatchStatusId)
                    x.updateRunningBatchStatus(runningBatchStatusMap.get(x.runningBatchStatusId!!.runningBatchStatusId)!!)

                }
        //Delete  Running-Batch-Status
        this.runningBatchStatus=this.runningBatchStatus!!.
                filter { x->updateRunningBatchStatusIds.contains(x.runningBatchStatusId!!.runningBatchStatusId) }

        if(newRunningBatchStatus!=null&&newRunningBatchStatus.size>0) {
            var rbsList = ArrayList(this.runningBatchStatus)
            rbsList.addAll(newRunningBatchStatus)
            this.runningBatchStatus=rbsList
        }

        return true
    }


    private fun sStatusUpdate(stockStatusList:List<StockStatus>?):Boolean?{

        if(stockStatusList!=null) {
            var updateStockStatusIds = HashSet<String>()
            //Create Stock-Status
            var newStockStatus = ArrayList<StockStatus>()
            for (ss in stockStatusList) {
                if (ss.stockStatusId.stockStatusId != null && ss.stockStatusId.stockStatusId.equals("")) {
                    ss.stockStatusId = StockStatusId()
                    newStockStatus.add(ss)
                }
            }
            var stockStatusMap: Map<String, StockStatus> = stockStatusList.map { x -> x.stockStatusId.stockStatusId to x }.toMap()
            //Update Stock Status
            this.stockStatus!!.filter { x -> stockStatusMap.containsKey(x.stockStatusId.stockStatusId) }.toList().forEach { x ->
                updateStockStatusIds.add(x.stockStatusId.stockStatusId)
                x.updateStockStatus(stockStatusMap.get(x.stockStatusId.stockStatusId)!!)
            }
            //Delete Stock Status
            this.stockStatus = this.stockStatus!!.filter { x -> updateStockStatusIds.contains(x.stockStatusId.stockStatusId) }

            if (newStockStatus != null && newStockStatus.size > 0) {
                var stockList = ArrayList(this.stockStatus)
                stockList.addAll(newStockStatus)
                this.stockStatus = stockList
            }

            return true
        }
        return null

    }
    private fun  cbsUpdate(completedBatchStatusList:List<CompletedBatchStatus>?):Boolean?
    {
        if(completedBatchStatusList!=null) {
            var updateCompleteBatchStatusIds = HashSet<String>()
            //Create Complete Batch Status
            var newCompleteBatchStatus = ArrayList<CompletedBatchStatus>()
            for (cbs in completedBatchStatusList) {
                if (cbs.completedBatchStatusId.completedBatchStatusId != null && cbs.completedBatchStatusId.completedBatchStatusId.equals("")) {
                    cbs.completedBatchStatusId = CompletedBatchStatusId()
                    newCompleteBatchStatus.add(cbs)
                }
            }
            var completeBatchStatusMap: Map<String, CompletedBatchStatus> = completedBatchStatusList.map { x -> x.completedBatchStatusId.completedBatchStatusId to x }.toMap()
            //Update Complete Batch Status
            this.completedBatchStatus!!.filter { x -> completeBatchStatusMap.containsKey(x.completedBatchStatusId.completedBatchStatusId) }.toList().forEach { x ->
                updateCompleteBatchStatusIds.add(x.completedBatchStatusId.completedBatchStatusId)
                x.updateCompletedBatchStatus(completeBatchStatusMap.get(x.completedBatchStatusId.completedBatchStatusId)!!)
            }

            //Delete  Complete Batch Status
            this.completedBatchStatus = this.completedBatchStatus!!.filter { x -> updateCompleteBatchStatusIds.contains(x.completedBatchStatusId!!.completedBatchStatusId) }

            if (newCompleteBatchStatus != null && newCompleteBatchStatus.size > 0) {
                var cbsList = ArrayList(this.completedBatchStatus)
                cbsList.addAll(newCompleteBatchStatus)
                this.completedBatchStatus = cbsList
            }
            return true

        }
        return null
    }



  /*  private fun sfrUpdate(studentFeedbackReportList:List<StudentFeedbackReport>):Boolean{
        var updateStudentFeedBackReportIds=HashSet<String>()

        //Create Student-Feedback-Report
        var newStudentFeedbackReport=ArrayList<StudentFeedbackReport>()

        for(sfr in studentFeedbackReportList){
            if(sfr.studentFeedbackReportId!!.studentFeedbackReportId!=null&&sfr.studentFeedbackReportId!!.studentFeedbackReportId.equals("")){
                sfr.studentFeedbackReportId= StudentFeedbackReportId()
                newStudentFeedbackReport.add(sfr)

            }
        }
        var studentFeedBackReportMap:Map<String,StudentFeedbackReport> = studentFeedbackReportList.map { x->x.studentFeedbackReportId!!.studentFeedbackReportId to x }.toMap()
        //Update Student-Feedback-Report
        this.studentFeedbackReport!!.filter {x->studentFeedBackReportMap.containsKey(x.studentFeedbackReportId!!.studentFeedbackReportId)  }.toList().
                forEach { x->
                    updateStudentFeedBackReportIds.add(x.studentFeedbackReportId!!.studentFeedbackReportId)
                    x.updateStudentFeedbackReport(studentFeedBackReportMap.get(x.studentFeedbackReportId!!.studentFeedbackReportId)!!)
                }
        //Delete Student-Feedback-Report
        this.studentFeedbackReport=this.studentFeedbackReport!!.filter { x->updateStudentFeedBackReportIds.contains(x.studentFeedbackReportId!!.studentFeedbackReportId) }

        if (newStudentFeedbackReport!=null&&newStudentFeedbackReport.size>0) {
            var sfrList = ArrayList(this.studentFeedbackReport)
            sfrList.addAll(newStudentFeedbackReport)
            this.studentFeedbackReport=sfrList
        }
        return true
    }

*/

    private fun cvrInfrastructureUpdate(cvrInfrastructureList: List<CvrInfrastructure>):Boolean{

        var updateCvrInfrastructureIds=HashSet<String>()
        var newCvrInfrastructure=ArrayList<CvrInfrastructure>()
        //Create Cvr-Infrastructure
        for(cvrinfra in cvrInfrastructureList){
            if(cvrinfra.cvrInfrastructureId!!.cvrInfrastructureId!=null&&cvrinfra.cvrInfrastructureId!!.cvrInfrastructureId.equals("")){
                cvrinfra.cvrInfrastructureId= CvrInfrastructureId()
                newCvrInfrastructure.add(cvrinfra)
            }
        }
        var cvrInfrastructureMap:Map<String,CvrInfrastructure> = cvrInfrastructureList.map { x-> x.cvrInfrastructureId!!.cvrInfrastructureId to x }.toMap()
        //Update  Cvr-Infrastructure
        this.infrastructure!!.filter { x->cvrInfrastructureMap.containsKey(x.cvrInfrastructureId!!.cvrInfrastructureId) }.toList().
                forEach { x ->
                    updateCvrInfrastructureIds.add(x.cvrInfrastructureId!!.cvrInfrastructureId)
                    x.updateCvrInfrastructure(cvrInfrastructureMap.get(x.cvrInfrastructureId!!.cvrInfrastructureId)!!)

                }
        //Delete  Running-Batch-Status
        this.infrastructure=this.infrastructure!!.
                filter { x->updateCvrInfrastructureIds.contains(x.cvrInfrastructureId!!.cvrInfrastructureId) }

        if(newCvrInfrastructure!=null&&newCvrInfrastructure.size>0) {
            var cvrInfraList = ArrayList(this.infrastructure)
            cvrInfraList.addAll(newCvrInfrastructure)
            this.infrastructure=cvrInfraList
        }

        return true
    }

    fun updateCenterVisitReport(centerVisitReport:CenterVisitReport):Boolean {

        if(centerVisitReport!=null) {

            this.dodUpdate(centerVisitReport.dropOutDetails)
            this.rbsUpdate(centerVisitReport.runningBatchStatus!!)
            this.sStatusUpdate(centerVisitReport.stockStatus)
            this.cbsUpdate(centerVisitReport.completedBatchStatus)
            if(this.todoForCenter==null && centerVisitReport.todoForCenter!=null) {
                var updateTodoForCenter = TodoForCenter(TodoForCenterId(), centerVisitReport.todoForCenter!!.todoListCenterList)
                updateTodoForCenter.updateIdInitilizer()
               // updateTodoForCenter.updateTodoCenter(centerVisitReport.todoForCenter)
                this.todoForCenter = updateTodoForCenter
            }
            else {
                this.todoForCenter?.updateTodoCenter(centerVisitReport.todoForCenter)

            }

            if(this.todoForApll==null && centerVisitReport.todoForApll!=null){
                var updateTodoForApll=TodoForApll(TodoForApllId(),centerVisitReport.todoForApll!!.todoListApllList)
                 updateTodoForApll.updateIdinitilizer()
               // updateTodoForApll.updateTodoApll(centerVisitReport.todoForApll)
                this.todoForApll=updateTodoForApll
            }
            else {

                this.todoForApll?.updateTodoApll(centerVisitReport.todoForApll)
            }

            this.cvrInfrastructureUpdate(centerVisitReport.infrastructure!!)
            this.centerVisitReportStatus = centerVisitReport.centerVisitReportStatus

            return true
        }else{
            return false
        }
    }
}
