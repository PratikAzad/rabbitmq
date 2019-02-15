package com.apll.centermanagementsservice.centrevisitreport.model.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.Valid


@ApiModel(value = " TodoForApllDTO", description = "Class representing  TodoForApllDTO", parent = Any::class)

data class TodoForApllDTO (


                            @ApiModelProperty(name = "todoForApllId", notes = "todoForApllId of a TodoForApllDTO")
                            var todoForApllId: String?,

                            @get:Valid
                            @ApiModelProperty(name = "todoListApll", notes = "todoListApll of a TodoForApllDTO")
                            var todoListApll:List<TodoListApllDTO>?){
}