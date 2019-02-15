package com.apll.centermanagementsservice.centerMinimalRequirment.service

import com.apll.centermanagementsservice.centerMinimalRequirment.model.dto.CenterMinimalReqDTO
import io.vavr.control.Either

interface ICenterMinimalReqService {

    fun saveCenMinimalReq(dto: CenterMinimalReqDTO):Either<Exception,CenterMinimalReqDTO>

    fun editCenMinimalReq(dto: CenterMinimalReqDTO):Either<Exception,CenterMinimalReqDTO>

    fun getMinimalReq():Either<Exception,CenterMinimalReqDTO>
    fun findById(centerMinimalReqId:String):Either<Exception,CenterMinimalReqDTO>
}