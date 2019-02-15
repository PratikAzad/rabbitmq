/*

package com.apll.centermanagementsservice.centerFeedbackForm


import com.apll.centermanagementsservice.centrevisreport.model.CenterFeedbackForm
import com.apll.centermanagementsservice.centrevisreport.model.CenterFeedbackFormId
import com.apll.centermanagementsservice.centrevisreport.model.Grading
import com.apll.centermanagementsservice.centrevisreport.model.dto.CenterFeedbackFormDTOConverter
import com.apll.centermanagementsservice.centerFeedbackForm.service.CenterFeedbackFormService
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
class CenterFeedbackFormTestIT {

    @Autowired
    lateinit var serviceCenter: CenterFeedbackFormService

    @Test
    fun insertFeedbackFormTest() {
        var feedbackForm = CenterFeedbackForm(
                CenterFeedbackFormId(),
                "NareshIT",
                "synycs",
                "India",
                "8746535553",
                "SYNYCS",
                Grading.GOOD,
                Grading.EXCELENT,
                Grading.EXCELENT,
                Grading.EXCELENT,
                Grading.EXCELENT,
                Grading.EXCELENT,
                "............",
                LocalDate.of(2018, 10, 26)
        )

        var resultdto = CenterFeedbackFormDTOConverter.Convertor.modelToDTOConvertor(feedbackForm)
        var result = serviceCenter.insertFeedbackForm(resultdto)
        Assert.assertEquals(true, result.isRight)
    }


    @Test
    fun getFeedbackFormByIdTestCase() {
        var resolverId = "5abd0f1d-0c15-4351-8e2f-7dafac09d8d3"
        var resultdto = serviceCenter.getFeedbackFormById(resolverId)
        println(resultdto)
        Assert.assertEquals(true, resultdto.isRight)
    }


    @Test
    fun updateFeedbackForm() {
        var resolverId = CenterFeedbackFormId()
        resolverId.centerFeedbackFormId= "5abd0f1d-0c15-4351-8e2f-7dafac09d8d3"

        var feedbackForm = CenterFeedbackForm(resolverId,
                "bbbb",
                "Human",
                "ameerpet",
                "8787960543",
                "aaaa",
                Grading.GOOD,
                Grading.GOOD,
                Grading.EXCELENT,
                Grading.EXCELENT,
                Grading.GOOD,
                Grading.FAIR,
                "==================",
                LocalDate.of(2018, 10, 29))

        var resultdto = CenterFeedbackFormDTOConverter.Convertor.modelToDTOConvertor(feedbackForm)
        var result = serviceCenter.updateFeedbackForm(resultdto)

        Assert.assertEquals(true, result.isRight)
    }

    @Test
    fun getAllFeedbackForm() {
        var resultFeedbackFormlist = serviceCenter.getAllFeedbackForm()
        println(resultFeedbackFormlist)
        Assert.assertEquals(true, resultFeedbackFormlist.isRight)

    }
}*/
