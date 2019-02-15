package com.apll.centermanagementsservice.schedulemanagement.frontendperformance.dto

data class ExistingCenterRequest (var existingRequests:Int,
                                  var visitedExistingRequests:Int,
                                  var performancePercentage:Float){


    fun performance() {
        this.performancePercentage= ((this.visitedExistingRequests.toFloat()/this.existingRequests.toFloat())*100.toFloat())

    }
}
