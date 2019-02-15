package com.apll.centermanagementsservice.schedulemanagement.model.dto

import com.apll.centermanagementsservice.schedulemanagement.model.FrontEndSchedule
import com.apll.centermanagementsservice.schedulemanagement.model.FrontEndScheduleId

class FrontEndScheduleDTOConvertor {
    object convertor{


        fun modelTODtoConvertor(frontEndSchedule: FrontEndSchedule):FrontEndScheduleDTO{
            var frontEndScheduleDTO=FrontEndScheduleDTO(frontEndSchedule.frontEndScheduleId.frontEndScheduleId,
                    frontEndSchedule.yearmonth,
                    frontEndSchedule.frontEndId,
                    frontEndSchedule.frontEndName,
                    ScheduleDTOConvertor.Convertor.modellistToDTOList(frontEndSchedule.schedules),
                    frontEndSchedule.feScheduleStatus,
                    frontEndSchedule.reasonForUpdate,
                    frontEndSchedule.messageForReject,
                    frontEndSchedule.rejectedDate,
                    frontEndSchedule.headAdminId)

            return frontEndScheduleDTO
        }

        fun dtoTOModelConvertor(frontEndScheduleDTO: FrontEndScheduleDTO):FrontEndSchedule{

            var id:String?=frontEndScheduleDTO.frontEndScheduleId
            if(id==null){ id="" }

            var frontEndSchedule=FrontEndSchedule(
                    FrontEndScheduleId(id),
                    frontEndScheduleDTO.frontEndName,
                    frontEndScheduleDTO.yearMonth!!,
                    frontEndScheduleDTO.frontEndId!!,
                    ScheduleDTOConvertor.Convertor.dtoListToModelList(frontEndScheduleDTO.schedules!!),
                    frontEndScheduleDTO.feScheduleStatus!!,
                    frontEndScheduleDTO.reasonForUpdate,
                    frontEndScheduleDTO.messageForReject,
                    frontEndScheduleDTO.rejectedDate,
                    frontEndScheduleDTO.headAdminId)
            return frontEndSchedule
        }

/*
        fun dtoTOModelUpdate(frontEndScheduleDTO: FrontEndScheduleDTO):FrontEndScheduleState{

            var frontEndSchedule=FrontEndScheduleState(
                    FrontEndScheduleId(frontEndScheduleDTO.frontEndScheduleId!!),
                    frontEndScheduleDTO.yearMonth,
                    frontEndScheduleDTO.frontEndId,
                    ScheduleDTOConvertor.Convertor.dtoListToModelListUpdate(frontEndScheduleDTO.schedules))

            return frontEndSchedule
        }*/

        fun modelListToDtoList(frontEndScheduleList:List<FrontEndSchedule>):List<FrontEndScheduleDTO>{
            var frontEndScheduleDtoList=ArrayList<FrontEndScheduleDTO>()
            for(frontEndSchedule in frontEndScheduleList){
                var frontEndScheduledto= modelTODtoConvertor(frontEndSchedule)
                frontEndScheduleDtoList.add(frontEndScheduledto)

            }
            return frontEndScheduleDtoList
        }

        fun dtoListToModelList(frontEndDtoList:List<FrontEndScheduleDTO>):List<FrontEndSchedule>{
            var frontEndScheduleList=ArrayList<FrontEndSchedule>()
            for(frontEndScheduleDto in frontEndDtoList){
                var frontEndSchedule= dtoTOModelConvertor(frontEndScheduleDto)
                frontEndScheduleList.add(frontEndSchedule)
            }
            return frontEndScheduleList
        }

    }


}