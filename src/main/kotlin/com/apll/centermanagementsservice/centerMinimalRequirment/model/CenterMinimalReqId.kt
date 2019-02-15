package com.apll.centermanagementsservice.centerMinimalRequirment.model

import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
class CenterMinimalReqId:Serializable{

    var centerMinimalReqId:String=UUID.randomUUID().toString()

    constructor()
    constructor(centerMinimalReqId: String) {
        this.centerMinimalReqId = centerMinimalReqId
    }


}