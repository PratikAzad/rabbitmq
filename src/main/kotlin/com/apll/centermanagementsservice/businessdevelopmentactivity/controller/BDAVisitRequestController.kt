package com.apll.centermanagementsservice.businessdevelopmentactivity.controller

import com.apll.centermanagementsservice.businessdevelopmentactivity.controller.bdadto.BDAVisitReportDto
import com.apll.centermanagementsservice.businessdevelopmentactivity.controller.bdadto.BDAVisitRequestDto
import com.apll.centermanagementsservice.businessdevelopmentactivity.controller.bdadto.BDARequestDto
import com.apll.centermanagementsservice.businessdevelopmentactivity.service.BDAVisitRequestService
import com.apll.centermanagementsservice.config.ResponseWithError
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/bdavisitrequest")
@Api("Bussiness developement Activity Visit Request Controller",description = "Rest Api for Bussiness developement Activity Visit Request")
class BDAVisitRequestController {

    @Autowired
    lateinit var service:BDAVisitRequestService

    val log = LoggerFactory.getLogger(BDAVisitRequestController::class.java)

    @CrossOrigin
    @PostMapping
    @ApiOperation("Insert Bussiness Developement Activity Visit Request",notes = "URI to Register Bussiness Developement Activity Visit Request",
            produces ="application/json",consumes = "application/json",response = BDARequestDto::class)
    fun registerBDAVisitRequest(@Valid @RequestBody bdaRequestDto: BDARequestDto)
                                                                           :ResponseWithError<BDARequestDto>{
            var resultbda = service.insertBusinessDevelopmentActivity(bdaRequestDto)
            if (resultbda.isRight) {
                log.info("Successfully Bussiness developement Activity Inserted................")
                return ResponseWithError.of(resultbda.get())
            }
            return ResponseWithError.ofError(resultbda.left.message)
    }

    @CrossOrigin
    @PutMapping
    @ApiOperation("Update Bussiness Developement Activity Visit Request",notes = "URI to Update Bussiness Developement Activity  Visit Request",
            produces ="application/json",consumes = "application/json",response = BDARequestDto::class)
    fun  editBDAVisitRequest(@Valid @RequestBody bdaRequestDto: BDARequestDto)
                                                              :ResponseWithError<BDARequestDto>{
            var resultbdadto = service.updateBusinessDevelopmentActivity(bdaRequestDto)
            if (resultbdadto.isRight) {
                log.info("Successfully Bussiness Developement Activity Updated................")
                return ResponseWithError.of(resultbdadto.get())
            }
            return ResponseWithError.ofError(resultbdadto.left.message)
    }

    @CrossOrigin
    @GetMapping("/{bdavisitrequestid}")
    @ApiOperation("Getting One Bussiness Developement Activity Visit Request By BDAVisitRequestId",notes = "URI to Getting One Bussiness Developement Activity Visit Request by BDAVisitRequestId",
            produces ="application/json",consumes = "application/json",response = BDARequestDto::class)
    fun  searchBDAVisitRequestById(@Valid @PathVariable("bdavisitrequestid") bdavisitrequestid:String)
                                                                             : ResponseWithError<BDARequestDto>{
            var resultdto = service.getBusinessDevelopmentActivityById(bdavisitrequestid)
            if (resultdto.isRight) {
                log.info("Successfully  getting One Bussiness Developement Activity Visit Request By Id.............")
                return ResponseWithError.of(resultdto.get())
            }
            return ResponseWithError.ofError(resultdto.left.message)
    }

    @CrossOrigin
    @GetMapping("byFrontEndId/{frontEndId}")
    @ApiOperation("Getting All Bussiness Developement Activity Visit Request by FrontEnd Id",notes = "URI to Getting All Bussiness Developement Activity Visit Request by frontEndId",
            produces ="application/json",consumes = "application/json",response =List::class)
    fun  getByFrontEndId(@Valid @PathVariable frontEndId:String):ResponseWithError<List<BDAVisitRequestDto>>{

            var bdaVisitRequestdtoList=service.getAllByFrontEndId(frontEndId)

            if (bdaVisitRequestdtoList.isLeft){
                log.error("BDA Visit Request not found")
                return ResponseWithError.ofError(bdaVisitRequestdtoList.left.message)
            }
            log.info("Successfully Getting All BDA Visit Request...")
            return  ResponseWithError.of(bdaVisitRequestdtoList.get())

    }

    @CrossOrigin
    @GetMapping("/bdaRequestOnOneMonth/{regionId}/{yearMonth}")
    @ApiOperation("Getting All Bussiness Developement Activity Visit Request by RegionId and yearMonth",notes = "URI to Getting All Bussiness Developement Activity Visit Request by RegionId and YearMonth",
            produces ="application/json",consumes = "application/json",response =List::class)
    fun getBdaVisitRequestPerOneMonthByRegionIdAndYearMonth(@Valid @PathVariable("regionId") regionId:String,@PathVariable("yearMonth") yearMonth:String ):ResponseWithError<List<BDAVisitRequestDto>>{

           var bdavisitresultdto = service.getOneMonthBdaVisitRequestsByregionIdAndDate(regionId,yearMonth)

           if (bdavisitresultdto.isLeft) {
               log.error("BDA Visit Request not found By region Id and bdadate")
               return ResponseWithError.ofError(bdavisitresultdto.left.message)
           }
           log.info("Successfully getting All Bda Visit Request ... By Region Id and Bda Date....")
           return ResponseWithError.of(bdavisitresultdto.get())

    }

