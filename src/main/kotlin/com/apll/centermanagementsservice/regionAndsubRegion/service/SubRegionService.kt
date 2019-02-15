package com.apll.centermanagementsservice.regionAndsubRegion.service


import com.apll.centermanagementsservice.regionAndsubRegion.controller.regiondto.SubRegionDTO
import io.vavr.control.Either


interface SubRegionService {

    fun insertSubRegion(subRegionDTO: SubRegionDTO): Either<Exception, SubRegionDTO>
    fun getSubRegionById(subRegionId: String):Either<Exception, SubRegionDTO>
    fun updateSubRegion(subRegionDTO: SubRegionDTO): Either<Exception, SubRegionDTO>
    fun getAllSubRegion():Either<Exception,List<SubRegionDTO>>
}