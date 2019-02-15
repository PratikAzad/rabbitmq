package com.apll.centermanagementsservice.centrevisitreport.model

import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable


@Embeddable
class CvrInfrastructureId :Serializable{
    var cvrInfrastructureId:String=UUID.randomUUID().toString()
    set(cvrInfrastructureId){
        field=cvrInfrastructureId
    }
    constructor()
    constructor(cvrInfrastructureId: String) {
        this.cvrInfrastructureId = cvrInfrastructureId
    }
}
