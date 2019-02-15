package com.apll.centermanagementsservice.schedulemanagement.model

import com.apll.centermanagementsservice.schedulemanagement.model.dto.RejectedDto
import io.vavr.control.Either
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.time.LocalDate
import javax.persistence.*
import kotlin.Exception

@Entity
@Table(name = "front_end_schedule")
class FrontEndSchedule{

    @EmbeddedId
    @Column(name = "front_end_schedule_event_id")
    var frontEndScheduleEventId=FrontEndScheduleEventId()

    @Column(name = "front_end_schedule_id")
    var frontEndScheduleId= FrontEndScheduleId()

    @Column(name="yearmonth")
    var yearmonth=LocalDate.now()

    @Column(name = "front_end_id")
    var frontEndId=""

    @Column(name = "front_end_name")
    var frontEndName:String? = ""

    @Column(name = "fe_schedule_status")
    @Enumerated(EnumType.STRING)
    var feScheduleStatus:FEScheduleStatus=FEScheduleStatus.DEFAULT

    @Column(name = "updated_by")
    var  updatedBy: String=""

    @Column(name = "operation_type")
    @Enumerated(EnumType.STRING)
    lateinit var operationType: OperationType

    @Column(name = "updated_date")
    var updatedDate=LocalDate.now()

    @Column(name = "version")
    var version:Int=0

    @Column(name = "employee_type")
    @Enumerated(EnumType.STRING)
    lateinit var employeeType:EmployeeType

    @Column(name = "reason_for_update")
    var reasonForUpdate:String?=""

    @Column(name = "message")
    var messageForReject:String?=""

    @Column(name="rejected_date")
    var rejectedDate:LocalDate?=null

    @Column(name=" head_admin_id")
    var headAdminId:String?=""

