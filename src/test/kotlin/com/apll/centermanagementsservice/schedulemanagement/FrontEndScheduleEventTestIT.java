/*
package com.apll.centermanagementsservice.schedulemanagement;

import com.apll.centermanagementsservice.config.CenterManagementsServiceApplication;
import com.apll.centermanagementsservice.schedulemanagement.model.*;
import com.apll.centermanagementsservice.schedulemanagement.service.FrontEndScheduleEventServiceImpl;
import io.vavr.control.Either;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CenterManagementsServiceApplication.class)
public class FrontEndScheduleEventTestIT {

    @Autowired
    private FrontEndScheduleEventServiceImpl service;


    @Test
    public void FrontEndScheduleEventSaveTestIT(){

        ScheduleState scheduleState1=new ScheduleState(
                new ScheduleId("s1"),
                LocalDate.of(2000,10,03),
                "r1",
                "note1",
                TypeOfRequest.BUSSINESS_DEVELOPEMENT_ACTVITY,
                false,
                "vr1"
        );


        ScheduleState scheduleState2=new ScheduleState(
                new ScheduleId("s2"),
                LocalDate.of(2000,10,04),
                "r2",
                "note2",
                TypeOfRequest.NEW_CENTER_REQUEST,
                false,
                "vr2"
        );

        ScheduleState scheduleState3=new ScheduleState(
                new ScheduleId("s3"),
                LocalDate.of(2000,10,05),
                "r3",
                "note3",
                TypeOfRequest.EXISTING_CENTER,
                false,
                "vr3"
        );

        List<ScheduleState> scheduleStates=new ArrayList<>();
        scheduleStates.add(scheduleState1);
        scheduleStates.add(scheduleState2);
        scheduleStates.add(scheduleState3);


        FrontEndScheduleEvent event=new FrontEndScheduleEvent(
                new FrontEndScheduleEventId(),
                "111",
                scheduleStates,
                LocalDate.of(2000,10,01),
                "f1",
                false,
                UpdatedBy.FRONT_END,
                LocalDate.of(2000,10,30),
                3);

        Either<Exception,String> result=service.saveFrontEndScheduleEvent(event);
        Assert.assertTrue("pass"+result.isRight(),true);
    }
}
*/
