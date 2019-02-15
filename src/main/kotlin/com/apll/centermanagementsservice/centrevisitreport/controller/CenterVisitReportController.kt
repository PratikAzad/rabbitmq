package com.apll.centermanagementsservice.centrevisitreport.controller

import com.apll.centermanagementsservice.center.model.dto.CenterDTO
import com.apll.centermanagementsservice.centrevisitreport.model.CenterVisitReportStatus
import com.apll.centermanagementsservice.centrevisitreport.model.dto.CenterVisitReportDto
import com.apll.centermanagementsservice.centrevisitreport.model.dto.CenterVisitReportUpdateDTO
import com.apll.centermanagementsservice.centrevisitreport.model.dto.TodoListApllResolvedDto
import com.apll.centermanagementsservice.centrevisitreport.model.dto.TodoListCenterResolvedDto
import com.apll.centermanagementsservice.centrevisitreport.service.CenterVisitReportService
import com.apll.centermanagementsservice.config.ResponseWithError
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/centerVisitReport")
@Api("CenterVisitReportController",description = "Rest Api for Center Visit Report")
class CenterVisitReportController {

    @Autowired
    lateinit var service:CenterVisitReportService


    val log = LoggerFactory.getLogger(CenterVisitReportController::class.java)


    @CrossOrigin
    @PostMapping
    @ApiOperation("insert Center Visit Report",notes = "URI to register Center Visit Report",
            produces ="application/json",consumes = "application/json",response = CenterVisitReportDto::class)
    fun registerCenterVisitReport(@Valid @RequestBody centerVisitReportdto: CenterVisitReportDto): ResponseWithError<CenterVisitReportDto> {

            var resultStatus=service.insertCenterVisitReport(centerVisitReportdto)
            if(resultStatus.isRight){

                log.info("Successfully inserted Center Visit Report....")
                return ResponseWithError.of(resultStatus.get())
            }
            return ResponseWithError.ofError(resultStatus.left.message)

    }

    @CrossOrigin
    @GetMapping("/{centerVisitReportId}")
    @ApiOperation("get one Center Visit Report by center Visit report Id",notes = "URI to Get one Center Visit Report by center Visit report Id",
            produces ="application/json",consumes = "application/json",response = CenterVisitReportDto::class)
    fun getCVRById(@Valid @PathVariable centerVisitReportId:String):ResponseWithError<CenterVisitReportDto> {

            var resultstatusdto = service.findByCenterVisitReportId(centerVisitReportId)
            if (resultstatusdto.isRight) {
                log.info( "Successfully getting One Center Visit Report By Id..")
                return ResponseWithError.of(resultstatusdto.get())
            }

            return ResponseWithError.ofError(resultstatusdto.left.message)

    }

    @CrossOrigin
    @GetMapping
    @ApiOperation("get All Center Visit Report",notes = "URI to Get all Center Visit Report",
            produces ="application/json",consumes = "application/json",response = List::class)
    fun getAllCVR():ResponseWithError<List<CenterVisitReportDto>> {

            var resultstatusdto = service.findAllCenterVisitReport()
            if (resultstatusdto.isRight) {
                log.info( "Successfully getting All Center Visit Reports.............. .")
                return ResponseWithError.of(resultstatusdto.get())
            }

            return ResponseWithError.ofError(resultstatusdto.left.message)

    }
   /* @CrossOrigin
    @PutMapping
    @ApiOperation("update one Center Visit Report",notes = "URI to Update   Center Visit Report",
            produces ="application/json",consumes = "application/json",response = CenterVisitReportDto::class)
    fun  editCenterVisitReport(@Valid @RequestBody centerVisitReportdto: CenterVisitReportDto):ResponseWithError<CenterVisitReportDto>{
        try {
            var resultcvrdto = service.updateCenterVisitReport(centerVisitReportdto)
            if (resultcvrdto.isRight) {
                log.info("Successfully Updated Center Visit Report............")
                return ResponseWithError.of(resultcvrdto.get())
            }
            return ResponseWithError.ofError(resultcvrdto.left.message)
        }catch (e:Exception){
            log.error("Exception raised on updating   center visit report....  fail operation......."+e.message)
            return ResponseWithError.ofError("Error raised on updating   center visit report")

        }
    }*/

