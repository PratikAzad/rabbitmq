package com.apll.centermanagementsservice.visitingrequest.controller

import com.apll.centermanagementsservice.config.ResponseWithError
import com.apll.centermanagementsservice.visitingrequest.model.RequestType
import com.apll.centermanagementsservice.visitingrequest.controller.visitrequestdto.ResolvedDTO
import com.apll.centermanagementsservice.visitingrequest.controller.visitrequestdto.VisitingRequestDTO
import com.apll.centermanagementsservice.visitingrequest.dashboard.VisReqResolvedAnalysis
import com.apll.centermanagementsservice.visitingrequest.dashboard.VisitRequestAnalysis
import com.apll.centermanagementsservice.visitingrequest.service.VisitingRequestService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/visitingrequest")
@Api("Visiting Request Controller",description = "Rest Api for Existing Center Visit Request")
class VisitingRequestController {
    @Autowired
    lateinit var service: VisitingRequestService

    val log = LoggerFactory.getLogger(VisitingRequestController::class.java)

    @CrossOrigin
    @PostMapping
    @ApiOperation("Insert Visiting Request", notes = "URI to Register Visiting Request",
            produces = "application/json", consumes = "application/json", response = VisitingRequestDTO::class)
    fun addVisitingRequest(@Valid @RequestBody dto: VisitingRequestDTO): ResponseWithError<VisitingRequestDTO> {

            var resultdto = service.saveVisitingRequest(dto)
            if (resultdto.isRight) {
                log.info("Successfully inserted Visiting Request....")
                return ResponseWithError.of(resultdto.get())
            }
               log.error(resultdto.left.message)
               return ResponseWithError.ofError(resultdto.left.message)
    }

    @CrossOrigin
    @GetMapping("getByVisitingRequestId/{id}")
    @ApiOperation("Get One Visiting Request", notes = "URI to Get One Visiting RequestId",
            produces = "application/json", consumes = "application/json", response = VisitingRequestDTO::class)
    fun searchVisitingRequestById(@Valid @PathVariable ("id")id:String): ResponseWithError<VisitingRequestDTO> {

            var resultdto = service.getVisitingRequestById(id)
            if (resultdto.isRight) {
                 log.info("Successfully Getting VisingRequest By Id.............")
                return ResponseWithError.of(resultdto.get())
            }
               log.error(resultdto.left.message)
              return ResponseWithError.ofError(resultdto.left.message)
    }


    @CrossOrigin
    @PutMapping
    @ApiOperation("Update Visiting Request", notes = "URI to Update Visiting Request",
            produces = "application/json", consumes = "application/json", response = VisitingRequestDTO::class)
    fun editVisitingRequest(@Valid @RequestBody dto: VisitingRequestDTO): ResponseWithError<VisitingRequestDTO> {

            var resultdto = service.updateVisitingRequest(dto)
            if (resultdto.isRight) {
                 log.info("Successfully Update Visiting Request........")
                return ResponseWithError.of(resultdto.get())
            }
               log.error(resultdto.left.message)
              return ResponseWithError.ofError(resultdto.left.message)
    }


    @CrossOrigin
    @ApiOperation("Getting List of  Visiting Request by RequestType",notes = "URI to Getting List of Visiting Request By RequestType",
            produces ="application/json",consumes = "application/json",response = List::class)
    @GetMapping("/{requestType}")
    fun   searchVisitingRequestByRequestBy(@PathVariable("requestType") requestType: RequestType):ResponseWithError<List<VisitingRequestDTO>>{

            var requestdtolist=service.getVisitingRequestByRequest(requestType)
            if (requestdtolist.isRight) {
                log.info("Successfully Getting  Visiting Requests by RequestBy ................")
                return ResponseWithError.of(requestdtolist.get())
            }
                  log.error(requestdtolist.left.message)
                return ResponseWithError.ofError(requestdtolist.left.message)
    }


    @CrossOrigin
    @GetMapping
    @ApiOperation("Get All Visiting Request", notes = "URI to Get All Visiting Request",
            produces = "application/json", consumes = "application/json", response = List::class)
    fun readAllVisitingRequest(): ResponseWithError<List<VisitingRequestDTO>> {

            var resultdtoList = service.getAllVisitingRequest()
            if (resultdtoList.isRight) {
                log.info("Successfully Getting All Visiting Requests................")
                return ResponseWithError.of(resultdtoList.get())
            }
               log.error(resultdtoList.left.message)
              return ResponseWithError.ofError(resultdtoList.left.message)
    }

