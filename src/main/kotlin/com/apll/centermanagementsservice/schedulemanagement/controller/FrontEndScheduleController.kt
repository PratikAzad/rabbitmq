package com.apll.centermanagementsservice.schedulemanagement.controller

import com.apll.centermanagementsservice.config.ResponseWithError
import com.apll.centermanagementsservice.schedulemanagement.model.ScheduleId
import com.apll.centermanagementsservice.schedulemanagement.model.dto.*
import com.apll.centermanagementsservice.schedulemanagement.service.IFrontEndScheduleService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("frontEndSchedule")
@Api("FrontEndScheduleController",description = "Rest Api for FrontEnd Schedule")
class FrontEndScheduleController {

    @Autowired
   lateinit var service:IFrontEndScheduleService

    val LOGGER= LoggerFactory.getLogger(FrontEndScheduleController::class.java)

    @CrossOrigin
    @PostMapping
    @ApiOperation("saveFrontEndSchedule  ",notes = "URI SAVE Front End Schedule",
            produces ="application/json",consumes = "application/json",response = String::class)
    fun saveFrontEndSchedule(@Valid @RequestBody frontEndScheduleDTO: FrontEndScheduleDTO):
                                                     ResponseWithError<String> {

            var result = service.insertFrontEndSchedule(frontEndScheduleDTO)

            if (result.isLeft) {
                LOGGER.error("Exception occurred in inserting Front End Schedule."+result.left.message)
                return ResponseWithError.ofError(result.left.message)
            }
            LOGGER.info("Front End Schedule is inserted/saved Successfully.")
            return ResponseWithError.of(result.get())
        }


    @CrossOrigin
    @GetMapping
    @ApiOperation("getAllFrontEndSchedule",notes = "URI Get All Front End Schedule",
            produces ="application/json",consumes = "application/json",response = List::class)
    fun getAllFrontEndSchedule():ResponseWithError<List<FrontEndScheduleDayWiseDTO>> {

            var FrontEndScheduleDayWiseDTO = service.getAllFrontEndSchedule()

            if (FrontEndScheduleDayWiseDTO.isLeft) {
                LOGGER.error("Exception occurred in getting All Front End Schedule.")
                return ResponseWithError.ofError(FrontEndScheduleDayWiseDTO.left.message)
            }
            LOGGER.info("Getting All Front End Schedules")
            return ResponseWithError.of(FrontEndScheduleDayWiseDTO.get())
    }

    @CrossOrigin
    @GetMapping("/{frontEndScheduleId}")
    @ApiOperation("searchByFrontEndScheduleId",notes = "URI Get One  Front End Schedule by FrontEndScheduleId",
            produces ="application/json",consumes = "application/json",response = FrontEndScheduleDayWiseDTO::class)
    fun searchByFrontEndScheduleId(@Valid @PathVariable frontEndScheduleId:String):ResponseWithError<FrontEndScheduleDayWiseDTO>{

            var FrontEndScheduleDayWiseDTO = service.getByFrontEndScheduleId(frontEndScheduleId)

            if (FrontEndScheduleDayWiseDTO.isLeft) {
                LOGGER.error("Exception in searching front End Schedule by Front End Schedule Id $frontEndScheduleId")
                return ResponseWithError.ofError(FrontEndScheduleDayWiseDTO.left.message)
            }
            LOGGER.info("Get One Front End Schedule by Front End Schedule Id.")
            return ResponseWithError.of(FrontEndScheduleDayWiseDTO.get())
    }

   /* @CrossOrigin
    @PutMapping
    @ApiOperation("updateFrontEndSchedule",notes = "URI Update One  Front End Schedule",
            produces ="application/json",consumes = "application/json",response = FrontEndScheduleDayWiseDTO::class)
    fun updateFrontEndSchedule(@Valid @RequestBody frontEndScheduleDTO: FrontEndScheduleDTO):ResponseWithError<FrontEndScheduleDayWiseDTO>{

        try {
            var FrontEndScheduleDayWiseDTO = service.editFrontEndSchedule(frontEndScheduleDTO)

            if (FrontEndScheduleDayWiseDTO.isLeft) {
                LOGGER.error("Exception occurred in updating One Front End Schedule.")
                return ResponseWithError.ofError(FrontEndScheduleDayWiseDTO.left.message)
            }
            LOGGER.info("One Front End Schedule is updated Successfully.")
            return ResponseWithError.of(FrontEndScheduleDayWiseDTO.get())
        }
        catch (e:Exception){
            LOGGER.error("Exception occurred in updating One Front End Schedule."+e.message)
            return ResponseWithError.ofError(e.message)
        }
    }*/

