package com.apll.centermanagementsservice.center.dto

import com.apll.centermanagementsservice.center.model.CenterStatus
import com.apll.centermanagementsservice.center.model.CenterType
import com.apll.centermanagementsservice.center.model.dto.CenterStateDTO
import com.apll.centermanagementsservice.center.model.dto.FacultyStateDto
import com.apll.centermanagementsservice.center.model.dto.InfrastructureDetailsDTO
import org.junit.Assert
import org.junit.Test
import java.time.LocalDate
import javax.validation.Validation

class CenterStateDTOTest {

    @Test
    fun centerStateValidationTestPass(){

        var facultyStateDTO= FacultyStateDto("1",
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

        var facultyList=HashSet<FacultyStateDto>()
        facultyList.add(facultyStateDTO)


        var infrastructureDetailsDTO= InfrastructureDetailsDTO("1",
                225f,
                20,
                222,
                12,
                321,
                 11)

        var centerStateDTO=CenterStateDTO("3",
                "center1",
                "shipra",
                "9999999999",
                "8888888888",
                "h21",
                "st2",
                "area1",
                "landmark1",
                "hyd",
                "123456",
                "ts",
                "cservice",
                "ship@gmail.com",
                "AAAPL1234C",
                LocalDate.of(2000,12,12),
                "29ABCDE1234F2Z5",
                CenterType.EXISTINGCENTER,
                CenterStatus.PENDING,
                facultyList,
                infrastructureDetailsDTO,
                "2341")

        val factory = Validation.buildDefaultValidatorFactory()
        val validator = factory.getValidator()
        val violations = validator.validate(centerStateDTO)
        Assert.assertEquals(4, violations.size)

    }

    @Test
    fun centerStateValidationTestFail(){

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

        var facultyList=HashSet<FacultyStateDto>()
        facultyList.add(facultyStateDTO)


        var infrastructureDetailsDTO=InfrastructureDetailsDTO("1",
                null,
                null,
                null,
                null,
                null,
                null)


        var centerStateDTO=CenterStateDTO("3",
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
        val violations = validator.validate(centerStateDTO)
        Assert.assertEquals(37, violations.size)
    }

    @Test
    fun centerStatePatternAndSizeTestFail(){

        var facultyStateDTO= FacultyStateDto("1",
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

        var facultyList=HashSet<FacultyStateDto>()
        facultyList.add(facultyStateDTO)


        var infrastructureDetailsDTO= InfrastructureDetailsDTO("1",
                228f,
                20,
                222,
                12,
                321,
                22)

        var centerStateDTO=CenterStateDTO("3",
                "center1",
                "shipra",
                "123456789",
                "212333343",
                "h21",
                "st2",
                "area1",
                "landmark1",
                "hyd",
                "12345",
                "ts",
                "cservice",
                "shipgmailcom",
                "aAPL1234",
                LocalDate.of(2000,12,12),
                "9ABCE12342Z",
                CenterType.EXISTINGCENTER,
                CenterStatus.PENDING,
                facultyList,
                infrastructureDetailsDTO,
                "2341")

        val factory = Validation.buildDefaultValidatorFactory()
        val validator = factory.getValidator()
        val violations = validator.validate(centerStateDTO)
        Assert.assertEquals(14, violations.size)

    }
}