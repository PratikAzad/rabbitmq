package com.apll.centermanagementsservice.resttemplate

import com.apll.centermanagementsservice.config.CenterManagementsServiceApplication
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/*@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(CenterManagementsServiceApplication::class))*/
class EmployeeRestTemplateIT {

    @Autowired
    var  employeeRestTemplate= EmployeeRestTemplate()

    @Test
    fun getAllEmployeesByRegionIdTestCase(){

        var employeeRestTemplate =EmployeeRestTemplate()
        var num ="12345"
        //var result=employeeRestTemplate.getAllEmployeesByRegionId(num)
       // print(result)
    }

}