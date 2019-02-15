package com.apll.centermanagementsservice.businessdevelopmentactivity.model

import io.vavr.control.Either
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.persistence.*
import kotlin.Exception

@Entity
@Table(name="bda_visit_request")
class BDAVisitRequest{

        @EmbeddedId
        @Column(name="bda_visit_request_id")
        var bdaVisitRequestId: BDAVisitRequestId?=null

        @Column(name="activity_type")
        @Enumerated(EnumType.STRING)
        var activityType:BDAType?=null

        @Column(name = "front_end_id")
        var frontEndId:String?=null

        @Column(name="front_end_name")
        var frontEndName:String?=null

        @Column(name="center_id")
        var centerId:String?=null

        @Column(name="center_name")
        var centerName:String?=null

        @Column(name="area")
        var  area:String?=null

        @Column(name="city")
        var city:String?=null

        @Column(name="place_name")
        var placeName:String?=null

        @Column(name="description")
        var description:String?=null


        @Column(name="start_date")
        var startDate:LocalDate?=null

        @Column(name="end_date")
        var endDate:LocalDate?=null

        @Column(name="bda_request_type")
         @Enumerated(EnumType.STRING)
        var bdaRequestType:BDARequestType?=null

        @OneToMany(cascade = arrayOf(CascadeType.ALL),fetch = FetchType.EAGER)
        @Fetch(value = FetchMode.SUBSELECT)
        @JoinTable(name = "bda_visit_request_bda_visit_report", joinColumns = arrayOf(JoinColumn(name = "bda_visit_request_id")), inverseJoinColumns = arrayOf(JoinColumn(name = "bda_visit_report_id")))
        var bdaReports:List<BDAVisitReport>?=null


        constructor()

        constructor(bdaVisitRequestId: BDAVisitRequestId?, activityType: BDAType?, frontEndId: String?, frontEndName: String?, centerId: String?, centerName: String?, area: String?, city: String?, placeName: String?, description: String?, startDate: LocalDate?, endDate: LocalDate?,bdaRequestType:BDARequestType, bdaReports: List<BDAVisitReport>?) {
                this.bdaVisitRequestId = bdaVisitRequestId
                this.activityType = activityType
                this.frontEndId = frontEndId
                this.frontEndName = frontEndName
                this.centerId = centerId
                this.centerName = centerName
                this.area = area
                this.city = city
                this.placeName = placeName
                this.description = description
                this.startDate = startDate
                this.endDate = endDate
                this.bdaRequestType=bdaRequestType
                this.bdaReports = bdaReports
        }
        constructor(bdaVisitRequestId: BDAVisitRequestId?, activityType: BDAType?, frontEndId: String?, frontEndName: String?, centerId: String?, centerName: String?,  area: String?, city: String?, placeName: String?, description: String?, startDate: LocalDate?, endDate: LocalDate?,bdaRequestType:BDARequestType?) {
                this.bdaVisitRequestId = bdaVisitRequestId
                this.activityType = activityType
                this.frontEndId = frontEndId
                this.frontEndName = frontEndName
                this.centerId = centerId
                this.centerName = centerName
                this.area = area
                this.city = city
                this.placeName = placeName
                this.description = description
                this.startDate = startDate
                this.endDate = endDate
                this.bdaRequestType=bdaRequestType
        }

        fun idInitilizer(){
                this.bdaVisitRequestId= BDAVisitRequestId()
                this.bdaReports=null
        }

        fun updateBDARequestWithCenterOrAddress(){
                if(this.bdaRequestType!!.equals(BDARequestType.BDA_REQUEST_CENTER))
                {
                        this.area=null
                        this.city=null
                        this.placeName=null
                }
                else{
                        this.centerId=null
                        this.centerName=null
                }
        }

        fun updateBDARequest(){
                this.bdaReports?.forEach {
                                it.idReportInitializer()
                }
        }

        fun updateBDARequest(bdaVisitRequest: BDAVisitRequest){
                this.area=bdaVisitRequest.area
                this.bdaRequestType=bdaVisitRequest.bdaRequestType
                this.centerId=bdaVisitRequest.centerId
                this.centerName=bdaVisitRequest.centerName
                this.city=bdaVisitRequest.city
                this.description=bdaVisitRequest.description
                this.placeName=bdaVisitRequest.placeName

               /* if(bdaVisitRequest.bdaReports!=null  && bdaVisitRequest.bdaReports!!.size>0) {
                        var bdaReportsIds=ArrayList<String>()

                        //Add BDAReport
                        val addBdAReport = bdaVisitRequest.bdaReports!!.
                                filter { x -> x.bdaVisitReportId!!.bdaVisitReportId.equals("") }.
                                map { x ->
                                        x.bdaVisitReportId = BDAVisitReportId()
                                        x
                                }

                        var newBDAReport = bdaVisitRequest.bdaReports!!.
                                map { x -> x.bdaVisitReportId!!.bdaVisitReportId to x }.toMap()


                        //Update BDAReport
                        this.bdaReports!!.filter { x -> newBDAReport.containsKey(x.bdaVisitReportId!!.bdaVisitReportId) }.
                                forEach {
                                bdaReportsIds.add(it.bdaVisitReportId!!.bdaVisitReportId)
                                it.updateBDA(newBDAReport.get(it.bdaVisitReportId!!.bdaVisitReportId)!!)
                        }

                        //Delete BDAReport
                        this.bdaReports=this.bdaReports!!.filter {x->bdaReportsIds.
                                contains(x.bdaVisitReportId!!.bdaVisitReportId)}

                        //Adding New BDAReport
                        var list = ArrayList(this.bdaReports)
                        list.addAll(addBdAReport)
                        this.bdaReports = list*/
        }


        fun validateReport(report: BDAVisitReport):Either<Exception,Boolean> {
                //First Condn
                var bdaReport=this.bdaReports!!.stream().
                        filter { x->x.bdaVisitReportDate!!.
                                equals(report.bdaVisitReportDate) }.findAny()
                if(bdaReport.isPresent){
                        return Either.left(Exception("Already Report is Created..."))
                }
                var con1=this.startDate!!.isBefore(report.bdaVisitReportDate)
                var con2=this.endDate!!.isAfter(report.bdaVisitReportDate)

            if(report.bdaVisitReportDate!!.isBefore(this.startDate) ||
                        report.bdaVisitReportDate!!.isAfter(this.endDate)){
                        return Either.left(Exception(" BdaRequest is not found between  Strat date and End Date ."))
                }
                return Either.right(true)
        }

        fun checkReportCompleted():Boolean{
                var days=ChronoUnit.DAYS.between(this.startDate,this.endDate).toInt()+1
                var  reportSize =this.bdaReports!!.size
                if(days!=null && reportSize!=null) {
                        return reportSize==days
                }
                return false
        }

}



