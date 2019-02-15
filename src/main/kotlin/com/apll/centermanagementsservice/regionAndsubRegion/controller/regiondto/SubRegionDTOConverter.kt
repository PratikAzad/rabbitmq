package com.apll.centermanagementsservice.regionAndsubRegion.controller.regiondto


import com.apll.centermanagementsservice.regionAndsubRegion.model.SubRegion
import com.apll.centermanagementsservice.regionAndsubRegion.model.SubRegionId

class SubRegionDTOConverter {
    object Convertor {
        fun modelToDTOConvertor(subRegion: SubRegion): SubRegionDTO {

            var dto = SubRegionDTO(
                    subRegion.subRegionId.subRegionId,
                    subRegion.regionId,
                    subRegion.subRegionName,
                    subRegion.regionName,
                    subRegion.city
            )
            return dto
        }

        fun dtoToModelConvertor(dto: SubRegionDTO): SubRegion {

            var id:String?=dto.subRegionId
            if(id==null){ id="" }

            /*var id = SubRegionId()
            if (!dto.subRegionId.equals("")) {
                id.subRegionId = dto.subRegionId
            }*/

            /*var regionId = RegionId()
            regionId.regionId = dto.regionId*/

            var subRegion = SubRegion(
                    SubRegionId(id),
                    dto.regionName!!,
                    dto.subRegionName!!,
                    dto.city!!
               )
            return subRegion
        }

/*
        fun dtoToModelList(dtoList: List<SubRegionDTO>): List<SubRegion> {
            var subRegionList = ArrayList<SubRegion>()
*/
        fun dtoToModelList(dtoList:Set<SubRegionDTO>):Set<SubRegion>{
            var subRegionList=HashSet<SubRegion>()

            for (dto in dtoList) {
                subRegionList.add(dtoToModelConvertor(dto))
            }
            return subRegionList
        }


        fun modelToDtoList(srList:Set<SubRegion>):Set<SubRegionDTO>{
            var subRegionList=HashSet<SubRegionDTO>()

            for(subRegion in srList){
                subRegionList.add(modelToDTOConvertor(subRegion))
            }
            return subRegionList
        }
    }
}
