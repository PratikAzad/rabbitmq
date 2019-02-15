package com.apll.centermanagementsservice.centrevisitreport.model

import com.apll.centermanagementsservice.centrevisitreport.model.dto.TodoListCenterResolvedDto
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name="todo_list_center")
 class TodoListCenter{
        @EmbeddedId
        @Column(name="todo_list_center_id")
        var todoListCenterId:TodoListCenterId?=null
                set(value){
                        field=value
                }
                get() =field


    @Column(name="issue_name")
        var issueName:String?=null
                set(value){
                        field=value
                }
                get() =field

        @Column(name=" raise_date")
        var raiseDate:LocalDate?=null

            set(value){
                field=value
            }
            get() =field
        @Enumerated(EnumType.STRING)
        @Column(name="issue_of_status")
        var issueOfStatus:ReasonStatus?=null
                set(value){
                        field=value
                }
                get() =field

 /*   @Column(name="resolved")
    var resolved:Boolean?=false

        set(value){
            field=value
        }
        get() =field*/

        @Column(name="resolved_by")
        @Enumerated(EnumType.STRING)
        var resolvedBy:ResolvedBy?=null
                set(value){
                        field=value
                }
                get() =field


        @Column(name="resolver_id")
        var resolverId:String?=null
                set(value){
                        field=value
                }
                get() =field

        @Column(name="resolved_date")
        var resolvedDate:LocalDate?=null
                set(value){
                        field=value
                }
                get() =field

        @Column(name="how_to_resolved")
        var howToResolved:String?=null
            set(value){
                field=value
            }
            get() =field

      /*  @JoinColumn(name="todo_for_center_id"*//*, nullable = false,columnDefinition = "VARCHAR(225)"*//*)
        @ManyToOne(cascade = arrayOf(CascadeType.ALL),fetch = FetchType.LAZY,optional = false)
        @OnDelete(action = OnDeleteAction.CASCADE)
        var todoForCenter:TodoForCenter?=null
                set(value){
                        field=value
                }
                get() =field*/


    constructor(todoListCenterId: TodoListCenterId?, issueName: String?, raiseDate: LocalDate?, issueOfStatus: ReasonStatus?, resolvedBy: ResolvedBy?, resolverId: String?, resolvedDate: LocalDate?, howToResolved:String?) {
        this.todoListCenterId = todoListCenterId
        this.issueName = issueName
        this.raiseDate = raiseDate
        this.issueOfStatus = issueOfStatus

        this.resolvedBy = resolvedBy
        this.resolverId = resolverId
        this.resolvedDate = resolvedDate
        this.howToResolved=howToResolved
    }
    constructor()

    override fun toString(): String {
        return "TodoListCenter()"
    }



    fun resolveTodoListCenter(dto: TodoListCenterResolvedDto) {
        this.resolvedBy = dto.resolvedBy
        this.resolverId = dto.resolverId
        this.resolvedDate = dto.resolvedDate
        this.howToResolved = dto.howToResolved
        this.issueOfStatus = dto.issueOfStatus


    }


    fun updateTodoListCenter(todoListCenter: TodoListCenter){

        this.issueOfStatus=todoListCenter.issueOfStatus
    }




}




