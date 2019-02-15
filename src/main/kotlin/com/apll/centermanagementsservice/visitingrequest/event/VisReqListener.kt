/*
package com.apll.centermanagementsservice.visitingrequest.event

import com.apll.centermanagementsservice.events.ReportCreatedEvent
import com.apll.centermanagementsservice.schedulemanagement.model.TypeOfRequest
import com.apll.centermanagementsservice.visitingrequest.controller.visitrequestdto.ResolvedDTO
import com.apll.centermanagementsservice.visitingrequest.service.VisitingRequestService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component


@Component
class VisReqListener {
    @Autowired
    lateinit var service: VisitingRequestService


    val LOGGER= LoggerFactory.getLogger(VisReqListener::class.java)

    @StreamListener(VisReqInputStream.INPUT)
    fun subscribe(@Payload event: ReportCreatedEvent) {
        if(event.typeOfReq!!.equals(TypeOfRequest.EXISTING_CENTER_REQUEST)){
            var request=service.getVisitingRequestById(event.requestId)
            if(request.isRight && request.get().resolved!!.equals(false)) {
                var resolvedDto = ResolvedDTO(event.resolvedDate, event.requestId, event.howToResolved, event.resolvedBy, event.resolverId, event.reasonForNotResolved)
                var result = service.resolve(resolvedDto)
                if (result.isRight) {
                    LOGGER.info("Visiting Request Resolved:: {}" + event)
                } else {
                    LOGGER.info("Visiting Request Not Resolved:: {}" + event)
                }
            }
        }
    }
}*/
