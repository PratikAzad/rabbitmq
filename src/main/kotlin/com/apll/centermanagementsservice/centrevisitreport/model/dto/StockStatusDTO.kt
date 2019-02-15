package com.apll.centermanagementsservice.centrevisitreport.model.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.Range
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@ApiModel(value = "StockStatus", description = "Class representing StockStatus", parent = Any::class)
data class StockStatusDTO(
        @ApiModelProperty(name = "stockStatusId", notes = "stockStatusId of a stockStatus")
        var stockStatusId: String,

        @ApiModelProperty(name = "item", notes = "item of a stockStatus")
        @get:NotNull(message = "item can not be NULL")
        @get:NotBlank(message = "item must not be blank")
        @get:Size(max=40,min=3,message = "item  should have atleast 3 to max 40 characters")
        var item:String?,

        @ApiModelProperty(name = "quantityInHand", notes = "quantity In Hand of a stockStatus")
        @get:NotNull(message = "quantity In Hand can not be Empty")
        @get:Range(max=1000,min=1,message = "quantityInHand   should have atleast 1 to  1000 quantitys")
        var quantityInHand:Int?,

        @ApiModelProperty(name = "quantityRequired", notes = "quantity Requested of a stockStatus")
        @get:NotNull(message = "quantity Requested can not be Empty")
        @get:Range(max=1000,min=1,message = "quantityRequested   should have atleast 1 to  1000 requests")
        var quantityRequested:Int?,

        @ApiModelProperty(name = "indentSentOnByMail", notes = "indent Sent On By Mail of a stockStatus")
        @get:NotNull(message = "indent SentOnByMail can not be NULL")
        @get:NotBlank(message = "indentSentOnByMail must not be blank")
        @get:Size(max=40,min=3,message = "indentSentOnByMail   should have atleast 3 to max 40 mails")
        var indentSentOnByMail:String?,

        @ApiModelProperty(name = "quantity Indented", notes = "quantity Indented of a stockStatus")
        @get:NotNull(message = "quantity Indented can not be Empty")
        @get:Range(max=1000,min=1,message = "quantityIndented    should have atleast 1 to max 100 quantityIndents")
        var quantityIndented:Int?,

        @ApiModelProperty(name = "indentTakenDuringVisit", notes = "indent Taken During Visit of a stockStatus")
        @get:NotNull(message = "quantity Indented can not be Empty")
        var indentTakenDuringVisit:Boolean?,

        @ApiModelProperty(name = "feedback", notes = "feedback of a stockStatus")
        @get:NotNull(message = "feedback can not be NULL")
        @get:Size(max=250,min=2,message = "feedback  should have atleast 2  to max 250 characters")
        @get:NotBlank(message = "feedback must not be blank")
        var feedback:String?
)