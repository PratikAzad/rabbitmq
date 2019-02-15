package com.apll.centermanagementsservice.schedulemanagement.frontendperformance.dto

class FrontEndPerformance {
    //var yearMonth:YearMonth?=null
    var frontEndId:String?= null
    var bdaRequest:BDARequest?=null
    var customRequest:CustomRequest?=null
    var existingCenter:ExistingCenter?=null
    var newCenterRequest:NewCenterRequest?=null
    var existingCenterRequest:ExistingCenterRequest?=null
    var empName:String?=null
    var allRequest:Int?=null
    var allVisitedRequests:Int?=null
    var overRallPercentage:Float?=null
    var workingDays:Int?=null




    constructor()
    constructor(frontEndId: String?, bdaRequest: BDARequest?, customRequest: CustomRequest?, existingCenter: ExistingCenter?, newCenterRequest: NewCenterRequest?, existingCenterRequest: ExistingCenterRequest?, empName: String?, allRequest: Int?, allVisitedRequests: Int?, overRallPercentage: Float?,workingDays:Int?) {
        this.frontEndId = frontEndId
        this.bdaRequest = bdaRequest
        this.customRequest = customRequest
        this.existingCenter = existingCenter
        this.newCenterRequest = newCenterRequest
        this.existingCenterRequest = existingCenterRequest
        this.empName = empName
        this.allRequest = allRequest
        this.allVisitedRequests = allVisitedRequests
        this.overRallPercentage = overRallPercentage
        this.workingDays=workingDays
    }


    fun calculatePerformance() {
        if (this.bdaRequest != null && this.bdaRequest!!.bdaRequests > 0) {
            this.bdaRequest!!.performance()
        }
        if (this.customRequest != null && this.customRequest!!.customRequests > 0){
            this.customRequest!!.performance()
        }

        if (this.existingCenter != null && this.existingCenter!!.ecRequests > 0) {
            this.existingCenter!!.performance()
        }
        if (this.existingCenterRequest != null && this.existingCenterRequest!!.existingRequests > 0) {
            this.existingCenterRequest!!.performance()
        }

        if (this.newCenterRequest != null && this.newCenterRequest!!.newRequests > 0) {
            this.newCenterRequest!!.performance()

        }
    }

    fun calculateOverRallPerformance(){
        if(this.allRequest!=null && this.allRequest!! >0) {

            this.overRallPercentage = ((this.allVisitedRequests!!.toFloat() / this.allRequest!!.toFloat()) * 100.toFloat())
        }
    }
}

