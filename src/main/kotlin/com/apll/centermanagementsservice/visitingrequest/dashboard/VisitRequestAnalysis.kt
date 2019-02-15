package com.apll.centermanagementsservice.visitingrequest.dashboard

class VisitRequestAnalysis {

    var resolvedVisReq:Int=0

    var unResolvedVisReq:Int=0

    var allVisReq:Int=0


    constructor()
    constructor(resolvedVisReq: Int, unResolvedVisReq: Int, allVisReq: Int) {
        this.resolvedVisReq = resolvedVisReq
        this.unResolvedVisReq = unResolvedVisReq
        this.allVisReq = allVisReq
    }

    override fun toString(): String {
        return "VisitRequestAnalysis(" +
                " resolvedVisReq=$resolvedVisReq," +
                " unResolvedVisReq=$unResolvedVisReq," +
                " allVisReq=$allVisReq)"
    }


}