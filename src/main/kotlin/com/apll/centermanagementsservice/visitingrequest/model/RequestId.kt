package com.apll.centermanagementsservice.visitingrequest.model

import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
class RequestId:Serializable {

    var requestId:String?= UUID.randomUUID().toString()
    set(requestId){
        field=requestId
    }

    //get()=this.requestId

    constructor(){
    }

    constructor(requestId: String?) {
        this.requestId = requestId
    }

}