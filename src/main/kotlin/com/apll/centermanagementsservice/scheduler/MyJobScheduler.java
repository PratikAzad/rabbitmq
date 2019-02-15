package com.apll.centermanagementsservice.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;


public class MyJobScheduler {

    Logger log=LoggerFactory.getLogger(MyJobScheduler.class);


    @Scheduled(fixedDelay =3000)
    public void makeSchedule(){
        log.info("Current DateTime :: "+LocalDateTime.now());
    }
}
