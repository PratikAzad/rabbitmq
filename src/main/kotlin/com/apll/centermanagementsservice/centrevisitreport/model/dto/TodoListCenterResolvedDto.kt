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

@ApiModel(value = "TodoListCenterResolvedDto ", description = "Class representing TodoListCenterResolvedDto ", parent = Any::class)
data class TodoListCenterResolvedDto (
         @ApiModelProperty(name = "todoListCenterId", notes = "todoListApllId of a  TodoListCenterResolvedDto  ")
         var todoListCenterId: String?,

         @get:NotNull(message = "resolved By can not be NULL")
         @get:NotBlank(message="resolved by must not be blank")
         @ApiModelProperty(name = "resolvedBy", notes = "resolvedBy of a  TodoListCenterResolvedDto  ")
         var resolvedBy: ResolvedBy?,

         @get:NotNull(message = "resolverId can not be NULL")
         @get:NotBlank(message = "resolverId must not be blank")
         @ApiModelProperty(name = "resolverId", notes = "resolverId of a  TodoListCenterResolvedDto  ")
         var resolverId:String?,

         @get:NotNull(message = "resolvedDate can not be NULL/EMPTY")
         @ApiModelProperty(name = "resolvedDate", notes = "resolvedDate of a TodoListCenterResolvedDto  ")
         @JsonDeserialize(using = LocalDateDeSerializer::class)
         @JsonSerialize(using = LocalDateSerializer::class)
         var resolvedDate: LocalDate?,

         @get:NotNull(message = "howToResolved can not be NULL")
         @get:NotBlank(message = "howToResolved must not be blank")
         @get:Size(max=500,min=5,message = "howToResolved should have min 5  to  max 500 characters")
         @ApiModelProperty(name = "howToResolved", notes = "howToResolved of a  TodoListCenterResolvedDto  ")
         var howToResolved:String?,


         @get:NotNull(message = "issueOfStatus can not be NULL/EMPTY")
         @ApiModelProperty(name = "issueOfStatus", notes = "issueOfStatus of a  TodoListCenterResolvedDto  ")
         var issueOfStatus: ReasonStatus?
         )
