package com.apll.centermanagementsservice.customreport.model

import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
class CustomReportId:Serializable {
    var customReportId:String=UUID.randomUUID().toString()
    set(customReportId){
        field=customReportId
    }
    constructor()

    constructor(customReportId: String) {
        this.customReportId = customReportId
    }
}