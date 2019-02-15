package com.apll.centermanagementsservice.centrevisitreport.model

import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
class TodoForApllId:Serializable {
    var todoForApllId:String=UUID.randomUUID().toString()
    set(value){
        field=value
    }
    get() = field

    constructor()
    constructor(todoForApllId: String) {
        this.todoForApllId = todoForApllId
    }


}