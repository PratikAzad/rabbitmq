package com.apll.centermanagementsservice.schedulemanagement.model.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class RejectedDto (
        @get:NotNull(message = "message For Reject schedule can not be null")
        @get:NotBlank(message = "message For Reject schedule can not be null")
        @get:Size(min=5,max = 250,message = "message For Reject schedule min=5,max=250.")
        var messageForReject:String?,

        var headAdminId:String){
}