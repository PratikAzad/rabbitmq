package com.apll.centermanagementsservice.centrevisitreport.model

import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable


@Embeddable
class StockStatusId :Serializable{

    var stockStatusId:String=UUID.randomUUID().toString()
    set(stockStatusId){
        field=stockStatusId
    }

    constructor()
    constructor(stockStatusId: String) {
        this.stockStatusId = stockStatusId
    }


}