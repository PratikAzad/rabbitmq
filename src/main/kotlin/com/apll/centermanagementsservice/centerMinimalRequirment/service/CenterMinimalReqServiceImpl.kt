package com.apll.centermanagementsservice.centerMinimalRequirment.service

import com.apll.centermanagementsservice.centerMinimalRequirment.model.CenterMinimalReqId
import com.apll.centermanagementsservice.centerMinimalRequirment.model.dto.CenterMinimalReqDTO
import com.apll.centermanagementsservice.centerMinimalRequirment.model.dto.CenterMinimalReqDTOConvertor
import com.apll.centermanagementsservice.centerMinimalRequirment.repository.CenterMinimalReqRepo
import io.vavr.control.Either
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CenterMinimalReqServiceImpl:ICenterMinimalReqService {

    @Autowired
    lateinit var repo:CenterMinimalReqRepo

    override
    fun saveCenMinimalReq(dto: CenterMinimalReqDTO): Either<Exception, CenterMinimalReqDTO> {
         var olddto=getMinimalReq()
        var re=olddto.get()

         if (olddto.isRight && olddto.get()==null){
             var req=CenterMinimalReqDTOConvertor.convertor.dtoToModelConvert(dto)
             req.idInitializer()
             var reqResult=repo.save(req)
             var dtoresult=CenterMinimalReqDTOConvertor.convertor.modelToDtoConvert(reqResult)
             return Either.right(dtoresult)
         }
        return Either.left(Exception("Center Minimal Requirement can not create."))
    }

    override
    fun editCenMinimalReq(dto: CenterMinimalReqDTO): Either<Exception,CenterMinimalReqDTO> {
        var result=findById(dto.centerMinimalReqId!!)
        if(result.isRight) {
            var req = CenterMinimalReqDTOConvertor.convertor.dtoToModelConvert(dto)
            var reqresult = repo.save(req)
            if (reqresult != null) {
                var dtoresult = CenterMinimalReqDTOConvertor.convertor.modelToDtoConvert(reqresult)
                return Either.right(dtoresult)
            }
        }
        return Either.left(Exception("Center Minimal Requirement not found."))
    }

    override
    fun getMinimalReq():Either<Exception,CenterMinimalReqDTO>{
        var reqs=repo.findAll().stream().findAny()

        if (reqs.isPresent){
            var dtolist=CenterMinimalReqDTOConvertor.convertor.modelToDtoConvert(reqs.get())
            return Either.right(dtolist)
        }
        return Either.right(null)
    }

    override
    fun findById(centerMinimalReqId:String):Either<Exception,CenterMinimalReqDTO>{
        var id=CenterMinimalReqId()
        id.centerMinimalReqId=centerMinimalReqId
        var req=repo.findById(id)
        if (req.isPresent){
            var dto=CenterMinimalReqDTOConvertor.convertor.modelToDtoConvert(req.get())
            return Either.right(dto)
        }
        return Either.left(Exception("Center Minimal req not found."))
    }

}