package com.apll.centermanagementsservice.businessdevelopmentactivity.model

import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
class BDAVisitReportId:Serializable {
    var bdaVisitReportId:String=UUID.randomUUID().toString()
    set(bdaVisitReportId){
        field=bdaVisitReportId
    }
    //get()=bdaVisitReportId


    constructor()

    constructor(bdaVisitReportId: String) {
        this.bdaVisitReportId = bdaVisitReportId
    }
}