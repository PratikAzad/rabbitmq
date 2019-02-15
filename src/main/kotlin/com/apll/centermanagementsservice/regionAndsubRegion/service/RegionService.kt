package com.apll.centermanagementsservice.regionAndsubRegion.service


import com.apll.centermanagementsservice.regionAndsubRegion.controller.regiondto.RegionDTO
import io.vavr.control.Either


interface RegionService {
    fun insertRegion(regionDTO: RegionDTO): Either<Exception, RegionDTO>
    fun getRegionById(regionId: String):Either<Exception, RegionDTO>
    fun updateRegion(regionDTO: RegionDTO): Either<Exception, RegionDTO>
    fun getAllRegion():Either<Exception,List<RegionDTO>>
    fun getByState(state:String):Either<Exception, List<RegionDTO>>

}