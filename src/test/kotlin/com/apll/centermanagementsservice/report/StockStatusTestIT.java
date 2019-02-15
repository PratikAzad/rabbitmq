/*
package com.apll.centermanagementsservice.report;


import com.apll.centermanagementsservice.config.CenterManagementsServiceApplication;
import com.apll.centermanagementsservice.centrevisreport.model.StockStatus;
import com.apll.centermanagementsservice.centrevisreport.model.StockStatusId;
import com.apll.centermanagementsservice.report.service.IStockStatusService;
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
public class StockStatusTestIT {

    @Autowired
    private IStockStatusService service;

    @Test
    public void saveStockStatusTest(){

        StockStatus status=new StockStatus(
                new StockStatusId(),
                "book",
                20,
                25,
                "yes",
                20,
                true,
                "good");
        Either<Exception,StockStatus> result=service.saveStockStatus(status);
        System.out.println(result);
        Assert.assertEquals(true,result.isRight());
    }

    @Test
    public void updateStockStatusTest(){

        StockStatusId resolverId=new StockStatusId();
        resolverId.setStockStatusId("2ad25921-fa79-4253-9869-d3b8e0e8baf1");
        StockStatus status=new StockStatus(
                 resolverId,
                "notebook",
                20,
                25,
                "yes",
                20,
                true,
                "good");
        Either<Exception,StockStatus> result=service.editStockStatus(status);
        System.out.println(result);
        Assert.assertEquals(true,result.isRight());

    }

    @Test
    public void searchStockStatusByIdTest(){

        StockStatusId resolverId=new StockStatusId();
        resolverId.setStockStatusId("2ad25921-fa79-4253-9869-d3b8e0e8baf1");
        Either<Exception,StockStatus> result=service.findStockStatusById(resolverId);
        System.out.println(result);
        Assert.assertEquals(true,result.isRight());
    }

    @Test
    public void getAllStockStatusTest(){
        Either<Exception, List<StockStatus>> result=service.findAllStockStatus();
        System.out.println(result);
        Assert.assertEquals(true,result.isRight());
    }

}

*/