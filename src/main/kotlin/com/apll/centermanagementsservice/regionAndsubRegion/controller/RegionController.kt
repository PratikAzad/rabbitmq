package com.apll.centermanagementsservice.regionAndsubRegion.controller


import com.apll.centermanagementsservice.config.ResponseWithError
import com.apll.centermanagementsservice.regionAndsubRegion.controller.regiondto.RegionDTO
import com.apll.centermanagementsservice.regionAndsubRegion.service.RegionServiceImpl
import com.apll.centermanagementsservice.resttemplate.EmployeeRestTemplate
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import org.springframework.web.bind.annotation.RequestMapping



@RequestMapping(value = ["/region"])
@RestController
@Api(value = "RegionController", description= "Rest API for Region Management")
class RegionController (var serviceRegion: RegionServiceImpl) {

    @Autowired
    private val employeeRestTemplate: EmployeeRestTemplate? = null

    val LOGGER = LoggerFactory.getLogger(RegionController::class.java)

    /*@Autowired(required = true)
    lateinit var serviceRegion: RegionServiceImpl*/

    @CrossOrigin
    @PostMapping
    @ApiOperation(value = "Resgister Region", notes = "URI to Register Region.", produces = "application/json", consumes = "application/json", response = RegionDTO::class)
    fun RegisterRegion(@Valid @RequestBody regionDTO: RegionDTO): ResponseWithError<RegionDTO> {
            var resultdto = serviceRegion.insertRegion(regionDTO)
            if (resultdto.isRight) {
                LOGGER.info("Successfully Getting Region is Inserted")
                return ResponseWithError.of(resultdto.get())
            }
                 LOGGER.error(resultdto.left.message)
                return ResponseWithError.ofError(resultdto.left.message)
    }


    @CrossOrigin
    @GetMapping("/{regionId}")
    @ApiOperation(value = "Get one Region", notes = "URI to get one  Region.", produces = "application/json", consumes = "application/json", response = RegionDTO::class)
    fun searchRegionById(@Valid @PathVariable regionId: String):ResponseWithError<RegionDTO>{
            var resultdto=serviceRegion.getRegionById(regionId)
            if(resultdto.isRight){
                LOGGER.info("Successfully  Getting Region By Id.....")
                return ResponseWithError.of(resultdto.get())
            }
                 LOGGER.error(resultdto.left.message)
                return ResponseWithError.ofError(resultdto.left.message)
    }


    @CrossOrigin
    @PutMapping
    @ApiOperation(value = "Update Region", notes = "URI to Update Region.", produces = "application/json", consumes = "application/json", response = RegionDTO::class)
    fun editRegion(@Valid @RequestBody regionDTO: RegionDTO):ResponseWithError<RegionDTO>{
            var resultdto=serviceRegion.updateRegion(regionDTO)
            if(resultdto.isRight){
                LOGGER.info("Successfully Getting Region is Updated...")
                return ResponseWithError.of(resultdto.get())
            }
                 LOGGER.error(resultdto.left.message)
                return ResponseWithError.ofError(resultdto.left.message)
    }


    @CrossOrigin
    @GetMapping
    @ApiOperation(value = "Get All  Region", notes = "URI to Get All Region.", produces = "application/json", consumes = "application/json", response = List::class)
    fun searchAllRegion():ResponseWithError<List<RegionDTO>>{
            var resuldtolist=serviceRegion.getAllRegion()
            if(resuldtolist.isRight){
                LOGGER.info("Successfully Getting SearchAll Region ...")
                return ResponseWithError.of(resuldtolist.get())
            }
                 LOGGER.error(resuldtolist.left.message)
                return ResponseWithError.ofError(resuldtolist.left.message)
    }

    @CrossOrigin
    @GetMapping("getByState/{state}")
    @ApiOperation(value = "Getting All Region by State", notes = "URI to Get All Region By State", produces = "application/json", consumes = "application/json", response = List::class)
    fun findByState(@Valid @PathVariable state:String):ResponseWithError<List<RegionDTO>>{
        var resuldtolist=serviceRegion.getByState(state)
        if(resuldtolist.isRight){
            LOGGER.info("Successfully Getting SearchAll Region By State ...")
            return ResponseWithError.of(resuldtolist.get())
        }
        LOGGER.error(resuldtolist.left.message)
        return ResponseWithError.ofError(resuldtolist.left.message)
    }





   /* @CrossOrigin
    @GetMapping("getAllEmployeesWithRegion/{regionId}")
    @ApiOperation(value = "Get All Employess with  Region Id", notes = "URI to Get All Employee with RegionId.", produces = "application/json", consumes = "application/json", response = List::class)
    fun getAllEmployeesWithRegion(@PathVariable("regionId") regionId:String): ResponseWithError<*>
    {
        var employeeRegionDto=EmployeeRegionDto()
       try {
           var resultdto = serviceRegion.getRegionById(regionId)
           if (resultdto.isLeft) {
               LOGGER.info("error{}", resultdto.left)
               return ResponseWithError.ofError<String>("error")
           }
           var employeedto = employeeRestTemplate!!.getAllEmployeesByRegionId(regionId)
           employeeRegionDto.employeeDtos = employeedto
           employeeRegionDto.regionDTO = resultdto.get()
       }catch (e:java.lang.Exception){
           LOGGER.info("error{}", e)
           return ResponseWithError.ofError<String>("error")
       }
        return ResponseWithError.of(employeeRegionDto)
    }
    }*/
}