package com.apll.centermanagementsservice.schedulemanagement.model

import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
class FrontEndScheduleEventId:Serializable {

    var  frontEndScheduleEventId:String= UUID.randomUUID().toString()
    set(value) {
        field=value
    }

    constructor()
    constructor(frontEndScheduleEventId: String) {
        this.frontEndScheduleEventId = frontEndScheduleEventId
    }

}