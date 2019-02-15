package com.apll.centermanagementsservice.visitingrequest.service



import com.apll.centermanagementsservice.center.model.dto.CenterDTO
import com.apll.centermanagementsservice.restTemplet.EmployeeService
import com.apll.centermanagementsservice.schedulemanagement.service.FrontEndScheduleServiceImpl
import com.apll.centermanagementsservice.visitingrequest.model.RequestId
import com.apll.centermanagementsservice.visitingrequest.model.RequestType
import com.apll.centermanagementsservice.visitingrequest.controller.visitrequestdto.ResolvedDTO
import com.apll.centermanagementsservice.visitingrequest.controller.visitrequestdto.VisitingRequestDTO
import com.apll.centermanagementsservice.visitingrequest.controller.visitrequestdto.VisitingRequestDTOConverter
import com.apll.centermanagementsservice.visitingrequest.dashboard.VisReqResolvedAnalysis
import com.apll.centermanagementsservice.visitingrequest.dashboard.VisitRequestAnalysis
import com.apll.centermanagementsservice.visitingrequest.repository.VisitingRequestRepository
import io.vavr.control.Either
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.ChronoUnit


@Service
class VisitingRequestServiceImpl: VisitingRequestService {

    @Autowired
    lateinit var repository: VisitingRequestRepository

    @Autowired
    lateinit var empService: EmployeeService

    @Autowired
    lateinit var scheduleService: FrontEndScheduleServiceImpl


    override fun getAllVisitingRequest(): Either<Exception, List<VisitingRequestDTO>> {
        var visitRequestList = repository.findAll()

        if (visitRequestList.size < 1) {
            return Either.left((Exception("visiting Request not found ...Fail..")))
        }
        var requestDtolist = ArrayList<VisitingRequestDTO>()
        for (requests in visitRequestList) {
            var dto = VisitingRequestDTOConverter.Convertor.modelToDTOConvertor(requests)
            requestDtolist.add(dto)
        }
        return Either.right(requestDtolist)
    }


    override fun updateVisitingRequest(dto: VisitingRequestDTO): Either<Exception, VisitingRequestDTO> {
        var oldVisRequest=repository.findById(RequestId(dto.requestId))
        if(!oldVisRequest.isPresent){
            return Either.left(Exception("visiting Request not Found"))
        }
        var visitrequest = VisitingRequestDTOConverter.Convertor.dtoToModelConvertor(dto)
        oldVisRequest.get().update(visitrequest)

        var request = repository.save(oldVisRequest.get())
        var dto = VisitingRequestDTOConverter.Convertor.modelToDTOConvertor(request)
        if (dto != null && !dto.equals(visitrequest)) {
            return Either.right(dto)
        }
        return Either.left(Exception("visiting Request is not Updated"))
    }


    override fun getVisitingRequestById(id: String): Either<Exception, VisitingRequestDTO> {
        var requestId = RequestId()
        requestId.requestId = id

        var visitEither = repository.findById(requestId)
        var visiting = visitEither.get()
        var dto = VisitingRequestDTOConverter.Convertor.modelToDTOConvertor(visiting)
        if (dto != null) {
            return Either.right(dto)
        }
        return Either.left(Exception("visiting Request  not found "))
    }

    override fun saveVisitingRequest(dto: VisitingRequestDTO): Either<Exception, VisitingRequestDTO> {
        var visitRequest = VisitingRequestDTOConverter.Convertor.dtoToModelConvertor(dto)
        visitRequest.saveVisReq()
        var either = repository.save(visitRequest)
        var dto = VisitingRequestDTOConverter.Convertor.modelToDTOConvertor(either)
        if (dto != null) {
            return Either.right(dto)
        }
        return Either.left(Exception("request not Inserted"))
    }

    override fun getVisitingRequestByRequest(requestBy: RequestType): Either<Exception, List<VisitingRequestDTO>> {

        var resultvisitingreq = repository.findByRequestBy(requestBy)
        if (resultvisitingreq.size < 1) {
            return Either.left(Exception("returned   Empty list.......................fail condition.."))
        }
        var visitingRequestDtoList = ArrayList<VisitingRequestDTO>()
        for (visitingrequest in resultvisitingreq) {

            var visitingRequestdto = VisitingRequestDTOConverter.Convertor.modelToDTOConvertor(visitingrequest)
            visitingRequestDtoList.add(visitingRequestdto)
        }
        return Either.right(visitingRequestDtoList)
    }


