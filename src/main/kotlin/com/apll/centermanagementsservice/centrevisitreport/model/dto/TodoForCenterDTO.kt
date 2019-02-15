package com.apll.centermanagementsservice.centrevisitreport.model.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.Valid

@ApiModel(value = " TodoForCenterDTO", description = "Class representing  TodoForCenterDTO", parent = Any::class)

data class TodoForCenterDTO(

        @ApiModelProperty(name = "todoForCenterId", notes = "todoForCenterId of aTodoForCenterDTO")
        var todoForCenterId: String?,

       @get:Valid
        @ApiModelProperty(name = "todoListCenterList", notes = "todoListCenterList of a TodoForCenterDTO")
        var todoListCenterList:List<TodoListCenterDTO>?) {
}