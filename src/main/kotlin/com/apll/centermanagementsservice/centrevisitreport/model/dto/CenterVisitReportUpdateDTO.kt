package com.apll.centermanagementsservice.centrevisitreport.model.dto

import com.apll.centermanagementsservice.centrevisitreport.model.CenterVisitReportStatus
import io.swagger.annotations.ApiModelProperty
import javax.validation.Valid
import javax.validation.constraints.NotNull

data class CenterVisitReportUpdateDTO (

        @ApiModelProperty(name="centerVisitReportId",notes="centerVisitReportId for CenterVisitReportUpdateDTO")
        var centerVisitReportId:String?,

        @get:Valid
        @ApiModelProperty(name="dropOutDetails",notes=" dropOutDetails for CenterVisitReportUpdateDTO")
        var dropOutDetails:List<DropOutDetailsDTO>?,


        @get:Valid
        @ApiModelProperty(name="runningBatchStatus",notes=" runningBatchStatus for CenterVisitReportUpdateDTO")
        var runningBatchStatus:List<RunningBatchStatusDTO>?,

        @get:Valid
        @ApiModelProperty(name="stockStatus",notes=" stockStatus for CenterVisitReportUpdateDTO")
        var stockStatus:List<StockStatusDTO>?,

        @get:Valid
        @ApiModelProperty(name="completedBatchStatus",notes="completedBatchStatus for CenterVisitReportUpdateDTO")
        var completedBatchStatus:List<CompletedBatchStatusDTO>?,


      /*  @get:Valid
        @ApiModelProperty(name="studentFeedbackReport",notes="studentFeedbackReport for CenterVisitReportUpdateDTO")
        var studentFeedbackReport:List<StudentFeedbackReportDTO>?,
*/
        @get:Valid
        @ApiModelProperty(name="todoForCenterdto",notes="todoForCenterdto for CenterVisitReportUpdateDTO")
        var todoForCenterdto:TodoForCenterDTO?,

        @get:Valid
        @ApiModelProperty(name="todoForAplldto",notes="todoForAplldto for CenterVisitReportUpdateDTO")
        var todoForAplldto:TodoForApllDTO?,

        @get:Valid
        @ApiModelProperty(name="infrastructureDTO",notes="infrastructureDTO for CenterVisitReportUpdateDTO")
        var infrastructureDTO: List<CvrInfrastructureDTO>?,


        @get:NotNull(message = "centerVisitReportStatus can not be NULL/EMPTY")
        @ApiModelProperty(name="centerVisitReportStatus",notes=" centerVisitReportStatus for CenterVisitReportUpdateDTO")
        var centerVisitReportStatus: CenterVisitReportStatus?
        )
