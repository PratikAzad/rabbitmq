package com.apll.centermanagementsservice.centrevisitreport.model

import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
class DropOutDetailsId:Serializable {
    var dropOutDetailsId:String=UUID.randomUUID().toString()
    set(dropOutDetailsId){
        field=dropOutDetailsId
    }

    constructor()

    constructor(dropOutDetailsId: String) {
        this.dropOutDetailsId = dropOutDetailsId
    }

}