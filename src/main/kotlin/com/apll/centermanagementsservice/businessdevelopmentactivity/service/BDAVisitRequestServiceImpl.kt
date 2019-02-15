package com.apll.centermanagementsservice.businessdevelopmentactivity.service


import com.apll.centermanagementsservice.businessdevelopmentactivity.controller.bdadto.*
import com.apll.centermanagementsservice.businessdevelopmentactivity.model.BDAVisitRequestId
import com.apll.centermanagementsservice.businessdevelopmentactivity.repository.BDAVisitRequestRepo
import com.apll.centermanagementsservice.events.ReportCreatedEvent
import com.apll.centermanagementsservice.events.ReportOutputStream
import io.vavr.control.Either
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service
import org.springframework.util.MimeTypeUtils
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

@Service
class BDAVisitRequestServiceImpl:BDAVisitRequestService {

    //For publish the Event
    @Autowired
    private lateinit var stream: ReportOutputStream


    @Autowired
    lateinit var bdaRepo: BDAVisitRequestRepo


    override fun getBDAVisitRequestWithReportById(bdaRequestId: String): Either<Exception, BDAVisitRequestDto> {
        var bdaId=BDAVisitRequestId(bdaRequestId)
        var result=bdaRepo.findById(bdaId)
        if(result.isPresent){
            var bdaRequestResult=result.get()
            var dto= BDAVisitRequestDtoConvertor.Convertor.modelToDtoConvertor(bdaRequestResult)
            return Either.right(dto)
        }
        return Either.left(Exception("Business Development Activity Request  Not Found"))
    }


    override fun insertBusinessDevelopmentActivity(businessDevelopmentActivitydto: BDARequestDto): Either<Exception, BDARequestDto> {
        var bda = BDAVisitRequestWithCenterAndAddressDTOConvertor.Convertor.dtoTOModelConvertor(businessDevelopmentActivitydto)
        bda.idInitilizer()
        var resultbda = bdaRepo.save(bda)
        if (resultbda != null) {
            var resultbdadto = BDAVisitRequestWithCenterAndAddressDTOConvertor.Convertor.modelToDtoConvertor(resultbda)
            return Either.right(resultbdadto)
        }
        return Either.left(Exception("Bussiness Development Activity is not Inserted..................."))
    }


    override fun getBusinessDevelopmentActivityById(businessDevelopementActivityId: String): Either<Exception, BDARequestDto> {
        var id = BDAVisitRequestId()
        id.bdaVisitRequestId = businessDevelopementActivityId
        var resultBDA = bdaRepo.findById(id)
        var resulting = resultBDA.get()

        if (resulting != null) {
            var resultBDADto = BDAVisitRequestWithCenterAndAddressDTOConvertor.Convertor.modelToDtoConvertor(resulting)
            return Either.right(resultBDADto)
        }
        return Either.left(Exception("Bussiness Developement Activity Id Not Found................fail......"))
    }


    override fun updateBusinessDevelopmentActivity(businessDevelopmentActivitydto: BDARequestDto): Either<Exception, BDARequestDto> {
        var newBDARequest = BDAVisitRequestWithCenterAndAddressDTOConvertor.Convertor.dtoTOModelConvertor(businessDevelopmentActivitydto)
            var oldBDARequest = bdaRepo.findById(newBDARequest.bdaVisitRequestId!!)
            if (!oldBDARequest.isPresent) {
                return Either.left(Exception("Business Development Activity not founded."))
            }
          oldBDARequest.get().updateBDARequest(newBDARequest)
          oldBDARequest.get().updateBDARequestWithCenterOrAddress()
        var resultbda = bdaRepo.saveAndFlush(oldBDARequest.get())
            var resultBDADto = BDAVisitRequestWithCenterAndAddressDTOConvertor.Convertor.modelToDtoConvertor(resultbda)
        if (resultBDADto != null) {
            return Either.right(resultBDADto)
        }
         return Either.left(Exception("Bussiness Developement Activity Not Update................fail......"))
    }


    override fun getAllByFrontEndId(frontEndId: String): Either<Exception, List<BDAVisitRequestDto>> {
        var bdaVisitRequestList = bdaRepo.findByFrontEndId(frontEndId)
        var bdaVisitRequestDtoList = ArrayList<BDAVisitRequestDto>()
        if(bdaVisitRequestList.size<1){
            return Either.right(bdaVisitRequestDtoList)
        }else {
            if (bdaVisitRequestList.size >=1) {
                for (bdaVisitRequest in bdaVisitRequestList) {
                    var bdaVisitRequestdto = BDAVisitRequestDtoConvertor.Convertor.modelToDtoConvertor(bdaVisitRequest)
                    bdaVisitRequestDtoList.add(bdaVisitRequestdto)
                }
                return Either.right(bdaVisitRequestDtoList)
            }
        }
        return Either.left(Exception(" Excepion Raised on Bussiness Developement Activity Visit Request by frontEnd Id not found."))
    }


