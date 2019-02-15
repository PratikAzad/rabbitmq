package com.apll.centermanagementsservice.customreport.service

import com.apll.centermanagementsservice.customreport.controller.dto.CustomReportDto
import com.apll.centermanagementsservice.customreport.controller.dto.CustomReportDtoConvertor
import com.apll.centermanagementsservice.customreport.model.CustomReport
import com.apll.centermanagementsservice.customreport.model.CustomReportId
import com.apll.centermanagementsservice.customreport.repository.CustomReportRepo
import com.apll.centermanagementsservice.events.ReportCreatedEvent
import com.apll.centermanagementsservice.events.ReportOutputStream
import io.vavr.control.Either
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service
import org.springframework.util.MimeTypeUtils


@Service
class CustomReportServiceImpl:CustomReportService {



    @Autowired
    lateinit var  customReportRepo:CustomReportRepo


    @Autowired
    private lateinit var stream: ReportOutputStream
    val log = LoggerFactory.getLogger(CustomReportServiceImpl::class.java)



    override fun insertCustomReport(customReportDto: CustomReportDto): Either<Exception, String> {
        var customReport=CustomReportDtoConvertor.Convertor.dtoToModelConvertor(customReportDto)
        var report=customReportRepo.save(customReport)
        if(report!=null){
            var dto=CustomReportDtoConvertor.Convertor.modelToDtoConvertor(report)
            log.info("Custom Report is created!")

            try {
                //Raised Event to Complete Schedule.
                var event = ReportCreatedEvent(customReportDto)
                val messageChannel = stream.publish()
                messageChannel.send(MessageBuilder.withPayload(event)
                        .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                        .build())
                log.info("Custom-Report-Event Created Successfully {}", event)
            }catch(e:Exception){
                log.error("Custom-Report-Event not Created"+e.printStackTrace())
            }

            return Either.right("Custom Request Report Created!!!!!")

        }
        return Either.left(Exception("custom report is not Created.."))
    }

  /*  override fun updateCustomReport(customReportDto: CustomReportDto): Either<Exception, CustomReportDto> {
        var customReport=CustomReportDtoConvertor.Convertor.dtoToModelConvertor(customReportDto)
        var updateReport=customReportRepo.save(customReport)
        if(updateReport!=null){
            var dto=CustomReportDtoConvertor.Convertor.modelToDtoConvertor(updateReport)
            return Either.right(dto)
        }
        return Either.left(Exception("custom report is not Updated.."))
    }*/

    override fun getByCustomReportId(customReportId: String): Either<Exception, CustomReportDto> {

        var id=CustomReportId(customReportId)
        var resultReport=customReportRepo.findById(id)
        if(resultReport.isPresent) {

            var resultingReport = resultReport.get()
            if (resultingReport != null) {
                var dto = CustomReportDtoConvertor.Convertor.modelToDtoConvertor(resultingReport)
                return Either.right(dto)
            }
        }
        return Either.left(Exception("Custom Report is Not Found.."))
    }
    override fun getAllCustomReports(): Either<Exception, List<CustomReportDto>> {

        var resultReports=customReportRepo.findAll()
        var customDtoList=ArrayList<CustomReportDto>()

        if(resultReports.size<1){
            return Either.right(customDtoList)

        }
        else{
            if(resultReports.size>=1){
                var res=CustomReportDtoConvertor.Convertor.modelListToDtoListConvertor(resultReports)
                customDtoList.addAll(res)
                return Either.right(customDtoList)
            }
        }
        return Either.left(Exception("Custom reports are not found....."))

    }




}