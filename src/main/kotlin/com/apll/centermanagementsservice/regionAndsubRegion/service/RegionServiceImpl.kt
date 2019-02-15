package com.apll.centermanagementsservice.regionAndsubRegion.service

import com.apll.centermanagementsservice.regionAndsubRegion.model.Region
import com.apll.centermanagementsservice.regionAndsubRegion.model.RegionId
import com.apll.centermanagementsservice.regionAndsubRegion.controller.regiondto.RegionDTO
import com.apll.centermanagementsservice.regionAndsubRegion.controller.regiondto.RegionDTOConverter
import com.apll.centermanagementsservice.regionAndsubRegion.repository.RegionRepository
import io.vavr.control.Either
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class RegionServiceImpl:RegionService {

    @Autowired
    lateinit var repository: RegionRepository

    override fun insertRegion(regionDTO: RegionDTO): Either<Exception, RegionDTO> {
        val region = RegionDTOConverter.Convertor.modelClassConverter(regionDTO)
        region.idInitializer()
        var result = checkRegionName(region)
        if (result.isRight) {

            var resultsRegion = repository.save(region)
            var resultdto = RegionDTOConverter.Convertor.dtoConverter(resultsRegion)
            if (resultdto != null) {
                return Either.right(resultdto)
            }
        }
        return Either.left(result.left)
    }


    fun checkRegionName(region:Region): Either<Exception, Boolean> {
        var regions = repository.findAll()
        if (regions.size < 1) {
            return Either.right(true)
        }
        else {
            regions.filter{x->!x.regionId.regionId.equals(region.regionId.regionId) }.toList().
                    forEach {
                if (it.regionName.equals(region.regionName,true)) {
                    return Either.left(java.lang.Exception("Region Name Already Exist..."))
                }
            }
            return Either.right(true)
        }
    }


       override fun updateRegion(regionDTO: RegionDTO): Either<Exception, RegionDTO> {
           var newRegion = RegionDTOConverter.Convertor.modelClassConverter(regionDTO)
           var result=checkRegionName(newRegion)
           if(result.isRight) {

               var oldRegion = repository.findById(newRegion.regionId)
               if (!oldRegion.isPresent) {
                   return Either.left(Exception("Region not founded."))
               }
               oldRegion.get().regionUpdate(newRegion)
               var region = repository.saveAndFlush(oldRegion.get())
               var resultdto = RegionDTOConverter.Convertor.dtoConverter(region)
               if (resultdto != null) {
                   return Either.right(resultdto)
               }
           }
            return Either.left(result.left)
        }


    override fun getRegionById(regionId: String): Either<Exception, RegionDTO> {
        var id = RegionId()
        id.regionId = regionId
        var region = repository.findById(id)
        if (!region.isPresent) {
            return Either.left(Exception("Region is  not found, fail operation.."))
        }
        var resultdto = RegionDTOConverter.Convertor.dtoConverter(region.get())
        return Either.right(resultdto)
    }


      override  fun getAllRegion(): Either<Exception, List<RegionDTO>> {
          var regionList = repository.findAll()
          if (regionList.size < 1) {
              return Either.right(ArrayList())
          }
          var regiontolist = ArrayList<RegionDTO>()
          for (region in regionList) {
              var dto = RegionDTOConverter.Convertor.dtoConverter(region)
              regiontolist.add(dto)
          }
          return Either.right(regiontolist)
      }

    override
    fun getByState(state:String):Either<Exception, List<RegionDTO>>{
        var result=repository.findByState(state)
        if(result.size<1){
            return Either.left(Exception("Region Not Found ..."))
        }
        var regionDTOList=ArrayList<RegionDTO>()
        for (region in result){
            var dto = RegionDTOConverter.Convertor.dtoConverter(region)
            regionDTOList.add(dto)
        }
        return Either.right(regionDTOList)
    }
}

