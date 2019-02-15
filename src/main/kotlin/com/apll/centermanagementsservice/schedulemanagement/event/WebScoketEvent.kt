package com.apll.websocketservice.model

import com.apll.centermanagementsservice.schedulemanagement.event.MessageType
import com.apll.centermanagementsservice.schedulemanagement.event.ScheduleEvent


data class WebScoketEvent (
        var messageType:MessageType?=null,
        var scheduleMessage:ScheduleEvent?=null
    )