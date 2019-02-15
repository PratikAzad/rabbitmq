package com.apll.centermanagementsservice.schedulemanagement.service


import com.apll.centermanagementsservice.restTemplet.EmployeeService
import com.apll.centermanagementsservice.schedulemanagement.event.MessageType
import com.apll.centermanagementsservice.schedulemanagement.event.ScheduleEvent
import com.apll.centermanagementsservice.schedulemanagement.event.OperationType
import com.apll.centermanagementsservice.schedulemanagement.event.WebScoketOutputStream
import com.apll.centermanagementsservice.schedulemanagement.model.*
import com.apll.centermanagementsservice.schedulemanagement.model.dto.*
import com.apll.centermanagementsservice.schedulemanagement.repository.FrontEndScheduleRepo
import com.apll.websocketservice.model.WebScoketEvent
import io.vavr.control.Either
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
class FrontEndScheduleServiceImpl(val repo: FrontEndScheduleRepo,
                                  val empTemp: EmployeeService,
                                  val socketStream:WebScoketOutputStream):IFrontEndScheduleService {

    /*@Autowired
    lateinit var repo: FrontEndScheduleRepo*/

    override
    fun insertFrontEndSchedule(frontEndScheduleDTO: FrontEndScheduleDTO): Either<Exception, String> {

        var status = findFrontEndScheduleByIdAndDate(frontEndScheduleDTO.yearMonth!!,
                frontEndScheduleDTO.frontEndId!!)

        if (status) {
            return Either.left(Exception("Front-End Schedule already created with FrontEndId" +
                    " ${frontEndScheduleDTO.frontEndId}"))
        }
        var fSchedule = FrontEndScheduleDTOConvertor.convertor.dtoTOModelConvertor(frontEndScheduleDTO)

        var result = checkValidation(fSchedule)
        if (result.isLeft) {
            return Either.left(result.left)
        }

        var initializeCheck = fSchedule.idInitializer()
        if (initializeCheck.isLeft) {
            return Either.left(initializeCheck.left)
        }

        var empresult = empTemp.getEmployee(fSchedule.frontEndId)
        if (empresult.isLeft) {
            return Either.left(empresult.left)
        }
        fSchedule.frontEndName = empresult.get().employeeFullName

        repo.save(fSchedule)

        //Create-Front-End-Scheduled Event
        var eventMsg=ScheduleEvent(fSchedule, OperationType.CREATE)
        var event= WebScoketEvent(MessageType.Schedule,eventMsg)

        socketStream.publish().send(MessageBuilder.withPayload(event).
                setHeader(MessageHeaders.CONTENT_TYPE,MimeTypeUtils.APPLICATION_JSON).build())



        println("Schedule-Create-Event :: "+event)
        return Either.right("Front-End Schedule created")
    }


    private fun checkValidation(frontEndSchedule: FrontEndSchedule): Either<Exception, Boolean> {
        var frontEndSchedules = repo.findAll()
        if (frontEndSchedules.isEmpty()) {
            return Either.right(true)
        }
        var map = frontEndSchedules.groupBy { x -> x.frontEndScheduleId.frontEndScheduleId }
        var fESchedules = ArrayList<FrontEndSchedule>()
        map.forEach {
            var version = it.value.map { x -> x.version }.toSet().max()
            var fESchedule = it.value.stream().filter { x -> x.version == version }.findAny()
            if (fESchedule.isPresent) {
                fESchedules.add(fESchedule.get())
            }
        }
        var monthWiseSchedule = MonthWiseSchedule(frontEndSchedule.yearmonth, fESchedules)

        var checkConflict = monthWiseSchedule.scheduleValidation(frontEndSchedule)
        if (checkConflict.isLeft) {
            return Either.left(checkConflict.left)
        }
        return Either.right(true)
    }

    /*private fun checkValidation(frontEndSchedule:FrontEndSchedule):Either<Exception,Boolean>{
        var frontEndSchedules=repo.getMinimalReq()

        if (frontEndSchedules.isEmpty()) {
            return Either.right(true)
        }
        var map=frontEndSchedules.groupBy { x->x.frontEndScheduleId.frontEndScheduleId }

        var fESchedules=ArrayList<FrontEndSchedule>()
        map.forEach{
            var version=it.value.map { x->x.version }.toSet().max()
            var fESchedule=it.value.stream().filter { x->x.version==version }.findAny()
            if(fESchedule.isPresent) {
                fESchedules.add(fESchedule.get())
            }
        }

        var monthWiseSchedule=MonthWiseSchedule(frontEndSchedule.yearmonth,fESchedules)
        var checkConflict=monthWiseSchedule.scheduleValidation(frontEndSchedule)
        if(checkConflict.isLeft){
            return Either.left(checkConflict.left)
        }
        return Either.right(true)
    }*/


    override
    fun getAllFrontEndSchedule(): Either<Exception, List<FrontEndScheduleDayWiseDTO>> {
        var frontEndSchedules = repo.findAll()

        if (frontEndSchedules.isEmpty()) {
            return Either.left(Exception("Front-End Schedules not found"))
        }
        var map = frontEndSchedules.groupBy { x -> x.frontEndScheduleId.frontEndScheduleId }

        var fESchedules = ArrayList<FrontEndSchedule>()
        map.forEach {
            var version = it.value.map { x -> x.version }.toSet().max()
            var fESchedule = it.value.stream().filter { x -> x.version == version }.findAny()
            if (fESchedule.isPresent) {
                fESchedules.add(fESchedule.get())
            }
        }

        var frontEndScheduleDTOList = modelListToDto(fESchedules)
        return Either.right(frontEndScheduleDTOList)
    }


    override
    fun getByFrontEndScheduleId(frontEndScheduleId: String): Either<Exception, FrontEndScheduleDayWiseDTO> {


        var maxVersion = repo.maxVersion(FrontEndScheduleId(frontEndScheduleId))
        var frontEndSchedule = repo.findByVersionAndFrontEndScheduleId(maxVersion, FrontEndScheduleId(frontEndScheduleId))
        //setEmpName()

        if (!frontEndSchedule.isPresent()) {
            return Either.left(Exception("Front-End Schedule not found"))
        } else if (frontEndSchedule.get().frontEndName.equals(null)) {
            var emp = empTemp.getEmployee(frontEndSchedule.get().frontEndId)
            if (emp.isLeft) {
                return Either.left(Exception("Employee not found."))
            }
            frontEndSchedule.get().frontEndName = emp.get().employeeFullName
        }

        var fSDWDto = FrontEndScheduleDayWiseDTOConverter.converter.modelToDtoConvert(frontEndSchedule.get())
        return Either.right(fSDWDto)
        //Collections.sort(frontEndScheduleDTOList, { o1, o2 -> o1.yearmonth.compareTo(o2.yearmonth) })
    }

    private fun setEmpName(feScheduleList: List<FrontEndSchedule>, frontEndId: String): Either<Exception, List<FrontEndSchedule>> {
        if (feScheduleList.filter { it.frontEndName.equals(null) }.size > 0) {
            var emp = empTemp.getEmployee(frontEndId)
            if (emp.isLeft) {
                return Either.left(emp.left)
            }
            for (feSchedule in feScheduleList) {
                if (feSchedule.frontEndName.equals(null)) {
                    feSchedule.frontEndName = emp.get().employeeFullName
                }
            }
        }
        return Either.right(feScheduleList)
    }


    override
    fun getByFrontEndId(frontEndId: String): Either<Exception, List<FrontEndScheduleDayWiseDTO>> {

        var fESchedules = feScheduleByFEId(frontEndId)
        var result = setEmpName(fESchedules, frontEndId)
        if (result.isLeft) {
            return Either.left(result.left)
        }

        var frontEndScheduleDTOList = modelListToDto(fESchedules)
        Collections.sort(frontEndScheduleDTOList, { o1, o2 -> o1.yearmonth.compareTo(o2.yearmonth) })
        return Either.right(frontEndScheduleDTOList)
    }


    override
    fun getFrontEndScheduleByYearMonth(yearmonth: String, frontEndId: String):
            Either<Exception, FrontEndScheduleDayWiseDTO> {

        val formatter = DateTimeFormatter.ofPattern("MM-yyyy")
        val yearmonth = YearMonth.parse(yearmonth, formatter)

        var dtoList = getByFrontEndId(frontEndId)
        if (dtoList.isLeft) {
            return Either.left(dtoList.left)
        }
        dtoList.get().forEach {
            var month = YearMonth.of(it.yearmonth.year, it.yearmonth.month)
            if (yearmonth.equals(month)) {
                return Either.right(it)
            }
        }
        return Either.left(Exception("Front-End Schedule not found for this month."))

    }

    private fun findByYearmonthBetweenAndFrontEndId(frontEndId: String, yearmonth: String): List<FrontEndSchedule> {
        val formatter = DateTimeFormatter.ofPattern("MM-yyyy")
        val yearMonth = YearMonth.parse(yearmonth, formatter)

        var startDate: LocalDate
        var endDate: LocalDate

        startDate = LocalDate.of(yearMonth.year, yearMonth.month, 1)
        endDate = LocalDate.of(yearMonth.year, yearMonth.month, yearMonth.month.length(yearMonth.isLeapYear))

        var frontEndSchedules = repo.findByYearmonthBetweenAndFrontEndId(startDate, endDate, frontEndId)
        return frontEndSchedules

    }

    override
    fun latestAndApprovedByYearMonth(frontEndId: String, yearmonth: String):
            Either<Exception, List<FrontEndScheduleDayWiseDTO>> {

        var frontEndSchedules = findByYearmonthBetweenAndFrontEndId(frontEndId, yearmonth)

        if (frontEndSchedules.isEmpty()) {
            return Either.left(Exception("Front-End Schedule not found for this month"))
        }
        //checking frontend name null or not
        var result = setEmpName(frontEndSchedules, frontEndId)
        if (result.isLeft) {
            return Either.left(result.left)
        }

        var map = frontEndSchedules.groupBy { x -> x.frontEndScheduleId.frontEndScheduleId }

        var fESchedules = ArrayList<FrontEndSchedule>()
        map.forEach {
            //Max Version(Latest update)
            var maxVersion = it.value.map { x -> x.version }.toSet().max()
            var fEScheduleMaxVer = it.value.stream().filter { x -> x.version == maxVersion }.findAny()

            if (fEScheduleMaxVer.isPresent) {
                fESchedules.add(fEScheduleMaxVer.get())
            }

            //Latest Approved
            var helper = 0
            for (i in 1..maxVersion!!) {
                var fESchedule = it.value.stream().filter { x -> x.version == maxVersion - i }.filter { x -> x.feScheduleStatus.equals(FEScheduleStatus.APPROVE) }.findAny()
                if (fESchedule.isPresent && helper == 0) {
                    fESchedules.add(fESchedule.get())
                    helper++
                }
            }
        }
        if (fESchedules.size < 1) {
            return Either.right(ArrayList())
        }
        var frontEndScheduleDTOList = modelListToDto(fESchedules)
        Collections.sort(frontEndScheduleDTOList, { o1, o2 -> o1.yearmonth.compareTo(o2.yearmonth) })
        return Either.right(frontEndScheduleDTOList)
    }

    private fun latestForFrontEnd(frontEndId: String, yearmonth: String): Either<Exception, FrontEndSchedule> {
        var frontEndSchedules = findByYearmonthBetweenAndFrontEndId(frontEndId, yearmonth)

        if (frontEndSchedules.isEmpty()) {
            return Either.left(Exception("Front-End Schedule not found for this month"))
        }
        var maxVersion = frontEndSchedules.map { x -> x.version }.toSet().max()
        for (i in 0..maxVersion!!) {
            var fESchedule = frontEndSchedules.stream().filter { x -> x.version == maxVersion - i }.filter { x -> x.employeeType!!.equals(EmployeeType.FRONT_END) }.findAny()
            if (fESchedule.isPresent) {
                return Either.right(fESchedule.get())
            }
        }
        return Either.left(Exception("No updation"))
    }

    private fun latestForHeadAdmin(frontEndId: String, yearmonth: String): Either<Exception, FrontEndSchedule> {
        var frontEndSchedules = findByYearmonthBetweenAndFrontEndId(frontEndId, yearmonth)

        if (frontEndSchedules.isEmpty()) {
            return Either.left(Exception("Front-End Schedule not found for this month"))
        }
        var maxVersion = frontEndSchedules.map { x -> x.version }.toSet().max()
        for (i in 0..maxVersion!!) {
            var fESchedule = frontEndSchedules.stream().filter { x -> x.version == maxVersion - i }.filter { x -> x.employeeType.equals(EmployeeType.HEAD_ADMIN) }.findAny()
            if (fESchedule.isPresent) {
                return Either.right(fESchedule.get())
            }
        }
        return Either.left(Exception("No update"))
    }


    override
    fun latestForFrontEndAndHeadAdmin(frontEndId: String, yearmonth: String): Either<Exception, List<FrontEndScheduleDayWiseDTO>> {
        var fESchedules = ArrayList<FrontEndSchedule>()
        var headAdminSchedule = latestForHeadAdmin(frontEndId, yearmonth)
        if (headAdminSchedule.isRight) {
            fESchedules.add(headAdminSchedule.get())
        }
        var frontEndSchedule = latestForFrontEnd(frontEndId, yearmonth)
        if (frontEndSchedule.isRight) {
            fESchedules.add(frontEndSchedule.get())
        }
        //checking frontend name
        var result = setEmpName(fESchedules, frontEndId)
        if (result.isLeft) {
            return Either.left(result.left)
        } else if (fESchedules.size < 1) {
            return Either.right(ArrayList())
        }
        return Either.right(modelListToDto(fESchedules))
    }

    override
    fun updateSchedule(dto: ScheduleUpdateDTO): Either<Exception, String> {

        var maxVersion = repo.maxVersion(FrontEndScheduleId(dto.frontEndScheduleId!!))
        var frontEndSchedule = repo.findByVersionAndFrontEndScheduleId(maxVersion, FrontEndScheduleId(dto.frontEndScheduleId!!))
        var schedules = ScheduleDTOConvertor.Convertor.dtoListToModelListUpdate(dto.listOfScheduleDto!!)

        if (frontEndSchedule.isPresent && schedules.size > 0) {
            var newfrontEnd = FrontEndSchedule(frontEndSchedule.get(), dto.employeeType!!)

            var result = checkValidation(FrontEndSchedule(dto.frontEndScheduleId!!, schedules))
            if (result.isLeft) {
                return Either.left(result.left)
            }

            var res = newfrontEnd.updateSchedule(schedules, dto.updatedBy!!, dto.reasonForUpdate)
            if (res.isLeft) {
                return Either.left(res.left)
            }

            newfrontEnd.updatedBy = dto.updatedBy!!

            repo.save(newfrontEnd)

            //Update-Front-End-Scheduled Event
            var eventMsg=ScheduleEvent(newfrontEnd, OperationType.UPDATE)
            var event= WebScoketEvent(MessageType.Schedule,eventMsg)

            socketStream.publish().send(MessageBuilder.withPayload(event).
                    setHeader(MessageHeaders.CONTENT_TYPE,MimeTypeUtils.APPLICATION_JSON).build())
            return Either.right("Schedule Updated")
        }
        return Either.left(Exception("Schedule Not Updated"))
    }


    /*  override
    fun getFrontEndScheduleByIdAndDate(yearMonth: String, frontEndId: String): Either<Exception, FrontEndScheduleDayWiseDTO> {

*//*        val formatter = DateTimeFormatter.ofPattern("MM-yyyy")
        val yearMonth = YearMonth.parse(yearMonth, formatter)

        var startDate: LocalDate
        var endDate: LocalDate

        startDate = LocalDate.of(yearMonth.year, yearMonth.month, 1)
        endDate = LocalDate.of(yearMonth.year, yearMonth.month, yearMonth.month.length(yearMonth.isLeapYear))

        var frontEndScheduleList = repo.findByYearmonthBetween(startDate, endDate)
        if (frontEndScheduleList.size > 0) {
            var frontEndSchedule = frontEndScheduleList.stream().filter { it.frontEndId.equals(frontEndId) }.findAny()

            if (frontEndSchedule.isPresent) {
                var frontEndScheduleDayWiseDTO = FrontEndScheduleDayWiseDTOConverter.converter.modelToDtoConvert(frontEndSchedule.get())
                return Either.right(frontEndScheduleDayWiseDTO)
            }
        }*//*
        return Either.left(Exception("NOT-Used::FrontEndSchedule not found"))
    }*/

    //for insert
    private fun findFrontEndScheduleByIdAndDate(yearMonth: LocalDate, frontEndId: String): Boolean {

        var startDate: LocalDate
        var endDate: LocalDate

        startDate = LocalDate.of(yearMonth.year, yearMonth.month, 1)
        endDate = LocalDate.of(yearMonth.year, yearMonth.month, yearMonth.month.length(yearMonth.isLeapYear))

        var frontEndScheduleList = repo.findByYearmonthBetween(startDate, endDate)
        if (frontEndScheduleList.size > 0) {
            var frontEndSchedule = frontEndScheduleList.stream().filter {
                it.frontEndId.equals(frontEndId)
                        && it.yearmonth.month.equals(yearMonth.month)
            }.findAny()

            if (frontEndSchedule.isPresent) {
                return true
            }
        }
        return false
    }

    //To get Request-ID of type EXISTING_CENTER(One of the type of Request) in last two month.
    //Purpose ::To find is there any EXISTING_CENTER is scheduled or not in last two month
    override
    fun findFrontEndScheduleByDates(startDate: LocalDate, endDate: LocalDate): List<String> {

        var frontEndScheduleList = repo.findByYearmonthBetween(startDate, endDate)

        var scheduleMap = frontEndScheduleList.groupBy { x -> x.frontEndScheduleId.frontEndScheduleId }.toMap()

        var maxVersionSchedule = ArrayList<FrontEndSchedule>()

        scheduleMap.forEach {
            var maxVersion = it.value.map { x -> x.version }.max()
            var feSch = it.value.stream().filter { x -> x.version.equals(maxVersion) }.findAny()

            if (feSch.isPresent) {
                maxVersionSchedule.add(feSch.get())
            }
        }

        var requestIds = ArrayList<String>()
        if (maxVersionSchedule.size > 0) {
            maxVersionSchedule.forEach {
                it.schedules.filter { x -> x.scheduleOnWhichPurpose.equals(TypeOfRequest.EXISTING_CENTER) }.map { x -> requestIds.add(x.requestId!!) }.toList()
            }
        }
        if (requestIds.size > 0) {
            return requestIds
        }
        return ArrayList()
    }


    override
    fun approveFrontEndSchedule(frontEndScheduleId: String): Either<Exception, String> {
        var maxVersion = repo.maxVersion(FrontEndScheduleId(frontEndScheduleId))
        var fESchedule = repo.findByVersionAndFrontEndScheduleId(maxVersion, FrontEndScheduleId(frontEndScheduleId))
        if (fESchedule.isPresent) {
            fESchedule.get().approve()
            repo.save(fESchedule.get())

            //Update-Front-End-Scheduled Event
            var eventMsg=ScheduleEvent(fESchedule.get(), OperationType.APPROVED)
            var event= WebScoketEvent(MessageType.Schedule,eventMsg)

            socketStream.publish().send(MessageBuilder.withPayload(event).
                    setHeader(MessageHeaders.CONTENT_TYPE,MimeTypeUtils.APPLICATION_JSON).build())


            return Either.right("Front-End Schedule Approved.")
        }
        return Either.left(Exception("Schedule not found."))
    }

    override
    fun last2ScheduleByFrontEndScheduleId(frontEndScheduleId: String): Either<Exception, List<FrontEndScheduleDayWiseDTO>> {
        var maxVersion = repo.maxVersion(FrontEndScheduleId(frontEndScheduleId))

        var fESchedules = ArrayList<FrontEndSchedule>()
        if (maxVersion > 0) {
            for (i in 0..1) {
                var fESchedule = repo.findByVersionAndFrontEndScheduleId(maxVersion - i, FrontEndScheduleId(frontEndScheduleId))
                fESchedules.add(fESchedule.get())
            }
            return Either.right(modelListToDto(fESchedules))
        }
        var fESchedule = repo.findByVersionAndFrontEndScheduleId(maxVersion, FrontEndScheduleId(frontEndScheduleId))
        if (fESchedule.isPresent) {
            fESchedules.add(fESchedule.get())
        }
        return Either.right(modelListToDto(fESchedules))
    }


    //Not Used
    fun approvedAndLastVersionSchedule(frontEndScheduleId: String): Either<Exception, List<FrontEndScheduleDayWiseDTO>> {
        var maxVersion = repo.maxVersion(FrontEndScheduleId(frontEndScheduleId))

        var fESchedules = ArrayList<FrontEndSchedule>()
        if (maxVersion > 0) {

            var schedules = repo.findByFrontEndScheduleId(FrontEndScheduleId(frontEndScheduleId))
            Collections.sort(schedules, { o1, o2 -> o1.yearmonth.compareTo(o2.yearmonth) })

            schedules.forEach {
                if (it.feScheduleStatus.equals(FEScheduleStatus.APPROVE))
                    fESchedules.add(it)
            }
        }
        var fESchedule = repo.findByVersionAndFrontEndScheduleId(maxVersion, FrontEndScheduleId(frontEndScheduleId))
        if (fESchedule.isPresent) {
            fESchedules.add(fESchedule.get())
        }
        var frontEndScheduleDTOList = modelListToDto(fESchedules)

        Collections.sort(frontEndScheduleDTOList, { o1, o2 -> o1.yearmonth.compareTo(o2.yearmonth) })
        return Either.right(frontEndScheduleDTOList)
    }

    private fun modelListToDto(fESchedules: List<FrontEndSchedule>): List<FrontEndScheduleDayWiseDTO> {
        var frontEndScheduleDTOList = ArrayList<FrontEndScheduleDayWiseDTO>()
        fESchedules.forEach {
            var fSDWDto = FrontEndScheduleDayWiseDTOConverter.converter.modelToDtoConvert(it)
            frontEndScheduleDTOList.add(fSDWDto)
        }
        return frontEndScheduleDTOList
    }


    //For visiting request.
    override
    fun getExitingVisitRequestId(): Either<Exception, List<String>> {
        var frontEndSchedules = repo.findAll()

        if (frontEndSchedules.isEmpty()) {
            return Either.left(Exception("Front-End Schedules not found"))
        }
        var map = frontEndSchedules.groupBy { x -> x.frontEndScheduleId.frontEndScheduleId }

        var fESchedules = ArrayList<FrontEndSchedule>()
        map.forEach {
            var version = it.value.map { x -> x.version }.toSet().max()
            var fESchedule = it.value.stream().filter { x -> x.version == version }.findAny()
            if (fESchedule.isPresent) {
                fESchedules.add(fESchedule.get())
            }
        }

        var monthWiseSchedule = MonthWiseSchedule(fESchedules)
        var exitingVisitRequest = monthWiseSchedule.existingRequestId()
        if (exitingVisitRequest.size > 0) {
            return Either.right(exitingVisitRequest)
        }
        return Either.left(Exception("There is No Existing Visit Request."))
    }

    override fun rejectFrontEndSchedule(frontEndScheduleId: String, rejectedDto: RejectedDto): Either<Exception, String> {
        var maxVersion = repo.maxVersion(FrontEndScheduleId(frontEndScheduleId))
        var frontEndSchedule = repo.findByVersionAndFrontEndScheduleId(maxVersion, FrontEndScheduleId(frontEndScheduleId))
        if (frontEndSchedule.isPresent) {
            frontEndSchedule.get().rejectFES(rejectedDto)
            repo.saveAndFlush(frontEndSchedule.get())

            //Reject-Front-End-Scheduled Event
            var eventMsg=ScheduleEvent(frontEndSchedule.get(), OperationType.REJECT)
            var event= WebScoketEvent(MessageType.Schedule,eventMsg)

            socketStream.publish().send(MessageBuilder.withPayload(event).
                    setHeader(MessageHeaders.CONTENT_TYPE,MimeTypeUtils.APPLICATION_JSON).build())

            return Either.right("Front End Schedule rejected")
        }
        return Either.left(Exception("Front End Schedule Not Found...."))
    }

    private fun feScheduleByFEId(fEId: String): List<FrontEndSchedule> {
        //Get Front-End Schedule
        var frontEndSchedules = repo.byFrontEndId(fEId)

        if (frontEndSchedules.isEmpty()) {
            return ArrayList()
        }
        var map = frontEndSchedules.groupBy { x -> x.frontEndScheduleId.frontEndScheduleId }

        var fESchedules = ArrayList<FrontEndSchedule>()
        map.forEach {
            var version = it.value.map { x -> x.version }.toSet().max()
            var fESchedule = it.value.stream().filter { x -> x.version == version }.findAny()
            if (fESchedule.isPresent) {
                fESchedules.add(fESchedule.get())
            }
        }
        return fESchedules
    }

    override fun bdaRequestIdByFEId(fEId: String): List<String> {

        var fESchedules = feScheduleByFEId(fEId)

        if (fESchedules.size > 1) {
            var bdaRequestId = ArrayList<String>()

            //Get Id of BUSSINESS_DEVELOPEMENT_ACTIVITY type of Request.
            fESchedules.filter { x -> x.feScheduleStatus.equals(FEScheduleStatus.APPROVE) }.toList().forEach {
                it.schedules.filter { x -> x.scheduleOnWhichPurpose.equals(TypeOfRequest.BUSSINESS_DEVELOPEMENT_ACTIVITY) }.map { x -> bdaRequestId.add(x.requestId!!) }
            }

            /*
            //Get Id of BUSSINESS_DEVELOPEMENT_ACTIVITY type of Request.
            var fesSchedule=fESchedules.filter { x -> x.feScheduleStatus.equals(FEScheduleStatus.APPROVE) }.toList()

            fesSchedule.forEach {
                it.schedules.forEach {
                    if(it.scheduleOnWhichPurpose.equals(TypeOfRequest.BUSSINESS_DEVELOPEMENT_ACTIVITY)){
                        bdaRequestId.add(it.requestId!!)
                    }
                }
            }*/
            return bdaRequestId
        }
        return ArrayList()
    }

    override
    fun scheduleCompleted(completedScheduleDTO: CompletedScheduleDTO): Either<Exception, String> {
        var maxVersion = repo.maxVersion(FrontEndScheduleId(completedScheduleDTO.frontEndScheduleId))
        var frontEndSchedule = repo.findByVersionAndFrontEndScheduleId(maxVersion,
                FrontEndScheduleId(completedScheduleDTO.frontEndScheduleId))

        if (frontEndSchedule.isPresent) {
            var result = frontEndSchedule.get().isScheduleCompleted(completedScheduleDTO.requestId)
            if (result.isLeft) {
                return Either.left(result.left)
            }
            repo.save(frontEndSchedule.get())

            //schedule Completed Event
            var eventMsg=ScheduleEvent(frontEndSchedule.get(), OperationType.SCHEDULE_COMPLETED)
            var event= WebScoketEvent(MessageType.Schedule,eventMsg)

            socketStream.publish().send(MessageBuilder.withPayload(event).
                    setHeader(MessageHeaders.CONTENT_TYPE,MimeTypeUtils.APPLICATION_JSON).build())

            return Either.right(result.get())
        }
        return Either.left(Exception("Front-End Schedule not found"))

    }

    override fun bdaScheduleCompleted(completedScheduleDTO: CompletedScheduleDTO): Either<Exception, String> {
        var maxVersion = repo.maxVersion(FrontEndScheduleId(completedScheduleDTO.frontEndScheduleId))
        var frontEndSchedule = repo.findByVersionAndFrontEndScheduleId(maxVersion,
                FrontEndScheduleId(completedScheduleDTO.frontEndScheduleId))

        if (frontEndSchedule.isPresent) {
            var result = frontEndSchedule.get().completedBDASchedule(completedScheduleDTO.requestId, completedScheduleDTO.sheduledate!!)
            if (result.isLeft) {
                return Either.left(result.left)
            }
            repo.save(frontEndSchedule.get())

            //schedule Completed Event
            var eventMsg=ScheduleEvent(frontEndSchedule.get(), OperationType.SCHEDULE_COMPLETED)
            var event= WebScoketEvent(MessageType.Schedule,eventMsg)

            socketStream.publish().send(MessageBuilder.withPayload(event).
                    setHeader(MessageHeaders.CONTENT_TYPE,MimeTypeUtils.APPLICATION_JSON).build())

            return Either.right(result.get())
        }
        return Either.left(Exception("Front-End Schedule not found"))

    }
    override fun customScheduleCompleted(completedCustomScheduleDTO: CompletedCustomScheduleDto): Either<Exception, String> {
        var maxVersion=repo.maxVersion(FrontEndScheduleId(completedCustomScheduleDTO.frontEndScheduleId))
        var frontEndSchedule=repo.findByVersionAndFrontEndScheduleId(maxVersion,FrontEndScheduleId(completedCustomScheduleDTO.frontEndScheduleId))
        if (frontEndSchedule.isPresent) {
            var result = frontEndSchedule.get().
                    completedCustomSchedule(completedCustomScheduleDTO.customScheduleId!!)
            if (result.isLeft) {
                return Either.left(result.left)
            }
            repo.save(frontEndSchedule.get())

            //schedule Completed Event
            var eventMsg=ScheduleEvent(frontEndSchedule.get(), OperationType.SCHEDULE_COMPLETED)
            var event= WebScoketEvent(MessageType.Schedule,eventMsg)

            socketStream.publish().send(MessageBuilder.withPayload(event).
                    setHeader(MessageHeaders.CONTENT_TYPE,MimeTypeUtils.APPLICATION_JSON).build())

            return Either.right(result.get())
        }
        return Either.left(Exception("Front-End Schedule not found"))

    }
}