    @CrossOrigin
    @GetMapping("/byFrontEndId/{frontEndId}")
    @ApiOperation("getFrontEndScheduleByFrontEndId",notes = "URI Get One  Front End Schedule By frontEndId",
            produces ="application/json",consumes = "application/json",response = List::class)
    fun getFrontEndScheduleByFrontEndId(@Valid @PathVariable frontEndId:String):ResponseWithError<List<FrontEndScheduleDayWiseDTO>>{

            var FrontEndScheduleDayWiseDTO=service.getByFrontEndId(frontEndId)

            if (FrontEndScheduleDayWiseDTO.isLeft){
                LOGGER.error("Exception occurred in getting One Front End Schedule.")
                return ResponseWithError.ofError(FrontEndScheduleDayWiseDTO.left.message)
            }
            LOGGER.info("Getting One Front End Schedule Successfully ")
            return ResponseWithError.of(FrontEndScheduleDayWiseDTO.get())
    }

    @CrossOrigin
    @GetMapping("/last2SVersion/{frontEndScheduleId}")
    @ApiOperation("last2ScheduleByFrontEndScheduleId",notes = "URI to Get last 2 Front End Schedule By frontEndScheduleId",
            produces ="application/json",consumes = "application/json",response = List::class)
    fun last2ScheduleByFrontEndScheduleId(@Valid @PathVariable frontEndScheduleId:String):ResponseWithError<List<FrontEndScheduleDayWiseDTO>>{

        var FrontEndScheduleDayWiseDTO=service.last2ScheduleByFrontEndScheduleId(frontEndScheduleId)

        if (FrontEndScheduleDayWiseDTO.isLeft){
            LOGGER.error("Exception occurred in getting One Front End Schedule.")
            return ResponseWithError.ofError(FrontEndScheduleDayWiseDTO.left.message)
        }
        LOGGER.info("Getting One Front End Schedule Successfully ")
        return ResponseWithError.of(FrontEndScheduleDayWiseDTO.get())
    }


    @CrossOrigin
    @PutMapping
    @ApiOperation("updateSchedule",notes = "URI Update Schedule",
            produces ="application/json",consumes = "application/json",response = FrontEndScheduleDTO::class)
    fun editSchedule(@Valid @RequestBody scheduleUpdateDTO: ScheduleUpdateDTO):ResponseWithError<String>{
            var frontEndScheduleDTO = service.updateSchedule(scheduleUpdateDTO)

            if (frontEndScheduleDTO.isLeft) {
                LOGGER.error("Exception occurred in updating One Front End Schedule.")
                return ResponseWithError.ofError(frontEndScheduleDTO.left.message)
            }
            LOGGER.info("One Front End Schedule is updated Successfully.")
            return ResponseWithError.of("Schedule updated")
    }


    @CrossOrigin
    @GetMapping("/byFrontEndIdAndDate/{yearMonth}/{frontEndId}")
    @ApiOperation("getFrontEndScheduleByFrontEndIdAndDate",notes = "URI to get one FrontEndSchedule By FrontEndId And Date",
            produces ="application/json",consumes = "application/json",response = FrontEndScheduleDayWiseDTO::class)
    fun getFrontEndScheduleByFrontEndIdAndDate(@Valid @PathVariable yearMonth:String,@Valid @PathVariable frontEndId: String):ResponseWithError<FrontEndScheduleDayWiseDTO>{
        var frontEndScheduleDayWiseDTO=service.getFrontEndScheduleByYearMonth(yearMonth,frontEndId)
        if (frontEndScheduleDayWiseDTO.isLeft){
            LOGGER.error("Exception occurred in getting One Front End Schedule by $frontEndId and $yearMonth.")
            return ResponseWithError.ofError(frontEndScheduleDayWiseDTO.left.message)
        }
        LOGGER.info("One Front End Schedule is getting  Successfully.")
        return ResponseWithError.of(frontEndScheduleDayWiseDTO.get())
    }

    @CrossOrigin
    @PutMapping("/{frontEndScheduleId}")
    @ApiOperation("approveFrontEndSchedule",notes = "URI to approve one FrontEndSchedule by FrontEndScheduleId ",
            produces ="application/json",consumes = "application/json",response = String::class)
    fun approveFrontEndSchedule(@Valid @PathVariable frontEndScheduleId: String):ResponseWithError<String>{
        var approveStatus=service.approveFrontEndSchedule(frontEndScheduleId)
        if (approveStatus.isLeft){
            LOGGER.error("Exception occurred in approving One Front End Schedule by $frontEndScheduleId.")
            return ResponseWithError.ofError(approveStatus.left.message)
        }
        LOGGER.info("One Front End Schedule fEScheduleStatus  Successfully.")
        return ResponseWithError.of(approveStatus.get())
    }

