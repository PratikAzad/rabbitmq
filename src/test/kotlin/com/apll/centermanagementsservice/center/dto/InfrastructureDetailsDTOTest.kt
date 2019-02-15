package com.apll.centermanagementsservice.center.dto

import com.apll.centermanagementsservice.center.model.dto.InfrastructureDetailsDTO
import org.junit.Assert
import org.junit.Test
import javax.validation.Validation

class InfrastructureDetailsDTOTest {

    @Test
    fun infrastructureDetailsDTOValidationTestPass(){

        var infrastructureDetailsDTO=InfrastructureDetailsDTO("1",
                227f,
                20,
                222,
                12,
                321,
                22)

        val factory = Validation.buildDefaultValidatorFactory()
        val validator = factory.getValidator()
        val violations = validator.validate(infrastructureDetailsDTO)
        Assert.assertEquals(0, violations.size)
    }

    @Test
    fun infrastructureDetailsDTOValidationTestFail(){

        var infrastructureDetailsDTO=InfrastructureDetailsDTO("1",
                null,
                null,
                null,
                null,
                null,
                null)

        val factory = Validation.buildDefaultValidatorFactory()
        val validator = factory.getValidator()
        val violations = validator.validate(infrastructureDetailsDTO)
        Assert.assertEquals(6, violations.size)
    }

}