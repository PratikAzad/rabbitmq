package com.apll.centermanagementsservice.resttemplet

import com.apll.centermanagementsservice.config.CenterManagementsServiceApplication
import com.apll.centermanagementsservice.restTemplet.EmployeeService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(CenterManagementsServiceApplication::class))
class EmployeeTestIT {

    @Autowired
    val employeeService: EmployeeService? = null

    @Test
    fun getEmpTest(){
        val result=employeeService!!.getEmployee("d6a43a7f-dae6-4216-b72a-8789ead1c232")
        print(result)
    }

    @Test
    fun getAllFEEmpTest(){
        val result=employeeService!!.getAllFrontEndEmployee()
        print(result)
    }

}