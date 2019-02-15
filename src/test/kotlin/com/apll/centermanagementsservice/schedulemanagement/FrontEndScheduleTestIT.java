


package com.apll.centermanagementsservice.schedulemanagement;


import com.apll.centermanagementsservice.config.CenterManagementsServiceApplication;
import com.apll.centermanagementsservice.schedulemanagement.controller.FrontEndScheduleController;
import com.apll.centermanagementsservice.schedulemanagement.model.EmployeeType;
import com.apll.centermanagementsservice.schedulemanagement.model.TypeOfRequest;
import com.apll.centermanagementsservice.schedulemanagement.model.dto.FrontEndScheduleDayWiseDTO;
import com.apll.centermanagementsservice.schedulemanagement.model.dto.ScheduleDTO;
import com.apll.centermanagementsservice.schedulemanagement.model.dto.ScheduleUpdateDTO;
import com.apll.centermanagementsservice.schedulemanagement.service.IFrontEndScheduleService;
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
public class FrontEndScheduleTestIT {

    @Autowired
    private FrontEndScheduleController controller;

    @Autowired
    private IFrontEndScheduleService service;


    @Test
    public void getAllFrontEndScheduleTest() {
        Either<Exception, List<FrontEndScheduleDayWiseDTO>> frontEndSchedulesDto =
                service.getAllFrontEndSchedule();
        Assert.assertTrue("Test is passed" + frontEndSchedulesDto, true);
    }

    @Test
    public void searchByFrontEndScheduleId(){

        Either<Exception,FrontEndScheduleDayWiseDTO> frontEndScheduleDTO=
                service.getByFrontEndScheduleId("99");
        Assert.assertTrue("Test is passed" + frontEndScheduleDTO, true);

    }

    @Test
    public void getByFrontEndId(){

        Either<Exception,List<FrontEndScheduleDayWiseDTO>> frontEndScheduleDTO=
                service.getByFrontEndId("203e1801-4e41-400a-bdab-f8d6bf1a1646");
       Assert.assertTrue("test is pass"+frontEndScheduleDTO,true);
    }

    @Test
    public void getFrontEndScheduleByYearMonthTest(){

        Either<Exception,FrontEndScheduleDayWiseDTO> frontEndScheduleDTO=
                service.getFrontEndScheduleByYearMonth("65","12-2018");
        Assert.assertTrue("test is pass"+frontEndScheduleDTO,true);
    }


/*
    @Test
    public void getByIdAndDateTest(){
        Either<Exception,FrontEndScheduleDayWiseDTO> dto=service.getFrontEndScheduleByIdAndDate("08-2000","2");
        Assert.assertTrue("pass"+dto.isRight(),true);
    }*/


    @Test
    public void latestAndApprovedByYearMonthTest(){
        Either<Exception,List<FrontEndScheduleDayWiseDTO>> dto=service.latestAndApprovedByYearMonth("65","12-2018");
        Assert.assertTrue("pass"+dto.isRight(),true);
    }

    @Test
    public void approveFrontEndSchedule(){
        Either<Exception,String> dto=service.approveFrontEndSchedule("00ebc6dc-4aba-43cc-844c-7df7e98ce567");
        Assert.assertTrue("pass"+dto.isRight(),true);
    }

    @Test
    public void getlatestFESForFEAndHA(){
        Either<Exception,List<FrontEndScheduleDayWiseDTO>> result=service.latestForFrontEndAndHeadAdmin("string","12-2018");
        Assert.assertTrue("pass",true);
    }

    @Test
    public void insertScheduleTest(){


        ScheduleDTO schedule1=new ScheduleDTO("111",
                LocalDate.of(2000,8,3),
                "222",
                "b",
                TypeOfRequest.EXISTING_CENTER_REQUEST,
                true,
                "333");


        ScheduleDTO schedule2=new ScheduleDTO("22",
                LocalDate.of(2000,8,5),
                "223",
                "asdad",
                TypeOfRequest.BUSSINESS_DEVELOPEMENT_ACTIVITY,
                true,
                "876");

        List<ScheduleDTO> scheduleList=new ArrayList<>();
        scheduleList.add(schedule1);
        scheduleList.add(schedule2);

/*

        FrontEndScheduleDTO frontEndSchedule=new FrontEndScheduleDTO("",
                LocalDate.of(2000,8,7),
                "2",
                "aaa",
                scheduleList);
*/

/*
       ResponseWithError< FrontEndScheduleDayWiseDTO> either=controller.saveFrontEndSchedule(frontEndSchedule);
        Assert.assertTrue("test is pass"+either,true);*/
    }


    @Test
    public void updateFrontEndScheduleTest() {
        ScheduleDTO schedule1=new ScheduleDTO("",
                LocalDate.of(2000,7,22),
                "222",
                "b",
                TypeOfRequest.EXISTING_CENTER_REQUEST,
                true,
                "333");


        ScheduleDTO schedule2=new ScheduleDTO("",
                LocalDate.of(2000,7,23),
                "223",
                "asdad",
                TypeOfRequest.BUSSINESS_DEVELOPEMENT_ACTIVITY,
                true,
                "876");

        List<ScheduleDTO> scheduleList=new ArrayList<>();
        scheduleList.add(schedule1);
        scheduleList.add(schedule2);


        ScheduleUpdateDTO dto=new ScheduleUpdateDTO("03b41421-3cee-4d92-bfcb-68e9d1a0ab31",
                "101",scheduleList,EmployeeType.HEAD_ADMIN,"msggg");

        Either<Exception,String> result=service.updateSchedule(dto);
        Assert.assertTrue("pass"+result.isRight(),true);


        /*FrontEndScheduleDTO frontEndScheduleDTO = FrontEndScheduleDTOConvertor.Convertor.INSTANCE.modelTODtoConvertor(frontEndSchedule);
        Either<Exception, FrontEndScheduleDTO> either = service.updateSchedule(frontEndScheduleDTO);
        Assert.assertTrue("test is pass" + either, true);*/

    }
   /* @Test
    public void deleteSchedule(){
        repo.deleteSchedule("101");
        System.out.println("delete");
    }*/

   /*@Test
   public void getExistingVisReq(){
       Either<Exception,List<String>> result=service.getExitingVisitRequestId();
       Assert.assertTrue("pass",true);

   }*/


}

