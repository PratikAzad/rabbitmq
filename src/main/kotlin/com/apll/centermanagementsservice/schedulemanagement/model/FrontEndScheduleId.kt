package com.apll.centermanagementsservice.schedulemanagement.model

import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable
import javax.persistence.Id

@Embeddable
class FrontEndScheduleId:Serializable {

    var  frontEndScheduleId:String=UUID.randomUUID().toString()
        set(frontEndScheduleId){
            field=frontEndScheduleId
        }

    constructor()
    constructor(frontEndScheduleId: String) {
        this.frontEndScheduleId = frontEndScheduleId
    }
}