package com.apll.centermanagementsservice.center.model.dto

import com.apll.centermanagementsservice.center.model.Center

class CenterConvertor {

    object  convertor{

        fun dtoToModel(centerUpdateDTO: CenterUpdateDTO):Center{

            var centerState=CenterStateDTOConverter.converter.dtoToModelConvertForUpdate(centerUpdateDTO)

            return Center(centerState)
        }

        fun modelToDto(center: Center):CenterStateDtoWithSR{
            return CenterStateDTOConverter.converter.modelToDtoConvertWithSR(center.centerState)
        }
    }
}