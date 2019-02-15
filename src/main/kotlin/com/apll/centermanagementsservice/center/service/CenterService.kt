package com.apll.centermanagementsservice.center.service

import com.apll.centermanagementsservice.center.model.Center
import com.apll.centermanagementsservice.center.model.CenterId
import com.apll.centermanagementsservice.center.model.CenterState
import com.apll.centermanagementsservice.center.model.dto.*
import com.apll.centermanagementsservice.center.reposotory.CenterRepo
import com.apll.centermanagementsservice.mail.MailOutputStream
import com.apll.centermanagementsservice.mail.User
import com.apll.centermanagementsservice.regionAndsubRegion.service.RegionService
import com.apll.centermanagementsservice.regionAndsubRegion.service.SubRegionService
import com.apll.centermanagementsservice.restTemplet.EmployeeService
import com.apll.centermanagementsservice.schedulemanagement.service.IFrontEndScheduleService
import com.apll.centermanagementsservice.visitingrequest.repository.VisitingRequestRepository
import io.vavr.control.Either
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service
import org.springframework.util.MimeTypeUtils
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Service
class CenterService:ICenterService {
    @Autowired
    private lateinit var repository: CenterRepo

//    @Autowired
//    private lateinit var fESRepository: FrontEndScheduleRepo

    @Autowired
    private lateinit var empService:EmployeeService

    @Autowired
    private var srService: SubRegionService?= null

    @Autowired
    private var regionService:RegionService?=null

    @Autowired
    private lateinit var repository1: VisitingRequestRepository

    @Autowired
    private lateinit var fesService:IFrontEndScheduleService

    @Autowired
    private lateinit var mailService: MailOutputStream


    /***
     * save center
     */
    override
    fun register(centerStateDto: CenterStateDTO): Either<Exception, CenterStateDtoWithSR> {

        //Checking :: Sub-Region is valid or not.
        var subRegionDTO=srService!!.getSubRegionById(centerStateDto.subRegionId!!)
        if(subRegionDTO.isLeft){
            return Either.left(subRegionDTO.left)
        }
        var center= CenterStateDTOConverter.converter.dtoToModelConvert(centerStateDto)
        center.idInitializer()
        center= repository!!.save(center)
        if(center!=null){
            var centerDto= CenterStateDTOConverter.converter.modelToDtoConvertWithSR(center)
            centerDto.subRegionDTO=subRegionDTO.get()

            //Send Conformation Mail
            var message="Center Name : "+center.centerName+", Owner Name : "+center.nameOfOwner+". Center Registered successfully."
            var user=User(center.emailId,"Center-Created",message)
       //  var user=User(center.centerName,center.nameOfOwner)
            mailService.sendMail().send(MessageBuilder.withPayload(user)
                    .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                    .build())
            return Either.right(centerDto)
        }
        return Either.left(Exception("Center Not Register"))
    }

    /***
     * get centerById
     */
    override
    fun getCenterById(centerId:String): Either<Exception, CenterStateDtoWithSR> {
        val id= CenterId(centerId)
        var centerState=repository!!.findById(id)

        if(centerState.isPresent){
            var centerDtoWithSR=CenterStateDTOConverter.converter.modelToDtoConvert(centerState.get())

            var subRegion=srService!!.getSubRegionById(centerState.get().subregionId)
            if(subRegion.isLeft){
                return Either.left(Exception("Sub Region Not Found"))
            }
            centerDtoWithSR.subRegionDTO=subRegion.get()

            return Either.right(centerDtoWithSR)
        }
        return Either.left(Exception("Center Not Found"))
    }

