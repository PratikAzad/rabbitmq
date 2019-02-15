package com.apll.centermanagementsservice.businessdevelopmentactivity.model

import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
class BDAVisitRequestId:Serializable {

   var bdaVisitRequestId:String=UUID.randomUUID().toString()
    set(bdaVisitRequestId){
        field=bdaVisitRequestId
    }

    constructor()
    constructor(bdaVisitRequestId: String) {
        this.bdaVisitRequestId = bdaVisitRequestId
    }


    fun getbdaVisitRequestId():String{
        return this.bdaVisitRequestId
    }
}