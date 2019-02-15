package com.apll.centermanagementsservice.schedulemanagement.model

import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
class ScheduleEventId: Serializable {

    var scheduleEventId=UUID.randomUUID().toString()
    set(value) {
        field=value
    }

    constructor()
    constructor(scheduleEventId: String) {
        this.scheduleEventId = scheduleEventId
    }

}