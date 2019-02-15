/*

package com.apll.centermanagementsservice.regionAndsubRegion


import com.apll.centermanagementsservice.center.model.CenterId
import com.apll.centermanagementsservice.center.model.CenterState
import com.apll.centermanagementsservice.config.CenterManagementsServiceApplication
import com.apll.centermanagementsservice.regionAndsubRegion.model.RegionId
import com.apll.centermanagementsservice.regionAndsubRegion.model.SubRegion
import com.apll.centermanagementsservice.regionAndsubRegion.model.SubRegionId
import com.apll.centermanagementsservice.regionAndsubRegion.model.dto.SubRegionDTOConverter
import com.apll.centermanagementsservice.regionAndsubRegion.service.SubRegionService
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner



@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(CenterManagementsServiceApplication::class))
class SubRegionTestIT {
    @Autowired
    lateinit var service: SubRegionService

    @Test
    fun insertSubRegion(){
        var subRegion= SubRegion(SubRegionId(),
                "Synycs",
                "apll",
                "Hyderbad")
        var resultdto= SubRegionDTOConverter.Convertor.modelToDTOConvertor(subRegion)
        var result=service.insertSubRegion(resultdto)

        Assert.assertEquals(true,result.isRight)

    }


    @Test
    fun getSubRegionByIdTestCase(){
        var resolverId="a6787f0a-0922-43fc-a142-69af6a773ae8"
        var resultdto=service.getSubRegionById(resolverId)
        println(resultdto)
        Assert.assertEquals(true,resultdto.isRight)
    }


    @Test
    fun updateSubRegion(){
        var resolverId=SubRegionId()
        resolverId.subRegionId="a6787f0a-0922-43fc-a142-69af6a773ae8"

        var subRegion=SubRegion(SubRegionId(),
                "bbbb",
                "GSFDHDH",
                "USSH"
                )
        var resultdto=SubRegionDTOConverter.Convertor.modelToDTOConvertor(subRegion)
        var result=service.updateSubRegion(resultdto)

        Assert.assertEquals(true,result.isRight)

    }


    @Test
    fun getAllSubRegion(){
        var resultSubRegionlist=service.getAllSubRegion()
        println(resultSubRegionlist)
        Assert.assertEquals(true,resultSubRegionlist.isRight)

    }


}




*/
