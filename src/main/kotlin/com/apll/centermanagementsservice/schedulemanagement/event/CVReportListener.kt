package com.apll.centermanagementsservice.schedulemanagement.event

import com.apll.centermanagementsservice.events.ReportCreatedEvent
import com.apll.centermanagementsservice.schedulemanagement.model.TypeOfRequest
import com.apll.centermanagementsservice.schedulemanagement.model.dto.CompletedCustomScheduleDto
import com.apll.centermanagementsservice.schedulemanagement.model.dto.CompletedScheduleDTO
import com.apll.centermanagementsservice.schedulemanagement.service.IFrontEndScheduleService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class CVReportListener {

    @Autowired
    lateinit var service:IFrontEndScheduleService

    val LOGGER= LoggerFactory.getLogger(CVReportListener::class.java)

    @StreamListener(CVReportInputStream.INPUT)
    fun subscribe(@Payload event: ReportCreatedEvent) {
        if(event.typeOfReq.equals(TypeOfRequest.BUSSINESS_DEVELOPEMENT_ACTIVITY)){
            var result = service.bdaScheduleCompleted(CompletedScheduleDTO
            (event.feScheduleId, event.requestId,event.scheduleDate,null))
            LOGGER.info("BDASchedule Completed :: {}", result.get())
        }
        else  if(event.typeOfReq.equals(TypeOfRequest.CUSTOM_REQUEST)) {

            LOGGER.info("Custom Schedule processing:: {}")
            var customResult = service.customScheduleCompleted(CompletedCustomScheduleDto(event.feScheduleId, event.scheduleDate, null,event.scheduleId))
            LOGGER.info("Custom Schedule Completed :: {}", customResult.get())
        }else if(event.typeOfReq.equals(TypeOfRequest.NEW_CENTER_REQUEST)){

            LOGGER.info("New Center  Schedule processing:: {}")
            var newCenterResult=service.scheduleCompleted(CompletedScheduleDTO(event.feScheduleId,event.requestId,null,null))
            LOGGER.info("New Center Schedule Completed :: {}", newCenterResult.get())

        }
        else{
            var result = service.scheduleCompleted(CompletedScheduleDTO(event.feScheduleId, event.requestId,null,null))
            if (result.isRight) {
                LOGGER.info("Schedule Completed :: {}", result.get())
            }
        }
    }

}
