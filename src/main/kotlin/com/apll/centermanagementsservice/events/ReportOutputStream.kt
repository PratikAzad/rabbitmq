package com.apll.centermanagementsservice.events

import org.springframework.cloud.stream.annotation.Output
import org.springframework.messaging.MessageChannel

interface ReportOutputStream {



    @Output(OUTPUT)
    fun publish(): MessageChannel

    companion object {
        const val OUTPUT = "cvreport-out"
    }
}