    @CrossOrigin
    @GetMapping("/latestAndApprovedFES/{frontEndId}/{yearmonth}")
    @ApiOperation("getLatestAndApprovedFESByYearMonth",notes = "URI to get Latest And Approved FES By YearMonth ",
            produces ="application/json",consumes = "application/json",response = List::class)
    fun getLatestAndApprovedFESByYearMonth(@Valid @PathVariable frontEndId: String,@Valid @PathVariable yearmonth :String):
                                                                             ResponseWithError<List<FrontEndScheduleDayWiseDTO>>{
        var frontEndScheduleDayWiseDTOList=service.latestAndApprovedByYearMonth(frontEndId,yearmonth)
        if (frontEndScheduleDayWiseDTOList.isLeft){
            LOGGER.error("Exception occurred in getting Latest And Approved FES By YearMonth .")
            return ResponseWithError.ofError(frontEndScheduleDayWiseDTOList.left.message)
        }
        LOGGER.info("Successfully getting Latest And Approved FES By YearMonth.")
        return ResponseWithError.of(frontEndScheduleDayWiseDTOList.get())
    }

    @CrossOrigin
    @GetMapping("latestFESForFEAndHA/{frontEndId}/{yearmonth}")
    @ApiOperation("getLatestFESForHAndFE",notes = "URI to get Latest And Approved FES for Head-Admin and Front-End .",
            produces ="application/json",consumes = "application/json",response = List::class)
    fun getLatestFESForHAndFE(@Valid @PathVariable frontEndId: String,@Valid @PathVariable yearmonth: String):
                                             ResponseWithError<List<FrontEndScheduleDayWiseDTO>>{
        var frontEndScheduleDayWiseDTOList=service.latestForFrontEndAndHeadAdmin(frontEndId,yearmonth)

        if (frontEndScheduleDayWiseDTOList.isLeft){
            LOGGER.error("Exception occurred in getting Latest And Approved FES for Head-Admin and Front-End .")
            return ResponseWithError.ofError(frontEndScheduleDayWiseDTOList.left.message)
        }
        LOGGER.info("Successfully getting Latest And Approved FES for Head-Admin and Front-End.")
        return ResponseWithError.of(frontEndScheduleDayWiseDTOList.get())

    }

    @CrossOrigin
    @PutMapping("/rejectFES/{frontEndScheduleId}")
    @ApiOperation("rejectedFrontEndSchedule",notes = "URI to reject Front End Schedule by FrontEnd Schedule Id .",
            produces ="application/json",consumes = "application/json",response = String::class)

    fun  rejectedFrontEndSchedule(@PathVariable frontEndScheduleId:String,@Valid @RequestBody   rejectedDto: RejectedDto):ResponseWithError<String>{
        try{
            var result=service.rejectFrontEndSchedule(frontEndScheduleId,rejectedDto)
            if(result.isLeft){
                LOGGER.error("Exception occurred in rejecting one Front Schedule by FrontEnd Schedule by $frontEndScheduleId")
                return ResponseWithError.ofError(result.left.message)

            }
            LOGGER.info("One Front End Schedule  reject by Front-End schedule ID Successfully")
            return ResponseWithError.of(result.get())
        }
        catch(e:Exception){
            LOGGER.error("Exception occurred in rejecting one Front Schedule by FrontEnd Schedule updated  by $frontEndScheduleId")
            return ResponseWithError.ofError("Error raised on update rejected Front End Schedule")
        }
    }

    @CrossOrigin
    @GetMapping("/bdaRequestIdByFEId/{frontEndId}")
    @ApiOperation("bdaRequestIdByFEId",notes = "URI to get approed BDAVisitRequest Id.",
            produces ="application/json",consumes = "application/json",response = List::class)
    fun  bdaRequestIdByFEId(@PathVariable frontEndId:String):ResponseWithError<List<String>> {

        var result = service.bdaRequestIdByFEId(frontEndId)
        return ResponseWithError.of(result)
    }

    /*@CrossOrigin
    @GetMapping("/findByDate")
    fun findByDate(@PathVariable date:String):ResponseWithError<List<String>>{

        var requestIds=service.findFrontEndScheduleByDates(startDate,endDate)
    }*/


    /*@CrossOrigin
    @PutMapping("/scheduleCompletion")
    @ApiOperation("scheduleCompletion",notes = "URI to do schedule Completion .",
            produces ="application/json",consumes = "application/json",response = String::class)
    fun scheduleCompletion(@Valid @RequestBody completedScheduleDTO: CompletedScheduleDTO):
                                                     ResponseWithError<String>{
        var result=service.scheduleCompleted(completedScheduleDTO)
        if (result.isLeft){
            return ResponseWithError.ofError(result.left.message)
        }
        return ResponseWithError.of(result.get())
    }*/

}