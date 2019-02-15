package com.apll.centermanagementsservice.schedulemanagement.event

import org.springframework.cloud.stream.annotation.Input
import org.springframework.messaging.SubscribableChannel

interface CVReportInputStream {

    @Input(INPUT)
    fun subscribe(): SubscribableChannel

    companion object {
        const val INPUT = "cvreport-in"
    }
}
