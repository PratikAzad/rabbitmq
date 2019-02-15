/*
package com.apll.centermanagementsservice.faculty

import com.apll.centermanagementsservice.config.CenterManagementsServiceApplication
import com.apll.centermanagementsservice.faculty.model.FacultyId
import com.apll.centermanagementsservice.faculty.model.FacultyState
import com.apll.centermanagementsservice.faculty.model.facultydto.FacultyDtoConverter
import com.apll.centermanagementsservice.faculty.service.FacultyService
import io.vavr.control.Either
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(CenterManagementsServiceApplication::class))
class FacultyTestIT {
    @Autowired
    lateinit var  service: FacultyService


    @Test
    fun insertFacultyTestCase() {
        val state = FacultyState(FacultyId(),
                "sai",
                "b-tech",
                "6666666666",
                "2666",
                "banajara hills",
                "film nager",
                "lotus",
                "hyd", "telangana",
                "12120",
                "computers",
                1,
                "sai@gmail.com")

        val dto = FacultyDtoConverter.Converter.dtoConverter(state)
        val result = service.addCenterFaculty(dto)
        Assert.assertEquals(true, result.isRight!=null)


    }
    @Test
    fun updateFacultyTestCase(){
        var resolverId=FacultyId()
        resolverId.facultyId="a5214b2f-ff53-49a3-b975-b2cbd552bc5e"

        val state=FacultyState(resolverId,
                "sairam ",
                "bsc",
                "7777777",
                "36666",
                "banajara hills",
                "film nager",
                "lotus pond",
                "hyd", "telangana",
                "303030",
                "computers",
                1,
                "sai@gmail.com")

        val dto = FacultyDtoConverter.Converter.dtoConverter(state)
        val result = service.editCenterFaculty(dto)
        Assert.assertEquals(true, result.isRight!=null)


    }
   @Test
    fun searchByFacultyIdTestCase(){

    var resolverId="a5214b2f-ff53-49a3-b975-b2cbd552bc5e"
    val result=service.readFacultyById(resolverId)
    println(result)
     Assert.assertTrue("resolverId is there ...",result.isRight!=null)

    }
    @Test

    fun getAllFacultiesTestCase(){
        var result=service.readAllFacultys()
        println(result)
        Assert.assertEquals(true, result.isRight!=null)

    }


}
*/
