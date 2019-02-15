package com.apll.centermanagementsservice.center.model.dto

import com.apll.centermanagementsservice.center.model.InfrastructureDetails
import com.apll.centermanagementsservice.center.model.InfrastructureDetailsId
import com.fasterxml.jackson.databind.util.BeanUtil


class InfrastructureDetailsDTOConvertor {

    object convertor{

        fun modelToDtoConvert(details: InfrastructureDetails):InfrastructureDetailsDTO{

            var dto=InfrastructureDetailsDTO(
                    details.infrastructureDetailsId!!.infrastructureDetailsId,
                    details.coveredArea,
                    details.classRooms,
                    details.sittingCapacityPerClass,
                    details.noOfMachines,
                    details.sittingCapacityPerLabs,
                    details.noOfLab
                    )
            return dto
        }


        fun dtoToModelConvert(dto:InfrastructureDetailsDTO):InfrastructureDetails{
            var id:String?=dto.infrastructureDetailsId
            if(id==null){ id="" }

            var details=InfrastructureDetails(
                    InfrastructureDetailsId(id),
                    dto.coveredArea!!,
                    dto.classRooms!!,
                    dto.sittingCapacityPerClass!!,
                    dto.noOfMachines!!,
                    dto.sittingCapacityPerLabs!!,
                    dto.noOfLab!!
                    )
            return details
        }




    }


}