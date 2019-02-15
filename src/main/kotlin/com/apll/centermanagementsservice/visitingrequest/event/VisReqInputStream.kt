package com.apll.centermanagementsservice.visitingrequest.event

import org.springframework.cloud.stream.annotation.Input
import org.springframework.messaging.SubscribableChannel

interface VisReqInputStream {
    @Input(INPUT)
    fun subscribe(): SubscribableChannel

    companion object {
        const val INPUT = "cvreport-in"
    }
}