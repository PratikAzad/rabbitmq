package com.apll.centermanagementsservice.schedulemanagement.dto

import com.apll.centermanagementsservice.schedulemanagement.model.FEScheduleStatus
import com.apll.centermanagementsservice.schedulemanagement.model.TypeOfRequest
import com.apll.centermanagementsservice.schedulemanagement.model.dto.FrontEndScheduleDTO
import com.apll.centermanagementsservice.schedulemanagement.model.dto.ScheduleDTO
import org.junit.Assert
import org.junit.Test
import java.time.LocalDate
import javax.validation.Validation

class FrontEndScheduleDTOTest {

    @Test
    fun frontEndScheduleValidationTestPass() {


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


        var frontEndScheduleDto = FrontEndScheduleDTO("1",
                LocalDate.of(2000, 12, 11),
                "11",
                "fName",
                schedules,
                FEScheduleStatus.DEFAULT,
                null,
                null,
                null,
                null)

        val factory = Validation.buildDefaultValidatorFactory()
        val validator = factory.getValidator()
        val violations = validator.validate(frontEndScheduleDto)
        Assert.assertEquals(0, violations.size)
    }

    @Test
    fun frontEndScheduleTestValidationFail() {


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


        var frontEndScheduleDto = FrontEndScheduleDTO("1",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null)

        val factory = Validation.buildDefaultValidatorFactory()
        val validator = factory.getValidator()
        val violations = validator.validate(frontEndScheduleDto)
        Assert.assertEquals(5, violations.size)
    }

    @Test
    fun frontEndScheduleValidationTest() {


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


        var frontEndScheduleDto = FrontEndScheduleDTO("1",
                LocalDate.of(2000, 12, 11),
                "11",
                null,
                schedules,
                null,
                null,
                null,
                null,
                null)

        val factory = Validation.buildDefaultValidatorFactory()
        val validator = factory.getValidator()
        val violations = validator.validate(frontEndScheduleDto)
        Assert.assertEquals(1, violations.size)
    }

}