package com.apll.centermanagementsservice.centrevisitreport.service

import com.apll.centermanagementsservice.center.model.dto.CenterDTO
import com.apll.centermanagementsservice.center.service.CenterService
import com.apll.centermanagementsservice.events.ReportCreatedEvent
import com.apll.centermanagementsservice.events.ReportOutputStream
import com.apll.centermanagementsservice.centrevisitreport.model.CenterVisitReportId
import com.apll.centermanagementsservice.centrevisitreport.model.CenterVisitReportStatus
import com.apll.centermanagementsservice.centrevisitreport.model.dto.*
import com.apll.centermanagementsservice.centrevisitreport.repository.CenterVisitReportRepo
import com.apll.centermanagementsservice.schedulemanagement.model.TypeOfRequest
import com.apll.centermanagementsservice.visitingrequest.service.VisitingRequestService
import io.vavr.control.Either
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service
import org.springframework.util.MimeTypeUtils
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Service
class CenterVisitReportServiceImpl:CenterVisitReportService {


    @Autowired
    private lateinit var centerVisitReportRepo:CenterVisitReportRepo

    @Autowired
    private lateinit var centerService: CenterService

    @Autowired
    private lateinit var visReqService: VisitingRequestService

    //For publish the Event
    @Autowired
    private lateinit var stream: ReportOutputStream

    val log = LoggerFactory.getLogger(CenterVisitReportServiceImpl::class.java)



    override fun insertCenterVisitReport(centerVisitReportDto: CenterVisitReportDto): Either<Exception, CenterVisitReportDto> {
        var  centerVisitReport=CenterVisitReportDtoConverter.converter.dtoToModelConverter(centerVisitReportDto)
        centerVisitReport.idInitializer()

        var result =centerVisitReportRepo.save(centerVisitReport)


        if(result!=null){

            var resultdto=CenterVisitReportDtoConverter.converter.modelToDtoConverter(result)
            log.info("Center Visit Report  is  Created")

            try {
                    //Publishing Event When we create CVReport Successfully.
                var event = ReportCreatedEvent()
                if(centerVisitReportDto.typeOfReq!!.equals(TypeOfRequest.EXISTING_CENTER_REQUEST)){
                     event = ReportCreatedEvent(result, centerVisitReportDto.typeOfReq!!)
                }else {
                    event = ReportCreatedEvent(result)
                }
                val messageChannel = stream.publish()
                messageChannel.send(MessageBuilder.withPayload(event)
                        .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                    .build())
                log.info("CVReport-Event Created Successfully {}", event)
            }
            catch (e: Exception){
                log.error("CVReport-Event not Created {}", e.printStackTrace())
            }

            return Either.right(resultdto)
        }
        return Either.left(Exception("Exception  raised  inserting of Center Visit Request .."))
    }



    override fun findByCenterVisitReportId(id: String): Either<Exception, CenterVisitReportDto> {
    var cvrid=CenterVisitReportId()
        cvrid.centerVisitReportId=id

        var result =centerVisitReportRepo.findById(cvrid)
         var resulting=result.get()

        var resultdto=CenterVisitReportDtoConverter.converter.modelToDtoConverter(resulting)

        if(resultdto!=null){
            return Either.right(resultdto)
        }
        return Either.left(Exception("Exception  getting center visit report  find by Center Visit Request Id......."))
    }


    override fun findAllCenterVisitReport(): Either<Exception, List<CenterVisitReportDto>> {

        var result = centerVisitReportRepo.findAll()
        var cvrdtolist = ArrayList<CenterVisitReportDto>()

        if (result.size < 1) {
            return Either.right(cvrdtolist)
        } else {
            if (result.size >=1) {
                for (cvr in result) {
                    var cvrdto = CenterVisitReportDtoConverter.converter.modelToDtoConverter(cvr)

                    cvrdtolist.add(cvrdto)
                }
                return Either.right(cvrdtolist)
            }
        }
        return Either.left(Exception("Exception occured in center visit report getting all center visit report"))
    }


