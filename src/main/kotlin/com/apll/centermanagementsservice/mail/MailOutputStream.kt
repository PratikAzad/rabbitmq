package com.apll.centermanagementsservice.mail

import org.springframework.cloud.stream.annotation.Output
import org.springframework.messaging.MessageChannel

interface MailOutputStream {

    @Output(OUTPUT)
    fun sendMail(): MessageChannel

    companion object {
        const val OUTPUT = "mail-out"
    }
}