        @CrossOrigin
        @GetMapping("getByRequesterId/{requesterId}")
        @ApiOperation("Getting All Visiting Request by Requester Id", notes = "URI to Visiting Request by Requester Id",
                produces = "application/json", consumes = "application/json", response = List::class)
        fun getVisitingRequestByRequesterId(@Valid @PathVariable requesterId: String):
                                                         ResponseWithError<List<VisitingRequestDTO>> {

                var visitingRequestDTO = service.getVisitingRequestByRequesterId(requesterId)
                if (visitingRequestDTO.isRight) {
                    log.info("Successfully Getting VisingRequest By RequesterId")
                    return ResponseWithError.of(visitingRequestDTO.get())
                }
                 log.error(visitingRequestDTO.left.message)
                return ResponseWithError.ofError(visitingRequestDTO.left.message)
        }

    @CrossOrigin
    @PutMapping("/{resolve}")
    @ApiOperation("Resolve Visiting Request", notes = "URI to Resolve Visiting Request",
            produces = "application/json", consumes = "application/json", response = ResolvedDTO::class)
    fun resolved(@Valid @RequestBody dto: ResolvedDTO): ResponseWithError<VisitingRequestDTO> {

        var resultdto = service.resolve(dto)
        if (resultdto.isRight) {
            log.info("Successfully Resolve Visiting Request........")
            return ResponseWithError.of(resultdto.get())
        }
            log.error(resultdto.left.message)
           return ResponseWithError.ofError(resultdto.left.message)
    }


    //For schedule we will use this uri.[To get all unresolved visiting request.]
    @CrossOrigin
    @GetMapping("getByUnResolvedRegionId/{regionId}")
    @ApiOperation("Getting UnResolved Visiting Request by Region Id", notes = "URI to Getting All Visiting Request by Region Id",
            produces = "application/json", consumes = "application/json", response = List::class)
    fun getVisitingRequestByRegionId(@Valid @PathVariable regionId: String):
            ResponseWithError<List<VisitingRequestDTO>> {

            var visitingRequestDTO = service.getVisitingRequestByRegionId(regionId)
            if (visitingRequestDTO.isRight) {
                log.info("Successfully Getting VisingRequest By RegionId")
                return ResponseWithError.of(visitingRequestDTO.get())
            }
                log.error(visitingRequestDTO.left.message)
               return ResponseWithError.ofError(visitingRequestDTO.left.message)
    }



    @CrossOrigin
    @GetMapping("getByResolvedRegionId/{regionId}")
    @ApiOperation("Getting Resolved Visiting Request By RegionId", notes = "URI to Resolved Visiting Request by Region Id",
            produces = "application/json", consumes = "application/json", response = List::class)
    fun getResolvedVisitingRequest(@Valid @PathVariable regionId: String):
            ResponseWithError<List<VisitingRequestDTO>> {

        var visitingRequestDTO = service.getResolvedVisitingRequest(regionId)
        if (visitingRequestDTO.isRight) {
            log.info("Successfully Getting VisingRequest ")
            return ResponseWithError.of(visitingRequestDTO.get())
        }
            log.error(visitingRequestDTO.left.message)
           return ResponseWithError.ofError(visitingRequestDTO.left.message)
    }

    @CrossOrigin
    @GetMapping("getNotScheduledRequestByEmployeeId/{employeeId}")
    @ApiOperation("Getting UnResolved VisitingRequest By EmployeeId ", notes = "URI to UnResolved Visiting Request by Employee Id",
            produces = "application/json", consumes = "application/json", response = List::class)

    fun getNotScheduledRequestByEmployeeId(@Valid @PathVariable employeeId: String):ResponseWithError<List<VisitingRequestDTO>>{

        var visitingRequestDTO=service.getNotScheduledRequestByEmployeeId(employeeId)
        if(visitingRequestDTO.isRight){
            log.info("Successfully Getting EmployeeId")
            return ResponseWithError.of(visitingRequestDTO.get())
        }
            log.error(visitingRequestDTO.left.message)
           return ResponseWithError.ofError(visitingRequestDTO.left.message)
    }


    @CrossOrigin
    @GetMapping("geByEmployeeId/{employeeId}")
    @ApiOperation("Getting Visiting Request By EmployeeId ", notes = "URI to Visiting Request by Employee Id",
            produces = "application/json", consumes = "application/json", response = List::class)