    override fun getVisitingRequestByRequesterId(requesterId: String): Either<Exception, List<VisitingRequestDTO>> {
        var visitingRequester = repository.findByRequesterId(requesterId)
        if (visitingRequester.size < 1) {
            return Either.left(Exception("Returned VistingRequestRequester Empty list.....fail condition.."))
        }
        var visitingRequestDtoList = ArrayList<VisitingRequestDTO>()
        for (visitingrequest in visitingRequester) {

            var visitingRequestdto = VisitingRequestDTOConverter.Convertor.modelToDTOConvertor(visitingrequest)
            visitingRequestDtoList.add(visitingRequestdto)
        }
        return Either.right(visitingRequestDtoList)
    }


    override fun getVisitingRequestByRegionId(regionId: String): Either<Exception, List<VisitingRequestDTO>> {

        var visitingRegionId = repository.findByRegionId(regionId)
        if (visitingRegionId.size < 1) {
            return Either.left(Exception("Returned VistingRequest RegionId Empty list.....fail condition.."))
        }
        var visitingRequestDtoList = ArrayList<VisitingRequestDTO>()
        for (visitingRequest in visitingRegionId) {

            var visitingRequestDto = VisitingRequestDTOConverter.Convertor.modelToDTOConvertor(visitingRequest)
            visitingRequestDtoList.add(visitingRequestDto)
        }
        return Either.right(visitingRequestDtoList)
    }


    override fun getResolvedVisitingRequest(regionId: String): Either<Exception, List<VisitingRequestDTO>> {

        var visitingRegionId = repository.findResolvedVisitingRequest(regionId)
        if (visitingRegionId.size < 1) {
            return Either.left(Exception("Returned VistingRequest Empty list.....fail condition.."))
        }
        var visitingRequestDtoList = ArrayList<VisitingRequestDTO>()
        for (visitingRequest in visitingRegionId) {

            var visitingRequestDto = VisitingRequestDTOConverter.Convertor.modelToDTOConvertor(visitingRequest)
            visitingRequestDtoList.add(visitingRequestDto)
        }
        return Either.right(visitingRequestDtoList)
    }


    override fun resolve(dto: ResolvedDTO): Either<Exception, VisitingRequestDTO> {

        var visitRequest = repository.findById(RequestId(dto.requestId!!))
        if (visitRequest.isPresent) {
            visitRequest.get().resolved(dto)
            var either = repository.save(visitRequest.get())
            var dtos = VisitingRequestDTOConverter.Convertor.modelToDTOConvertor(either)
            if (dtos != null) {
                return Either.right(dtos)
            }
        }
        return Either.left(Exception("Visiting Request not Resolve"))
    }


    /***
     * Not Scheduled by Employee-Id.[For Scheduled]
     */
    override
    fun getNotScheduledRequestByEmployeeId(employeeId: String): Either<Exception, List<VisitingRequestDTO>> {
        var employeeEither = empService.getEmployee(employeeId)
        if (employeeEither.isLeft) {
            return Either.left(employeeEither.left)
        }
        if (employeeEither.get().listOfCenters == null || employeeEither.get().listOfCenters!!.size < 1) {
            return Either.right(ArrayList())
        }

        var centersId = employeeEither.get()!!.listOfCenters!!.map { it.centerId }.toSet()
        var visitingRequests = repository.findAll()

        var visReqForEmployee = visitingRequests.
                filter { x -> centersId.contains(x.centerId.centerId) }.toSet()

        if (visReqForEmployee.size < 1) {
            return Either.right(ArrayList())
        }

        //Getting already Scheduled Visit Request
        var existingVisReqId = scheduleService.getExitingVisitRequestId()
        if (existingVisReqId.isLeft) {
            var visitingRequestDTO = VisitingRequestDTOConverter.Convertor.modelToDtoList(visReqForEmployee.toList())
            return Either.right(visitingRequestDTO)
        }

        //Filter the schedule which is not scheduled.
        visReqForEmployee = visReqForEmployee.filter { x -> !existingVisReqId.get().contains(x.requestId.requestId) }.toSet()
        if (visReqForEmployee.size < 1) {
            return Either.right(ArrayList())
        }

        var visitingRequestDTO = VisitingRequestDTOConverter.Convertor.modelToDtoList(visReqForEmployee.toList())
        return Either.right(visitingRequestDTO)
    }


