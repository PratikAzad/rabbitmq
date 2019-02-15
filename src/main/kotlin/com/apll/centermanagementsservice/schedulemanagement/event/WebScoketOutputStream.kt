package com.apll.centermanagementsservice.schedulemanagement.event

import org.springframework.cloud.stream.annotation.Output
import org.springframework.messaging.MessageChannel

interface WebScoketOutputStream {

    @Output(OUTPUT)
    fun publish(): MessageChannel

    companion object {
        const val OUTPUT = "webscoket-out"
    }
}