package com.apll.centermanagementsservice.centerMinimalRequirment.model.dto

import com.apll.centermanagementsservice.center.model.dto.CenterStateDtoWithSR
import com.apll.centermanagementsservice.centerMinimalRequirment.model.CenterMinimalReq
import com.apll.centermanagementsservice.centerMinimalRequirment.model.CenterMinimalReqId

class CenterMinimalReqDTOConvertor {

    object convertor{

        fun dtoToModelConvert(dto: CenterMinimalReqDTO):CenterMinimalReq{

            var centerMinimalReq=CenterMinimalReq(
                    CenterMinimalReqId(dto.centerMinimalReqId!!),
                    dto.coveredArea!!,
                    dto.classRooms!!,
                    dto.sittingCapacityPerClass!!,
                    dto.noOfMachines!!,
                    dto.sittingCapacityPerLabs!!,
                    dto.noOfLab!!)
            return centerMinimalReq
        }

        fun modelToDtoConvert(req: CenterMinimalReq):CenterMinimalReqDTO{
            var centerMinimalReqDTO=CenterMinimalReqDTO(
                    req.centerMinimalReqId.centerMinimalReqId,
                    req.coveredArea,
                    req.classRooms,
                    req.sittingCapacityPerClass,
                    req.noOfMachines,
                    req.sittingCapacityPerLabs,
                    req.noOfLab)
            return centerMinimalReqDTO
        }

        fun modelToDtoList(req: List<CenterMinimalReq>):List<CenterMinimalReqDTO>{
            var dtolist=ArrayList<CenterMinimalReqDTO>()

            for (onereq in req){
                var dto=CenterMinimalReqDTOConvertor.convertor.modelToDtoConvert(onereq)
                dtolist.add(dto)
            }
            return dtolist
        }
        }
    }
