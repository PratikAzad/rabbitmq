package com.apll.centermanagementsservice.visitingrequest.dashboard

class VisReqResolvedAnalysis {
    var resolvedWithIn15Days:Int=0
    var resolvedWithIn30Days:Int=0
    var resolvedWithIn45Days:Int=0
    var resolvedWithIn60Days:Int=0
    var resolvedAfter60Days:Int=0

    constructor()
    constructor(resolvedWithIn15Days: Int, resolvedWithIn30Days: Int, resolvedWithIn45Days: Int, resolvedWithIn60Days: Int, resolvedAfter60Days: Int) {
        this.resolvedWithIn15Days = resolvedWithIn15Days
        this.resolvedWithIn30Days = resolvedWithIn30Days
        this.resolvedWithIn45Days = resolvedWithIn45Days
        this.resolvedWithIn60Days = resolvedWithIn60Days
        this.resolvedAfter60Days = resolvedAfter60Days
    }

    override fun toString(): String {
        return "VisReqResolvedAnalysis(" +
                "resolvedWithIn15Days=$resolvedWithIn15Days, " +
                "resolvedWithIn30Days=$resolvedWithIn30Days, " +
                "resolvedWithIn45Days=$resolvedWithIn45Days, " +
                "resolvedWithIn60Days=$resolvedWithIn60Days, " +
                "resolvedAfter60Days=$resolvedAfter60Days)"
    }


}