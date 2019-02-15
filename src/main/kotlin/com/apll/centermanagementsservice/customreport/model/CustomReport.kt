package com.apll.centermanagementsservice.customreport.model

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table


@Entity
@Table(name="custom_report")
class CustomReport {

    @EmbeddedId
    @Column(name="custom_report_id")
    var customReportId:CustomReportId?=null

    @Column(name="custom_schedule_id")
    var customScheduleId:String?=null

    @Column(name="description")
    var description:String?=null

    @Column(name="custom_report_date")
    var customReportDate: LocalDate?=null


    @Column(name="front_end_schedule_id")
    var frontEndScheduleId:String?=null

    @Column(name="front_end_name")
    var frontEndName:String?=null



    constructor()

    constructor(customReportId: CustomReportId?, customScheduleId: String?, description: String?, customReportDate: LocalDate?,frontEndScheduleId:String?,frontEndName:String?) {
        this.customReportId = customReportId
        this.customScheduleId = customScheduleId
        this.description = description
        this.customReportDate = LocalDate.now()
        this.frontEndScheduleId=frontEndScheduleId
        this.frontEndName=frontEndName
    }
}