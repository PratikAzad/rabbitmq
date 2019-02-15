package com.apll.centermanagementsservice.regionAndsubRegion.model

import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable


@Embeddable
class RegionId:Serializable {
    var regionId:String = UUID.randomUUID().toString()
        set(regionId){
            field=regionId
        }

    constructor()
    constructor(regionId: String) {
        this.regionId = regionId
    }

}