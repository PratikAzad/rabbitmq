package com.apll.centermanagementsservice.visitingrequest.dto


import com.apll.centermanagementsservice.centrevisitreport.model.ResolvedBy
import com.apll.centermanagementsservice.visitingrequest.model.RequestType
import com.apll.centermanagementsservice.visitingrequest.controller.visitrequestdto.VisitingRequestDTO
import org.junit.Assert
import org.junit.Test
import java.time.LocalDate
import javax.validation.Validation

class VisitingRequestDTOTest {

    @Test
    fun visitingRequestValidationTestPass() {

        var visitingRequestDTO = VisitingRequestDTO(
                "1",
                RequestType.HEAD_ADMIN,
                "kyu btaye",
                "1",
                "c1",
                "1",
                "r1",
                LocalDate.of(2018, 12, 9),
                LocalDate.of(2018, 9, 12),
                true,
                "htrgfg",
                ResolvedBy.HEAD_ADMIN,
                "1",
                "ghasg"
        )

        val factory = Validation.buildDefaultValidatorFactory()
        val validator = factory.getValidator()
        val violations = validator.validate(visitingRequestDTO)
        Assert.assertEquals(0, violations.size)

    }

        @Test
        fun visitingRequestValidationTestFail() {

            var visitingRequestDTO = VisitingRequestDTO(
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    LocalDate.of(2018, 9, 12),
                    null,
                    "fgggd",
                    ResolvedBy.HEAD_ADMIN,
                    "2",
                    "gdhdhh"
            )

            val factory = Validation.buildDefaultValidatorFactory()
            val validator = factory.getValidator()
            val violations = validator.validate(visitingRequestDTO)
            Assert.assertEquals(8, violations.size)

    }
}