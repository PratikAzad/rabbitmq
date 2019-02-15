package com.apll.centermanagementsservice.schedulemanagement.model


import com.apll.centermanagementsservice.config.LocalDateDeSerializer
import com.apll.centermanagementsservice.config.LocalDateSerializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "schedule")
 class Schedule {


    fun updateSchedule(schedule: Schedule) {
        this.scheduleDate=schedule.scheduleDate
        //this.note=schedule.note
    }

    @EmbeddedId
    @Column(name = "schedule_event_id")
    var scheduleEventId=ScheduleEventId()

    @Column(name = "schedule_id")
    var scheduleId= ScheduleId()

    @JsonDeserialize(using = LocalDateDeSerializer::class)
    @JsonSerialize(using = LocalDateSerializer::class)
    @Column(name = "schedule_date")
     var scheduleDate: LocalDate?=null

    @Column(name = "region_id")
     var regionId: String?=""

    @Column(name = "note")
     var note: String=""

    @Column(name = "schedule_on_which_purpose")
    @Enumerated(EnumType.STRING)
    lateinit var scheduleOnWhichPurpose: TypeOfRequest

    @Column(name = "is_schedule_completed")
     var isScheduleCompleted:Boolean=false

    @Column(name = "visiting_request_id")
     var requestId: String?=""

     /*@JsonIgnore
     @JoinColumn(name = "front_end_schedule_id"*//*, nullable = false,columnDefinition = "VARCHAR(225)"*//*)
     @ManyToOne(cascade = arrayOf(CascadeType.ALL), fetch = FetchType.LAZY, optional = false)
     @OnDelete(action = OnDeleteAction.CASCADE)
     var frontEndSchedule: FrontEndSchedule?=null
         get() = field

         // setter
         set(value) {
             field = value
         }
*/
     constructor()




    //For save schedule
     constructor(regionId: String?, scheduleDate: LocalDate, note: String, scheduleOnWhichPurpose: TypeOfRequest, visitingRequestId: String?) {
        this.regionId=regionId
         this.scheduleDate = scheduleDate
         this.note = note
         this.scheduleOnWhichPurpose = scheduleOnWhichPurpose
         this.requestId = visitingRequestId
     }

    //For Update schedule
    constructor(scheduleId: ScheduleId,scheduleDate: LocalDate, regionId: String?, note: String, scheduleOnWhichPurpose: TypeOfRequest, isScheduleCompleted: Boolean, visitingRequestId: String?) {
        this.scheduleId=scheduleId
        this.scheduleDate = scheduleDate
        this.regionId = regionId
        this.note = note
        this.scheduleOnWhichPurpose = scheduleOnWhichPurpose
        this.isScheduleCompleted = isScheduleCompleted
        this.requestId = visitingRequestId
    }

    constructor(schedule:Schedule){
        this.scheduleEventId=ScheduleEventId()
        this.scheduleId=schedule.scheduleId
        this.scheduleDate = schedule.scheduleDate
        this.regionId = schedule.regionId
        this.note = schedule.note
        this.scheduleOnWhichPurpose = schedule.scheduleOnWhichPurpose
        this.isScheduleCompleted = schedule.isScheduleCompleted
        this.requestId =schedule.requestId
    }


 }




