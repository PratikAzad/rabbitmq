package com.apll.centermanagementsservice.schedulemanagement.model.dto

import com.apll.centermanagementsservice.schedulemanagement.model.Schedule
import com.apll.centermanagementsservice.schedulemanagement.model.ScheduleId

class ScheduleDTOConvertor {
    object Convertor {
        fun modelToDtoConvertor(schedule: Schedule): ScheduleDTO {
            var scheduleDto = ScheduleDTO(schedule.scheduleId.scheduleId,
                    schedule.scheduleDate!!,
                    schedule.regionId,
                    schedule.note,
                    schedule.scheduleOnWhichPurpose!!,
                    schedule.isScheduleCompleted,
                    schedule.requestId
            )

            return scheduleDto
        }

        fun dtoToModelConvertor(scheduledto: ScheduleDTO): Schedule {

            var schedule = Schedule(
                    scheduledto.regionId,
                    scheduledto.scheduleDate!!,
                    scheduledto.note!!,
                    scheduledto.scheduleOnWhichPurpose!!,
                    scheduledto.visitingRequestId)
            return schedule
        }

        fun dtoToModelUpdate(scheduledto: ScheduleDTO): Schedule {

           /* var id=ScheduleId()
            if(scheduledto.requestId!=null){
                 id=ScheduleId(scheduledto.requestId!!)
            }
            else{
                id=ScheduleId("")
            }*/

            var id:String?=scheduledto.scheduleId
            if(id==null){ id="" }

            var schedule = Schedule(
                    ScheduleId(id),
                    scheduledto.scheduleDate!!,
                    scheduledto.regionId,
                    scheduledto.note!!,
                    scheduledto.scheduleOnWhichPurpose!!,
                    scheduledto.isScheduleCompleted!!,
                    scheduledto.visitingRequestId)
            return schedule
        }


        fun modellistToDTOList(scheduleList:List<Schedule>):ArrayList<ScheduleDTO>{

            var scheduleDtoList=ArrayList<ScheduleDTO>()
            for (schedule in scheduleList){
               var scheduledto= modelToDtoConvertor(schedule)
                scheduleDtoList.add(scheduledto)

            }
            return scheduleDtoList
        }



        fun dtoListToModelList(scheduleListDto:List<ScheduleDTO>):ArrayList<Schedule>{
            var scheduleList=ArrayList<Schedule>()
            for(scheduledto in scheduleListDto){
                var schedule= dtoToModelConvertor(scheduledto)
                scheduleList.add(schedule)
            }
            return scheduleList
        }


        fun dtoListToModelListUpdate(scheduleListDto:List<ScheduleDTO>):ArrayList<Schedule>{
            var scheduleList=ArrayList<Schedule>()
            for(scheduledto in scheduleListDto){
                var schedule= dtoToModelUpdate(scheduledto)
                scheduleList.add(schedule)
            }
            return scheduleList
        }

    }

}