package com.apll.centermanagementsservice.centrevisitreport.model

import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
class RunningBatchStatusId:Serializable {
    var runningBatchStatusId:String=UUID.randomUUID().toString()
    set(runningBatchStatusId){
        field=runningBatchStatusId
    }

    constructor()
    constructor(runningBatchStatusId: String) {
        this.runningBatchStatusId = runningBatchStatusId
    }

}