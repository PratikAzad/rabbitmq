package com.apll.centermanagementsservice.otp.model

import com.apll.centermanagementsservice.config.JsonDateTimeDeSerializer
import com.apll.centermanagementsservice.config.JsonDateTimeSerializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.vavr.control.Either
import java.lang.Exception
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import javax.persistence.*

@Entity
@Table(name="center_otp")
class CenterOtp {
    @EmbeddedId
    @Column(name = "center_otp_id")
    var centerOtpId: CenterOtpId?= CenterOtpId()

    @Column(name="center_id")
    var centerId:String?=null

    @Column(name = "otp")
    var otp:String?=null

    @JsonSerialize(using = JsonDateTimeSerializer::class)
    @JsonDeserialize(using = JsonDateTimeDeSerializer::class)
    @Column(name = "created_time")
    var createdTime: LocalDateTime?=LocalDateTime.now()

    @JsonSerialize(using = JsonDateTimeSerializer::class)
    @JsonDeserialize(using = JsonDateTimeDeSerializer::class)
    @Column(name = "validate_time")
    var validateTime: LocalDateTime?=LocalDateTime.now()

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status:Status?=null


    constructor()
    constructor(centerOtpId: CenterOtpId, centerId: String?, otp: String?,
                createdTime: LocalDateTime, validateTime: LocalDateTime?,
                status: Status?) {
        this.centerOtpId = centerOtpId
        this.centerId = centerId
        this.otp = otp
        this.createdTime = createdTime
        this.validateTime = validateTime
        this.status = status
    }

    constructor(centerId: String,otp: String){
        this.centerId=centerId
        this.centerOtpId= CenterOtpId()
        this.createdTime= LocalDateTime.now()
        this.validateTime= LocalDateTime.now().plusMinutes(15)
        this.status=Status.Active
        this.otp=otp
    }


    fun otpValidation():Either<Exception,Boolean>{
        var minutes= ChronoUnit.MINUTES.between(this.createdTime,this.validateTime)
        if(minutes>15){
            return Either.left(Exception("OTP Timed Out..."))
        }
         return Either.right(true)

    }


}