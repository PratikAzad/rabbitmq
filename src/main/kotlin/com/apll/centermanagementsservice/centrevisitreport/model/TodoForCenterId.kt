package com.apll.centermanagementsservice.centrevisitreport.model

import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
class TodoForCenterId:Serializable {

    var  todoForCenterId:String=UUID.randomUUID().toString()
    set(value){
        field=value
    }
    get() =field

    constructor() {
        this.todoForCenterId = todoForCenterId
    }

}