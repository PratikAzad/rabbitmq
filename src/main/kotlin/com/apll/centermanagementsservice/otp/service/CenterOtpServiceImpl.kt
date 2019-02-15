package com.apll.centermanagementsservice.otp.service

import com.apll.centermanagementsservice.center.model.CenterId
import com.apll.centermanagementsservice.center.reposotory.CenterRepo
import com.apll.centermanagementsservice.mail.MailOutputStream
import com.apll.centermanagementsservice.mail.User
import com.apll.centermanagementsservice.otp.model.CenterOtp
import com.apll.centermanagementsservice.otp.model.Status
import com.apll.centermanagementsservice.otp.otpGenerater.RandomNumberGenerate
import com.apll.centermanagementsservice.otp.repository.CenterOtpRepository
import io.vavr.control.Either
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service
import org.springframework.util.MimeTypeUtils
import java.lang.Exception
import java.time.LocalDateTime


@Service
class CenterOtpServiceImpl: CenterOtpService {

    @Autowired
    lateinit var generator:RandomNumberGenerate

    @Autowired
    lateinit var repository:CenterOtpRepository

    @Autowired
    lateinit var centerRepo:CenterRepo

    @Autowired
    private lateinit var mailService: MailOutputStream


    override
    fun generateCenterOTP(centerOtp: CenterOtp):Either<Exception,String> {
        var center=centerRepo.findById(CenterId(centerOtp.centerId!!))
        if(!center.isPresent){
            return Either.left(Exception("Center Not Found."))
        }
        var otp=generator.generateRandomString()
        var centerOTP=CenterOtp(centerOtp.centerId!!,otp)

        repository.save(centerOTP)

        var user= User(center.get().emailId,"OTP Verification.","Your OTP is ::"+centerOTP.otp)
        mailService.sendMail().send(MessageBuilder.withPayload(user)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build())

        return Either.right("OTP Sent to your Mail.")
    }



    override
    fun verifyOtp(centerId: String, otp:String):Either<Exception,Boolean>{
        var center=centerRepo.findById(CenterId(centerId))
        if(!center.isPresent){
            return Either.left(Exception("Center Not Found."))
        }
        var result= repository.findByCenterIdAndStatus(centerId,Status.Active)
        var maxTime=result.stream().map({ u -> u.createdTime!! }).max(LocalDateTime::compareTo).get()
        var centerOTP=result.stream().filter { x->x.createdTime!!.equals(maxTime) }.findAny()
        if(centerOTP.isPresent){
            var validation=centerOTP.get().otpValidation()
             if(validation.isRight){
                 if(otp.equals(centerOTP.get().otp)) {
                     centerOTP.get().status=Status.InActive
                     repository.save(centerOTP.get())
                     result.filter { x->x.status!!.equals(Status.Active)}.
                             forEach {
                                 it.status=Status.Unused
                                 repository.save(it)
                             }

                     return Either.right(true)
                 }
                 return Either.left(Exception("OTP Not Matched."))
             }
            return Either.left(validation.left)
        }
        return Either.left(Exception("OTP Not Found."))
    }

}