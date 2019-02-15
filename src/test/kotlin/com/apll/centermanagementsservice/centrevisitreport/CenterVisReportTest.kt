/*
package com.apll.centermanagementsservice.centrevisitreport

import com.apll.centermanagementsservice.centrevisitreport.service.CenterVisitReportService
import com.apll.centermanagementsservice.config.CenterManagementsServiceApplication
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(CenterManagementsServiceApplication::class))
class CenterVisReportTest {

    @Autowired
    lateinit var centerVisitReportService: CenterVisitReportService

    @Test


    fun insertCVR(){
        val result = centerVisitReportService.getMinimalReq()
        val re = centerVisitReportService.insertCenterVisitReport(result.get()[0])
        println(re)
        var res=centerVisitReportService.insertCenterVisitReport(re.get())
        println(res)
    }


}*/
