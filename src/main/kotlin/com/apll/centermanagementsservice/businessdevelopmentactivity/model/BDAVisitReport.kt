package com.apll.centermanagementsservice.businessdevelopmentactivity.model

import com.apll.centermanagementsservice.businessdevelopmentactivity.model.image.BDAVisitImage
import com.apll.centermanagementsservice.businessdevelopmentactivity.model.image.BDAVisitImageId
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name="bda_visit_report")
class BDAVisitReport {
        @EmbeddedId
        @Column(name="bda_visit_report_id")
        var  bdaVisitReportId: BDAVisitReportId?=null

        @Column(name="bda_visit_request_id")
        var  bdaVisitRequestId: String?=null

        @OrderColumn(name = "bda_visit_image_order")
        @OneToMany(cascade = arrayOf(CascadeType.ALL),fetch = FetchType.EAGER)
        @Fetch(value = FetchMode.SUBSELECT)
        @JoinTable(name = "bda_visit_report_bda_visit_image", joinColumns = arrayOf(JoinColumn(name = "bda_visit_report_id")), inverseJoinColumns = arrayOf(JoinColumn(name = "bda_visit_image_id")))
        var  bdaVisitImageList:List<BDAVisitImage>?=null

        @Column(name="description")
        var  description:String?=null

        @Column(name="bda_visit_report_date")
        var bdaVisitReportDate:LocalDate?=null

        @Column(name="bda_date")
        var bdaDate:LocalDate?=null

      constructor(bdaVisitReportId: BDAVisitReportId?, bdaVisitRequestId: String?, bdaVisitImageList: List<BDAVisitImage>?, description: String?, bdaVisitReportDate: LocalDate?, bdaDate: LocalDate?) {
                this.bdaVisitReportId = bdaVisitReportId
                this.bdaVisitRequestId = bdaVisitRequestId
                this.bdaVisitImageList = bdaVisitImageList
                this.description = description
                this.bdaVisitReportDate = bdaVisitReportDate
                this.bdaDate = bdaDate
        }
        constructor(bdaVisitReportId: BDAVisitReportId?, bdaVisitRequestId: String?, bdaVisitImageList: List<BDAVisitImage>?, description: String?, bdaVisitReportDate: LocalDate?) {
                this.bdaVisitReportId = bdaVisitReportId
                this.bdaVisitRequestId = bdaVisitRequestId
                this.bdaVisitImageList = bdaVisitImageList
                this.description = description
                this.bdaVisitReportDate = bdaVisitReportDate

        }


        fun idReportInitializer(){
            if(this.bdaVisitReportId!!.equals("") && this.bdaVisitReportId!!.equals(null) && this.bdaVisitReportId!!.equals("String")){
                this.bdaVisitReportId= BDAVisitReportId()

                this.bdaVisitImageList!!.forEach {
                    if(it.bdaVisitImageId.equals("")&&it.bdaVisitImageId.equals(null)) {

                        it.bdaVisitImageId = BDAVisitImageId()
                    }
                }
            }
        }

        fun updateBDA(bdaVisitReport: BDAVisitReport){
            this.bdaDate=bdaVisitReport.bdaDate
            this.bdaVisitReportDate=bdaVisitReport.bdaVisitReportDate
            this.description=bdaVisitReport.description

            if(bdaVisitReport.bdaVisitImageList!=null  && bdaVisitReport.bdaVisitImageList!!.size>0) {
                var bdaVisitImageListIds = ArrayList<String>()

                //Add BDAVisitImage
                val addBDAVisitImage = bdaVisitReport.bdaVisitImageList!!.filter { x -> x.bdaVisitImageId.bdaVisitImageId.equals("") }.map { x ->
                    x.bdaVisitImageId = BDAVisitImageId()
                    x
                }

                var newBDAVisitImage = bdaVisitReport.bdaVisitImageList!!.map { x -> x.bdaVisitImageId.bdaVisitImageId to x }.toMap()

                //Update BDAVisitImage
                this.bdaVisitImageList!!.filter { x -> newBDAVisitImage.containsKey(x.bdaVisitImageId.bdaVisitImageId) }.forEach {
                    bdaVisitImageListIds.add(it.bdaVisitImageId.bdaVisitImageId)
                    it.updateBDAVisitImage(newBDAVisitImage.get(it.bdaVisitImageId.bdaVisitImageId)!!)
                }

                //Delete BDAVisitImage
                this.bdaVisitImageList = this.bdaVisitImageList!!.
                        filter { x -> bdaVisitImageListIds.contains(x.bdaVisitImageId.bdaVisitImageId) }


                //Adding New BDAVisitImage
                var list = ArrayList(this.bdaVisitImageList)
                list.addAll(addBDAVisitImage)
                this.bdaVisitImageList = list

            }

        }
}


