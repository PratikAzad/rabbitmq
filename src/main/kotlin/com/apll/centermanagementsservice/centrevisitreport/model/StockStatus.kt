package com.apll.centermanagementsservice.centrevisitreport.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
@Table(name = "stock_status")
class StockStatus{

    @EmbeddedId
    @Column(name = "stock_status_id")
    var stockStatusId=StockStatusId()

    @Column(name = "item")
    var item:String?=null

    @Column(name = "quantity_in_hand")
    var quantityInHand:Int? = 0

    @Column(name = "quantity_required")
    var quantityRequired:Int? = 0

    @Column(name = "indent_sent_on_by_mail")
    var indentSentOnByMail:String?=null

    @Column(name = "quantity_indented")
    var quantityIndented:Int? = 0

    @Column(name ="indent_taken_during_visit")
    var indentTakenDuringVisit:Boolean ?=false

    @Column(name = "feedback")
    var feedback:String?=null

    /*@JsonIgnore
    @JoinColumn(name="center_visit_report_id"*//*, nullable = false,columnDefinition = "VARCHAR(225)"*//*)
    @ManyToOne(cascade = arrayOf(CascadeType.ALL),fetch = FetchType.LAZY,optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    var centerVisitReport: CenterVisitReport? = null
        get() = field

        // setter
        set(value) {
            field = value
        }*/


    constructor(stockStatusId: StockStatusId, item: String?, quantityInHand: Int?, quantityRequested: Int?, indentSentOnByMail: String?, quantityIndented: Int?, indentTakenDuringVisit: Boolean?, feedback: String?) {
        this.stockStatusId = stockStatusId
        this.item = item
        this.quantityInHand = quantityInHand
        this.quantityRequired = quantityRequested
        this.indentSentOnByMail = indentSentOnByMail
        this.quantityIndented = quantityIndented
        this.indentTakenDuringVisit = indentTakenDuringVisit
        this.feedback = feedback
    }


    fun updateStockStatus(stockStatus:StockStatus){
        this.item=stockStatus.item
        this.quantityInHand=stockStatus.quantityInHand
        this.quantityRequired=stockStatus.quantityRequired
        this.indentSentOnByMail=stockStatus.indentSentOnByMail
        this.quantityIndented=stockStatus.quantityIndented
        this.indentTakenDuringVisit=stockStatus.indentTakenDuringVisit
        this.feedback=stockStatus.feedback


    }

    constructor()
}