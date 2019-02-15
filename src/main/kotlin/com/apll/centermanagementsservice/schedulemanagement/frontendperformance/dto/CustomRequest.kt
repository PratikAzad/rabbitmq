package com.apll.centermanagementsservice.schedulemanagement.frontendperformance.dto

data class CustomRequest ( var customRequests:Int,
                           var visitedCustomRequests:Int,
                           var performancePercentage:Float){


    fun performance() {
        this.performancePercentage= ((this.visitedCustomRequests.toFloat()/this.customRequests.toFloat())*100.toFloat())

    }
}

