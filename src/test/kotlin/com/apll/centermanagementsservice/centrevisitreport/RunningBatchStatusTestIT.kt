/*
package com.apll.centermanagementsservice.centrevisreport

import com.apll.centermanagementsservice.center.model.CenterId
import com.apll.centermanagementsservice.centrevisreport.model.RunningBatchStatus
import com.apll.centermanagementsservice.centrevisreport.model.RunningBatchStatusId
import com.apll.centermanagementsservice.centrevisreport.model.dto.RunningBatchStatusDTOConvertor
import com.apll.centermanagementsservice.centrevisreport.service.RunningBatchStatusService
import com.apll.centermanagementsservice.config.CenterManagementsServiceApplication
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(CenterManagementsServiceApplication::class))
class RunningBatchStatusTestIT {
    @Autowired
    lateinit var service:RunningBatchStatusService
@Test
fun insertRunningBatchStatusTestCase(){
        var status=RunningBatchStatus(RunningBatchStatusId(),
                "101",
                CenterId(),
                12,
                LocalDateTime.now(),
                LocalDate.now(),
                "kiran",
                "12",
                "12",
                123,12)
        var result=RunningBatchStatusDTOConvertor.Convertor.modelToDtoConvertor(status)
        var resultdto=service.insertRunningBatchStatus(result)
        Assert.assertEquals(true,resultdto.isRight)


    }
    @Test
    fun updateRunningBatchStatusTestCase(){
        var resolverId=RunningBatchStatusId()
        resolverId.runningBatchStatusId="101"

        var cid=CenterId()
        cid.centerId="1111"

        var status=RunningBatchStatus(resolverId,
                "1",
                cid,
                123,
                LocalDateTime.now(),
                LocalDate.now(),
                "babu",
                "16",
                "16",
                1456,13)
        var result=RunningBatchStatusDTOConvertor.Convertor.modelToDtoConvertor(status)
        var resultdto=service.insertRunningBatchStatus(result)
        Assert.assertEquals(true,resultdto.isRight)

    }
    @Test
    fun getRunningBatchStatusByIdTestCase(){



        var resolverId="e77e6cbe-c219-4684-8bab-bb53224e4a5d"
        var result=service.getRunningBatchStatusById(resolverId)
        println(result)
        Assert.assertEquals(true,result.isRight)

    }
    @Test
    fun getAllRunningBatchStatus(){
        var resultList=service.getAllRunningBatchStatus()
        println(resultList)
        Assert.assertEquals(true,resultList.isRight)
    }

@Test
    fun getRunningBatchStatusByCenterId(){

       var  resolverId="ef5672bd-fd83-4a05-b469-369a3ed63bbc"

        var resultbatchDtoList=service.getRunningBatchStatusByCenterId(resolverId)

        println(resultbatchDtoList)
        Assert.assertEquals(true,resultbatchDtoList.isRight)
    }


}*/