    override
    fun getByEmployeeId(employeeId: String): Either<Exception, List<VisitingRequestDTO>> {
        var employeeEither = empService.getEmployee(employeeId)
        if (employeeEither.isLeft) {
            return Either.left(employeeEither.left)
        }
        if (employeeEither.get().listOfCenters == null || employeeEither.get().listOfCenters!!.size < 1) {
            return Either.right(ArrayList())
        }
        var centersId = employeeEither.get()!!.listOfCenters!!.map { it.centerId }.toSet()
        var visitingRequests = repository.findAll()

        var visReqForEmployee = visitingRequests.
                filter { x -> centersId.contains(x.centerId.centerId) }.toSet()

        if (visReqForEmployee.size < 1) {
            return Either.right(ArrayList())
        }
        /*var existingVisReqId = scheduleService.getExitingVisitRequestId()
        if (existingVisReqId.isLeft) {
            var visitingRequestDTO = VisitingRequestDTOConverter.Convertor.modelToDtoList(visReqForEmployee.toList())
            return Either.right(visitingRequestDTO)
        }
        visReqForEmployee = visReqForEmployee.filter { x -> !existingVisReqId.get().contains(x.requestId.requestId) }.toSet()
        if (visReqForEmployee.size < 1) {
            return Either.right(ArrayList())
        }*/
        var visitingRequestDTO = VisitingRequestDTOConverter.Convertor.modelToDtoList(visReqForEmployee.toList())
        return Either.right(visitingRequestDTO)
    }

    override
    fun findCenterNameByRequestId(requestId: String): Either<Exception,CenterDTO> {
        var id = RequestId()
        id.requestId = requestId

        var visitingRequest = repository.findById(id)
        if (visitingRequest.isPresent) {
            var cname=visitingRequest.get().centerName
            var cid=visitingRequest.get().centerId.centerId

            var centerDTO=CenterDTO(cid,cname)

            return Either.right(centerDTO)
        }
        return Either.left(Exception("Center Name not found "))
    }


    //For Dash-Board
    override fun visReqAnalysis():Either<Exception,VisitRequestAnalysis>{

        var visReqs=repository.findAll()

        var analysis=VisitRequestAnalysis()

        analysis.allVisReq=visReqs.size
        analysis.unResolvedVisReq=visReqs.filter { x->x.resolved!!.equals(false) }.size
        analysis.resolvedVisReq=visReqs.filter { x->x.resolved!!.equals(true) }.size

        return Either.right(analysis)
    }

    override fun visReqResolvedAnalysis():Either<Exception,VisReqResolvedAnalysis>{

        var visReqs=repository.findAll()
        var analysis=VisReqResolvedAnalysis()
        visReqs=visReqs.filter { x->x.resolved!!.equals(true)}

        visReqs.forEach {
            var days=ChronoUnit.DAYS.between(it.requestDate,it.resolvedDate)

            if(days<15){
                analysis.resolvedWithIn15Days++
            }
            else if(days<30){
                analysis.resolvedWithIn30Days++
            }
            else if(days<45){
                analysis.resolvedWithIn45Days++
            }
            else if(days<60){
                analysis.resolvedWithIn60Days++
            }
            else{
                analysis.resolvedAfter60Days++
            }
        }
        return Either.right(analysis)
    }

    @Override
    override fun last2MonthAnalysis():Either<Exception,VisitRequestAnalysis>{
        var cMonth=YearMonth.now()
        var endDate=LocalDate.of(cMonth.year,cMonth.month,cMonth.lengthOfMonth())
        var pMonth=cMonth.minusMonths(1)
        var startDate=LocalDate.of(pMonth.year,pMonth.month,1)

        var visReqs=repository.findByRequestDateBetween(startDate,endDate)

        var analysis=VisitRequestAnalysis()

        analysis.allVisReq=visReqs.size
        analysis.unResolvedVisReq=visReqs.filter { x->x.resolved!!.equals(false) }.size
        analysis.resolvedVisReq=visReqs.filter { x->x.resolved!!.equals(true) }.size

        return Either.right(analysis)
    }


    override
    fun getUnresolved():Either<Exception,List<VisitingRequestDTO>>{

        var result=repository.findByResolved(false)

        if (result.size < 1) {
            return Either.left(Exception("Pending Visiting Request Not Found..."))
        }
        var visitingRequestDto = VisitingRequestDTOConverter.Convertor.modelToDtoList(result)
        return Either.right(visitingRequestDto)

    }
}