    /***
     * get All Centers
     */
    override
    fun findAllCenter():Either<Exception,List<CenterStateDtoWithSR>>{

        var centerStateList=repository!!.findAll()

        if (centerStateList.size>0){
            var centerStateDtoWithSRList=CenterStateDTOConverter.converter.modelToDtoListConvert(centerStateList)

            var subRegions=srService!!.getAllSubRegion()
            if(subRegions.isLeft){
                return Either.left(subRegions.left)
            }

            var subRegionMap=subRegions.get().map { x->x.subRegionId!!.trim() to x }.toMap()

            for(center in centerStateList){
                centerStateDtoWithSRList.forEach{
                    if(it.centerId.equals(center.centerId.centerId)){
                        it.subRegionDTO= subRegionMap.get(center.subregionId.trim())!!
                    }
                }
            }

         return Either.right(centerStateDtoWithSRList)
        }
        return Either.left(Exception("Center is not found"))
    }

    /***
     * update Center
     */
    override
    fun editCenter(dto: CenterUpdateDTO):Either<Exception,CenterStateDtoWithSR>{

        var updateCenter=CenterConvertor.convertor.dtoToModel(dto)

        var oldCenter=repository.findById(CenterId(dto.centerId!!))

        if (!oldCenter.isPresent) {
            return Either.left(Exception("Center not found."))
        }
        var center=Center(oldCenter.get())

        center.updateCenter(updateCenter.centerState)

        var centerStateResult=repository.save(oldCenter.get())

        var centerStateDtoWithSR=CenterStateDTOConverter.converter.modelToDtoConvertWithSR(centerStateResult)

        //Checking :: Sub-Region is valid or not.
        var subRegionDTOR = srService!!.getSubRegionById(oldCenter.get().subregionId)
        if (subRegionDTOR.isLeft) {
            return Either.left(subRegionDTOR.left)
        }
        centerStateDtoWithSR.subRegionDTO=subRegionDTOR.get()

        if (centerStateDtoWithSR!=null){
            return Either.right(centerStateDtoWithSR)
        }
        return Either.left(Exception("Center not updated"))
    }

    /***
     * find Center by sub region Id
     */
    override
    fun getCenterBySubRegionId(subregionId: String):Either<Exception,Set<CenterStateDtoWithSR>>{

        var centerState=repository.findBySubregionId(subregionId)
        if (!(centerState.size>0)){
            return Either.left(Exception("Center not found for given sub-region"))
        }
        var centerStateDtoWithSR=CenterStateDTOConverter.converter.modelToDtoListConvert(centerState.toList())

        for(center in centerState){
            var subregionId=center.subregionId
            var subRegion=srService!!.getSubRegionById(subregionId)
            centerStateDtoWithSR.forEach{
                if(it.centerId.equals(center.centerId.centerId)){
                    it.subRegionDTO=subRegion.get()
                }
            }
        }
        return Either.right(centerStateDtoWithSR.toSet())
    }

    override
    fun getAllCenterStatesByRegionId(regionId: String):Either<Exception,List<CenterStateDtoWithSR>> {
        var centerStateList = ArrayList<CenterStateDtoWithSR>()
        var resultregion = regionService!!.getRegionById(regionId)
        if (resultregion.isRight) {
            var subregionlist = resultregion.get().subRegionDtos

            for (subregion in subregionlist!!) {
                /*var centers = getCenterBySubRegionId(subregion.subRegionId)
                if (centers.isRight) {
                    centerStateList.addAll(centers.get())
                }*/
                val subRegionCenter=getCenterBySubRegionId(subregion.subRegionId!!)
                if(subRegionCenter.isRight && subRegionCenter.get().size>0) {
                    centerStateList.addAll(subRegionCenter.get())
                }
            }
            if(centerStateList.size<1){
                return Either.left(Exception("Center not found for given Region"))
            }
            return Either.right(centerStateList)
        }
        return Either.left(Exception("not getting Regions................."))
    }


