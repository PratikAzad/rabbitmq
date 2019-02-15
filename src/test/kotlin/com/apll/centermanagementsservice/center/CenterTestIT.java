//package com.apll.centermanagementsservice.com.apll.apllcommon.config.center;
//
//import com.apll.centermanagementsservice.center.model.CenterId;
//import com.apll.centermanagementsservice.center.model.CenterState;
//import com.apll.centermanagementsservice.center.model.CenterStatus;
//import com.apll.centermanagementsservice.center.model.CenterType;
//import com.apll.centermanagementsservice.center.model.dto.CenterStateDTO;
//import com.apll.centermanagementsservice.center.service.CenterService;
//import com.apll.centermanagementsservice.com.apll.apllcommon.config.center.model.dto.CenterStateDTO;
//import com.apll.centermanagementsservice.config.CenterManagementsServiceApplication;
//import com.apll.centermannagement.centerdemo.model.CenterState;
//import io.vavr.control.Either;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = CenterManagementsServiceApplication.class)
//public class CenterTestIT {
//
//    @Autowired
//    private CenterService service;
//
//    @Test
//    public void insertCenterTest(){
//        CenterState state=new CenterState(new CenterId(),
//                "Center",
//                "NameOfOwnae",
//                "9883245185",
//                "1245125546",
//                "Ho No. 10",
//                "Jubli Hills",
//                "Bnja",
//                "Lotus",
//                "Hyd",
//                "841454",
//                "Tela",
//                "Couloj",
//                "pka@gmail.com",
//                "johjol",
//                LocalDate.of(2012,10,12),
//                101,
//                CenterType.NEWCENTER,
//                CenterStatus.STARTING
//                );
//
//        CenterStateDTO dto= CenterStateDTOConverter.converter.INSTANCE.dtoConverter(state);
//
//        Either<Exception,CenterStateDTO> either=service.insertCenter(dto);
//        System.out.println(either.right());
//        Assert.assertEquals(true,either.isRight());
//        Assert.assertTrue(either.isRight());
//
//    }
//
//    @Test
//    public void updateCenterStateByIdTest(){
//        CenterId resolverId=new CenterId();
//        resolverId.setCenterId("574e5ee6-4436-472e-9dc9-e4766a4686e7");
//        CenterState state=new CenterState(resolverId,
//                "aaaa",
//                "shipra",
//                "8888888888",
//                "6666666666",
//                "Ho No. 60",
//                "banjara Hills",
//                "mla",
//                "omegha",
//                "hyderabad",
//                "999999",
//                "ts",
//                "ppppp",
//                "ship@gmail.com",
//                "johjol",
//                LocalDate.of(2000,6,7),
//                200,
//                CenterType.EXISTINGCENTER,
//                CenterStatus.ENDING);
//
//        CenterStateDTO dto= CenterStateDTOConverter.converter.INSTANCE.dtoConverter(state);
//        Either<Exception,CenterStateDTO> either=service.updateCenter(dto);
//        System.out.println(either.right());
//        Assert.assertEquals(true,either.isRight());
//        Assert.assertTrue(either.isRight());
//    }
//
//    @Test
//    public void searchCenterByIdTest(){
//
//        Either<Exception,CenterStateDTO> either=service.searchCenter("0fcc12af-8e1f-4b88-a8c3-d4a45dff4070");
//        System.out.println(either.right());
//        Assert.assertEquals(true,either.isRight());
//        Assert.assertTrue(either.isRight());
//    }
//
//    @Test
//    public void getAllCenterTest(){
//        Either<Exception,List<CenterStateDTO>> either=service.findAllCenters();
//        System.out.println(either.right());
//        Assert.assertEquals(true,either.isRight());
//        Assert.assertTrue(either.isRight());
//
//    }
//}