    fun getByEmployeeId(@Valid @PathVariable employeeId: String):ResponseWithError<List<VisitingRequestDTO>> {

        var visitingRequestDTO = service.getByEmployeeId(employeeId)
        if (visitingRequestDTO.isRight) {
            log.info("Successfully Getting EmployeeId")
            return ResponseWithError.of(visitingRequestDTO.get())
        }
            log.error(visitingRequestDTO.left.message)
           return ResponseWithError.ofError(visitingRequestDTO.left.message)
    }

    @CrossOrigin
    @GetMapping("/visReqAnalysis")
    @ApiOperation(" Visiting Request Analysis", notes = "URI to Get Visiting Request Analysis",
            produces = "application/json", consumes = "application/json", response = VisitRequestAnalysis::class)
    fun getVisReqAnalysis(): ResponseWithError<VisitRequestAnalysis> {

        var result=service.visReqAnalysis()
        if(result.isRight){

            log.info("Successfully Getting Visiting Requests Analysis.......")
            return ResponseWithError.of(result.get())
        }
        return ResponseWithError.ofError(result.left.message)

    }


    @CrossOrigin
    @GetMapping("/visReqResolvedAnalysis")
    @ApiOperation(" Visiting Request Resolved Analysis", notes = "URI to Get Visiting Request Resolved Analysis",
            produces = "application/json", consumes = "application/json", response = VisReqResolvedAnalysis::class)
    fun getVisReqResolvedAnalysis(): ResponseWithError<VisReqResolvedAnalysis> {

        var result=service.visReqResolvedAnalysis()
        if(result.isRight){

            log.info("Successfully Getting Visiting Requests Resolved Analysis.......")
            return ResponseWithError.of(result.get())
        }
        return ResponseWithError.ofError(result.left.message)
    }


    @CrossOrigin
    @GetMapping("/last2MonthAnalysis")
    @ApiOperation(" Visiting Request Analysis for two month.", notes = "URI to Get Visiting Request Analysis for Two-Month",
            produces = "application/json", consumes = "application/json", response = VisitRequestAnalysis::class)
    fun last2MonthAnalysis(): ResponseWithError<VisitRequestAnalysis> {

        var result=service.last2MonthAnalysis()
        if(result.isRight){

            log.info("Successfully Getting Visiting Requests Analysis.......")
            return ResponseWithError.of(result.get())
        }
        return ResponseWithError.ofError(result.left.message)
    }

    @CrossOrigin
    @GetMapping("/unresolved")
    @ApiOperation(" Visiting Request UnResolved.", notes = "URI to Get Visiting Request UnResolved..",
            produces = "application/json", consumes = "application/json", response = List::class)
    fun getUnresolved(): ResponseWithError<List<VisitingRequestDTO>> {

        var result=service.getUnresolved()
        if(result.isRight){
            log.info("Successfully Getting Visiting Requests Unresolved.......")
            return ResponseWithError.of(result.get())
        }
        log.error(result.left.message)
        return ResponseWithError.ofError(result.left.message)
    }


}


/* @CrossOrigin
    @GetMapping("getByEmployeeId/{employeeId}")
    @ApiOperation("Get All Existing Center visiting Request According to Employee.",
            notes = "URI to Get All Existing Center visiting Request According to Employee.",
            produces = "application/json", consumes = "application/json", response = List::class)
    fun getVisitingRequestByEmployeeId(@Valid @PathVariable employeeId: String): ResponseWithError<*> {

        var visitingRequestDTO = service.getVisitingRequestByEmployeeId(employeeId)
        if (visitingRequestDTO.isRight) {
            log.info("Successfully Getting VisingRequest By EmployeeId")
            return ResponseWithError.of(visitingRequestDTO.get())
        }
        return ResponseWithError.of(visitingRequestDTO.left.message)
    }*/




/*@CrossOrigin
   @PostMapping("/listvisitingrequest")
   @ApiOperation("insert list of  visiting Request",notes = "URI to insert list of visiting Request",
           produces ="application/json",consumes = "application/json",response = List::class)
   fun insertListOfVisitingRequest( @Valid @RequestBody  dtoList:List<VisitingRequestDTO>):ResponseWithError<List<VisitingRequestDTO>> {
       try {
           var requestList = ArrayList<VisitingRequestDTO>()
           var requesterrormsg = ""
           for (dto in dtoList) {
               var resultdto = service.saveVisitingRequest(dto)
               if (resultdto.isRight) {
                   log.info("successfully inserted list of Visiting Request....")
                   requestList.add(resultdto.get())
               } else {
                   requesterrormsg.plus(resultdto.left.message + "/n")
               }
           }
           if (requesterrormsg.equals("")) {
               return ResponseWithError.of(requestList)
           }
           return ResponseWithError.ofError("List of Visiting Requests Nopt Inserted..........")
       }catch (e:Exception){
           log.error("Exception Occured in visitng Request List of insertion............")
           return ResponseWithError.ofError(e.message)
       }
   }*/





