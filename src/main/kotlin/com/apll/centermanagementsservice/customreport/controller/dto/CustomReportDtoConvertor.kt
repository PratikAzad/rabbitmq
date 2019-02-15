package com.apll.centermanagementsservice.customreport.controller.dto

import com.apll.centermanagementsservice.customreport.model.CustomReport
import com.apll.centermanagementsservice.customreport.model.CustomReportId

class CustomReportDtoConvertor {

    object Convertor {


        fun modelToDtoConvertor(customReport: CustomReport): CustomReportDto {

            var customReportDto = CustomReportDto(customReport.customReportId!!.customReportId,
                    customReport.customScheduleId!!,
                    customReport.description!!,
                    customReport.customReportDate!!,
                    customReport.frontEndScheduleId!!,
                    customReport.frontEndName,null)
            return customReportDto
        }

        fun dtoToModelConvertor(customReportDto: CustomReportDto):CustomReport{
           /* var customId=CustomReportId()
            if(customReportDto.customReportId.equals("")) {
                customId.customReportId = customReportDto.customReportId
            }*/

            var customReport=CustomReport(CustomReportId(),
                            customReportDto.customScheduleId,
                            customReportDto.description,
                            customReportDto.customReportDate,
                            customReportDto.frontEndScheduleId,
                            customReportDto.frontEndName
                    )

            return customReport
        }

        fun modelListToDtoListConvertor(customList:List<CustomReport>):List<CustomReportDto>{
            var customReportDtoList=ArrayList<CustomReportDto>()
            for(custom in customList){
                var dto= modelToDtoConvertor(custom)
                customReportDtoList.add(dto)
            }
            return customReportDtoList

        }


        fun dtoListToModelListConvertor(customReportDtoList: List<CustomReportDto>):List<CustomReport>{
            var customList=ArrayList<CustomReport>()
            for(customDto in customReportDtoList){
                var custom= dtoToModelConvertor(customDto)
                customList.add(custom)
            }
            return customList
        }


    }


}