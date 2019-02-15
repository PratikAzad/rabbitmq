package com.apll.centermanagementsservice.centrevisitreport.model.dto

import com.apll.centermanagementsservice.centrevisitreport.model.CenterVisitReport
import com.apll.centermanagementsservice.centrevisitreport.model.CenterVisitReportId

class CenterVisitReportDtoConverter {

    object converter{
        fun modelToDtoConverter(centerVisitReport: CenterVisitReport):CenterVisitReportDto{
            var centerVisitReportDto=CenterVisitReportDto(
                    centerVisitReport.centerVisitReportId!!.centerVisitReportId,
                    centerVisitReport.centerId,
                    centerVisitReport.frontEndId,
                    centerVisitReport.requestId,
                    centerVisitReport.frontEndScheduleId,
                    centerVisitReport.scheduleDate,
                    centerVisitReport.centerVisitReportDate,
                    DropOutDetailsDTOConvertor.Convertor.modelToDtoConverterList(centerVisitReport.dropOutDetails),
                    RunningBatchStatusDTOConvertor.Convertor.modelToDtoConverterList(centerVisitReport.runningBatchStatus!!),
                    StockStatusDTOConvertor.convertor.modelToDtoConverterList(centerVisitReport.stockStatus),
                    CompletedBatchStatusDTOConvertor.Convertor.modelToDtoConverterList(centerVisitReport.completedBatchStatus),
                    TodoForCenterDTOConvertor.Convertor.modelToDTOConvertor(centerVisitReport.todoForCenter),
                    TodoForApllDTOConvertor.Convertor.modelToDTOConvertor(centerVisitReport.todoForApll),
                    CvrInfrastructureDtoConvertor.Convertor.modelListToDtoList(centerVisitReport.infrastructure!!),
                            centerVisitReport.centerVisitReportStatus,
                    null
                    )

            return centerVisitReportDto
        }


        fun dtoToModelConverter(dto: CenterVisitReportDto):CenterVisitReport{
            var id=CenterVisitReportId()

            if(!dto.centerVisitReportId.equals("")){
               id.centerVisitReportId= dto.centerVisitReportId!!
        }

            var centerVisitReport=CenterVisitReport(id,

                    dto.centerId!!,
                    dto.frontEndId!!,
                    dto.requestId!!,
                    dto.frontEndScheduleId!!,
                    dto.scheduleDate!!,
                    dto.centerVisitReportDate!!,
                    DropOutDetailsDTOConvertor.Convertor.dtoToModelConverterList(dto.dropOutDetails),
                    RunningBatchStatusDTOConvertor.Convertor.dtoToModelConverterList(dto.runningBatchStatus!!),
                    StockStatusDTOConvertor.convertor.dtoToModelConverterList(dto.stockStatus),
                    CompletedBatchStatusDTOConvertor.Convertor.dtoToModelConverterList(dto.completedBatchStatus),
                    TodoForCenterDTOConvertor.Convertor.dtoToModelConvertor(dto.todoForCenterdto),
                    TodoForApllDTOConvertor.Convertor.dtoToModelConvertor(dto.todoForAplldto),
                    CvrInfrastructureDtoConvertor.Convertor.dtoListToModelList(dto.infrastructureDTO!!),
                    dto.centerVisitReportStatus!!)


            return centerVisitReport
        }


        fun dtoToModelConverterList(dtos:List<CenterVisitReportDto>):List<CenterVisitReport>{
            var centerVisitReportSet=ArrayList<CenterVisitReport>()

            for(dto in dtos){
                centerVisitReportSet.add(dtoToModelConverter(dto))
            }
            return centerVisitReportSet
        }

        fun modelToDtoConverterList(CenterVisitReport: List<CenterVisitReport>):List<CenterVisitReportDto>{
            var centerVisitReportSet=ArrayList<CenterVisitReportDto>()

            for(cvr in CenterVisitReport){
                centerVisitReportSet.add(modelToDtoConverter(cvr))
            }
            return centerVisitReportSet
        }

        fun modelToDtoForUpdate(centerVisitReport: CenterVisitReport):CenterVisitReportUpdateDTO{
            var  centerVisitReportUpdateDto=CenterVisitReportUpdateDTO(
                    centerVisitReport.centerVisitReportId!!.centerVisitReportId,
                    DropOutDetailsDTOConvertor.Convertor.modelToDtoConverterList(centerVisitReport.dropOutDetails!!),
                    RunningBatchStatusDTOConvertor.Convertor.modelToDtoConverterList(centerVisitReport.runningBatchStatus!!),
                    StockStatusDTOConvertor.convertor.modelToDtoConverterList(centerVisitReport.stockStatus!!),
                    CompletedBatchStatusDTOConvertor.Convertor.modelToDtoConverterList(centerVisitReport.completedBatchStatus!!),
                    TodoForCenterDTOConvertor.Convertor.modelToDTOConvertor(centerVisitReport.todoForCenter),
                    TodoForApllDTOConvertor.Convertor.modelToDTOConvertor(centerVisitReport.todoForApll),
                    CvrInfrastructureDtoConvertor.Convertor.modelListToDtoList(centerVisitReport.infrastructure!!),
                    centerVisitReport.centerVisitReportStatus


            )
            return centerVisitReportUpdateDto
        }
        fun dtoToModelForUpdate(centerVisitReportUpdateDTO:CenterVisitReportUpdateDTO):CenterVisitReport{
            var cvrId=CenterVisitReportId(centerVisitReportUpdateDTO.centerVisitReportId!!)

            var centerVisitReport=CenterVisitReport(
                    cvrId,
                    DropOutDetailsDTOConvertor.Convertor.dtoToModelConverterList(centerVisitReportUpdateDTO.dropOutDetails),
                    RunningBatchStatusDTOConvertor.Convertor.dtoToModelConverterList(centerVisitReportUpdateDTO.runningBatchStatus!!),
                    StockStatusDTOConvertor.convertor.dtoToModelConverterList(centerVisitReportUpdateDTO.stockStatus),
                    CompletedBatchStatusDTOConvertor.Convertor.dtoToModelConverterList(centerVisitReportUpdateDTO.completedBatchStatus),
                    TodoForCenterDTOConvertor.Convertor.dtoToModelConvertor(centerVisitReportUpdateDTO.todoForCenterdto),
                    TodoForApllDTOConvertor.Convertor.dtoToModelConvertor(centerVisitReportUpdateDTO.todoForAplldto),
                    CvrInfrastructureDtoConvertor.Convertor.dtoListToModelList(centerVisitReportUpdateDTO.infrastructureDTO!!),
                    centerVisitReportUpdateDTO.centerVisitReportStatus!!)



            return centerVisitReport



        }





    }
}