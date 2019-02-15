package com.apll.centermanagementsservice.visitingrequest.model

import com.apll.centermanagementsservice.center.model.CenterId
import com.apll.centermanagementsservice.centrevisitreport.model.ResolvedBy
import com.apll.centermanagementsservice.visitingrequest.controller.visitrequestdto.ResolvedDTO
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name="visiting_request")

data class VisitingRequest(@EmbeddedId
                           @Column(name="request_id")
                             var requestId: RequestId,

                        @Column(name="request_by")
                        @Enumerated(EnumType.STRING)
                         var requestBy: RequestType,

                        @Column(name="reason")
                         var reason:String,

                        @Column(name="center_id")
                         var centerId:CenterId,

                        @Column(name="center_name")
                         var centerName:String,

                        @Column(name="requester_id")
                         var requesterId :String,

                       @Column(name="requester_name")
                         var requesterName :String,

                       @Column(name="request_date")
                        var requestDate: LocalDate,

                        @Column(name="resolved_date")
                         var resolvedDate: LocalDate?,

                        @Column(name="resolved")
                        var resolved:Boolean?,

                       @Column(name="how_to_resolved")
                        var howToResolved :String?,

                       @Column(name="resolved_by")
                       @Enumerated(EnumType.STRING)
                        var resolvedBy: ResolvedBy?,

                       @Column(name="resolver_id")
                        var resolverId:String?,

                       @Column(name="reason_for_not_resolved")
                       var reasonForNotResolved:String?





){

    fun saveVisReq(){
        this.requestId=RequestId()
        this.resolved=false

    }

    fun resolved(dto:ResolvedDTO){

        this.resolved=dto.resolved
        this.resolvedDate=dto.resolvedDate
        this.howToResolved=dto.howToResolved
        this.resolvedBy=dto.resolvedBy
        this.resolverId=dto.resolverId
        this.reasonForNotResolved=dto.reasonForNotResolved
    }

    fun update(visitingRequest: VisitingRequest){
        this.resolved=false
        this.reason=visitingRequest.reason
    }
}