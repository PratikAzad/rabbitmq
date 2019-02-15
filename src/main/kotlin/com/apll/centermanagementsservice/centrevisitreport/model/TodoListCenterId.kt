
package com.apll.centermanagementsservice.centrevisitreport.model

import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
class TodoListCenterId:Serializable {
    var todoListCenterId:String=UUID.randomUUID().toString()
    set(value){
        field=value
    }
    get() = field

    constructor()
    constructor(todoListCenterId: String) {
        this.todoListCenterId = todoListCenterId
    }

}
