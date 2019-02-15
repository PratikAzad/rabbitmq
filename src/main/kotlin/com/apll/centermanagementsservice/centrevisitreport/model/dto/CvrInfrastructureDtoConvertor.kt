package com.apll.centermanagementsservice.centrevisitreport.model.dto

import com.apll.centermanagementsservice.centrevisitreport.model.CvrInfrastructure
import com.apll.centermanagementsservice.centrevisitreport.model.CvrInfrastructureId

class CvrInfrastructureDtoConvertor {

    object Convertor{
        fun modelToDtoConvertor(infrastructure: CvrInfrastructure):CvrInfrastructureDTO{
            var infrastructureDto=CvrInfrastructureDTO(infrastructure.cvrInfrastructureId!!.cvrInfrastructureId,
                    infrastructure.infrastructureName!!,
                    infrastructure.minimalValue!!
                    ,
                    infrastructure.minimalRequirementSatisfies!!,
                    infrastructure.currentValue
                    )
            return infrastructureDto

        }

        fun dtoToModelConvertor(infrastructureDto:CvrInfrastructureDTO):CvrInfrastructure{

            var id=CvrInfrastructureId(infrastructureDto.cvrInfrastructureId)

            var infrastructure=CvrInfrastructure(id,
                    infrastructureDto.infrastructureName,
                    infrastructureDto.minimalValue,
                    infrastructureDto.minimalRequirementSatisfies,
                    infrastructureDto.currentValue)

            return infrastructure
        }

        fun modelListToDtoList(infrastructureList: List<CvrInfrastructure>):List<CvrInfrastructureDTO>{
            var infrastructureDTOList=ArrayList<CvrInfrastructureDTO>()
            for(infrastructure in infrastructureList) {
                var dto = modelToDtoConvertor(infrastructure)
                infrastructureDTOList.add(dto)
            }
            return infrastructureDTOList
        }
        fun dtoListToModelList(infrastructureDtoList: List<CvrInfrastructureDTO>):List<CvrInfrastructure>{
            var infrastructureList=ArrayList<CvrInfrastructure>()
            for(infrastructureDto in infrastructureDtoList){
                var infrastructure= dtoToModelConvertor(infrastructureDto)
                infrastructureList.add(infrastructure)
            }
            return infrastructureList
        }



    }
}
