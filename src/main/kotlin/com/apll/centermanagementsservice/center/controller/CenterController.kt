package com.apll.centermanagementsservice.center.controller

import com.apll.centermanagementsservice.center.model.dto.CenterStateDTO
import com.apll.centermanagementsservice.center.model.dto.CenterStateDtoWithSR
import com.apll.centermanagementsservice.center.model.dto.CenterUpdateDTO
import com.apll.centermanagementsservice.center.service.CenterService
import com.apll.centermanagementsservice.config.ResponseWithError
import com.apll.centermanagementsservice.restTemplet.EmployeeService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import javax.validation.Valid

@RestController
@RequestMapping("/center")
@Api("CenterController",description = "Rest Api for Center")
class CenterController {

    @Autowired
    private var service:CenterService?=null

    val LOGGER = LoggerFactory.getLogger(CenterController::class.java)


    @CrossOrigin
    @PostMapping
    @ApiOperation("Register Center ",notes = "URI to register Register Center",
            produces ="application/json",consumes = "application/json",response = CenterStateDtoWithSR::class)
    fun registerCenter(@Valid @RequestBody centerStateDto: CenterStateDTO): ResponseWithError<CenterStateDtoWithSR> {

            var centerStateDtoWithSR=service!!.register(centerStateDto)
            if (centerStateDtoWithSR.isLeft){
                LOGGER.error(centerStateDtoWithSR.left.message)
                return ResponseWithError.ofError(centerStateDtoWithSR.left.message)
            }
            LOGGER.info("Successfully Center inserted")
            return ResponseWithError.of(centerStateDtoWithSR.get())
    }

    @CrossOrigin
    @ApiOperation("Get Center By centerId ",notes = "URI to get one Center",
            produces ="application/json",consumes = "application/json",response = CenterStateDtoWithSR::class)
    @GetMapping("/{centerId}")
    fun getById(@Valid @PathVariable centerId: String): ResponseWithError<CenterStateDtoWithSR> {

            var centerStateDtoWithSR=service!!.getCenterById(centerId)

            if (centerStateDtoWithSR.isLeft){
                LOGGER.error("Exception occurred to get Center by ID")
                return ResponseWithError.ofError(centerStateDtoWithSR.left.message)
            }
            LOGGER.info("Center :: getById(CenterId)")
            return ResponseWithError.of(centerStateDtoWithSR.get())
    }


    @CrossOrigin
    @ApiOperation("Get All Center ",notes = "URI to Get All Center",
            produces ="application/json",consumes = "application/json",response = List::class)
    @GetMapping
    fun getAllCenter():ResponseWithError<List<CenterStateDtoWithSR>>{

            var centerStateDtoWithSRList=service!!.findAllCenter()

            if (centerStateDtoWithSRList.isLeft){
                LOGGER.error(("Exception occurred in getting All center"))
                return ResponseWithError.ofError(centerStateDtoWithSRList.left.message)
            }
            LOGGER.info("Getting All center ")
            return ResponseWithError.of(centerStateDtoWithSRList.get())
    }


    @CrossOrigin
    @ApiOperation("Update Center ",notes = "URI to Update Center",
            produces ="application/json",consumes = "application/json",response = CenterStateDtoWithSR::class)
    @PutMapping
    fun updateCenter(@Valid @RequestBody centerUpdateDTO: CenterUpdateDTO):ResponseWithError<CenterStateDtoWithSR>{

            var centerStateDtoWithSR=service!!.editCenter(centerUpdateDTO)

            if (centerStateDtoWithSR.isLeft){
                LOGGER.error("Exception occurred in updating center")
                return ResponseWithError.ofError(centerStateDtoWithSR.left.message)
            }
            LOGGER.info("Center is updated successfully")
            return ResponseWithError.of(centerStateDtoWithSR.get())
    }

    @CrossOrigin
    @ApiOperation("Get Center By SubRegionId ",notes = "URI to  Get Center By SubRegionId",
            produces ="application/json",consumes = "application/json",response = CenterStateDtoWithSR::class)
    @GetMapping("/getCenterBySubRegionId/{subRegionId}")
    fun findCenterBySubRegionId(@Valid @PathVariable subRegionId: String):ResponseWithError<Set<CenterStateDtoWithSR>>{

           var centerStateDtoWithSR = service!!.getCenterBySubRegionId(subRegionId)

           if (centerStateDtoWithSR.isLeft) {
               LOGGER.error("Exception occurred in getting center by sub regionId. $subRegionId")
               return ResponseWithError.ofError(centerStateDtoWithSR.left.message)
           }
            LOGGER.info("Successfully getting center by sub regionId")
            return ResponseWithError.of(centerStateDtoWithSR.get())
    }


