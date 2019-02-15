/*
package com.apll.centermanagementsservice.report;


import com.apll.centermanagementsservice.config.CenterManagementsServiceApplication;
import com.apll.centermanagementsservice.centrevisreport.model.StudentFeedbackReport;
import com.apll.centermanagementsservice.centrevisreport.model.StudentFeedbackReportId;
import com.apll.centermanagementsservice.report.service.IStudentFeedbackReportService;
import io.vavr.control.Either;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CenterManagementsServiceApplication.class)
public class StudentFeedbackReportTestIT {

    @Autowired
    private IStudentFeedbackReportService service;

    @Test
    public void saveStudentFeedbackReportTest(){

        StudentFeedbackReport report=new StudentFeedbackReport(
                new StudentFeedbackReportId(),
                "shipra",
                "111",
                "1",
                "good");

        Either<Exception,StudentFeedbackReport>  either=service.saveStudentFeedbackReport(report);
        System.out.println(either);
        Assert.assertEquals(true,either.isRight());
    }


    @Test
    public void updateStudentFeedbackReportTest(){

        StudentFeedbackReportId resolverId=new StudentFeedbackReportId();
        resolverId.setStudentFeedbackReportId("055b6a0c-d0a4-4204-82ec-bba44b1cef5f");

        StudentFeedbackReport report=new StudentFeedbackReport(
                 resolverId,
                "shipra aatram",
                "333",
                "1",
                "very good");

        Either<Exception,StudentFeedbackReport>  either=service.updateStudentFeedbackReport(report);
        System.out.println(either);
        Assert.assertEquals(true,either.isRight());

    }


    @Test
    public void searchByIdTest(){
        StudentFeedbackReportId resolverId=new StudentFeedbackReportId();
        resolverId.setStudentFeedbackReportId("055b6a0c-d0a4-4204-82ec-bba44b1cef5f");

        Either<Exception,StudentFeedbackReport>  either=service.searchStudentFeedbackReportById(resolverId);
        System.out.println(either);
        Assert.assertEquals(true,either.isRight());

    }

    @Test
    public void getAll(){
        Either<Exception, List<StudentFeedbackReport>> either=service.getAllStudentFeedbackReportById();
        System.out.println(either);
        Assert.assertEquals(true,either.isRight());

    }
}

*/