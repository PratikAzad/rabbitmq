package com.apll.centermanagementsservice.businessdevelopmentactivity.model.image

import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
class BDAVisitImageId:Serializable {
    var bdaVisitImageId:String=UUID.randomUUID().toString()
    set(bdaVisitImageId){
        field=bdaVisitImageId
    }
    get()=field


    constructor(){

    }

    constructor(bdaVisitImageId: String) {
        this.bdaVisitImageId = bdaVisitImageId
    }
}