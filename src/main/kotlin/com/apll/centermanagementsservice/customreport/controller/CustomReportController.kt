package com.apll.centermanagementsservice.customreport.controller

import com.apll.centermanagementsservice.config.ResponseWithError
import com.apll.centermanagementsservice.customreport.controller.dto.CustomReportDto
import com.apll.centermanagementsservice.customreport.model.CustomReport
import com.apll.centermanagementsservice.customreport.service.CustomReportService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RequestMapping("/customreport")
@RestController
@Api("CustomReportController",description = "Rest Api for Custom Report")
class CustomReportController {

    @Autowired
    lateinit var service:CustomReportService

    val log = LoggerFactory.getLogger(CustomReportController::class.java)

    @CrossOrigin
    @PostMapping
    @ApiOperation("insert custom Report",notes = "URI to register Custom  Report",
            produces ="application/json",consumes = "application/json",response = String::class)
    fun registerCustomReport(@RequestBody customReportDto: CustomReportDto):ResponseWithError<String>{
        var  resultReport=service.insertCustomReport(customReportDto)
        if(resultReport.isRight){
            log.info("Custom Report is Created sucessfully!!!!!!")
            return ResponseWithError.of(resultReport.get())

        }
        log.error("error raised on inserting Custom Report,Report is not  Created ...")
        return ResponseWithError.ofError(resultReport.left.message)
    }



    @CrossOrigin
    @GetMapping("/{customReportId}")
    @ApiOperation("get one custom Report",notes = "URI to get one Custom  Report",
            produces ="application/json",consumes = "application/json",response = CustomReportDto::class)
    fun SerachByCustomReportId(@PathVariable customReportId:String):ResponseWithError<CustomReportDto>{
        var resultReport=service.getByCustomReportId(customReportId)
        if(resultReport.isRight){
            log.info("getting  the Custom Report sucessfully!!!!!!")
            return ResponseWithError.of(resultReport.get())

        }
        log.error("Error raised on getting Custom  report By Id..")
        return ResponseWithError.ofError(resultReport.left.message)
    }

    @CrossOrigin
    @GetMapping
    @ApiOperation("getting  All custom Report",notes = "URI to getting All Custom  Report",
            produces ="application/json",consumes = "application/json",response = CustomReportDto::class)
    fun searchAllCustomReport():ResponseWithError<List<CustomReportDto>>{
        var reports=service.getAllCustomReports()
        if(reports.isRight){
            log.info("getting  All  Custom Reports sucessfully!!!!!!")
            return ResponseWithError.of(reports.get())
        }
        log.error("Error raised on getting Custom reports")
        return ResponseWithError.ofError(reports.left.message)
    }








    /* @CrossOrigin
    @PutMapping
    @ApiOperation("Update custom Report",notes = "URI to Update Custom  Report",
            produces ="application/json",consumes = "application/json",response = CustomReportDto::class)
    fun editCustomReport(@RequestBody customReportDto: CustomReportDto):ResponseWithError<CustomReportDto>{
        var updateReport=service.updateCustomReport(customReportDto)
        if(updateReport.isRight){
            log.info("Custom Report is Updated sucessfully!!!!!!")
            return ResponseWithError.of(updateReport.get())
        }
        log.error("Error raised on updating custom Report,Report is Not Updated..")
        return ResponseWithError.ofError(updateReport.left.message)
    }*/
}