package com.apll.centermanagementsservice.centrevisitreport.model.dto

import com.apll.centermanagementsservice.centrevisitreport.model.ReasonStatus
import com.apll.centermanagementsservice.centrevisitreport.model.ResolvedBy
import com.apll.centermanagementsservice.config.LocalDateDeSerializer
import com.apll.centermanagementsservice.config.LocalDateSerializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@ApiModel(value = " TodoListApllDTO", description = "Class representing TodoListApllDTO", parent = Any::class)
data class TodoListApllDTO (

        @ApiModelProperty(name = "todoListApllId", notes = "todoListApllId of a TodoListApllDTO ")
        var todoListApllId: String?,

        @ApiModelProperty(name = "issueName", notes = "issueName of a TodoListApllDTO ")
        @get:NotNull(message = "issue Name can not be NULL")
        @get:Size(max=150,min=5,message = "issueName should have min 5  to max 150 characters")
        @get:NotBlank(message = "issueName must not be balnk")
        var issueName:String?,

        @get:NotNull(message = "raise Date can not be NULL/EMPTY")
        @ApiModelProperty(name = "raiseDate", notes = "raiseDate of a TodoListApllDTO ")
        @JsonDeserialize(using = LocalDateDeSerializer::class)
        @JsonSerialize(using = LocalDateSerializer::class)
        var raiseDate: LocalDate?,

        @ApiModelProperty(name = "issueOfStatus", notes = "issueOfStatus of a TodoListApllDTO")
        @get:NotNull(message = "issue Of Status can not be NULL/EMPTY")
        var issueOfStatus: ReasonStatus?,



        @ApiModelProperty(name = "resolvedBy", notes = "resolvedBy of a TodoListApllDTO ")
        var resolvedBy: ResolvedBy?,

        @ApiModelProperty(name = "resolverId", notes = "resolverId of a TodoListApllDTO ")
        var resolverId:String?,

        @ApiModelProperty(name = "resolvedDate", notes = "resolvedDate of a TodoListApllDTO ")
        @JsonDeserialize(using = LocalDateDeSerializer::class)
        @JsonSerialize(using = LocalDateSerializer::class)
        var resolvedDate: LocalDate?,

        @ApiModelProperty(name = "howToResolved", notes = "howToResolved  of a TodoListApllDTO ")
        @get:Size(max=500,min=5,message = "howToResolved should have min 5  to  max 500 characters")
        var howToResolved:String?



){
}