    @CrossOrigin
    @ApiOperation("Get Center By RegionId ",notes = "URI to  Get Center By RegionId",
            produces ="application/json",consumes = "application/json",response = List::class)
    @GetMapping("/getCenterByRegionId/{regionId}")

    fun findCentersByRegionId(@Valid @PathVariable regionId:String):ResponseWithError<List<CenterStateDtoWithSR>>{

            var centerStateDtoWithSRList = service!!.getAllCenterStatesByRegionId(regionId)
            if (centerStateDtoWithSRList.isLeft) {
                LOGGER.error("Exception occurred in getting Center by regionId.. $regionId")
                return ResponseWithError.ofError(centerStateDtoWithSRList.left.message)
            }
            LOGGER.info("Successfully getting center by  regionId")
            return ResponseWithError.of(centerStateDtoWithSRList.get())
    }


   /* @CrossOrigin
    @GetMapping("/getLastTwoMonthNotVisitedCentersAccRegion/{monthYear}/{regionId}")
    @ApiOperation("Get Last Two Months Not Visited Center By Region Id",notes = "URI to Last Two months Not Visited Visiting Request By RequestDate to  visiting Request",
            produces ="application/json",consumes = "application/json",response = List::class)
    fun searchLastTwoMonthsNotVisitedCentersByRegionId(@Valid @PathVariable("monthYear") monthYear: String,@Valid @PathVariable("regionId")regionId:String)
                                                            : ResponseWithError<List<CenterStateDtoWithSR>>? {

        val formatter = DateTimeFormatter.ofPattern("MM-yyyy")
            val yearmonth = YearMonth.parse(monthYear.trim(), formatter)

            var resultVisitingRequest = service!!.getLastTwoMonthNotVisitedCentersAccRegion(LocalDate.of(yearmonth.year,yearmonth.month,1),regionId)
            if (resultVisitingRequest.isLeft) {
                LOGGER.error("Exception occurred in searching List of Center not visited from Last Two Months by region id.")
                return ResponseWithError.ofError(resultVisitingRequest.left.message)
            }
            LOGGER.info("Getting List of Centers not visited from last month by region Id")
            return ResponseWithError.of(resultVisitingRequest.get())
    }*/


    @CrossOrigin
    @GetMapping("/byEmployeeId/{yearmonth}/{employeeId}")
    @ApiOperation("Get Last Two Months Not Visited Center By Employee Id",notes = "URI to Last Two months Not Visited Visiting Request By RequestDate to  visiting Request by Employee Id",
            produces ="application/json",consumes = "application/json",response = List::class)
    fun findCenterNotVisitedLastTwoMonthsByEmployeeId(@Valid @PathVariable yearmonth: String,@Valid @PathVariable employeeId: String): ResponseWithError<List<CenterStateDtoWithSR>> {

        var centerStateDtoWithSRList = service!!.getLastTwoMonthNotVisitedCentersAccEmployeeId(yearmonth,employeeId)
        if (centerStateDtoWithSRList.isLeft) {
            LOGGER.error("Exception occurred in searching List of Center not visited from Last Two Months by employee Id.")
            return ResponseWithError.ofError(centerStateDtoWithSRList.left.message)
        }
        LOGGER.info("Getting List of Centers not visited from last month by employee Id")
        return ResponseWithError.of(centerStateDtoWithSRList.get())
    }





/*
@PostMapping("/center")
    fun addCenter(@RequestBody center: CenterState): CenterState {
        return centerRepo.save(center)
    }

    @GetMapping("/center/{centerId}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    fun findCenter(@PathVariable centerId:String): CenterState? {
        var resolverId= CenterId(centerId)
        var center= centerRepo.findById(resolverId)
        if(center.isPresent){
            return center.get()
        }
        return null
    }

    @GetMapping("/center")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    fun findAllCenter(): List<CenterState> {
        return centerRepo.getMinimalReq()
    }*/

}
