/*
package com.apll.centermanagementsservice.businessdevelopmentactivity

import com.apll.centermanagementsservice.businessdevelopmentactivity.controller.bdadto.BDARequestDto
import com.apll.centermanagementsservice.businessdevelopmentactivity.controller.bdadto.BDAVisitReportDTOConvertor
import com.apll.centermanagementsservice.businessdevelopmentactivity.controller.bdadto.BDAVisitReportDto
import com.apll.centermanagementsservice.businessdevelopmentactivity.controller.bdadto.BDAVisitRequestDto
import com.apll.centermanagementsservice.businessdevelopmentactivity.model.*
import com.apll.centermanagementsservice.businessdevelopmentactivity.model.image.BDAVisitImage
import com.apll.centermanagementsservice.businessdevelopmentactivity.model.image.BDAVisitImageId
import com.apll.centermanagementsservice.businessdevelopmentactivity.controller.imagedto.BDAVisitImageDTO
import com.apll.centermanagementsservice.businessdevelopmentactivity.service.BDAVisitRequestService
import com.apll.centermanagementsservice.config.CenterManagementsServiceApplication
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate
import javax.validation.Validation
import javax.validation.ValidatorFactory

@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(CenterManagementsServiceApplication::class))
class BusinessDevelopmentActivityTestIT {
    @Autowired
    lateinit var service: BDAVisitRequestService


@Test
    fun insertBusinessDevelopmentActivityTest() {
        val bdaVisitDTO = BDARequestDto("1",
                BDAType.BUSINESS_ACTIVITY,
                "1",
                "hdgdf",
                "11",
                "jddnn",
                "telangana",
                "hyd",
                "hyd",
                "some others",

                LocalDate.of(2018, 11, 22),
                LocalDate.of(2018, 11, 16),
                BDARequestType.BDA_REQUEST_CENTER)
        var  factory : ValidatorFactory = Validation.buildDefaultValidatorFactory();

        var validator = factory.getValidator();
        var violations = validator.validate(bdaVisitDTO);
        Assert.assertEquals(0,violations.size)

        var result=service.insertBusinessDevelopmentActivity(bdaVisitDTO)

        Assert.assertEquals(true,result.isRight)

    }



    @Test
    fun insertBDAReportTestCase() {

        var imageList=ArrayList<BDAVisitImageDTO>()
        var img= BDAVisitImageDTO("22",
                "String")
        imageList.add(img)


        var bdaReports=ArrayList<BDAVisitReportDto>()
        var report= BDAVisitReportDto("11", "1", imageList, "some problem", LocalDate.now())
        bdaReports.add(report)

        var bussinessDevelopementActivity = BDAVisitRequestDto("2",
                BDAType.BUSINESS_ACTIVITY,
                "121",
                "sathya",
                "107",
                "hyd",
                "telangana",
                "hyderabad",
                "banjarahills",
                "Bussiness purpose..",

                LocalDate.of(2018, 9, 9),
                LocalDate.of(2018, 9, 12),
                BDARequestType.BDA_REQUEST_CENTER,
                bdaReports,
                false
        )
        var  factory : ValidatorFactory = Validation.buildDefaultValidatorFactory();

        var validator = factory.getValidator();
        var violations = validator.validate(report);
        Assert.assertEquals(0,violations.size)
        //var resultdto = BDAVisitRequestDtoConvertor.Convertor.modelToDtoConvertor(bussinessDevelopementActivity)
        var result = service.fileBdaReport(report)
        Assert.assertEquals(true, result.isRight)
    }

    @Test
    fun getBDAById() {
        var id = "e5cace77-e4de-4f8e-bb1c-b54655184053"
        var result = service.getBusinessDevelopmentActivityById(id)
        Assert.assertEquals(true, result.isRight)
    }

    @Test
    fun updateBDAVisitReuest() {
        var imageList=ArrayList<BDAVisitImage>()
        var img=BDAVisitImage(BDAVisitImageId(),
                "String")
        imageList.add(img)


        var bdaReports=ArrayList<BDAVisitReport>()
        var report=BDAVisitReport(BDAVisitReportId(),"1",imageList,"some problem", LocalDate.now())
        bdaReports.add(report)
        var id = BDAVisitRequestId()
        id.bdaVisitRequestId = "e5cace77-e4de-4f8e-bb1c-b54655184053"
        val bdaVisitDTO = BDARequestDto("1",
                BDAType.BUSINESS_ACTIVITY,
                "1",
                "hdgdf",
                "11",
                "jddnn",
                "telangana",
                "hyd",
                "hyd",
                "some others",

                LocalDate.of(2018, 11, 22),
                LocalDate.of(2018, 11, 16),
                BDARequestType.BDA_REQUEST_CENTER)


        var  factory : ValidatorFactory = Validation.buildDefaultValidatorFactory();

        var validator = factory.getValidator();
        var violations = validator.validate(bdaVisitDTO);
        Assert.assertEquals(0,violations.size)
      //  var resultdto = BDAVisitRequestDtoConvertor.Convertor.modelToDtoConvertor(bussinessDevelopementActivity)
        var result = service.updateBusinessDevelopmentActivity(bdaVisitDTO)
        Assert.assertEquals(true, result.isRight)
    }



    @Test
    fun getAllByFrontEndId() {
        var result = service.getAllByFrontEndId("1")
        Assert.assertTrue("test is passed $result", true)
    }

    @Test
    fun getBdaRequestsonMonthbyRegionId() {
        var regionId = "2"
        var date = "04-11-2018"

        var result = service.getOneMonthBdaVisitRequestsByregionIdAndDate(regionId, date)
        println(result)
        Assert.assertEquals(true, result.isRight)
    }

    @Test
    fun  getFiredBdaReports(){
        var imageList=ArrayList<BDAVisitImage>()
        var img=BDAVisitImage(BDAVisitImageId(),
                "String")
        imageList.add(img)


        var bdaReports=ArrayList<BDAVisitReport>()
        var report=BDAVisitReport(BDAVisitReportId(),"cc96572d-502b-4fc0-a300-95b8ebf17645",imageList,"some problem", LocalDate.of(2018,12,11))
        bdaReports.add(report)
        var dto= BDAVisitReportDTOConvertor.convertor.modelToDtoConvertor(report)
        var result=service.fileBdaReport(dto)

        println(result)
        Assert.assertEquals(true,result.isRight)


    }
}
*/
