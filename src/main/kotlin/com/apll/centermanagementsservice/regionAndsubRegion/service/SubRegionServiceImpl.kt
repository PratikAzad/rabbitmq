package com.apll.centermanagementsservice.regionAndsubRegion.service


import com.apll.centermanagementsservice.regionAndsubRegion.model.SubRegionId
import com.apll.centermanagementsservice.regionAndsubRegion.controller.regiondto.SubRegionDTO
import com.apll.centermanagementsservice.regionAndsubRegion.controller.regiondto.SubRegionDTOConverter
import com.apll.centermanagementsservice.regionAndsubRegion.repository.SubRegionRepository
import io.vavr.control.Either
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class SubRegionServiceImpl:SubRegionService {

    @Autowired
    lateinit var repository: SubRegionRepository

    override fun insertSubRegion(subRegionDTO: SubRegionDTO): Either<Exception, SubRegionDTO> {
        var subRegion= SubRegionDTOConverter.Convertor.dtoToModelConvertor(subRegionDTO)
        var resultSubRegion=repository.save(subRegion)
        var resultdto= SubRegionDTOConverter.Convertor.modelToDTOConvertor(resultSubRegion)
        if(resultdto!=null){
            return Either.right(resultdto)
        }
        return Either.left(Exception("Sub-Region is not Inserted...Fail operation"))
    }


    override fun getSubRegionById(subRegionId: String): Either<Exception, SubRegionDTO> {
        var id= SubRegionId()
        id.subRegionId=subRegionId
        var subRegion=repository.findById(id)
        if(!subRegion.isPresent) {
            return Either.left(Exception("Sub-Region is  not found, fail operation.."))
        }
        var resultdto = SubRegionDTOConverter.Convertor.modelToDTOConvertor(subRegion.get())

        return Either.right(resultdto)
    }


    override fun updateSubRegion(subRegionDTO: SubRegionDTO): Either<Exception, SubRegionDTO> {
        var subRegion= SubRegionDTOConverter.Convertor.dtoToModelConvertor(subRegionDTO)
        var eitherSubRegion=repository.saveAndFlush(subRegion)
        var resultdto= SubRegionDTOConverter.Convertor.modelToDTOConvertor(eitherSubRegion)
        if(resultdto!=null&&!resultdto.equals(subRegion)){
            return Either.right(resultdto)
        }
        return Either.left(Exception("Sub-Region not Updated...fail.."))
    }

    override fun getAllSubRegion(): Either<Exception, List<SubRegionDTO>> {
        var subRegionList = repository.findAll()
        if (subRegionList.size < 1) {
            return Either.left(Exception(" Empty list return ... fail .."))
        }
        var subRegionToList = ArrayList<SubRegionDTO>()
        for (subRegion in subRegionList) {
            var dto = SubRegionDTOConverter.Convertor.modelToDTOConvertor(subRegion)
            subRegionToList.add(dto)
        }
        return Either.right(subRegionToList)
    }
}