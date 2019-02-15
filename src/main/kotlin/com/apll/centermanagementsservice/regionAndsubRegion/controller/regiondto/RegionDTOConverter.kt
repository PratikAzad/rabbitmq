package com.apll.centermanagementsservice.regionAndsubRegion.controller.regiondto

import com.apll.centermanagementsservice.regionAndsubRegion.model.Region
import com.apll.centermanagementsservice.regionAndsubRegion.model.RegionId


class RegionDTOConverter {
    object Convertor {
        fun dtoConverter(region: Region): RegionDTO {
            var subRegionDTO= SubRegionDTOConverter.Convertor.modelToDtoList(region.subRegions)

            var dto = RegionDTO(
                    region.regionId.regionId,
                    region.regionName,
                    region.state,
                    subRegionDTO
            )
            return dto
        }

        fun modelClassConverter(dto: RegionDTO): Region {
           /* val regionId = RegionId()
            if (!dto.regionId.equals("")) {
                regionId.regionId = dto.regionId
            }*/

            var regionId:String?=dto.regionId
            if(regionId==null){ regionId="" }

            var subRegions= SubRegionDTOConverter.Convertor.dtoToModelList(dto.subRegionDtos!!)

            var region = Region(
                    RegionId(regionId),
                    dto.regionName!!,
                    dto.state!!,
                    subRegions)
            return region
        }

        fun dtoToModelConverterList(dtos:Set<RegionDTO>):List<Region>{
            var regionList=ArrayList<Region>()

            for(dto in dtos){
                regionList.plus(modelClassConverter(dto))
            }
            return regionList
        }


        fun modelToDtoConverterList(Region: MutableList<Region>):List<RegionDTO>{
            var regionList=ArrayList<RegionDTO>()

            for(region in Region){
                regionList.plus(dtoConverter(region))
            }
            return regionList
        }
    }
}


