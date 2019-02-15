package com.apll.centermanagementsservice.visitingrequest.controller.visitrequestdto

import com.apll.centermanagementsservice.centrevisitreport.model.ReasonStatus
import com.apll.centermanagementsservice.centrevisitreport.model.ResolvedBy
import com.apll.centermanagementsservice.config.LocalDateDeSerializer
import com.apll.centermanagementsservice.config.LocalDateSerializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


@ApiModel("Center visit Request" ,description = "visit Request for Existing Center",parent = Any::class)
data class ResolvedDTO (

    @JsonDeserialize(using = LocalDateDeSerializer::class)
    @JsonSerialize(using = LocalDateSerializer::class)
    @get:NotNull(message="Resolved Date can not be NULL(Empty).")
    @ApiModelProperty(name="resolved_date",notes = " date of sending resolvedDate for Visiting Request ")
    var resolvedDate: LocalDate?,

    @get:NotNull(message="Request Id can not be NULL(Empty).")
    @ApiModelProperty(name="request_Id",notes = "requestId for Visiting Request")
    var requestId:String?,


    @ApiModelProperty(name = "how_To_Resolved",notes = "howToResolved for visiting center")
    var howToResolved: String?,

    @get:NotNull(message="ResolvedBy can not be NULL(Empty).")
    @ApiModelProperty(name="resolved_By",notes = "ResolvedBy for Visiting Request")
    var resolvedBy: ResolvedBy?,

    @get:NotNull(message="ResolverId can not be NULL(Empty).")
    @ApiModelProperty(name="resolved_Id",notes = "resolvedId for Visiting Request")
    var resolverId:String?,

    @ApiModelProperty(name = "reason_for_not_resolved",notes = "reason for visiting center")
    var reasonForNotResolved:String?,

    @get:NotNull(message="Resolved can not be NULL(Empty).")
    @ApiModelProperty(name="resolved",notes = "resolved for Visiting Request")
    var resolved:Boolean?
)

