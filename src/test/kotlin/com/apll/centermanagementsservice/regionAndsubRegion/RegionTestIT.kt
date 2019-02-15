package com.apll.centermanagementsservice.regionAndsubRegion


import com.apll.centermanagementsservice.config.CenterManagementsServiceApplication
import com.apll.centermanagementsservice.regionAndsubRegion.model.Region
import com.apll.centermanagementsservice.regionAndsubRegion.model.RegionId
import com.apll.centermanagementsservice.regionAndsubRegion.model.SubRegion
import com.apll.centermanagementsservice.regionAndsubRegion.model.SubRegionId
import com.apll.centermanagementsservice.regionAndsubRegion.controller.regiondto.RegionDTO
import com.apll.centermanagementsservice.regionAndsubRegion.controller.regiondto.RegionDTOConverter
import com.apll.centermanagementsservice.regionAndsubRegion.controller.regiondto.SubRegionDTO
import com.apll.centermanagementsservice.regionAndsubRegion.service.RegionService
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(CenterManagementsServiceApplication::class))
class RegionTestIT {
    @Autowired
    lateinit var service: RegionService

    @Test
    fun insertRegion(){
        var subRegionsSet=HashSet<SubRegionDTO>()
        var subRegion= SubRegionDTO(
                "12345",
                "",
                "abcde",
                "abc",
                "rytfhg")
        subRegionsSet.add(subRegion)

        var region= RegionDTO("",
                "hgg",
                "Hyderabad",
                subRegionsSet
        )

        var result=service.insertRegion(region)

        Assert.assertTrue("pass"+result,true)

    }


    @Test
    fun getRegionByIdTestCase(){
        var regionId="1"
        var resultdto=service.getRegionById(regionId)
        println(resultdto)
        Assert.assertEquals(true,resultdto.isRight)
    }


    @Test
    fun updateRegion(){

        var id= RegionId()
        id.regionId="538135eb-e207-4e76-a515-019dba99eabf"


        var subRegions=HashSet<SubRegionDTO>()
        var subRegion= SubRegionDTO("9876",
                "",
                "hsgsbb",
                "up",
                "huj"
        )
        subRegions.add(subRegion)

        var region= RegionDTO("",
                "UP",
                "ap",
                subRegions)


        var result=service.updateRegion(region)

        Assert.assertEquals(true,result.isRight)

    }




    @Test
    fun getAllRegion(){
        var resultRegionlist=service.getAllRegion()
        println(resultRegionlist)
        Assert.assertEquals(true,resultRegionlist.isRight)

    }

}
