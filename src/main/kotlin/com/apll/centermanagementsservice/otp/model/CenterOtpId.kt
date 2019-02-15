package com.apll.centermanagementsservice.otp.model

import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
class CenterOtpId:Serializable {
    var centerOtpId: String = UUID.randomUUID().toString()

    constructor()
    constructor(centerOTPId: String) {
        this.centerOtpId = centerOTPId
    }


}