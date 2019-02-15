/*
package com.apll.centermanagementsservice.visitingrequest.model.visitrequestdto

import com.apll.centermanagementsservice.center.model.CenterId
import com.apll.centermanagementsservice.visitingrequest.model.RequestId
import com.apll.centermanagementsservice.visitingrequest.model.VisitingRequest
import java.time.LocalDate

class ResolvedDTOConverter {
    object Convertor {
        fun modelToDTOConvertor(visitRequest: VisitingRequest): ResolvedDTO {


            var dto = ResolvedDTO(
                   visitRequest.resolvedDate,
                    visitRequest.resolved,
                    visitRequest.howToResolved

            )
            return dto
        }

        fun dtoToModelConvertor(dto: ResolvedDTO): VisitingRequest {
            var resolverId = RequestId()

            if (!dto.requestId.equals("")) {
            resolverId.requestId = dto.requestId
            }


            var visitRequest = VisitingRequest(resolverId,

                    dto.resolvedDate,
                    dto.resolved,
                    dto.howToResolved

                    )

            return visitRequest
        }
    }
}*/
