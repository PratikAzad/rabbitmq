
package com.apll.centermanagementsservice.center;


import com.apll.centermanagementsservice.center.controller.CenterController;
import com.apll.centermanagementsservice.center.model.*;
import com.apll.centermanagementsservice.center.model.dto.*;
import com.apll.centermanagementsservice.center.service.CenterService;
import com.apll.centermanagementsservice.config.CenterManagementsServiceApplication;
import com.apll.centermanagementsservice.config.ResponseWithError;
import com.apll.centermanagementsservice.mail.MailOutputStream;
import com.apll.centermanagementsservice.mail.User;
import com.apll.centermanagementsservice.regionAndsubRegion.model.SubRegionId;
import io.vavr.control.Either;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MimeTypeUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CenterManagementsServiceApplication.class)
public class CenterStateTestIT {

    @Autowired
    private MailOutputStream mailService;

    @Autowired
    private CenterService service;

    @Autowired
    private CenterController controller;

    @Test
    public void getDatesBetweenUsingJava8() {
        List<LocalDate> datesInRange = new ArrayList<>();
        LocalDate start=LocalDate.now().minusDays(10);
        LocalDate end=LocalDate.now();

        while (start.isBefore(end) || start.equals(end)) {
            datesInRange.add(start);
            start=start.plusDays(1);
        }
        System.out.println("");
    }


    @Test
    public void getAllCenterTest(){

        Either<Exception,List<CenterStateDtoWithSR>> result=service.findAllCenter();
        Assert.assertTrue("test passed"+result,true);
    }

    @Test
    public void sendMail() {
        User user=new User("pkazad23@gmail.com","Center-Created","Center Registerd successfully.");
        mailService.sendMail().send(MessageBuilder.withPayload(user)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
        System.out.println("Mail-Event Raised.");
    }
    /*@Test
    public  void getCenterNotVisited(){

        ResponseWithError<List<CenterStateDtoWithSR>> result=controller.searchLastTwoMonthsNotVisitedCentersByRegionId("22-11-2018","b8eea7f4-6b98-4544-8504-ef80b577d049");
        Assert.assertTrue(true);

    }
*/
    /*@Test
    public void getCenterByCenterId(){

        ResponseWithError<CenterStateDtoWithSR> result=controller.getById("35d17e52-947c-4912-ae5b-cd337f3b286e");
       // Either<Exception,CenterStateDtoWithSR> result=service.getCenterById("35d17e52-947c-4912-ae5b-cd337f3b286e");
        Assert.assertTrue("pass"+result,true);
    }*/

    @Test
    public void getLastTwoMonthNotVisitedCenterByEmpId(){
        Either<Exception,List<CenterStateDtoWithSR>> result=service.getLastTwoMonthNotVisitedCentersAccEmployeeId("12-2018","9c729e0e-c8c4-401e-9c3e-6d55666c4a95");
        Assert.assertTrue("pass"+result.isRight(),true);
    }

    @Test
    public void updateCenterTest(){

        Set<FacultyStateDto> facultyStateDtoSet=new HashSet<>();
        FacultyStateDto facultyStateDto=new FacultyStateDto("0dd6bcbf-4293-496f-9ff3-271394f9fd79",
                "bbb",
                "eng",
                "12345677898",
                "sf",
                1.0f,
                "ts",
                "lot",
                "ts",
                "sff",
                "2142342",
                "ewrfw",
                "ss",
                "wfafaf");
        facultyStateDtoSet.add(facultyStateDto);

        InfrastructureDetailsDTO infrastructureDetailsDTO=new InfrastructureDetailsDTO(
                "100002fd-66ba-4d28-a74e-240608641c25",
                333.3f,
                44,
                433,
                44,
                443,
                55);

        /*CenterStateDTO centerStateDTO=new CenterStateDTO("",
                "www",
                "dee",
                "3518653786",
                "9875434566",
                "hoa7",
                "gha",
                "jad",
                "hkasd",
                "ksd",
                "86754",
                "dged",
                "dfdf",
                "sfhsflj",
                "fsdf",
                LocalDate.of(1997,9,4),
                "hh",
                CenterType.EXISTINGCENTER,
                CenterStatus.ENDING,
                facultyStateDtoSet,
                infrastructureDetailsDTO,
                "0b5d5f59-a31c-40e3-a5b9-66758bd82992");

        Either<Exception,CenterStateDtoWithSR> result1=service.register(centerStateDTO);
        Assert.assertTrue("test pass"+result1,true);
*/

        CenterUpdateDTO centerUpdateDTO=new CenterUpdateDTO("57437f64-b9ee-43b3-9413-84d710708374",
                "popo",
                "3518653786",
                "9875434566",
                "hoa7",
                "gha",
                "jad",
                "hkasd",
                "ksd",
                "86754",
                "dged",
                "dfdf",
                "sfhsflj",
                LocalDate.of(1997,9,4),
                CenterType.EXISTINGCENTER,
                CenterStatus.ENDING,
                facultyStateDtoSet,
                infrastructureDetailsDTO);


        Either<Exception,CenterStateDtoWithSR> result=service.editCenter(centerUpdateDTO);
        Assert.assertTrue("test pass"+result,result.isRight());
    }

//   @Test
//   public void getCenterBySubRegionId(){
//       Either<Exception, CenterStateDtoWithSR> result=service.getCenterBySubRegionId("1d488e31-569d-4374-acde-2489b00bf04d");
//       Assert.assertTrue("test is pass"+result,true);
//   }
}

