package com.apll.centermanagementsservice.schedulemanagement.dto

import com.apll.centermanagementsservice.config.CenterManagementsServiceApplication
import com.apll.centermanagementsservice.schedulemanagement.model.TypeOfRequest
import com.apll.centermanagementsservice.schedulemanagement.model.dto.ScheduleDTO
import com.apll.centermanagementsservice.schedulemanagement.model.dto.ScheduleDTOConvertor
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate
import javax.validation.Validation.buildDefaultValidatorFactory
import javax.validation.ValidatorFactory
import javax.validation.ConstraintViolation
import javax.validation.Validation


class ScheduleDTOTest {


    @Test
    fun scheduleValidationTestPass(){

        var scheduleDTO=ScheduleDTO("111",
                LocalDate.of(2018,12,1),
                "123",
                "note1",
                TypeOfRequest.BUSSINESS_DEVELOPEMENT_ACTIVITY,
                false,
                null)
        val factory = Validation.buildDefaultValidatorFactory()
        val validator = factory.getValidator()
        val violations = validator.validate(scheduleDTO)
        Assert.assertEquals(0,violations.size)

        /*val s=ScheduleDTOConvertor.Convertor.dtoToModelUpdate(scheduleDTO)
        Assert.assertFalse(s.isScheduleCompleted)*/
    }

    @Test
    fun scheduleValidationTestFail(){

        var scheduleDTO=ScheduleDTO("111",
                null,
                "123",
                null,
                null,
                false,
                null)
        val factory = Validation.buildDefaultValidatorFactory()
        val validator = factory.getValidator()
        val violations = validator.validate(scheduleDTO)
        Assert.assertEquals(4,violations.size)
    }

    @Test
    fun scheduleValidationTest(){

        var scheduleDTO=ScheduleDTO("111",
                null,
                "123",
                "note",
                null,
                false,
                null)
        val factory = Validation.buildDefaultValidatorFactory()
        val validator = factory.getValidator()
        val violations = validator.validate(scheduleDTO)
        Assert.assertEquals(2,violations.size)
    }




}