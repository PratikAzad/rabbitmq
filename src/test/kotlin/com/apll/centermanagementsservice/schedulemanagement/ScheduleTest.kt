/*
package com.apll.centermanagementsservice.schedulemanagement

import com.apll.centermanagementsservice.config.CenterManagementsServiceApplication
import com.apll.centermanagementsservice.schedulemanagement.model.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate

@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(CenterManagementsServiceApplication::class))
class ScheduleTest {
    @Test
    fun updateScheduleTest() {
        val schedule1 = Schedule(ScheduleId("1"),
                LocalDate.of(2000, 8, 3),
                "222",
                "b",
                TypeOfRequest.BUSSINESS_DEVELOPEMENT_ACTIVITY,
                true,
                "333")
        val schedule2 = Schedule(ScheduleId("2"),
                LocalDate.of(2000, 8, 5),
                "223",
                "asdad",
                TypeOfRequest.BUSSINESS_DEVELOPEMENT_ACTIVITY,
                true,
                "876")
        val schedule3 = Schedule(ScheduleId("3"),
                LocalDate.of(2000, 8, 9),
                "223",
                "asdad",
                TypeOfRequest.BUSSINESS_DEVELOPEMENT_ACTIVITY,
                true,
                "876")
        val scheduleList = ArrayList<Schedule>()
        scheduleList.add(schedule1)
        scheduleList.add(schedule2)
        scheduleList.add(schedule3)
        var frontEndScheduleState = FrontEndSchedule("101", scheduleList,"")
        var schedule4 = Schedule(ScheduleId("1"),
                LocalDate.of(2000, 8, 4),
                "222",
                "bbbb",
                TypeOfRequest.EXISTING_CENTER_REQUEST,
                true,
                "333")
        var schedule5 = Schedule(ScheduleId("2"),
                LocalDate.of(2000, 8, 6),
                "222",
                "Aaaaa",
                TypeOfRequest.EXISTING_CENTER_REQUEST,
                true,
                "333")
        var schedule6 = Schedule(ScheduleId(""),
                LocalDate.of(2000, 8, 16),
                "222",
                "Aaaaa",
                TypeOfRequest.EXISTING_CENTER_REQUEST,
                true,
                "333")
        var scheDList = ArrayList<Schedule>()
        scheDList.add(schedule4)
        scheDList.add(schedule5)
        scheDList.add(schedule6)
        frontEndScheduleState.updateSchedule(scheDList,"","msg")
        Assert.assertEquals(schedule1.scheduleDate, LocalDate.of(2000, 8, 4))
        Assert.assertEquals(schedule2.scheduleDate, LocalDate.of(2000, 8, 6))
        Assert.assertEquals(schedule6.scheduleDate, LocalDate.of(2000, 8, 16))
    }
    @Test
    fun fEScheduleInsertTestFalse(){
        //Existing First Front-End-Schedule
        val schedule01 = Schedule(ScheduleId("1"),
                LocalDate.of(2000, 1, 1),
                "011",
                "EXISTING_CENTER",
                TypeOfRequest.EXISTING_CENTER,
                true,
                "101")
        val schedule02 = Schedule(ScheduleId("2"),
                LocalDate.of(2000, 1, 2),
                "223",
                "EXISTING_CENTER",
                TypeOfRequest.EXISTING_CENTER,
                true,
                "333")
        val schedule03 = Schedule(ScheduleId("3"),
                LocalDate.of(2000, 1, 3),
                "223",
                "BUSSINESS_DEVELOPEMENT_ACTIVITY",
                TypeOfRequest.BUSSINESS_DEVELOPEMENT_ACTIVITY,
                true,
                "102")
        val scheduleList01 = ArrayList<Schedule>()
        scheduleList01.add(schedule01)
        scheduleList01.add(schedule02)
        scheduleList01.add(schedule03)
        var frontEndScheduleState01 = FrontEndSchedule("101", scheduleList01,"11")
        //Existing Second Front-End-Schedule
        val schedule001 = Schedule(ScheduleId("10"),
                LocalDate.of(2000, 1, 1),
                "222",
                "EXISTING_CENTER_REQUEST",
                TypeOfRequest.EXISTING_CENTER_REQUEST,
                true,
                "222")
        val schedule002 = Schedule(ScheduleId("20"),
                LocalDate.of(2000, 1, 2),
                "445",
                "EXISTING_CENTER_REQUEST",
                TypeOfRequest.EXISTING_CENTER_REQUEST,
                true,
                "103")
        val schedule003 = Schedule(ScheduleId("30"),
                LocalDate.of(2000, 1, 3),
                "223",
                "BUSSINESS_DEVELOPEMENT_ACTIVITY",
                TypeOfRequest.BUSSINESS_DEVELOPEMENT_ACTIVITY,
                true,
                "104")
        val scheduleList02 = ArrayList<Schedule>()
        scheduleList02.add(schedule001)
        scheduleList02.add(schedule002)
        scheduleList02.add(schedule003)
        var frontEndScheduleState02 = FrontEndSchedule("102", scheduleList02,"22")
        var fESList=ArrayList<FrontEndSchedule>()
        fESList.add(frontEndScheduleState01)
        fESList.add(frontEndScheduleState02)
        var monthWiseSchedule=MonthWiseSchedule(LocalDate.of(2000,1,1),fESList)
        val schedule1 = Schedule(ScheduleId(""),
                LocalDate.of(2000, 1, 1),
                "201",
                "EXISTING_CENTER_REQUEST",
                TypeOfRequest.EXISTING_CENTER_REQUEST,
                true,
                "111")
        val schedule2 = Schedule(ScheduleId(""),
                LocalDate.of(2000, 1, 2),
                "202",
                "EXISTING_CENTER_REQUEST",
                TypeOfRequest.EXISTING_CENTER_REQUEST,
                true,
                "222")
        val schedule3 = Schedule(ScheduleId(""),
                LocalDate.of(2000, 1, 3),
                "203",
                "EXISTING_CENTER",
                TypeOfRequest.EXISTING_CENTER,
                true,
                "333")
        val schedule4 = Schedule(ScheduleId(""),
                LocalDate.of(2000, 1, 4),
                "204",
                "EXISTING_CENTER",
                TypeOfRequest.EXISTING_CENTER,
                true,
                "444")
        val scheduleList = ArrayList<Schedule>()
        scheduleList.add(schedule1)
        scheduleList.add(schedule2)
        scheduleList.add(schedule3)
        scheduleList.add(schedule4)
        var frontEndScheduleState = FrontEndSchedule("", scheduleList,"33")
        var result=monthWiseSchedule.scheduleValidation(frontEndScheduleState)
        println(result)
        Assert.assertEquals(true,result.isLeft)
    }
    @Test
    fun fEScheduleInsertTestTrue() {
        //Existing First Front-End-Schedule
        val schedule01 = Schedule(ScheduleId("1"),
                LocalDate.of(2000, 1, 1),
                "011",
                "EXISTING_CENTER",
                TypeOfRequest.EXISTING_CENTER,
                true,
                "101")
  var schedule2Id=result1.get().listOfDayWiseScheduleDTO.stream()
                  .filter{it.dayOfMonth==LocalDate.of(2000, 8, 5)}
                  .findAny().get().listOfScheduleDTO.get(0).requestId

        val schedule02 = Schedule(ScheduleId("2"),
                LocalDate.of(2000, 1, 2),
                "223",
                "EXISTING_CENTER",
                TypeOfRequest.EXISTING_CENTER,
                true,
                "303")
        val schedule03 = Schedule(ScheduleId("3"),
                LocalDate.of(2000, 1, 3),
                "223",
                "BUSSINESS_DEVELOPEMENT_ACTIVITY",
                TypeOfRequest.BUSSINESS_DEVELOPEMENT_ACTIVITY,
                true,
                "102")
        val scheduleList01 = ArrayList<Schedule>()
        scheduleList01.add(schedule01)
        scheduleList01.add(schedule02)
        scheduleList01.add(schedule03)
 var schedule3Id=result1.get().listOfDayWiseScheduleDTO.stream()
                 .filter{it.dayOfMonth==LocalDate.of(2000, 8, 9)}
                 .findAny().get().listOfScheduleDTO.get(0).requestId

        var frontEndScheduleState01 = FrontEndSchedule("101", scheduleList01, "11")
        //Existing Second Front-End-Schedule
        val schedule001 = Schedule(ScheduleId("10"),
                LocalDate.of(2000, 1, 1),
                "222",
                "EXISTING_CENTER_REQUEST",
                TypeOfRequest.EXISTING_CENTER_REQUEST,
                true,
                "202")
        val schedule002 = Schedule(ScheduleId("20"),
                LocalDate.of(2000, 1, 2),
                "445",
                "EXISTING_CENTER_REQUEST",
                TypeOfRequest.EXISTING_CENTER_REQUEST,
                true,
                "103")
        val schedule003 = Schedule(ScheduleId("30"),
                LocalDate.of(2000, 1, 3),
                "223",
                "BUSSINESS_DEVELOPEMENT_ACTIVITY",
                TypeOfRequest.BUSSINESS_DEVELOPEMENT_ACTIVITY,
                true,
                "104")
        val scheduleList02 = ArrayList<Schedule>()
        scheduleList02.add(schedule001)
        scheduleList02.add(schedule002)
        scheduleList02.add(schedule003)
        var frontEndScheduleState02 = FrontEndSchedule("102", scheduleList02, "22")
        var fESList = ArrayList<FrontEndSchedule>()
        fESList.add(frontEndScheduleState01)
        fESList.add(frontEndScheduleState02)
        var monthWiseSchedule = MonthWiseSchedule(LocalDate.of(2000,1,2), fESList)
        val schedule1 = Schedule(ScheduleId(""),
                LocalDate.of(2000, 1, 1),
                "201",
                "EXISTING_CENTER_REQUEST",
                TypeOfRequest.EXISTING_CENTER_REQUEST,
                true,
                "111")
        val schedule2 = Schedule(ScheduleId(""),
                LocalDate.of(2000, 1, 2),
                "202",
                "EXISTING_CENTER_REQUEST",
                TypeOfRequest.EXISTING_CENTER_REQUEST,
                true,
                "222")
        val schedule3 = Schedule(ScheduleId(""),
                LocalDate.of(2000, 1, 3),
                "203",
                "EXISTING_CENTER",
                TypeOfRequest.EXISTING_CENTER,
                true,
                "333")
        val schedule4 = Schedule(ScheduleId(""),
                LocalDate.of(2000, 1, 4),
                "204",
                "EXISTING_CENTER",
                TypeOfRequest.EXISTING_CENTER,
                true,
                "303")
        val scheduleList = ArrayList<Schedule>()
        scheduleList.add(schedule1)
        scheduleList.add(schedule2)
        scheduleList.add(schedule3)
        scheduleList.add(schedule4)
        var frontEndScheduleState = FrontEndSchedule("", scheduleList, "33")
        var result = monthWiseSchedule.scheduleValidation(frontEndScheduleState)
        println(result)
        Assert.assertEquals(true, result.isLeft)
    }

    @Test
    fun checkUpdateScheduleForCustomType(){
        val schedule1 = Schedule(ScheduleId(""),
                LocalDate.of(2018, 11, 2),
                "null",
                "kk",
                TypeOfRequest.BUSSINESS_DEVELOPEMENT_ACTIVITY,
                false,
                "null")

        val schedule2= Schedule(ScheduleId("f20e2fd6-9ef5-408d-a30e-c670e986f2da"),
                LocalDate.of(2018, 11, 1),
                "null",
                "hf",
                TypeOfRequest.CUSTOM_REQUEST,
                false,
                "null")

        val scheduleList = ArrayList<Schedule>()
        scheduleList.add(schedule1)
        scheduleList.add(schedule2)

        var frontEndScheduleState01 = FrontEndSchedule("d8703c12-149d-484e-9325-691ce0972168", scheduleList, "764")

         var result=frontEndScheduleState01.updateSchedule(scheduleList,"me","msg1")
        Assert.assertEquals(true, result.isRight)
    }
}
*/
