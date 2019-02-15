package com.apll.centermanagementsservice.otp.service

import com.apll.centermanagementsservice.otp.model.CenterOtp
import io.vavr.control.Either
import java.lang.Exception

interface CenterOtpService {


    fun generateCenterOTP(centerOtp: CenterOtp):Either<Exception,String>

    fun verifyOtp(centerId: String, otp:String):Either<Exception,Boolean>

}