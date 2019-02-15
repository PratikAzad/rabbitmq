package com.apll.centermanagementsservice.schedulemanagement.frontendperformance.dto

 data class BDARequest (
    var bdaRequests:Int,
    var visitedRequests:Int,
    var performancePercentage:Float) {


  fun performance() {
   this.performancePercentage= ((this.visitedRequests.toFloat()/this.bdaRequests.toFloat())*100.toFloat())

  }
 }