    @CrossOrigin
    @PutMapping
    @ApiOperation("update  Center Visit Report",notes = "URI to Update   Center Visit Report",
            produces ="application/json",consumes = "application/json",response = CenterVisitReportDto::class)
    fun  updateCenterVisitReport(@Valid @RequestBody centerVisitReportUpdateDTO: CenterVisitReportUpdateDTO):ResponseWithError<CenterVisitReportDto> {

            var resultcvrdto = service.updateCVR(centerVisitReportUpdateDTO)
            if (resultcvrdto.isRight) {
                log.info("Successfully Updated Center Visit Report............")
                return ResponseWithError.of(resultcvrdto.get())
            }
            return ResponseWithError.ofError(resultcvrdto.left.message)

    }

    @CrossOrigin
    @GetMapping("/frontend/{frontEndId}")
    @ApiOperation("getting Center Visit Report by FrontEnd Id",notes = "URI togetting Center Visit Report by FrontEnd Id",
            produces ="application/json",consumes = "application/json",response = List::class)
    fun getCenterVisitReportByFrontEnd(@Valid @PathVariable frontEndId:String):ResponseWithError<List<CenterVisitReportDto>> {

            var resultdtolist = service.getCenterVisitReportByFrontEndId(frontEndId)
            if (resultdtolist.isRight) {
                log.info("Successfully getting center visit reports by frontEnd Id..........")
                return ResponseWithError.of(resultdtolist.get())
            }
            return ResponseWithError.ofError(resultdtolist.left.message)

    }

    @CrossOrigin
    @PutMapping("/changeStatus/{status}/{cvrid}")
    @ApiOperation("Change Center Visit Report Status  by cvr Id",notes = "URI to Change  Center Visit Report" +
            " Status by CvrId",
            produces ="application/json",consumes = "application/json",response = CenterVisitReportDto::class)
    fun changeStatus(@Valid @PathVariable status:CenterVisitReportStatus, @PathVariable cvrid:String ):ResponseWithError<CenterVisitReportDto> {

            var resultdtolist = service.changeStatus(status,cvrid)
            if (resultdtolist.isRight) {
                log.info("Successfully changing  center visit report Status by CenterVisitReport Id.........")
                return ResponseWithError.of(resultdtolist.get())
            }
            return ResponseWithError.ofError(resultdtolist.left.message)

    }

    @CrossOrigin
    @GetMapping("/getmonth/{centerId}/{yearMonth}")
    @ApiOperation("getting  one month Center Visit Report  by centerId and year month",notes = "URI to getting  month Center Visit Report by center Id and year month",
            produces ="application/json",consumes = "application/json",response = List::class)
    fun getMonthCenterVisitReportByCenterIdAndYearMonth(@Valid @PathVariable centerId:String, @PathVariable yearMonth: String):ResponseWithError<List<CenterVisitReportDto>> {


          var resultdtoList = service.getMonthWiseCenterVisitReportByCenterId(centerId,yearMonth)

        if (resultdtoList.isRight) {
            log.info("successfully getting CenterVisit Report  on  passing yearmonth,centerId,.....")
            return ResponseWithError.of(resultdtoList.get())
        }
        return ResponseWithError.ofError(resultdtoList.left.message)
    }




