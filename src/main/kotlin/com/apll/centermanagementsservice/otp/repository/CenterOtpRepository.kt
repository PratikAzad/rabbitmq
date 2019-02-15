package com.apll.centermanagementsservice.otp.repository

import com.apll.centermanagementsservice.otp.model.CenterOtp
import com.apll.centermanagementsservice.otp.model.CenterOtpId
import com.apll.centermanagementsservice.otp.model.Status
import org.springframework.data.jpa.repository.JpaRepository


interface CenterOtpRepository: JpaRepository<CenterOtp, CenterOtpId> {


    fun findByCenterIdAndStatus(centerId: String,status:Status):List<CenterOtp>


}