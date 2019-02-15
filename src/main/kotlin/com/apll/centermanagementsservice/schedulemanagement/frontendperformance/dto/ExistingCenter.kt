package com.apll.centermanagementsservice.schedulemanagement.frontendperformance.dto

data class ExistingCenter (var ecRequests:Int,
                           var visitedExistingRequests:Int,
                           var performancePercentage:Float){


    fun performance() {
        this.performancePercentage= ((this.visitedExistingRequests.toFloat()/this.ecRequests.toFloat())*100.toFloat())

    }
}
