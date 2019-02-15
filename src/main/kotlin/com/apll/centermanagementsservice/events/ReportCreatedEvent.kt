package com.apll.centermanagementsservice.events

import com.apll.centermanagementsservice.businessdevelopmentactivity.controller.bdadto.BDAVisitReportDto
import com.apll.centermanagementsservice.businessdevelopmentactivity.model.BDAVisitReport
import com.apll.centermanagementsservice.centrevisitreport.model.CenterVisitReport
import com.apll.centermanagementsservice.centrevisitreport.model.ResolvedBy
import com.apll.centermanagementsservice.config.LocalDateDeSerializer
import com.apll.centermanagementsservice.config.LocalDateSerializer
import com.apll.centermanagementsservice.customreport.controller.dto.CustomReportDto
import com.apll.centermanagementsservice.schedulemanagement.model.TypeOfRequest
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.time.LocalDate

data class ReportCreatedEvent (
        var typeOfReq: TypeOfRequest,

        var feScheduleId: String,

        var requestId: String,

        @JsonDeserialize(using = LocalDateDeSerializer::class)
        @JsonSerialize(using = LocalDateSerializer::class)
        var resolvedDate: LocalDate?,

        var howToResolved: String?,

        var resolvedBy: ResolvedBy?,

        var resolverId: String?,

        @JsonDeserialize(using = LocalDateDeSerializer::class)
        @JsonSerialize(using = LocalDateSerializer::class)
        var scheduleDate:LocalDate?,

        var reasonForNotResolved: String?,

        var scheduleId:String?
){
    constructor():this(TypeOfRequest.EXISTING_CENTER,"","",null,null,null,null,null,null,null)

    constructor(cvReport: CenterVisitReport):
            this(TypeOfRequest.EXISTING_CENTER,cvReport.frontEndScheduleId!!, cvReport.requestId!!,null,null,null,null,null,null,null)

    constructor(cvReport: CenterVisitReport, typeOfReq: TypeOfRequest) :
            this(TypeOfRequest.EXISTING_CENTER_REQUEST,
                    cvReport.frontEndScheduleId!!,
                    cvReport.requestId!!,
                    LocalDate.now(),
                    "Front-End Filed report for your request.",
                    ResolvedBy.FRONT_END,
                    cvReport.frontEndId,
                    null,
                    null,
                    null)

    constructor(report: BDAVisitReportDto) : this(TypeOfRequest.BUSSINESS_DEVELOPEMENT_ACTIVITY, report.feScheduleId!!,report.requestId!!,null,null,null,null,report.scheduleDate,null,null)

    constructor(customReport: CustomReportDto):this(TypeOfRequest.CUSTOM_REQUEST, customReport.frontEndScheduleId!!,"",null,null,null,null,null,null,customReport.customScheduleId)

}
