package com.apll.centermanagementsservice.schedulemanagement.model

import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
class ScheduleId:Serializable {

    var scheduleId:String=UUID.randomUUID().toString()
    set(scheduleId){
            field=scheduleId
        }

    constructor()
    constructor(scheduleId:String) {
        this.scheduleId = scheduleId
    }


}