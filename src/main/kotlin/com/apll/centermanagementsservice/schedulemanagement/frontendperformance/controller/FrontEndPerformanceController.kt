package com.apll.centermanagementsservice.schedulemanagement.frontendperformance.controller

import com.apll.centermanagementsservice.config.ResponseWithError
import com.apll.centermanagementsservice.schedulemanagement.controller.FrontEndScheduleController
import com.apll.centermanagementsservice.schedulemanagement.frontendperformance.dto.FrontEndPerformance
import com.apll.centermanagementsservice.schedulemanagement.frontendperformance.service.FrontEndPerformanceService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@Api("FrontEndPerformanceController",description = "Rest Api for FrontEndPerformance")
@RequestMapping("/fePerformance")
@RestController
class FrontEndPerformanceController {

    @Autowired
    lateinit var frontEndPerformanceService: FrontEndPerformanceService

    val LOGGER= LoggerFactory.getLogger(FrontEndScheduleController::class.java)

    @CrossOrigin
    @GetMapping("/{yearMonth}")
    @ApiOperation("FrontEndPerformance",notes = "URI  FrontEnd Performance",
            produces ="application/json",consumes = "application/json",response = String::class)
    fun frontEndPerforamnceMonthWise(@PathVariable yearMonth:String):ResponseWithError<List<FrontEndPerformance>>{

        var fePerformance=frontEndPerformanceService.findFrontEndScheduleByIsScheduleCompletedAndDate(yearMonth)
        if(fePerformance.isRight) {
            LOGGER.info("FrontEnd Performance calculated successfully!!!!!!")
            return ResponseWithError.of(fePerformance.get())

        }
        LOGGER.info(" Error raised on FrontEnd Performance calculation.")
        return ResponseWithError.ofError(fePerformance.left.message)
        }

    }
