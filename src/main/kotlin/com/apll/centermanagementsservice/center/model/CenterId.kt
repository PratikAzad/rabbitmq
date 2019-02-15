package com.apll.centermanagementsservice.center.model

import javax.persistence.Embeddable
import java.io.Serializable
import java.util.UUID

@Embeddable
class CenterId : Serializable {
    var centerId:String =UUID.randomUUID().toString()
    set(centerId){
        field=centerId
    }


    constructor()

    constructor(centerId: String){
        this.centerId=centerId
    }

    override fun toString(): String {
        return "CenterId(centerId='$centerId')"
    }
}