    @OneToMany(cascade = arrayOf(CascadeType.ALL),fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "schedule_front_end_schedule",
            joinColumns = arrayOf(JoinColumn(name = "front_end_Schedule_event_id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "schedule_event_id")))
    var schedules: List<Schedule> = ArrayList()

    constructor(frontEndSchedule: FrontEndSchedule,employeeType: EmployeeType) {
        var newSchedule=ArrayList<Schedule>()
        frontEndSchedule.schedules.forEach{newSchedule.add(Schedule(it))}

        this.frontEndScheduleId = frontEndSchedule.frontEndScheduleId
        this.version = frontEndSchedule.version
        this.yearmonth = frontEndSchedule.yearmonth
        this.frontEndId = frontEndSchedule.frontEndId
        this.frontEndName = frontEndSchedule.frontEndName
        this.employeeType = employeeType
        this.schedules = newSchedule
    }

    constructor()
    //creating FrontEnd Schedule
    constructor(frontEndScheduleId: FrontEndScheduleId,frontEndName: String?, yearmonth: LocalDate, frontEndId: String,/*employeeType: EmployeeType,*/
                schedules: ArrayList<Schedule>, feScheduleStatus: FEScheduleStatus,reasonForUpdate:String?, messageForReject:String?, rejectedDate:LocalDate?, headAdminId:String?) {
        this.frontEndScheduleId=frontEndScheduleId
        this.frontEndName = frontEndName
        this.yearmonth = yearmonth
        this.frontEndId = frontEndId
        //this.employeeType = employeeType
        this.schedules = schedules
        this.feScheduleStatus=feScheduleStatus
        this.reasonForUpdate=reasonForUpdate
        this.messageForReject=messageForReject
        this.rejectedDate=rejectedDate
        this.headAdminId=headAdminId

    }

    constructor(frontEndScheduleId: String,schedules: ArrayList<Schedule>,frontEndId: String){
        this.frontEndId=frontEndId
        this.employeeType= EmployeeType.FRONT_END
        this.frontEndScheduleId.frontEndScheduleId=frontEndScheduleId
        this.schedules = schedules
    }

    constructor(frontEndScheduleId: String, schedules: ArrayList<Schedule>){
        this.frontEndScheduleId.frontEndScheduleId=frontEndScheduleId
        this.schedules=schedules
    }


    fun idInitializer():Either<Exception,Boolean>{
            this.frontEndScheduleId= FrontEndScheduleId()
            this.frontEndScheduleEventId=FrontEndScheduleEventId()
            this.operationType=OperationType.INSERT
            this.updatedDate= LocalDate.now()
            this.updatedBy=this.frontEndId
            this.version=0
            this.employeeType=EmployeeType.FRONT_END
            this.schedules.forEach{it.scheduleEventId=ScheduleEventId()}

           this.feScheduleStatus=FEScheduleStatus.DEFAULT
            this.schedules.forEach{
                if(!(it.scheduleOnWhichPurpose.equals(TypeOfRequest.CUSTOM_REQUEST)) &&
                        it.requestId.equals(null)){
                    return Either.left(Exception("Request-Id can't be NULL."))
                }

                it.scheduleEventId=ScheduleEventId()
                it.scheduleId=ScheduleId()
                it.isScheduleCompleted=false
            }
        return Either.right(true)
       }



    fun updateSchedule(schedulesList: ArrayList<Schedule>,updatedBy:String,reasonForUpdate:String?):Either<Exception,Boolean>{
        var updateSid=HashSet<String>()

        //Add Schedule
        val newlyAdded=schedulesList.filter { x-> x.scheduleId.scheduleId.equals("") }
                .map { x->
                    x.scheduleId=ScheduleId()
                    x.isScheduleCompleted=false
                    x
                }

        //checking validation
        if(newlyAdded.size>0) {
            newlyAdded.forEach {
                if (!(it.scheduleOnWhichPurpose.equals(TypeOfRequest.CUSTOM_REQUEST)) &&
                        it.requestId.equals(null)) {
                    return Either.left(Exception("Request-Id can't be NULL."))
                }
            }
        }

        val newSchedules:Map<String,Schedule> = schedulesList.
                map { x->x.scheduleId.scheduleId to x }.toMap()

        //Update
        this.schedules.
                filter { x->newSchedules.containsKey(x.scheduleId.scheduleId) }.
                forEach{x->
                    updateSid.add(x.scheduleId.scheduleId)
                    if (!x.isScheduleCompleted.equals(true)) {
                        x.updateSchedule(newSchedules.get(x.scheduleId.scheduleId)!!)
                    }
                }

        //Delete
        this.schedules=this.schedules.
                filter { x->updateSid.contains(x.scheduleId.scheduleId) }


        val list=ArrayList(this.schedules)
        list.addAll(newlyAdded)
        this.schedules=list

        if(updateSid.size>0 || newSchedules.size>0){

            //If head admin is updating. Then we fEScheduleStatus with update.
            if(employeeType.equals(EmployeeType.HEAD_ADMIN)){
                this.feScheduleStatus = FEScheduleStatus.APPROVE
            }else{
                this.feScheduleStatus=FEScheduleStatus.UPDATE
            }
            this.frontEndScheduleEventId=FrontEndScheduleEventId()
            this.schedules.forEach{it.scheduleEventId=ScheduleEventId()}
            this.operationType=OperationType.UPDATE
            this.version=this.version+1
            this.updatedDate= LocalDate.now()
            this.reasonForUpdate=reasonForUpdate
            this.updatedBy=updatedBy
        }
        return Either.right(true)
    }

    fun approve() {
        this.feScheduleStatus = FEScheduleStatus.APPROVE
    }


    fun rejectFES(rejectedDto: RejectedDto) {
        this.messageForReject = rejectedDto.messageForReject
        this.rejectedDate = LocalDate.now()
        this.headAdminId = rejectedDto.headAdminId
        this.feScheduleStatus = FEScheduleStatus.REJECT
        this.employeeType = EmployeeType.HEAD_ADMIN
    }

    fun isScheduleCompleted(requestId:String):Either<Exception,String>{

        var completedSchedule=this.schedules.stream().filter { x->requestId.equals(x.requestId) }.findAny()
        if(!completedSchedule.isPresent){
            return Either.left(Exception("Schedule not found."))
        }
        completedSchedule.get().isScheduleCompleted=true
        return Either.right("Schedules Completed")
    }

    fun completedBDASchedule(requestId:String, scheduleDate:LocalDate):Either<Exception,String>{

        var completedSchedule=this.schedules.stream().
                filter { x->(requestId.equals(x.requestId) && scheduleDate.isEqual(x.scheduleDate))}.findAny()
        if(!completedSchedule.isPresent){
            return Either.left(Exception("Schedule not found."))
        }
        completedSchedule.get().isScheduleCompleted=true
        return Either.right("Schedules Completed")
    }
    fun completedCustomSchedule(customScheduleId: String):Either<Exception,String>{
        var completedSchedule=this.schedules.stream().
                filter { x->(customScheduleId.equals(x.scheduleId.scheduleId))}.findAny()
        if(!completedSchedule.isPresent){
            return Either.left(Exception("Schedule not found."))
        }
        completedSchedule.get().isScheduleCompleted=true
        return Either.right("Schedules Completed")
    }

  /*  fun completedNewCenterSchedule(requestId:String,scheduleId:String):Either<Exception,String>{
        var  completedNewCenterSchedule=this.schedules.stream().filter{x->(requestId.equals(x.requestId)&& scheduleId.equals(x.scheduleId) )}.findAny()
        if(!completedNewCenterSchedule.isPresent){
            return Either.left(Exception("Schedule not found."))

        }
        completedNewCenterSchedule.get().isScheduleCompleted=true
        return Either.right("Schedules Completed")
    }*/
}







