package com.apll.centermanagementsservice.otp.controller

import com.apll.centermanagementsservice.config.ResponseWithError
import com.apll.centermanagementsservice.otp.model.CenterOtp
import com.apll.centermanagementsservice.otp.service.CenterOtpService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/centerOTP")
@Api("Center OTP Controller",description = "Rest Api for Center OTP")
class CenterOTPController {

    @Autowired
    lateinit var service: CenterOtpService

    val log = LoggerFactory.getLogger(CenterOTPController::class.java)

    @PostMapping
    @CrossOrigin
    @ApiOperation(value = "Generate CenterOtp ", notes = "URI to Inserting CenterOtp .", produces = "application/json", consumes = "application/json", response = String::class)
    fun generateOTP(@Valid @RequestBody centerOtp: CenterOtp):ResponseWithError<String>{
        var result = service.generateCenterOTP(centerOtp)
        if (result.isRight) {
            log.info("Successfully OTP Generated :: "+result.get())
            return ResponseWithError.of(result.get())
        }
        log.error(result.left.message)
        return ResponseWithError.ofError(result.left.message)
        }


    @GetMapping("/verifyOtp/{centerId}/{otp}")
    @CrossOrigin
    @ApiOperation(value = "Get By CenterId for CenterOtp ", notes = "URI to get By CenterId for CenterOtp .", produces = "application/json", consumes = "application/json", response = Boolean::class)
    fun verifyOtp(@Valid @PathVariable centerId: String,
                       @PathVariable("otp")otp : String ): ResponseWithError<Boolean>{
        var result = service.verifyOtp(centerId,otp)
        if (result.isRight) {
            log.info("Successfully Getting CenterId And OTP.")
            return ResponseWithError.of(result.get())
        }
        log.error(result.left.message)
        return ResponseWithError.ofError(result.left.message)






    }


}