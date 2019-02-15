package com.apll.centermanagementsservice.visitingrequest

import com.apll.centermanagementsservice.center.model.CenterId
import com.apll.centermanagementsservice.centrevisitreport.model.ResolvedBy
import com.apll.centermanagementsservice.config.CenterManagementsServiceApplication
import com.apll.centermanagementsservice.restTemplet.EmployeeService
import com.apll.centermanagementsservice.visitingrequest.model.RequestId
import com.apll.centermanagementsservice.visitingrequest.model.RequestType
import com.apll.centermanagementsservice.visitingrequest.model.VisitingRequest
import com.apll.centermanagementsservice.visitingrequest.controller.visitrequestdto.VisitingRequestDTOConverter
import com.apll.centermanagementsservice.visitingrequest.repository.VisitingRequestRepository
import com.apll.centermanagementsservice.visitingrequest.service.VisitingRequestService
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate



@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(CenterManagementsServiceApplication::class))
class VisitingRequestTestIT {

    @Autowired
    lateinit var service: VisitingRequestService

    @Autowired
    lateinit var repository: VisitingRequestRepository

    @Test
    fun insertVisitingRequest() {
        var visitingRequest = VisitingRequest(RequestId(),
                RequestType.HEAD_ADMIN,
                "NNNN",
                CenterId(),
                "Apll CRRR",
                "111",
                "requester111",
                LocalDate.of(2018, 8, 9),
                LocalDate.of(2018, 7, 12),
                true,
                "kyu btaye",
                ResolvedBy.HEAD_ADMIN,
                "222",
                "jdcdjcdbcdxbn")

        var dto = VisitingRequestDTOConverter.Convertor.modelToDTOConvertor(visitingRequest)
        var result = service.saveVisitingRequest(dto)
        Assert.assertEquals(true, result.isRight)
    }

    @Test
    fun getVisitingRequestByIdTestCase() {

        var id = "61e7169f-e2f7-42a0-9917-7d8a8f2c7ba8"
        var resultdto = service.getVisitingRequestById(id)
        println(resultdto)
        Assert.assertEquals(true, resultdto.isRight)
    }

    @Test
    fun updateVisitngRequestTestCase() {
        var id = RequestId()
        id.requestId = "61e7169f-e2f7-42a0-9917-7d8a8f2c7ba8"

        var cid = CenterId()
        cid.centerId = ""

        var visitingRequest = VisitingRequest(id,
                RequestType.BACK_END,
                "10",
                cid, "Apll Center2",
                "3",
                "Cente2",
                LocalDate.now(),
                LocalDate.now(),
                false,
                "hdgdgddg",
                ResolvedBy.BACK_END,
                "13",
                "csdjchsdcvbsdc")

        var dto = VisitingRequestDTOConverter.Convertor.modelToDTOConvertor(visitingRequest)
        var result = service.updateVisitingRequest(dto)
        Assert.assertEquals(true, result.isRight)
    }

    @Test
    fun getAllVisitingRequestTestCase() {
        var resultdtolist = service.getAllVisitingRequest()
        println(resultdtolist)
        Assert.assertEquals(true, resultdtolist.isRight)
    }

    @Test
    fun getVisitingByRequestBy() {
        //var req=TypeOfRequest.HEAD_ADMIN
        var resultvrs = service.getVisitingRequestByRequest(RequestType.HEAD_ADMIN)
        println(resultvrs)
        Assert.assertEquals(true, resultvrs.isRight)

    }


    @Test
    fun getByRegionId() {
        var result = service.getVisitingRequestByRegionId("538135eb-e207-4e76-a515-019dba99eabf")
        println(result)
        Assert.assertEquals(true, result.isRight)
    }


    @Test
    fun getByVisitngRequestByCenterId() {

        var cid = CenterId()
        cid.centerId = "101"
        var result = repository.findByCenterId(cid)
        println(result)
        Assert.assertEquals(true, result != null)
    }



    @Test
    fun getResolvedVisitingRequest() {
        var result = service.getResolvedVisitingRequest("3")
        println(result)
        Assert.assertEquals(true, result.isRight)
    }



    @Test
     fun getByEmployeeId(){
        var result=service.getByEmployeeId("cc377068-384f-4c10-b834-a35cb235aa0c")
        println(result)
        Assert.assertEquals(true,result.isRight)
    }


    @Test
    fun getByUnResolvedEmployeeId(){
        var result=service.getNotScheduledRequestByEmployeeId("cc377068-384f-4c10-b834-a35cb235aa0c")
        println(result)
        Assert.assertEquals(true,result.isRight)
    }


    @Test
    fun getUnresolved(){
        var result=service.getUnresolved()
        println(result)
        Assert.assertEquals(true,result.isRight)
    }


    /*@Test
    fun getByEmployeeId() {
        var result = empService.getEmployee("cc377068-384f-4c10-b834-a35cb235aa0c")
        println(result)

        //Collections.sort(result.get(), { o1, o2 -> o1.requestDate.compareTo(o2.requestDate) })

        println(result)
        Assert.assertEquals(true, result.isRight)
    }*/

/*@Test
    fun getLastTwoMonthsNotVisitedCenterRequests(){
        var date=LocalDate.now()
        var result=service.getLastTwoMonthsNotVisitedCenters(date)
        println(result)
        Assert.assertEquals(true,result.isRight)
    }*/


    /*
@Test
    fun changeStatusToVisitingRequestTest() {
        var resolverId = ""
        var status = false
        var result = service.changeStatusToVisitingRequest(status, resolverId)
        println(result)
        Assert.assertEquals(true, result.isRight)
    }
*/


/*
@Test
    fun getAllCVRByMonth(){

var yearMonth=LocalDate.now()
        var result=
                service.getAllCVRForMonth(yearMonth,"2")

        var date="21-11-2018"
        var result=service.getAllCVRForMonth(date,"5")
        println(result)
        //Assert.assertEquals(true,result.isRight)
    }
*/


    /*@Test
    fun getVisitingRequestByRequesterId() {
        var requesterId=String
        var resrequester =
                service.getVisitingRequestByRequesterId("2")
var controller=VisitingRequestController()
        var resrequester =controller.getVisitingRequestByRequesterId("2")

        println(resrequester)
        Assert.assertTrue(true)

    }*/

    /* @Test
    fun getByResolvedTest() {
        var resultdtolist = service.getByResolved()
        println(resultdtolist)
        Assert.assertEquals(true, resultdtolist.isRight)
    }
*/


}