    override fun getOneMonthBdaVisitRequestsByregionIdAndDate(regionId: String, yearMonth: String): Either<Exception, List<BDAVisitRequestDto>> {
        val formatter = DateTimeFormatter.ofPattern("MM-yyyy")
        val ym = YearMonth.parse(yearMonth, formatter)
        var srtDate: LocalDate
        var edDate: LocalDate

        srtDate = LocalDate.of(ym.year, ym.month, 1)
        edDate = LocalDate.of(ym.year, ym.month, ym.lengthOfMonth())

        var bdaVisitRequestList = bdaRepo.findBybdaDateAndRegionId(regionId, srtDate, edDate)
        var bdaRequestDtoList = ArrayList<BDAVisitRequestDto>()
        if (bdaVisitRequestList.size < 1) {
            return Either.right(bdaRequestDtoList)
        }
        else{
            if(bdaVisitRequestList.size >=1){
        var dtoList = BDAVisitRequestDtoConvertor.Convertor.modelListToDtoList(bdaVisitRequestList)
        bdaRequestDtoList.addAll(dtoList)
        return Either.right(bdaRequestDtoList)
    }
    }
    return Either.left(Exception("Exception in  get Bda Visit Request by region Id and yearMonth....."))
}


    override fun getMonthWiseBdaVisitRequestByFrontEndIdAndYearMonth(yearMonth:String,frontEndId:String): Either<Exception, List<BDAVisitRequestDto>> {
        val formatter = DateTimeFormatter.ofPattern("MM-yyyy")
        val ym = YearMonth.parse(yearMonth, formatter)

        var srtDate: LocalDate
        var edDate: LocalDate

        srtDate = LocalDate.of(ym.year, ym.month, 1)
        edDate = LocalDate.of(ym.year, ym.month, ym.lengthOfMonth())

        var resultBdaList = bdaRepo.findByStartDateBetweenAndFrontEndId(srtDate, edDate, frontEndId)
        var resultBdaDtoList = ArrayList<BDAVisitRequestDto>()
        if ( resultBdaList.size<1) {
            return Either.right(resultBdaDtoList)
        }else {
            if (resultBdaList.size >=1) {
                //var list=resultBdaList.stream().sorted { bda1, bda2 ->bda1.startDate.compareTo(bda2.startDate) }.toList()
                var dtoList = BDAVisitRequestDtoConvertor.Convertor.modelListToDtoList(resultBdaList)
                resultBdaDtoList.addAll(dtoList)
                Collections.sort(resultBdaDtoList, { bda1, bda2 -> bda1.startDate!!.compareTo(bda2.startDate) })

                return Either.right(resultBdaDtoList)
            }
        }
        return Either.left(Exception(" Exception raised on Bda Visit request by  FrontEndId AndYearMonth returned.."))
    }


    override fun fileBdaReport(bdaVisitReportDto: BDAVisitReportDto): Either<Exception, BDAVisitRequestDto> {
        var bdarequestId =BDAVisitRequestId( bdaVisitReportDto.bdaVisitRequestId!!)
        var bdaRequestOptional =bdaRepo.findById(bdarequestId)
        if(bdaRequestOptional.isPresent){
            var  bdaRequest=bdaRequestOptional.get()
            var bdaVisitReport = BDAVisitReportDTOConvertor.convertor.dtoToModelConvertor(bdaVisitReportDto)
            var result=bdaRequest.validateReport(bdaVisitReport)
            if(result.isRight){
                var list= ArrayList(bdaRequest.bdaReports)
                list.add(bdaVisitReport)
                bdaRequest.bdaReports=list

                bdaRequest.updateBDARequest()

                var res=bdaRepo.save(bdaRequest)
                if(res!=null){
                    var dtoBda= BDAVisitRequestDtoConvertor.Convertor.modelToDtoConvertor(res)

                    //Raised Event to Complete Schedule.
                    var event=ReportCreatedEvent(bdaVisitReportDto)
                    val messageChannel = stream.publish()
                    messageChannel.send(MessageBuilder.withPayload(event)
                            .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                            .build())

                    return Either.right(dtoBda)
                }
            }
            return Either.left(result.left)
        }
        return Either.left(Exception("Exception Occured in Fired BDA Visit Report..not found.."))
    }


    override fun getAllBDAVisitRequestWithBDAReports(): Either<Exception, List<BDAVisitRequestDto>> {
        var bdaVisitRequestList=bdaRepo.findAll()
        var   bdaVisitRequestDtoList=ArrayList<BDAVisitRequestDto>()
        if(bdaVisitRequestList.size<1){
            return Either.right(bdaVisitRequestDtoList)

        }else{
            if(bdaVisitRequestList.size>=1){
                var dtoList= BDAVisitRequestDtoConvertor.Convertor.modelListToDtoList(bdaVisitRequestList)
                bdaVisitRequestDtoList.addAll(dtoList)
                Collections.sort(bdaVisitRequestDtoList, { bda1, bda2 -> bda1.startDate!!.compareTo(bda2.startDate) })
                return Either.right(bdaVisitRequestDtoList)
            }
        }
        return Either.left(Exception("Exception raised getting All BDA Visit Report with BDA Report.. fail .."))
    }

}


/*override fun insertBusinessDevelopmentActivity(dto: BDAVisitDTO): Either<Exception, List<BDARequestDto>> {
        var bda = BDAVisitDTOConverter.converter.dtoToModelConverter(dto)
        var bdaVisitRequestList=ArrayList<BDARequestDto>()

        for (bdaVisitRequest in bda){
            var resultbda = bdaRepo.save(bdaVisitRequest)
            if (resultbda != null) {
                var resultbdadto = BDAVisitRequestDtoConvertor.Convertor.modelToDtoConvertor(resultbda)
                bdaVisitRequestList.add(resultbdadto)
            }
        }
        return Either.right(bdaVisitRequestList)
        //return Either.left(Exception("BussinessDevelopmentActivity is not inserted..................."))
    }*/