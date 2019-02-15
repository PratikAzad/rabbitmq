/*
package com.apll.centermanagementsservice.centrevisreport

import com.apll.centermanagementsservice.center.model.Center
import com.apll.centermanagementsservice.center.model.CenterId
import com.apll.centermanagementsservice.centrevisreport.model.CompletedBatchStatus
import com.apll.centermanagementsservice.centrevisreport.model.CompletedBatchStatusId
import com.apll.centermanagementsservice.centrevisreport.model.dto.CompletedBatchStatusDTOConvertor
import com.apll.centermanagementsservice.centrevisreport.service.CompletedBatchStatusService
import com.apll.centermanagementsservice.config.CenterManagementsServiceApplication
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate

@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(CenterManagementsServiceApplication::class))
class CompletedBatchStatusTestIT {
    @Autowired
    lateinit var service:CompletedBatchStatusService
    @Test
    fun insertCompletedBatchStatusTestCase(){
        var status=CompletedBatchStatus(CompletedBatchStatusId(),
                                        CenterId(),
                                        "123",
                                         true,
                                            LocalDate.now(),
                                           1234,
                                           1000,
                                            "ok..")

        var dto=CompletedBatchStatusDTOConvertor.Convertor.modelToDTOConvertor(status)
        var resultdto=service.insertCompletedBatchStatus(dto)
        Assert.assertEquals(true,resultdto.isRight)


    }

@Test
    fun updateCompletedBatchStatusTestCase(){

        var resolverId=CompletedBatchStatusId()
        resolverId.completedBatchStatusId="7894"
        var cid=CenterId()
        cid.centerId="789"

        var status=CompletedBatchStatus(resolverId,
                cid,
                "000",
                true,
                LocalDate.now(),
                45,
                96,

                "fine.")

        var dto=CompletedBatchStatusDTOConvertor.Convertor.modelToDTOConvertor(status)
        var resultdto=service.updateCompletedBatchStatus(dto)
        Assert.assertEquals(true,resultdto.isRight)

    }

    @Test
    fun getCompletedBatchStatusByIdTestCase(){
        var resolverId="1fc040f8-6b85-441f-88e4-7d1aee365808"

        var  resultdto=service.getCompletedBatchStatusById(resolverId)
        println(resultdto)
        Assert.assertEquals(true,resultdto.isRight)

    }

    @Test
    fun getAllCompletedBatchStatusTestCase()
    {
        var resultStatusList=service.getAllCompletedBatchStatus()
        println(resultStatusList)
        Assert.assertEquals(true,resultStatusList.isRight)
    }
    @Test
    fun getCompletedBatchStatusByCenterIdTestCase(){
        var resolverId="9eb6c25a-6cb4-4038-8e86-9f6b374f7749"
        var resultbatchDto=service.getCompletedBatchStatusByCenterId(resolverId)
        println(resultbatchDto)
        Assert.assertEquals(true,resultbatchDto.isRight)
    }


}*/
