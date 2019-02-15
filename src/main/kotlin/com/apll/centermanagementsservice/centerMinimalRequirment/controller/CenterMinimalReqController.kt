package com.apll.centermanagementsservice.centerMinimalRequirment.controller

import com.apll.centermanagementsservice.centerMinimalRequirment.model.dto.CenterMinimalReqDTO
import com.apll.centermanagementsservice.centerMinimalRequirment.service.ICenterMinimalReqService
import com.apll.centermanagementsservice.config.ResponseWithError
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/minimalReq")
@Api("CenterMinimalReqController",description = "Rest Api for Center Minimal Requirement")
class CenterMinimalReqController {

    @Autowired
    lateinit var service: ICenterMinimalReqService

    @CrossOrigin
    @ApiOperation("create Center Minimal Req",notes = "Uri to  create Center Minimal Requirement",
            produces = "application/json",consumes = "application/json",response = CenterMinimalReqDTO::class)
    @PostMapping("/create")
    fun createCenterMinimalReq(@Valid @RequestBody dto: CenterMinimalReqDTO):ResponseWithError<CenterMinimalReqDTO>{
        var result=service.saveCenMinimalReq(dto)
        if (result.isLeft){
            return ResponseWithError.ofError(result.left.message)
        }
        return ResponseWithError.of(result.get())
    }

    @CrossOrigin
    @ApiOperation("update Center Minimal Req",notes = "Uri to update Center Minimal Requirement",
            produces = "application/json",consumes = "application/json",response = CenterMinimalReqDTO::class)
    @PutMapping("/update")
    fun updateCenterMinimalReq(@Valid @RequestBody dto: CenterMinimalReqDTO):ResponseWithError<CenterMinimalReqDTO>{
        var result=service.editCenMinimalReq(dto)
        if (result.isLeft){
            return ResponseWithError.ofError(result.left.message)
        }
        return ResponseWithError.of(result.get())
    }

    @CrossOrigin
    @ApiOperation("get Center Minimal Req",notes = "Uri to get Center Minimal Requirement",
            produces = "application/json",consumes = "application/json",response = CenterMinimalReqDTO::class)
    @GetMapping("/findMinimalReq")
    fun getCenterMinimalReq():ResponseWithError<CenterMinimalReqDTO>{
        var result=service.getMinimalReq()
        if (result.isLeft){
            return ResponseWithError.ofError(result.left.message)
        }
        return ResponseWithError.of(result.get())
    }

   /* @CrossOrigin
    @ApiOperation("get By Id Center Minimal Req",notes = "Uri to get Center Minimal Requirement by centerMinimalReqId",
                 produces = "application/json",consumes = "application/json",response = CenterMinimalReqDTO::class)
    @GetMapping("/findById/{centerMinimalReqId}")
    fun getById(@Valid @PathVariable centerMinimalReqId: String):ResponseWithError<CenterMinimalReqDTO>{
        var result=service.findById(centerMinimalReqId)
        if (result.isLeft){
            return ResponseWithError.ofError(result.left.message)
        }
        return ResponseWithError.of(result.get())
    }*/
}