    //NOT USED
    override
    fun getLastTwoMonthNotVisitedCentersAccRegion(localDate: LocalDate, regionId: String): Either<Exception, List<CenterStateDtoWithSR>> {

       var startDate:LocalDate
       var endDate:LocalDate

       if(localDate.dayOfMonth>21){
            startDate = LocalDate.of(localDate.year, localDate.month-1, 1)
            endDate = LocalDate.of(localDate.year, localDate.plusMonths(1).month,localDate.plusMonths(1).dayOfMonth)
       }
       else{
            startDate = LocalDate.of(localDate.year, localDate.month-2, 22)
            endDate = LocalDate.of(localDate.year, localDate.month, 21)
       }

        var visitingRequestlist = repository1.findByRequestDateBetween(startDate,endDate)
        var centers=getAllCenterStatesByRegionId(regionId)
        if(centers.isLeft) {
            return Either.left(Exception("Centre Not Found."))
        }
        var notVisCen=HashSet<CenterStateDtoWithSR>()

        for(cen in centers.get()){
            var result = visitingRequestlist.stream().filter{it.centerId.centerId==cen.centerId}.findAny()
            if(!result.isPresent){
                notVisCen.add(cen)
            }
        }
        if(notVisCen.size<1) {
            return Either.left(Exception("Within Region Every Center are visited Successfully"))
        }
        return Either.right(notVisCen.toList())
    }

    private fun getCenterByEmployeeId(employeeId: String):Either<Exception,Set<CenterState>> {
        var empEither = empService.getEmployee(employeeId)
        if (empEither.isLeft) {
            return Either.left(empEither.left)
        }

        if(empEither.get().listOfCenters!=null && empEither.get().listOfCenters!!.size<1){
            return Either.right(HashSet())
        }

        var centers=HashSet<CenterState>()
        empEither.get().listOfCenters!!.forEach {
            var center=repository.findById(CenterId(it.centerId!!))
            if(center.isPresent) {
                centers.add(center.get())
            }
        }
        return Either.right(centers)
    }

    override
    fun getLastTwoMonthNotVisitedCentersAccEmployeeId(monthYear: String, employeeId: String):
            Either<Exception, List<CenterStateDtoWithSR>> {

        val formatter = DateTimeFormatter.ofPattern("MM-yyyy")
        val secondMonth = YearMonth.parse(monthYear.trim(), formatter)

        var firstMonth:YearMonth=secondMonth.minusMonths(1)

        var startDate = LocalDate.of(firstMonth.year, firstMonth.month, 1)
        var endDate = LocalDate.of(secondMonth.year, secondMonth.month, secondMonth.lengthOfMonth())


        var visitingRequestlist = repository1.findByRequestDateBetween(startDate,endDate)

        val visCenterId=visitingRequestlist.map { x->x.centerId.centerId }.toSet()

        var centerEither=getCenterByEmployeeId(employeeId)
        if(centerEither.isLeft){
            return Either.left(centerEither.left)
        }
        var centerStates=centerEither.get()

        centerStates=centerStates.filter { x->!visCenterId.contains(x.centerId.centerId)}.toSet()
        if(centerStates.size<1){
            return Either.right(ArrayList<CenterStateDtoWithSR>())
        }
        //checking centerId with visitReq Id
        var requestId=fesService.findFrontEndScheduleByDates(startDate,endDate)
        centerStates= centerStates.filter { x->!requestId.contains(x.centerId.centerId) }.toSet()

        if (centerStates.size<1){
            return Either.right(ArrayList<CenterStateDtoWithSR>())
        }

        var dto=CenterStateDTOConverter.converter.modelToDtoListConvert(centerStates.toList())
        //Set Sub-Region to Dto
        for(center in centerStates){
            var subregionId=center.subregionId
            var subRegion=srService!!.getSubRegionById(subregionId)

            dto.forEach{
                if(it.centerId.equals(center.centerId.centerId)){
                    it.subRegionDTO=subRegion.get()
                }
            }
        }
        return Either.right(dto)
    }

    override
    fun getCenterName(centerId:String):Either<Exception,CenterDTO>{
        var center=repository.findById(CenterId(centerId))
        if(center.isPresent){
            var cid=center.get().centerId.centerId
            var cname=center.get().centerName

            var centerDTO=CenterDTO(cid,cname)
            return Either.right(centerDTO)
        }
        return Either.left(Exception("Center not found."))
    }
}