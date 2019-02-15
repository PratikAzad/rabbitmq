
package com.apll.centermanagementsservice.centrevisitreport.model

import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
class TodoListApllId:Serializable {

    var todoListApllId:String=UUID.randomUUID().toString()

    constructor() {
        this.todoListApllId = todoListApllId
    }
    fun settodoListApllId(todoListApllId:String){
        this.todoListApllId=todoListApllId
    }
    fun gettodoListApllId():String{
        return this.todoListApllId


    }



}
