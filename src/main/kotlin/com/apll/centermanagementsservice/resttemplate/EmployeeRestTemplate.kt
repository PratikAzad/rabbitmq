package com.apll.centermanagementsservice.resttemplate

import com.apll.centermanagementsservice.regionAndsubRegion.controller.regiondto.EmployeeDto
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

import java.util.Arrays

@Service
class EmployeeRestTemplate {

//internal var ROOT_URI = "http://localhost:8082/employee"
  internal var ROOT_URI = "http://101.53.153.204:8082/getAllEmployeesByRegionId"
    fun getAllEmployeesByRegionId(id: String): List<EmployeeDto> {
        val headers = HttpHeaders()
        headers.accept = Arrays.asList(MediaType.APPLICATION_JSON)
        val entity = HttpEntity<EmployeeDto>(headers)

        val response = RestTemplate().exchange(
                "$ROOT_URI/$id", HttpMethod.GET, entity, Array<EmployeeDto>::class.java).body
        return Arrays.asList(*response!!)
    }

    /*    internal var ROOT_URI = "http://localhost:8086/region"
    fun getEmployeeByRegion(resolverId: String): RegionDto?
    {
        var hashMap:LinkedHashMap<Any,Any>
        var regionDto=RegionDto()

        var response: ResponseWithError<*> = RestTemplate().getForEntity(
                "$ROOT_URI/$resolverId",  ResponseWithError::class.java).body!!
        if(!response!!.isError){
             hashMap= response.response as LinkedHashMap<Any, Any>
           regionDto.regionId= hashMap.get("regionId") as String?
          regionDto.regionName=hashMap.get("regionName") as String?
        }
        return regionDto;
    }
*/
}
