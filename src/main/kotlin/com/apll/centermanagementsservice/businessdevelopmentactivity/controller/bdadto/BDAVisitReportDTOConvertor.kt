package com.apll.centermanagementsservice.businessdevelopmentactivity.controller.bdadto

import com.apll.centermanagementsservice.businessdevelopmentactivity.model.BDAVisitReport
import com.apll.centermanagementsservice.businessdevelopmentactivity.model.BDAVisitReportId
import com.apll.centermanagementsservice.businessdevelopmentactivity.controller.imagedto.BDAVisitImageDTOConvertor

class BDAVisitReportDTOConvertor {

    object convertor{
        fun modelToDtoConvertor(bdaVisitReport: BDAVisitReport): BDAVisitReportDto {
            var bdaVisitReportDto= BDAVisitReportDto(bdaVisitReport.bdaVisitReportId!!.bdaVisitReportId,
                    bdaVisitReport.bdaVisitRequestId,
                    BDAVisitImageDTOConvertor.Convertor.modelListToDtoList(bdaVisitReport.bdaVisitImageList!!),
                    bdaVisitReport.description!!,
                    bdaVisitReport.bdaVisitReportDate!!,null,null,null,null)

            return bdaVisitReportDto
                    }
        fun dtoToModelConvertor(bdaVisitReportDto: BDAVisitReportDto): BDAVisitReport {
            var id= BDAVisitReportId()
            if(!(bdaVisitReportDto.bdaVisitReportId.equals("") || bdaVisitReportDto.bdaVisitReportId == null)) {
                id.bdaVisitReportId = bdaVisitReportDto.bdaVisitReportId!!
            }
            var bdaVisitReport= BDAVisitReport(
                    id,
                    bdaVisitReportDto.bdaVisitRequestId!!,
                    BDAVisitImageDTOConvertor.Convertor.dtoListToModelList(bdaVisitReportDto.bdaVisitImageList!!),
                    bdaVisitReportDto.description,
                    bdaVisitReportDto.bdaVisitReportDate)
            return bdaVisitReport



        }

        fun modelListToDtoList(bdaVisitReportList:List<BDAVisitReport>):List<BDAVisitReportDto>{
            var  bdaVisitReportDtoList=ArrayList<BDAVisitReportDto>()
            for(bdaVisitReport in bdaVisitReportList){
                var bdaVisitReportDto= modelToDtoConvertor(bdaVisitReport)
                bdaVisitReportDtoList.add(bdaVisitReportDto)
            }
            return bdaVisitReportDtoList
        }

        fun dtoListToModelList(bdaVisitReportDtoList:List<BDAVisitReportDto>):List<BDAVisitReport>
        {
            var bdaVisitReportList=ArrayList<BDAVisitReport>()
            for(bdaVisitReportDto in bdaVisitReportDtoList){
                var bdaVisitReport= dtoToModelConvertor(bdaVisitReportDto)
                bdaVisitReportList.add(bdaVisitReport)
            }
            return bdaVisitReportList
        }




    }
}