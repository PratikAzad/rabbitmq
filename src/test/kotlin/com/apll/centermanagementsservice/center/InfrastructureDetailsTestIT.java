/*
package com.apll.centermanagementsservice.com.apll.apllcommon.config.center;


import com.apll.centermanagementsservice.com.apll.apllcommon.config.center.model.InfrastructureDetails;
import com.apll.centermanagementsservice.com.apll.apllcommon.config.center.model.InfrastructureDetailsId;
import com.apll.centermanagementsservice.com.apll.apllcommon.config.center.model.dto.InfrastructureDetailsDTO;
import com.apll.centermanagementsservice.com.apll.apllcommon.config.center.model.dto.InfrastructureDetailsDTOConvertor;
import com.apll.centermanagementsservice.com.apll.apllcommon.config.center.service.InfrastructureDetailsService;
import com.apll.centermanagementsservice.config.CenterManagementsServiceApplication;
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
public class InfrastructureDetailsTestIT {

    @Autowired
    private InfrastructureDetailsService service;

    @Test
    public void insertInfrastructureDetailsTest(){
        InfrastructureDetails details=new InfrastructureDetails(
                new InfrastructureDetailsId(),
                100,
                50,
                5,
                60,
                10,
                30);
       InfrastructureDetailsDTO dto= InfrastructureDetailsDTOConvertor.
                         convertor.INSTANCE.dtoConvertor(details);

        Either<Exception,InfrastructureDetailsDTO> either=
                service.insertInfrastructureDetails(dto);

        System.out.println(either.right());
        Assert.assertEquals(true,either.isRight());
        Assert.assertTrue(either.isRight());
    }


    @Test
    public void updateInfrastructureDetailsByIdTest(){

        InfrastructureDetailsId  resolverId=new InfrastructureDetailsId();
        resolverId.setInfrastructureDetailsId("f7fbe035-9076-4173-a0a8-d8fff32f6dcd");
        InfrastructureDetails details=new InfrastructureDetails(resolverId,
                350,
                50,
                5,
                60,
                10,
                30);
        InfrastructureDetailsDTO dto= InfrastructureDetailsDTOConvertor.
                convertor.INSTANCE.dtoConvertor(details);

        Either<Exception,InfrastructureDetailsDTO> either=
                service.editInfrastructureDetails(dto);

        System.out.println(either.right());
        Assert.assertEquals(true,either.isRight());
        Assert.assertTrue(either.isRight());
    }

    @Test
    public void searchInfrastructureDetailsByIdTest() {

        Either<Exception, InfrastructureDetailsDTO> either =
                service.searchInfrastructureDetails("f7fbe035-9076-4173-a0a8-d8fff32f6dcd");
        System.out.println(either.right());
        Assert.assertEquals(true,either.isRight());
        Assert.assertTrue(either.isRight());
    }

    @Test
    public  void getAllInfrastructureDetailsTest(){


        Either<Exception, List<InfrastructureDetailsDTO>> either =
                service.getInfrastructureDetails();
        System.out.println(either.right());
        Assert.assertEquals(true,either.isRight());
        Assert.assertTrue(either.isRight());

    }
}

*/
