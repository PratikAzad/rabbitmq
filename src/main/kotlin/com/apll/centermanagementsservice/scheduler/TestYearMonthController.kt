package com.apll.centermanagementsservice.scheduler

import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.*
import java.time.YearMonth

@Slf4j
@RestController
class TestYearMonthController {

    /*@PostMapping("testYearMonth")
    fun yearMonthSTest(@RequestBody model:YearMonthModel){

        println(model)
    }

    @GetMapping("testYearMonth")
    fun yearMonthDTest():YearMonthModel{
        var result=YearMonthModel()
        try {
             result=YearMonthModel(YearMonth.of(2018, 10))
        }
        catch(e:Exception){
            e.printStackTrace()
        }
        return result
    }*/
}