    @CrossOrigin
    @ApiOperation("Getting  month Wise  Bussiness Developement Activity Visit Request by FrontEndId and yearMonth",notes = "URI to Getting Month wise Bussiness Developement Activity Visit Request by FrontEndId and YearMonth",
            produces ="application/json",consumes = "application/json",response =List::class)
    @GetMapping("/sortedBdaRequestBy/{yearMonth}/{frontEndId}")
    fun getMonthWiseSortedBdaVisitRequestByFrontEndIdAndYearMonth(@Valid @PathVariable("yearMonth") yearMonth: String, @PathVariable("frontEndId") frontEndId: String):ResponseWithError<List<BDAVisitRequestDto>>{

            var resultBdaDto = service.getMonthWiseBdaVisitRequestByFrontEndIdAndYearMonth( yearMonth,frontEndId)
            if (resultBdaDto.isLeft) {
                log.error("Empty List returned in Bda Visit Request  by getting month Bda Visit requests and by passing FrontEnd Id and year Month.................")
                return ResponseWithError.ofError(resultBdaDto.left.message)
            }
            log.info("Successfully getting monthWise BdaVisit Request By passing FrontEnd Id and yearMonth................")
            return ResponseWithError.of(resultBdaDto.get())

    }

    @CrossOrigin
    @PutMapping("/fileBdaReport")
    @ApiOperation("file Report on  Bussiness Developement Activity Visit Request ",notes = "URI to file Report on  Bussiness Developement Activity Visit Request",
            produces ="application/json",consumes = "application/json",response = BDARequestDto::class)

    fun  fileBDAReport(@Valid @RequestBody bdaReport: BDAVisitReportDto):ResponseWithError<BDAVisitRequestDto>{

            var bdaReports=service.fileBdaReport(bdaReport)
            if(bdaReports.isLeft){
                log.error(" not file BDa Report on Bda Visit Request  .................")
                return ResponseWithError.ofError(bdaReports.left.message)

            }
            log.info("Sucessfully completed  filed BDA Visit Report ..")
            return ResponseWithError.of(bdaReports.get())

    }

    @CrossOrigin
    @GetMapping("/getAllBdaRequestWithReports")
    @ApiOperation("get All BdaRequest With Reports  Bussiness Developement Activity Visit Request ",notes = "URI to get All BdaRequest With Reports  Bussiness Developement Activity Visit Request ",
            produces ="application/json",consumes = "application/json",response =List::class)

    fun  getAllBdaVisitRequestWithReports():ResponseWithError<List<BDAVisitRequestDto>>{
            var bdaRequestsWithResults=service.getAllBDAVisitRequestWithBDAReports()
            if(bdaRequestsWithResults.isLeft)
            {
                log.error("Error raised on Getting All BDA Visit Requests With BDa Reports....")
                return ResponseWithError.ofError(bdaRequestsWithResults.left.message)
            }
            log.info("successfully getting all Bda Visit Request  with Bda Reports...")
            return ResponseWithError.of(bdaRequestsWithResults.get())
        }

     @CrossOrigin
     @GetMapping("/getOneBdaVisitRequestWithReport/{bdaVisitRequestId}")
     @ApiOperation("get one  BdaRequest With Reports  Bussiness Developement Activity  ",notes = "URI to get one  BdaRequest With Reports  Bussiness Developement Activity  ",
        produces ="application/json",consumes = "application/json",response = BDAVisitRequestDto::class)
     fun  getBdaVisitRequestWithReportsById(@Valid @PathVariable bdaVisitRequestId:String):ResponseWithError<BDAVisitRequestDto>{
        var bdaRequestsWithResults=service.getBDAVisitRequestWithReportById(bdaVisitRequestId)
        if(bdaRequestsWithResults.isLeft)
        {
            log.error("Error raised on Getting  one  BDA Visit Requests With BDa Reports by Id....")
            return ResponseWithError.ofError(bdaRequestsWithResults.left.message)
        }
        log.info("successfully getting one  Bda Visit Request  with Bda Reports by Id...")
        return ResponseWithError.of(bdaRequestsWithResults.get())
    }
}


/*@CrossOrigin
   @PostMapping
   @ApiOperation("insert Center Visit Report",notes = "URI to register Center Visit Report",
           produces ="application/json",consumes = "application/json",response = List::class)
   fun registerBDAVisitRequest(@Valid @RequestBody dto:BDAVisitDTO):ResponseWithError <List<BDARequestDto>> {
       try {
           var resultbdadto = service.insertBusinessDevelopmentActivity(dto)
           if (resultbdadto.isRight){
               log.info("Successfully Inserted Bussiness Developement activity visit request...")
               return ResponseWithError.of(resultbdadto.get())
           }
           return ResponseWithError.ofError(resultbdadto.left.message)
       }
       catch (e:Exception){
           log.error("Exception Occured in Inserted Bussiness Developement activity visit request...fail.."+e.message)
           return ResponseWithError.ofError(e.message)
       }
   }*/