    @CrossOrigin
    @ApiOperation("getting  one month Center Visit Report by frontEndId and year month ",notes = "URI to getting  month Center Visit Report by frontEndId and year month",
            produces ="application/json",consumes = "application/json",response = List::class)
    @GetMapping("/getmonthby/{yearMonth}/{frontEndId}")
    fun getMonthCenterVisitReportByYearMonthAndFrontEndId(@Valid @PathVariable yearMonth:String,@PathVariable frontEndId:String):ResponseWithError<List<CenterVisitReportDto>>
    {

            var resultDtoList=service.getMonthWiseCenterVisitReportByYearMonthAndFrontEndId(yearMonth,frontEndId)
            if(resultDtoList.isRight){
                log.info("successfully getting CenterVisitReport on One Month passing FrontEndId and yearmonth..............")
                return ResponseWithError.of(resultDtoList.get())
            }
            return ResponseWithError.ofError(resultDtoList.left.message)



    }
    @CrossOrigin
    @PutMapping("/resolvedForCenter/{centerVisitReportId}")
    @ApiOperation("updating resolved TodoListCenters in TodoForCenters By Center Visit Report ",notes = "URI to updating resolved TodoListCenters in TodoForCenters By Center Visit Report",
            produces ="application/json",consumes = "application/json",response = CenterVisitReportDto::class)

    fun  resolvedTodoListCentersByCenterVisitReportId(@Valid @PathVariable centerVisitReportId:String, @RequestBody resolvedDtoList:List<TodoListCenterResolvedDto>):ResponseWithError<CenterVisitReportDto>{
        try{
            var resolvedResult=service.resolvedTodoForCenterByCvrId(centerVisitReportId,resolvedDtoList)
            if(resolvedResult.isRight){
                log.info("successfully updating resolved TodoListCenters in TodoForCenters By Center Visit Report......")
                return ResponseWithError.of(resolvedResult.get())
            }

            return ResponseWithError.ofError(resolvedResult.left.message)
        }
        catch(e:Exception){
            log.error("Exception Raised on  updating resolved TodoListCenters in TodoForCenters By Center Visit Report....."+e.message)
            return ResponseWithError.ofError("Error Raised on  updating resolved TodoListCenters in TodoForCenters By Center Visit Report")
        }


    }


    @CrossOrigin
    @PutMapping("/resolvedForApll/{centerVisitReportId}")
    @ApiOperation("updating resolved TodoListApll in TodoForApll By Center Visit Report ",notes = "URI to updating resolved TodoListApll in TodoForApll By Center Visit Report",
            produces ="application/json",consumes = "application/json",response = CenterVisitReportDto::class)

    fun resolvedTodoListApllByCenterVisitReportId(@Valid @PathVariable centerVisitReportId:String,@RequestBody  resolvedApllDtoList:List<TodoListApllResolvedDto>):ResponseWithError<CenterVisitReportDto>
    {

            var resolvedResult=service.resolvedTodoForApllByCvrId(centerVisitReportId,resolvedApllDtoList)
            if(resolvedResult.isRight){
                log.info("successfully updating resolved TodoListapll TodoForApll by Center visit Reporty Id.")
                return ResponseWithError.of(resolvedResult.get())
            }
            return ResponseWithError.ofError(resolvedResult.left.message)


    }


    @CrossOrigin
    @GetMapping("/getCenterDetails/{requestTypeId}/{typeOfRequest}")
    @ApiOperation("Get center name by passing requestId and typeOfRequest.",notes = "Get center name and center Id by passing requestTypeId and typeOfRequest.",
            produces ="application/json",consumes = "application/json",response = String::class)
    fun getCenterName(@Valid @PathVariable requestTypeId:String,@PathVariable typeOfRequest:String):ResponseWithError<CenterDTO>
    {
        var resolvedResult=service.getCenterName(requestTypeId,typeOfRequest)
        if(resolvedResult.isRight){
            log.info("successfully Get center name by passing requestId and typeOfRequest..")
            return ResponseWithError.of(resolvedResult.get())
        }
        return ResponseWithError.ofError(resolvedResult.left.message)
    }
}
