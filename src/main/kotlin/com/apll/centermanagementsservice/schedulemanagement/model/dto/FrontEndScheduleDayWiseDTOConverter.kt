package com.apll.centermanagementsservice.schedulemanagement.model.dto

import com.apll.centermanagementsservice.schedulemanagement.model.FrontEndSchedule
import com.apll.centermanagementsservice.schedulemanagement.model.Schedule
import java.time.LocalDate
import kotlin.collections.ArrayList


class FrontEndScheduleDayWiseDTOConverter {

    object converter {
        fun modelToDtoConvert(frontEndSchedule: FrontEndSchedule):FrontEndScheduleDayWiseDTO{

            var startDate = LocalDate.of(frontEndSchedule.yearmonth.year,
                    frontEndSchedule.yearmonth.month, 1)

            var endDate = LocalDate.of(frontEndSchedule.yearmonth.year,
                    frontEndSchedule.yearmonth.month,
                    frontEndSchedule.yearmonth.month.length(frontEndSchedule.yearmonth.isLeapYear))


            var dayWiseScheduleDTOList=ArrayList<DayWiseScheduleDTO>()

            while (startDate.isBefore(endDate) || startDate.equals(endDate)){

                var date=startDate
                var oneDaySchedule=ArrayList<Schedule>()

                frontEndSchedule.schedules.forEach{
                    if(it.scheduleDate!!.equals(date)){
                        oneDaySchedule.add(it)
                    }
                }

                var oneDayScheduleDto=ArrayList<ScheduleDTO>()
                if(oneDaySchedule.size>0) {
                    oneDayScheduleDto = ScheduleDTOConvertor.Convertor.modellistToDTOList(oneDaySchedule)
                }

                var daySchedule=DayWiseScheduleDTO(oneDayScheduleDto,date)
                dayWiseScheduleDTOList.add(daySchedule)
                startDate=startDate.plusDays(1)
            }



            var frontEndScheduleDayWiseDTO=FrontEndScheduleDayWiseDTO(
                    frontEndSchedule.frontEndScheduleId.frontEndScheduleId,
                    frontEndSchedule.yearmonth,
                    frontEndSchedule.frontEndId,
                    frontEndSchedule.frontEndName,
                    dayWiseScheduleDTOList,
                    frontEndSchedule.feScheduleStatus,
                    frontEndSchedule.employeeType,
                    frontEndSchedule.reasonForUpdate,
                    frontEndSchedule.messageForReject,
                    frontEndSchedule.rejectedDate,
                    frontEndSchedule.headAdminId
            )
            return frontEndScheduleDayWiseDTO
        }
    }
}
