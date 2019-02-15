package com.apll.centermanagementsservice.center.model



import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
class InfrastructureDetailsId :Serializable{

    var infrastructureDetailsId:String = UUID.randomUUID().toString()
    set(infrastructureDetailsId){
        field=infrastructureDetailsId
    }
    get() = field


    constructor()
    constructor(infrastructureDetailsId: String) {
        this.infrastructureDetailsId = infrastructureDetailsId
    }


    override fun toString(): String {
        return "InfrastructureDetailsId(infrastructureDetailsId='$infrastructureDetailsId')"
    }


}
