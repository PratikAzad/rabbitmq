package com.apll.centermanagementsservice.businessdevelopmentactivity.controller.bdadto

import com.apll.centermanagementsservice.businessdevelopmentactivity.model.BDAVisitRequest
import com.apll.centermanagementsservice.businessdevelopmentactivity.model.BDAVisitRequestId

class BDAVisitRequestDtoConvertor {


    object Convertor
    {
        fun modelToDtoConvertor(bussinessDevelopementActivity: BDAVisitRequest): BDAVisitRequestDto {
            var bussinessDevelopmentActivitydto= BDAVisitRequestDto(
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
                    bussinessDevelopementActivity.bdaRequestType!!,
                    BDAVisitReportDTOConvertor.convertor.modelListToDtoList(bussinessDevelopementActivity.bdaReports!!),
                    bussinessDevelopementActivity.checkReportCompleted()
            )

            return bussinessDevelopmentActivitydto
        }

        fun dtoTOModelConvertor(bussinessDevelopementActivitydto: BDAVisitRequestDto):BDAVisitRequest{
            var id=BDAVisitRequestId()

            if(!bussinessDevelopementActivitydto.bdaVisitRequestId.equals("")){
                id.bdaVisitRequestId=bussinessDevelopementActivitydto.bdaVisitRequestId!!
            }

            var bussinessDevelopementActivity=BDAVisitRequest(
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
                    bussinessDevelopementActivitydto.bdaRequestType!!,
                    BDAVisitReportDTOConvertor.convertor.dtoListToModelList(bussinessDevelopementActivitydto.bdaReports!!)
                    )

            return bussinessDevelopementActivity
        }

        fun modelListToDtoList(bdalist:List<BDAVisitRequest>):List<BDAVisitRequestDto>{
            var bdadtoList=ArrayList<BDAVisitRequestDto>()
            for (bda in bdalist){
                var bdadto= modelToDtoConvertor(bda)
                bdadtoList.add(bdadto)
            }
            return bdadtoList
        }

        fun dtoListToBdaList(insertBdaDtoList:List<BDAVisitRequestDto>):List<BDAVisitRequest>{
            var bdaList=ArrayList<BDAVisitRequest>()
            for(bdadto in insertBdaDtoList){
                var bda= dtoTOModelConvertor(bdadto)
                bdaList.add(bda)

            }
            return bdaList
        }
    }
}