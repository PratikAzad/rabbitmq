package com.apll.centermanagementsservice.centrevisitreport.model

import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
class CenterVisitReportId :Serializable{
    var centerVisitReportId= UUID.randomUUID().toString()
    set(centerVisitReportId){
        field=centerVisitReportId
    }

    constructor()

    constructor(centerVisitReportId: String){
        this.centerVisitReportId=centerVisitReportId
    }
}