package com.apll.centermanagementsservice.restTemplet

import com.apll.centermanagementsservice.config.ResponseWithError
import com.apll.centermanagementsservice.regionAndsubRegion.controller.regiondto.EmployeeDto
import com.apll.centermanagementsservice.regionAndsubRegion.controller.regiondto.EmployeeId
import io.vavr.control.Either
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

@Service
class EmployeeService {
    @Value("\${rms.uri}")
    private val ROOT_URI:String?=null


    fun getEmployee(employeeId:String):Either<Exception, EmployeeDto>{
        var uri=ROOT_URI+"/employee/"+employeeId

        val employee = RestTemplate().getForObject(uri,
                EmployeeDto::class.java,employeeId)

        if(employee!!.equals(null)){
            return Either.left(Exception("Employee not found"))
        }

        return Either.right(employee!!)
    }

    fun getAllFrontEndEmployee(): List<EmployeeDto>?
    {


        val headers = HttpHeaders()
        headers.accept = Arrays.asList(MediaType.APPLICATION_JSON)
        val entity = HttpEntity<EmployeeDto>(headers)
        var uri="$ROOT_URI"+"/getAllFrentEndEmployees"
        val response: ResponseWithError<*>? = RestTemplate().getForEntity(
                uri,  ResponseWithError::class.java).body!!

        if(!response?.isError!!) {
            var list = response?.response as List<LinkedHashMap<String, String>>
            return list.map {
                var employeeDto=EmployeeDto()
                employeeDto.employeeFullName=it.get("employeeFullName")
                val empObj = it.get("employeeId") as Any
                val empMap = empObj as HashMap<String, String>
                val employeeId = EmployeeId()
                employeeId.id=empMap.get("id")
                employeeDto.employeeId=employeeId
                //LinkedHashMap<String, String> employeeId=it.get("employeeId")
               employeeDto

            }.toList()
        }

        return ArrayList<EmployeeDto>();
    }
}