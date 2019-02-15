package com.apll.centermanagementsservice.visitingrequest.controller.visitrequestdto

import com.apll.centermanagementsservice.centrevisitreport.model.ResolvedBy
import com.apll.centermanagementsservice.config.LocalDateDeSerializer
import com.apll.centermanagementsservice.config.LocalDateSerializer
import com.apll.centermanagementsservice.visitingrequest.model.RequestType
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


@ApiModel("Center visit Request" ,description = "visit Request for Existing Center",parent = Any::class)
data class VisitingRequestDTO (

        @ApiModelProperty(name="request_id",notes="requestid for visiting Request")
        var requestId: String?,

        @get:NotNull(message="RequestType can not be NULL(Empty).")
        @ApiModelProperty(name="request_by",notes = "requestBy  means  who is sending  Request for  visiting  Existing center")
        var requestBy: RequestType?,

        @get:NotNull(message="Reason can not be NULL(Empty).")
        @get:Size(max=500,min=5, message="Reason should have max 500 characters and min 5 characters..")
        @ApiModelProperty(name="reason",notes = "reason for visiting center")
        var reason:String?,

        @get:NotNull(message="Center Id can not be NULL(Empty).")
        @ApiModelProperty(name = "center_id",notes = "center_id for center")
        var centerId: String?,

        @get:NotNull(message="Center Name can not be NULL(Empty).")
        @ApiModelProperty(name = "centerName",notes = "centerName for center")
        var centerName: String?,

        @get:NotNull(message="Requester Id can not be NULL(Empty).")
        @ApiModelProperty(name="requester_id",notes = "requester_id for Requester")
        var requesterId :String?,

        @get:NotNull(message="Requester Name can not be NULL(Empty).")
        @ApiModelProperty(name="requesterName",notes = "requesterName for Requester")
        var requesterName :String?,

        @JsonDeserialize(using = LocalDateDeSerializer::class)
        @JsonSerialize(using = LocalDateSerializer::class)
        @get:NotNull(message="Request Date can not be NULL(Empty).")
        @ApiModelProperty(name="request_date",notes = " date of sending Request ")
        var requestDate: LocalDate?,

        @JsonDeserialize(using = LocalDateDeSerializer::class)
        @JsonSerialize(using = LocalDateSerializer::class)
        @ApiModelProperty(name="resolved_date",notes = " date of sending resolvedDate ")
        var resolvedDate: LocalDate?,

        @get:NotNull(message="Resolved can not be NULL(Empty).")
        @ApiModelProperty(name="resolved",notes = "resolved")
        var resolved:Boolean?,

        @ApiModelProperty(name = "howToResolved",notes = "howToResolved for visiting center")
        var howToResolved: String?,

        @ApiModelProperty(name="resolved_By",notes = "ResolvedBy")
        var resolvedBy: ResolvedBy?,

        @ApiModelProperty(name="resolved_Id",notes = "resolvedId")
        var resolverId:String?,

        @ApiModelProperty(name="reason_For_Not_Resolved", notes = "reasonForNotResolved")
        var reasonForNotResolved:String?


)