package com.apll.centermanagementsservice.schedulemanagement.frontendperformance.dto

data class NewCenterRequest(var newRequests:Int,
                            var visitedNewRequests:Int,
                            var performancePercentage:Float){

    fun performance() {
        this.performancePercentage= ((this.visitedNewRequests.toFloat()/this.newRequests.toFloat())*100.toFloat())

    }
}
