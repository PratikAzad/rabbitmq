package com.apll.centermanagementsservice.center.dto

import com.apll.centermanagementsservice.center.model.dto.FacultyStateDto
import org.junit.Assert
import org.junit.Test
import javax.validation.Validation

class FacultyStateDTOTest {

    @Test
    fun facultyStateValidationTestPass(){
        var facultyStateDTO=FacultyStateDto("1",
                "nameFa",
                "btech",
                "9999999999",
                "spcl",
                1.0f,
                "ship@gmail.com",
                "12",
                "dsf",
                "dfsef",
                "ff",
                "ts",
                "fs",
                "123456")

        val factory = Validation.buildDefaultValidatorFactory()
        val validator = factory.getValidator()
        val violations = validator.validate(facultyStateDTO)
        Assert.assertEquals(3, violations.size)
    }

    @Test
    fun facultyStateValidationTestFail(){
        var facultyStateDTO=FacultyStateDto("1",
                null,
                null,
                null,
                null,
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
        val violations = validator.validate(facultyStateDTO)
        Assert.assertEquals(25, violations.size)
    }


    @Test
    fun facultyStatePatternAndSizeTestFail(){
        var facultyStateDTO=FacultyStateDto("1",
                "nameFa",
                "btech",
                "123456789",
                "spcl",
                1.0f,
                "shipgmailcom",
                "12",
                "dsf",
                "dfsef",
                "ff",
                "ts",
                "fs",
                "12345")

        val factory = Validation.buildDefaultValidatorFactory()
        val validator = factory.getValidator()
        val violations = validator.validate(facultyStateDTO)
        Assert.assertEquals(7, violations.size)
    }
}