/* @CrossOrigin
   @GetMapping("/visitingrequest/resolved")
   @ApiOperation("Resolved visiting Request", notes = "URI to Resolved visiting Request",
           produces = "application/json", consumes = "application/json", response = List::class)
   fun readResolvedMethod(): ResponseWithError<List<VisitingRequestDTO>> {

       try {
           var resultdtoList = service.getByResolved()

           if (resultdtoList.isRight) {
               log.info("Successfully Getting Resolved Visiting Requests................")
               return ResponseWithError.of(resultdtoList.get())
           }
           return ResponseWithError.ofError(resultdtoList.left.message)
       } catch (e: Exception) {
           log.error("Exception Occured In Getting Resolved Visiting Request....." + e.message)
           return ResponseWithError.ofError(e.message)
       }
   }
*/






/*@CrossOrigin
   @ApiOperation("Getting Request for One Month  Center visiting Request",notes = "URI to Getting Request for One Month center Visiting Request",
           produces ="application/json",consumes = "application/json",response = List::class)
  @GetMapping(name = "visitingrequest/yearmonth/{monthYear}/regionId/{regionId}")
   //@GetMapping(name = "/visitingrequest/abcd/{monthYear}")
   fun readAllCVRForMonth(@PathVariable("monthYear") monthYear: String, @PathVariable("regionId") regionId: String):ResponseWithError<List<VisitingRequestDTO>>{
       try {
           var requestDtoList=service.getAllCVRForMonth(regionId,monthYear)
           if(requestDtoList.isRight){
               log.info("Successfully Getting Request for One Month CenterVisiting Requests.....")
               return ResponseWithError.of(requestDtoList.get())
           }
             log.error(requestDtoList.left.message+" :: "+requestDtoList.left.printStackTrace())
           return ResponseWithError.ofError(requestDtoList.left.message)
       }
       catch (e:Exception){
           log.error(("Exception Occured In Getting Request for One Month Center Visiting Request.....")+e.message)
           return ResponseWithError.ofError(e.message)
       }
   }*/

/*@CrossOrigin
@GetMapping("visitingrequest/{date}/{regionId}")
@ApiOperation("Get All Center Visited Report For Month By Region Id", notes = "URI to All Center Visiting Report Request By RequestDate and Region ID visiting Request",
        produces = "application/json", consumes = "application/json", response = List::class)
fun searchAllCVRForMonthAndByRegionId(@PathVariable("date") date: String,
                                      @PathVariable("regionId") regionId: String): ResponseWithError<List<VisitingRequestDTO>> {
    try {

        var resultVisitingRequest = service.getAllCVRForMonth(date, regionId)
        if (resultVisitingRequest.isRight) {
            log.info("Successfully Getting Request for One Month CenterVisiting Requests...........")
            return ResponseWithError.of(resultVisitingRequest.get())
        }
        return ResponseWithError.ofError(resultVisitingRequest.left.message)
    } catch (e: Exception) {
        log.error("Exception Occured In Getting Request for One Month Center Visiting Request..........." + e.message)
        return ResponseWithError.ofError(e.message)
    }
}
*/


/*@CrossOrigin
   @ApiOperation("Change  Status to   visiting Request ",notes = "URI to Change Status to  visiting Request",
           produces ="application/json",consumes = "application/json",response = VisitingRequestDTO::class)
   @PutMapping("visitingrequest/status/{resolverId}/{status}")
   fun  EditVisitingRequestStatus(@Valid @PathVariable("resolverId")  resolverId:String, @PathVariable("status") status:Boolean):ResponseWithError<VisitingRequestDTO>{
      try {
          var resultdto = service.changeStatusToVisitingRequest(status, resolverId)
           if(resultdto.isRight){
               log.info("Sucessfully Change Status to visiting Request......")
               return ResponseWithError.of(resultdto.get())
           }
          return ResponseWithError.ofError(resultdto.left.message)
      }catch (e:Exception){
          log.error("Exception Occured in Change Status to visiting Request..........fail.."+e.message)
          return ResponseWithError.ofError(e.message)
      }
   }
*/