   /* override fun updateCenterVisitReport(centerVisitReportDto: CenterVisitReportDto): Either<Exception, CenterVisitReportDto> {
        var centerVisitReport=CenterVisitReportDtoConverter.converter.dtoToModelConverter(centerVisitReportDto)
        centerVisitReport.updateCVR()
        var resultcvr=centerVisitReportRepo.saveAndFlush(centerVisitReport)
        if(resultcvr!=null&&!resultcvr.equals(centerVisitReport)){
            var resultCvrDto=CenterVisitReportDtoConverter.converter.modelToDtoConverter(resultcvr)
            return Either.right(resultCvrDto)
        }
        return Either.left(Exception("Exception  occured Center visit Report is not Updated .................... "))
    }

*/

    override fun updateCVR(centerVisitReportUpdateDTO: CenterVisitReportUpdateDTO): Either<Exception, CenterVisitReportDto> {

    var cvr=CenterVisitReportDtoConverter.converter.dtoToModelForUpdate(centerVisitReportUpdateDTO)
        var  resultCvr=centerVisitReportRepo.findById(CenterVisitReportId(cvr.centerVisitReportId!!.centerVisitReportId))

        if(resultCvr.isPresent) {
            var resultingCVR = resultCvr.get()
            resultingCVR.updateCenterVisitReport(cvr)
            var updateCvr = centerVisitReportRepo.save(resultingCVR)
            if (updateCvr != null) {
            var updateCvrDto = CenterVisitReportDtoConverter.converter.modelToDtoConverter(updateCvr)

                return Either.right(updateCvrDto)
            }
        }

        return Either.left(Exception("Exception Occured on updation of Center Visit Report......"))
    }



    override fun getCenterVisitReportByFrontEndId(frontEndId: String): Either<Exception, List<CenterVisitReportDto>> {
        var resultcvrlist = centerVisitReportRepo.findByFrontEndId(frontEndId)
        var resultcvrdtolist = ArrayList<CenterVisitReportDto>()
        if (resultcvrlist.size < 1) {
            return Either.right(resultcvrdtolist)
        } else {
            if (resultcvrlist.size >= 1) {
                for (resultcvr in resultcvrlist) {
                    var cvrdto = CenterVisitReportDtoConverter.converter.modelToDtoConverter(resultcvr)
                    resultcvrdtolist.add(cvrdto)

                }
                return Either.right(resultcvrdtolist)
            }
        }
        return Either.left(Exception("Exception raised on  Getting Center visit report by passing FrontEndId........."))
    }

    override fun changeStatus(centerVisitReportStatus: CenterVisitReportStatus,centerVisRepoId:String):Either<Exception,CenterVisitReportDto> {
        var cenVisRep = centerVisitReportRepo.findById(CenterVisitReportId(centerVisRepoId))
        if (!cenVisRep.isPresent) {
            return Either.left(Exception("Center Visit Report not found"))
        }
        cenVisRep.get().centerVisitReportStatus = centerVisitReportStatus
        //var modelCvr = CenterVisitReportDtoConverter.converter.dtoToModelConverter(cenVisRep.get())
        var updateVisRep = centerVisitReportRepo.saveAndFlush(cenVisRep.get())
        var cvrDto = CenterVisitReportDtoConverter.converter.modelToDtoConverter(updateVisRep)

        return Either.right(cvrDto)
    }

  override fun getMonthWiseCenterVisitReportByCenterId(centerId: String,centerVisitReportmonth: String): Either<Exception,List<CenterVisitReportDto>>{
        val formatter = DateTimeFormatter.ofPattern("MM-yyyy")
        val ym = YearMonth.parse(centerVisitReportmonth, formatter)
        var startDate : LocalDate
        var endDate : LocalDate
        /*if(localDate.dayOfMonth>21){
            startDate = LocalDate.of(localDate.year, localDate.month, 1)
            endDate = LocalDate.of(localDate.year, localDate.month-1, 21)
        }
        else{
            startDate = LocalDate.of(localDate.year, localDate.month-1, 22)
            endDate = LocalDate.of(localDate.year, localDate.month, 21)
        }*/

      startDate = LocalDate.of(ym.year, ym.month, 1)
      endDate = LocalDate.of(ym.year, ym.month, ym.lengthOfMonth())


        var cvrDtoList=ArrayList<CenterVisitReportDto>()

        var cvrList=centerVisitReportRepo.findByCenterIdAndCenterVisitReportDateBetween(centerId,startDate,endDate)
        if(cvrList.size<1) {

            return Either.right(cvrDtoList)

        }else {
            if (cvrList.size >=1) {

                var cvrdto = CenterVisitReportDtoConverter.converter.modelToDtoConverterList(cvrList)
                cvrDtoList.addAll(cvrdto)

                return Either.right(cvrDtoList)
            }
        }
            return Either.left(Exception("Center Visit report Error occured................,center visit report not Found Corresponding Date,centerId "))
        }

