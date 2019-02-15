/*
package com.apll.centermanagementsservice.businessdevelopmentactivity.model.dto

import com.apll.centermanagementsservice.businessdevelopmentactivity.model.BDAVisitRequest
import com.apll.centermanagementsservice.businessdevelopmentactivity.model.BDAVisitRequestId
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class BDAVisitDTOConverter {

    object converter {
        */
/*fun dtoToModelConverter(dto: BDAVisitDTO): List<BDAVisitRequest> {

            var numOfDays = ChronoUnit.DAYS.between(dto.fromBdaDate, dto.toBdaDate).toInt()

            var bdaList=ArrayList<BDAVisitRequest>()

            while (numOfDays >= 0) {
                var date = dto.toBdaDate.minusDays(numOfDays.toLong())
                var toDate:LocalDate

                var resolverId = BDAVisitRequestId()

                if (!dto.bdaVisitRequestId.equals("")) {
                    resolverId.bdaVisitRequestId = dto.bdaVisitRequestId
                }

                var bdaVisitRequest = BDAVisitRequest(
                        resolverId,
                        dto.activityType,
                        dto.frontEndId,
                        dto.frontEndName,
                        dto.centerId,
                        dto.centerName,
                        dto.description,
                        date
                )
                bdaList.add(bdaVisitRequest)
                numOfDays = numOfDays - 1
            }
            return bdaList
        }
   *//*

    }
}*/
