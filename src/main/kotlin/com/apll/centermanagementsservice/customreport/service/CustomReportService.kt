package com.apll.centermanagementsservice.customreport.service

import com.apll.centermanagementsservice.customreport.controller.dto.CustomReportDto
import com.apll.centermanagementsservice.customreport.model.CustomReport
import io.vavr.control.Either

interface CustomReportService {

    fun insertCustomReport(customReportDto: CustomReportDto): Either<Exception, String>
   /* fun updateCustomReport(customReportDto: CustomReportDto): Either<Exception, CustomReportDto>*/
    fun getByCustomReportId(customReportId:String):Either<Exception,CustomReportDto>
    fun getAllCustomReports():Either<Exception,List<CustomReportDto>>

}