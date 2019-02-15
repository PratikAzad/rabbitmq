package com.apll.centermanagementsservice.visitingrequest

import com.apll.centermanagementsservice.config.CenterManagementsServiceApplication
import com.apll.centermanagementsservice.visitingrequest.service.VisitingRequestServiceImpl
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(CenterManagementsServiceApplication::class))
class VisitingReqDashBoardTestIT {

    @Autowired
    lateinit var service: VisitingRequestServiceImpl


    @Test
    fun getVisReqAnalysis1TestCase(){
        var result=service.visReqAnalysis()
        println(result)
        Assert.assertEquals(true, result.isRight)
    }


    @Test
    fun getVisReqAnalysis2TestCase(){
        var result=service.visReqResolvedAnalysis()
        println(result)
        Assert.assertEquals(true, result.isRight)
    }

}