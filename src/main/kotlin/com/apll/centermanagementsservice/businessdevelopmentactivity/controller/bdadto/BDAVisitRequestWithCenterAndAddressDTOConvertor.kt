
package com.apll.centermanagementsservice.businessdevelopmentactivity.controller.bdadto

import com.apll.centermanagementsservice.businessdevelopmentactivity.model.BDAVisitRequest
import com.apll.centermanagementsservice.businessdevelopmentactivity.model.BDAVisitRequestId

class BDAVisitRequestWithCenterAndAddressDTOConvertor {

    object Convertor
    {
        fun modelToDtoConvertor(bussinessDevelopementActivity:BDAVisitRequest): BDARequestDto {
            var bussinessDevelopmentActivitydto= BDARequestDto(
                    bussinessDevelopementActivity.bdaVisitRequestId!!.bdaVisitRequestId,
                    bussinessDevelopementActivity.activityType!!,
                    bussinessDevelopementActivity.frontEndId!!,
                    bussinessDevelopementActivity.frontEndName,
                    bussinessDevelopementActivity.centerId,
                    bussinessDevelopementActivity.centerName,
                    bussinessDevelopementActivity.area,
                    bussinessDevelopementActivity.city,
                    bussinessDevelopementActivity.placeName,
                    bussinessDevelopementActivity.description!!,
                    bussinessDevelopementActivity.startDate!!,
                    bussinessDevelopementActivity.endDate!!,
                    bussinessDevelopementActivity.bdaRequestType!!
            )

            return bussinessDevelopmentActivitydto
        }

        fun dtoTOModelConvertor(bussinessDevelopementActivitydto: BDARequestDto):BDAVisitRequest {
            var id= BDAVisitRequestId()

            if(!bussinessDevelopementActivitydto.bdaVisitRequestId.equals("")){
                id.bdaVisitRequestId=bussinessDevelopementActivitydto.bdaVisitRequestId!!
            }

            var bussinessDevelopementActivity= BDAVisitRequest(
                    id,
                    bussinessDevelopementActivitydto.activityType,
                    bussinessDevelopementActivitydto.frontEndId,
                    bussinessDevelopementActivitydto.frontEndName,
                    bussinessDevelopementActivitydto.centerId,
                    bussinessDevelopementActivitydto.centerName,
                    bussinessDevelopementActivitydto.area,
                    bussinessDevelopementActivitydto.city,
                    bussinessDevelopementActivitydto.placeName,
                    bussinessDevelopementActivitydto.description,
                    bussinessDevelopementActivitydto.startDate,
                    bussinessDevelopementActivitydto.endDate,
                    bussinessDevelopementActivitydto.bdaRequestType

            )

            return bussinessDevelopementActivity
        }

        fun modelListToDtoList(bdalist:List<BDAVisitRequest>):List<BDARequestDto>{
            var bdadtoList=ArrayList<BDARequestDto>()
            for (bda in bdalist){
                var bdadto= modelToDtoConvertor(bda)
                bdadtoList.add(bdadto)
            }
            return bdadtoList
        }

        fun dtoListToBdaList(bdaDtoList:List<BDARequestDto>):List<BDAVisitRequest>{
            var bdaList=ArrayList<BDAVisitRequest>()
            for(bdadto in bdaDtoList){
                var bda= dtoTOModelConvertor(bdadto)
                bdaList.add(bda)

            }
            return bdaList
        }



    }
}
