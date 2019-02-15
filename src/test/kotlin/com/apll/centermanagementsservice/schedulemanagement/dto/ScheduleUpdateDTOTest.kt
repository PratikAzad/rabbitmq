package com.apll.centermanagementsservice.schedulemanagement.dto

import com.apll.centermanagementsservice.schedulemanagement.model.EmployeeType
import com.apll.centermanagementsservice.schedulemanagement.model.TypeOfRequest
import com.apll.centermanagementsservice.schedulemanagement.model.dto.ScheduleDTO
import com.apll.centermanagementsservice.schedulemanagement.model.dto.ScheduleUpdateDTO
import org.junit.Assert
import org.junit.Test
import java.time.LocalDate
import javax.validation.Validation

class ScheduleUpdateDTOTest {

    @Test
    fun scheduleUpdateValidationTestPass(){

        var scheduleDTO1 = ScheduleDTO("111",
                LocalDate.of(2018, 12, 1),
                "123",
                "note1",
                TypeOfRequest.BUSSINESS_DEVELOPEMENT_ACTIVITY,
                false,
                "121")

        var scheduleDTO2 = ScheduleDTO("1221",
                LocalDate.of(2018, 11, 1),
                "23",
                "note2",
                TypeOfRequest.EXISTING_CENTER,
                false,
                "321")

        var schedules = ArrayList<ScheduleDTO>()
        schedules.add(scheduleDTO1)
        schedules.add(scheduleDTO2)


        var scheduleUpdateDTO=ScheduleUpdateDTO("11",
                "fe",
                schedules,
                EmployeeType.FRONT_END,
                "some reason")

        val factory = Validation.buildDefaultValidatorFactory()
        val validator = factory.getValidator()
        val violations = validator.validate(scheduleUpdateDTO)
        Assert.assertEquals(0, violations.size)
    }


    @Test
    fun scheduleUpdateValidationTestFail(){

        var scheduleDTO1 = ScheduleDTO("111",
                LocalDate.of(2018, 12, 1),
                "123",
                "note1",
                TypeOfRequest.BUSSINESS_DEVELOPEMENT_ACTIVITY,
                false,
                "121")

        var scheduleDTO2 = ScheduleDTO("1221",
                LocalDate.of(2018, 11, 1),
                "23",
                "note2",
                TypeOfRequest.EXISTING_CENTER,
                false,
                "321")

        var schedules = ArrayList<ScheduleDTO>()
        schedules.add(scheduleDTO1)
        schedules.add(scheduleDTO2)


        var scheduleUpdateDTO=ScheduleUpdateDTO(null,
                null,
                null,
                null,
                null)

        val factory = Validation.buildDefaultValidatorFactory()
        val validator = factory.getValidator()
        val violations = validator.validate(scheduleUpdateDTO)
        Assert.assertEquals(7, violations.size)
    }

    @Test
    fun scheduleUpdateValidationTest(){

        var scheduleDTO1 = ScheduleDTO("111",
                LocalDate.of(2018, 12, 1),
                "123",
                "note1",
                TypeOfRequest.BUSSINESS_DEVELOPEMENT_ACTIVITY,
                false,
                "121")

        var scheduleDTO2 = ScheduleDTO("1221",
                LocalDate.of(2018, 11, 1),
                "23",
                "note2",
                TypeOfRequest.EXISTING_CENTER,
                false,
                "321")

        var schedules = ArrayList<ScheduleDTO>()
        schedules.add(scheduleDTO1)
        schedules.add(scheduleDTO2)


        var scheduleUpdateDTO=ScheduleUpdateDTO("11",
                null,
                schedules,
                null,
                "some reason")

        val factory = Validation.buildDefaultValidatorFactory()
        val validator = factory.getValidator()
        val violations = validator.validate(scheduleUpdateDTO)
        Assert.assertEquals(3, violations.size)
    }
}