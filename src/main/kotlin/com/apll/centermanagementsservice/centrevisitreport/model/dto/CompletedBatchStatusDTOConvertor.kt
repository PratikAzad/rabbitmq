package com.apll.centermanagementsservice.centrevisitreport.model.dto

import com.apll.centermanagementsservice.centrevisitreport.model.CompletedBatchStatus
import com.apll.centermanagementsservice.centrevisitreport.model.CompletedBatchStatusId

class CompletedBatchStatusDTOConvertor {


    object Convertor {

        fun modelToDTOConvertor(completedBatchStatus: CompletedBatchStatus?): CompletedBatchStatusDTO? {
            if(completedBatchStatus!=null) {
                var statusdto = CompletedBatchStatusDTO(completedBatchStatus.completedBatchStatusId.completedBatchStatusId,
                        completedBatchStatus.batchNo,
                        completedBatchStatus.examConducted,
                        completedBatchStatus.conductedOn,
                        completedBatchStatus.totalNoOfStudents,
                        completedBatchStatus.noOfStudentsAppeared,
                        completedBatchStatus.remarks)

                return statusdto
            }
            return null
        }
        fun dtoToModelConvertor(completedBatchStatusDTO: CompletedBatchStatusDTO?):CompletedBatchStatus?{
            if(completedBatchStatusDTO!=null) {

                var id = CompletedBatchStatusId(completedBatchStatusId = completedBatchStatusDTO.completedBatchStatusId)

                var status = CompletedBatchStatus(id,
                        completedBatchStatusDTO.batchNo,
                        completedBatchStatusDTO.examConductedOn,
                        completedBatchStatusDTO.conductedOn,
                        completedBatchStatusDTO.totalNoOfStudents,
                        completedBatchStatusDTO.noOfStudentsAppeared,
                        completedBatchStatusDTO.remarks)

                return status
            }
            return null

        }

        fun dtoToModelConverterList(dtos:List<CompletedBatchStatusDTO>?):List<CompletedBatchStatus>?{
            if(dtos!=null) {
                var completedBatchStatusList = ArrayList<CompletedBatchStatus>()

                for (dto in dtos) {
                    var completedBatchStatus = dtoToModelConvertor(dto)
                    if (completedBatchStatus != null) {

                        completedBatchStatusList.add(completedBatchStatus)
                    }
                }
                return completedBatchStatusList
            }
            return null
        }

        fun modelToDtoConverterList(completedBatchStatus:List<CompletedBatchStatus>?):List<CompletedBatchStatusDTO>?{
            if(completedBatchStatus!=null) {
                var completedBatchStatusDtoList = ArrayList<CompletedBatchStatusDTO>()

                for (cbs in completedBatchStatus) {
                    var cbsDto = modelToDTOConvertor(cbs)
                    if (cbsDto != null) {
                        completedBatchStatusDtoList.add(cbsDto)
                    }


                }
                return completedBatchStatusDtoList
            }
            return null
        }


    }



}