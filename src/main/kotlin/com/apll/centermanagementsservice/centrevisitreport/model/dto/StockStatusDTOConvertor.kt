package com.apll.centermanagementsservice.centrevisitreport.model.dto

import com.apll.centermanagementsservice.centrevisitreport.model.StockStatus
import com.apll.centermanagementsservice.centrevisitreport.model.StockStatusId

class StockStatusDTOConvertor {

    object convertor{

        fun dtoToModelConvert(stockStatusDTO: StockStatusDTO?): StockStatus? {
            if(stockStatusDTO!=null) {

                var stockStatusId = StockStatusId(stockStatusDTO.stockStatusId)

                var stockStatus = StockStatus(
                        stockStatusId,
                        stockStatusDTO.item,
                        stockStatusDTO.quantityInHand,
                        stockStatusDTO.quantityRequested,
                        stockStatusDTO.indentSentOnByMail,
                        stockStatusDTO.quantityIndented,
                        stockStatusDTO.indentTakenDuringVisit,
                        stockStatusDTO.feedback)
                return stockStatus
            }
            return null
        }

        fun modelToDtoConvert(stockStatus: StockStatus?): StockStatusDTO? {

            if (stockStatus != null) {


                var stockStatusDTO = StockStatusDTO(
                        stockStatus.stockStatusId.stockStatusId,
                        stockStatus.item,
                        stockStatus.quantityInHand,
                        stockStatus.quantityRequired,
                        stockStatus.indentSentOnByMail,
                        stockStatus.quantityIndented,
                        stockStatus.indentTakenDuringVisit,
                        stockStatus.feedback)
                return stockStatusDTO
            }
            return null
        }


        fun dtoToModelConverterList(dtos:List<StockStatusDTO>?):List<StockStatus>?{
            if(dtos!=null) {
                var StockStatusList = ArrayList<StockStatus>()

                for (dto in dtos) {
                    var stockStatus = dtoToModelConvert(dto)
                    if (stockStatus != null) {
                        StockStatusList.add(stockStatus)
                    }
                }
                return StockStatusList
            }
            return null
        }

        fun modelToDtoConverterList(stockStatus:List<StockStatus>?):List<StockStatusDTO>?{
            if(stockStatus!=null) {

                var StockStatusDtoList = ArrayList<StockStatusDTO>()

                for (ss in stockStatus) {
                    var ssDto = modelToDtoConvert(ss)
                    if (ssDto != null) {
                        StockStatusDtoList.add(ssDto)
                    }
                }
                return StockStatusDtoList
            }
            return null
        }
    }
}