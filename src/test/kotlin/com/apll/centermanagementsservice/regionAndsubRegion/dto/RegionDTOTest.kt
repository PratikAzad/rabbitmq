package com.apll.centermanagementsservice.regionAndsubRegion.dto

import com.apll.centermanagementsservice.regionAndsubRegion.controller.regiondto.RegionDTO
import com.apll.centermanagementsservice.regionAndsubRegion.controller.regiondto.SubRegionDTO
import org.junit.Assert
import org.junit.Test
import javax.validation.Validation

class RegionDTOTest {

    @Test
    fun regionValidationTestPass(){

        var subRegionsSet=HashSet<SubRegionDTO>()
        var subRegion= SubRegionDTO(
                "09",
                "1",
                "zyefj",
                "allahabad",
                "gofth")
        subRegionsSet.add(subRegion)

        var regionDTO= RegionDTO("1",
                "allahabad",
                "foggg",
                subRegionsSet)

        val factory = Validation.buildDefaultValidatorFactory()
        val validator = factory.getValidator()
        val violations = validator.validate(regionDTO)
        Assert.assertEquals(0,violations.size)
    }

    @Test
    fun regionValidationTestFail(){

        var subRegionSet=HashSet<SubRegionDTO>()
        var subRegion= SubRegionDTO(
                null,
                null,
                null,
                null,
                null)

        subRegionSet.add(subRegion)

        var regionDTO= RegionDTO(null,
                null,
                null,
                subRegionSet)

        val factory = Validation.buildDefaultValidatorFactory()
        val validator = factory.getValidator()
        val violations = validator.validate(regionDTO)
        Assert.assertEquals(4,violations.size)

    }
}