    override fun getMonthWiseCenterVisitReportByYearMonthAndFrontEndId(yearMonth: String,frontEndId:String): Either<Exception, List<CenterVisitReportDto>> {
        var startDate:LocalDate
        var endDate:LocalDate
        var formatter = DateTimeFormatter.ofPattern("MM-yyyy");
        var ym = YearMonth.parse(yearMonth, formatter);
        startDate=LocalDate.of(ym.year,ym.month,1)
        endDate=LocalDate.of(ym.year,ym.month,ym.lengthOfMonth())
        var resultlist = centerVisitReportRepo.findByCenterVisitReportDateBetweenAndFrontEndId(startDate,endDate,frontEndId)

        var centerVisitReportDtoList = ArrayList<CenterVisitReportDto>()
        if(resultlist.size<1){

            return Either.right( centerVisitReportDtoList)
        }
        else {
            if (resultlist.size >=1) {
                var resultdto = CenterVisitReportDtoConverter.converter.modelToDtoConverterList(resultlist)
                centerVisitReportDtoList.addAll(resultdto)
                return Either.right(centerVisitReportDtoList)

            }
        }
        return Either.left(Exception(" Exception  occured   center visit report by YearMonth  frontEnd Id...........in centerVisit Report by year month...FAIL operation........."))


    }

    override fun resolvedTodoForCenterByCvrId(cvrId: String,resolvedCenterdtoList:List<TodoListCenterResolvedDto>): Either<Exception, CenterVisitReportDto> {
        var id=CenterVisitReportId(cvrId)
        //id.centerVisitReportId=cvrId
        var  result=centerVisitReportRepo.findById(id)
        if(result.isPresent)
        {
            result.get().todoForCenter!!.resolvedTodoListCenters(resolvedCenterdtoList)
            var resolvedResult=centerVisitReportRepo.save(result.get())
            var resolveddto=CenterVisitReportDtoConverter.converter.modelToDtoConverter(resolvedResult)
            if(resolveddto!=null){
                return Either.right(resolveddto)

            }
        }
        return Either.left(Exception("Center visit report Resolved  todo List Centers in  Todo For Center Not Updated in db."))
    }

    override fun resolvedTodoForApllByCvrId(cvrId: String, resolvedApllDtoList:List<TodoListApllResolvedDto>): Either<Exception, CenterVisitReportDto> {
        var id=CenterVisitReportId()
        id.centerVisitReportId=cvrId

        var cvrResult=centerVisitReportRepo.findById(id)
        if(cvrResult.isPresent){
            cvrResult.get().todoForApll!!.resolvedTodoListAplls(resolvedApllDtoList)
            var resolvedResult=centerVisitReportRepo.save(cvrResult.get())
            var resolvedDto=CenterVisitReportDtoConverter.converter.modelToDtoConverter(resolvedResult)
            if(resolvedDto!=null){
                return Either.right(resolvedDto)

            }
        }
        return Either.left(Exception("Error in updating resolved  todo for apll By Center visit report Id...."))
    }

    override fun getCenterName(requestTypeId:String,centerType:String):Either<Exception,CenterDTO>{
        var a : String = TypeOfRequest.EXISTING_CENTER_REQUEST.toString()

        if(centerType.equals("EXISTING_CENTER")){
            var result=centerService.getCenterName(requestTypeId)
            if(result.isRight){
                return Either.right(result.get())
            }
            return Either.left(result.left)
        }
        else if(centerType.equals("EXISTING_CENTER_REQUEST")){
            var result=visReqService.findCenterNameByRequestId(requestTypeId)
            if(result.isRight){
                return Either.right(result.get())
            }
            return Either.left(result.left)
        }
        return Either.left(Exception("This is not correct request Type."))
    }


}

