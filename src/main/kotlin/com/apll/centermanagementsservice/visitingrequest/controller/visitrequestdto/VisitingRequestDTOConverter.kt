package com.apll.centermanagementsservice.visitingrequest.controller.visitrequestdto

import com.apll.centermanagementsservice.center.model.CenterId
import com.apll.centermanagementsservice.visitingrequest.model.RequestId
import com.apll.centermanagementsservice.visitingrequest.model.VisitingRequest


class VisitingRequestDTOConverter {
    object Convertor {
        fun modelToDTOConvertor(visitRequest: VisitingRequest): VisitingRequestDTO {


            var dto = VisitingRequestDTO(
                    visitRequest.requestId.requestId,
                    visitRequest.requestBy,
                    visitRequest.reason,
                    visitRequest.centerId.centerId,
                    visitRequest.centerName,
                    visitRequest.requesterId,
                    visitRequest.requesterName,
                    visitRequest.requestDate,
                    visitRequest.resolvedDate,
                    visitRequest.resolved,
                    visitRequest.howToResolved,
                    visitRequest.resolvedBy,
                    visitRequest.resolverId,
                    visitRequest.reasonForNotResolved
            )
            return dto
        }

        fun dtoToModelConvertor(dto: VisitingRequestDTO): VisitingRequest {

            var id:String?=dto.requestId
            if(id==null){ id="" }

            var visitRequest = VisitingRequest(RequestId(id),
                    dto.requestBy!!,
                    dto.reason!!,
                    CenterId(dto.centerId!!),
                    dto.centerName!!,
                    dto.requesterId!!,
                    dto.requesterName!!,
                    dto.requestDate!!,//LocalDate.now(),
                    dto.resolvedDate,
                    dto.resolved,
                    dto.howToResolved,
                    dto.resolvedBy,
                    dto.resolverId,
                    dto.reasonForNotResolved

                    )

            return visitRequest
        }

        fun modelToDtoList(visitRequests: List<VisitingRequest>):List<VisitingRequestDTO>{

            var visRequests=ArrayList<VisitingRequestDTO>()
            visitRequests.forEach{
                var visReq= modelToDTOConvertor(it)
                visRequests.add(visReq)
            }
            return visRequests
        }
    }
}