/*

package com.apll.centermanagementsservice.schedulemanagement;


/*
import com.apll.centermanagementsservice.config.CenterManagementsServiceApplication;
import com.apll.centermanagementsservice.schedulemanagement.model.*;
import com.apll.centermanagementsservice.schedulemanagement.model.dto.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CenterManagementsServiceApplication.class)
*/
/*public class FrontEndScheduleDayWiseDTOTest {

   @Test
    public void modelToDtoConvertTest(){

        ScheduleId scheduleId1=new ScheduleId();
        scheduleId1.setRequestId("111");

        Schedule schedule1=new Schedule(
                scheduleId1,
                LocalDate.of(2018,11,19),
                "1",
                "yes",
                TypeOfRequest.NEW_CENTER_REQUEST,
                true,
                "11");

        ScheduleId scheduleId2=new ScheduleId();
        scheduleId2.setRequestId("222");

        Schedule schedule2=new Schedule(
                scheduleId2,
                LocalDate.of(2018,11,19),
                "2",
                "yes",
                TypeOfRequest.EXISITNG_CENTER_REQUEST,
                true,
                "22");


        ScheduleId scheduleId3=new ScheduleId();
        scheduleId3.setRequestId("333");

        Schedule schedule3=new Schedule(
                scheduleId3,
                LocalDate.of(2018,11,4),
                "3",
                "yes",
                TypeOfRequest.BUSSINESS_DEVELOPEMENT_ACTVITY,
                true,
                "33");

        ScheduleId scheduleId4=new ScheduleId();
        scheduleId4.setRequestId("444");

        Schedule schedule4=new Schedule(
                scheduleId4,
                LocalDate.of(2018,11,4),
                "4",
                "yes",
                TypeOfRequest.EXISTING_CENTER,
                true,
                "44");

        ScheduleId scheduleId5=new ScheduleId();
        scheduleId5.setRequestId("555");

        Schedule schedule5=new Schedule(
                scheduleId5,
                LocalDate.of(2018,11,21),
                "5",
                "yes",
                TypeOfRequest.NEW_CENTER_REQUEST,
                true,
                "55");

        ScheduleId scheduleId6=new ScheduleId();
        scheduleId6.setRequestId("555");

        Schedule schedule6=new Schedule(
                scheduleId6,
                LocalDate.of(2018,11,21),
                "6",
                "yes",
                TypeOfRequest.BUSSINESS_DEVELOPEMENT_ACTVITY,
                true,
                "66");


        ScheduleId scheduleId7=new ScheduleId();
        scheduleId7.setRequestId("555");

        Schedule schedule7=new Schedule(
                scheduleId7,
                LocalDate.of(2018,10,22),
                "7",
                "yes",
                TypeOfRequest.NEW_CENTER_REQUEST,
                true,
                "77");

        List<Schedule> scheduleList=new ArrayList<>();
        scheduleList.add(schedule1);
        scheduleList.add(schedule2);
        scheduleList.add(schedule3);
        scheduleList.add(schedule4);
        scheduleList.add(schedule5);
        scheduleList.add(schedule6);
        scheduleList.add(schedule7);

        FrontEndScheduleId frontEndScheduleId=new FrontEndScheduleId();
        frontEndScheduleId.setFrontEndScheduleId("1");

        FrontEndSchedule frontEndSchedule=new FrontEndSchedule(
                frontEndScheduleId,
                LocalDate.of(2018,11,21),
                "111",
                "fe1",
                scheduleList
        );

        FrontEndScheduleDayWiseDTO dayWiseDTO= FrontEndScheduleDayWiseDTOConverter.converter.INSTANCE.modelToDtoConvert(frontEndSchedule);
        Assert.assertTrue("test is passed",true);
    }*//*

}

*/
