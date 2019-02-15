package com.apll.centermanagementsservice.centrevisitreport.model.dto

import com.apll.centermanagementsservice.centrevisitreport.model.DropOutDetails
import com.apll.centermanagementsservice.centrevisitreport.model.DropOutDetailsId

class DropOutDetailsDTOConvertor {

    object Convertor {

        fun modelToDTOConvertor(dropOutDetails: DropOutDetails?): DropOutDetailsDTO? {
            if(dropOutDetails!=null) {
                var detailsdto = DropOutDetailsDTO(dropOutDetails.dropOutDetailsId.dropOutDetailsId,
                        dropOutDetails.nameOfStudents,
                        dropOutDetails.id,
                        dropOutDetails.batchNo,
                        dropOutDetails.dropOutDate,
                        dropOutDetails.remarks
                )
                return detailsdto
            }
            return null
        }



        fun dtoToModelConvertor(dropOutDetailsDTO: DropOutDetailsDTO?): DropOutDetails? {
            if (dropOutDetailsDTO != null) {

                var id = DropOutDetailsId(dropOutDetailsDTO.dropOutDetailsId)


                var details = DropOutDetails(id,
                        dropOutDetailsDTO.nameOfStudents,
                        dropOutDetailsDTO.id,
                        dropOutDetailsDTO.batchNo,
                        dropOutDetailsDTO.dropOutDate,
                        dropOutDetailsDTO.remarks)


                return details
            }
            return null
        }

        fun dtoToModelConverterList(dtos:List<DropOutDetailsDTO>?):List<DropOutDetails>?{
            if(dtos!=null) {
                var dropOutDetailsList = ArrayList<DropOutDetails>()

                for (dto in dtos) {
                    var dropOutDetails = dtoToModelConvertor(dto)
                    if (dropOutDetails != null) {
                        dropOutDetailsList.add(dropOutDetails)
                    }
                }
                return dropOutDetailsList
            }
            return null
        }

        fun modelToDtoConverterList(dropOutDetails:List<DropOutDetails>?):List<DropOutDetailsDTO>?{
            if(dropOutDetails!=null) {
                var dropOutDetailsDtoList = ArrayList<DropOutDetailsDTO>()

                for (cbs in dropOutDetails) {
                    var cbsdto = modelToDTOConvertor(cbs)
                    if (cbsdto != null) {
                        dropOutDetailsDtoList.add(cbsdto)
                    }
                }
                return dropOutDetailsDtoList
            }
            return null
        }
    }

}