package com.apll.centermanagementsservice.centrevisitreport.model

import javax.persistence.*

@Entity
@Table(name="cvr_infrastructure")
class CvrInfrastructure {

    @EmbeddedId
    @Column(name="cvr_infrastructure_id")
    var cvrInfrastructureId:CvrInfrastructureId?=null

    @Column(name="infrastructure_name")
    var infrastructureName:String?=null

    @Column(name="minimal_value")
    var minimalValue:Float?=0.0F


    @Column(name="minimal_requirement_satisfies")
    var minimalRequirementSatisfies:Boolean?=null

    @Column(name="current_value")
    var currentValue:Float?=0.0F

    constructor()

    constructor(infrastructureId: CvrInfrastructureId?, infrastructureName: String?, minimalValue:Float?, minimalSatisfies: Boolean?, currentValue: Float?) {
        this.cvrInfrastructureId = infrastructureId
        this.infrastructureName = infrastructureName
        this.minimalValue=minimalValue
        this.minimalRequirementSatisfies = minimalSatisfies
        this.currentValue = currentValue
    }

    fun updateCvrInfrastructure(infrastructure: CvrInfrastructure){


        this.minimalRequirementSatisfies=infrastructure.minimalRequirementSatisfies
        this.currentValue=infrastructure.currentValue
       /* if(!this.minimalRequirementSatisfies!!.equals(true)){
            this.minimalRequirementSatisfies=infrastructure.minimalRequirementSatisfies
        }
        if(this.minimalRequirementSatisfies!!.equals(false)){
            this.currentValue=infrastructure.currentValue
        }
        */
    }
}