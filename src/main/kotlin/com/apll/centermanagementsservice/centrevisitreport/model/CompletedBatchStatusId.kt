package com.apll.centermanagementsservice.centrevisitreport.model

import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
class CompletedBatchStatusId:Serializable {
    var completedBatchStatusId:String=UUID.randomUUID().toString()
    set(completedBatchStatusId){
        field=completedBatchStatusId
    }

    constructor()
    constructor(completedBatchStatusId: String) {
        this.completedBatchStatusId = completedBatchStatusId
    }


}