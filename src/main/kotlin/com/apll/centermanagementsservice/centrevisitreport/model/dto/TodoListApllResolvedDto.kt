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

@ApiModel(value = "TodoListApllResolvedDto ", description = "Class representing  TodoListApllResolvedDto ", parent = Any::class)
data class TodoListApllResolvedDto (
                               @ApiModelProperty(name = "todoListApllId", notes = "todoListApllId of a  TodoListApllResolvedDto ")
                               var todoListApllId: String?,

                               @ApiModelProperty(name = "resolvedBy", notes = "resolvedBy of a  TodoListApllResolvedDto")
                               @get:NotNull(message = "resolved By can not be NULL")
                               @get:NotBlank
                               var resolvedBy: ResolvedBy?,

                               @ApiModelProperty(name = "resolverId", notes = "resolverId of a  TodoListApllResolvedDto")
                               @get:NotNull(message = "resolverId can not be NULL")
                               @get:NotBlank
                               var resolverId:String?,


                               @get:NotNull(message = "resolvedDate can not be NULL/EMPTY")
                               @ApiModelProperty(name = "resolvedDate", notes = "resolvedDate of a  TodoListApllResolvedDto ")
                               @JsonDeserialize(using = LocalDateDeSerializer::class)
                               @JsonSerialize(using = LocalDateSerializer::class)
                               var resolvedDate: LocalDate?,


                               @get:NotNull(message = "howToResolved can not be NULL")
                               @get:NotBlank
                               @get:Size(max=500,min=5,message = "howToResolved should have min 5  to  max 500 characters")
                               @ApiModelProperty(name = " howToResolved", notes = " howToResolved of a  TodoListApllResolvedDto")
                               var howToResolved:String?,


                               @get:NotNull(message = "issueOfStatus can not be NULL/EMPTY")
                               @ApiModelProperty(name = "issueOfStatus", notes = "issueOfStatus of a  TodoListApllResolvedDto")
                               var issueOfStatus: ReasonStatus?


){
}