package com.apll.centermanagementsservice.regionAndsubRegion.model

import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
class SubRegionId : Serializable {
    var subRegionId:String = UUID.randomUUID().toString()

    set(subRegionId){
        field=subRegionId
    }

    constructor()
    constructor(subRegionId: String) {
        this.subRegionId = subRegionId
    }
}