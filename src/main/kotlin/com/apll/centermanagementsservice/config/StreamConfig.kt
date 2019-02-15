package com.apll.centermanagementsservice.config

import com.apll.centermanagementsservice.events.ReportOutputStream
import com.apll.centermanagementsservice.mail.MailOutputStream
import com.apll.centermanagementsservice.schedulemanagement.event.CVReportInputStream
import com.apll.centermanagementsservice.schedulemanagement.event.WebScoketOutputStream
import org.springframework.cloud.stream.annotation.EnableBinding

@EnableBinding(
        ReportOutputStream::class,
        CVReportInputStream::class,
        MailOutputStream::class,
        WebScoketOutputStream::class)
class StreamConfig
