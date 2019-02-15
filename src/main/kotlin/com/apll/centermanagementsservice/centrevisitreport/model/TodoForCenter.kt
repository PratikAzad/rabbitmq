package com.apll.centermanagementsservice.centrevisitreport.model



import com.apll.centermanagementsservice.centrevisitreport.model.dto.TodoListCenterResolvedDto
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import javax.persistence.*

@Entity
@Table(name="todo_for_center")
data  class TodoForCenter(
        @EmbeddedId
        @Column(name="todo_for_center_id")
        var todoForCenterId: TodoForCenterId,


        @OrderColumn(name = "todo_for_center_order")
        @OneToMany(cascade = arrayOf(CascadeType.ALL),fetch = FetchType.EAGER)
        @Fetch(value = FetchMode.SUBSELECT)
        @JoinTable(name = "todo_for_center_todo_list_center",
                joinColumns = arrayOf(JoinColumn(name = "todo_for_center_id")),
                inverseJoinColumns = arrayOf(JoinColumn(name = "todo_list_center_id")))
        var todoListCenterList:List<TodoListCenter>)
       /* @OneToMany( fetch = FetchType.EAGER ,cascade = arrayOf(CascadeType.ALL),mappedBy = "todoForCenter")

        var todoListCenterList:Set<TodoListCenter>)*/
{

        fun idInitializer() {

                this.todoForCenterId= TodoForCenterId()
                this.todoListCenterList.forEach {
                        it.todoListCenterId = TodoListCenterId()


                }
        }
        fun updateIdInitilizer(){

                this.todoListCenterList.forEach {
                        it.todoListCenterId = TodoListCenterId()


                }
        }



        fun resolvedTodoListCenter(dto:TodoListCenterResolvedDto){
                this.todoListCenterList.stream().filter { x->x.todoListCenterId!!.todoListCenterId.equals(dto.todoListCenterId) }.findAny().get().resolveTodoListCenter(dto)
        }


        fun resolvedTodoListCenters(dtos:List<TodoListCenterResolvedDto>){
                this.todoListCenterList.forEach {
                        var dto= dtos.stream().filter{x->x.todoListCenterId.equals(it.todoListCenterId!!.todoListCenterId)}.findAny()
                        if(dto.isPresent){
                                it.resolveTodoListCenter(dto.get())
                        }
                }
        }



                fun updateTodoCenter(todoForCenter: TodoForCenter?) {
                       this.todoListCenterUpdate(todoForCenter?.todoListCenterList)
                }

        fun todoListCenterUpdate(todoListCenterSet:List<TodoListCenter>?):Boolean?{
                if(todoListCenterSet!=null) {

                        var updateTodoListCenterIds = HashSet<String>()

                        //Create Todo-List-Center
                        var newTodoListCenter = ArrayList<TodoListCenter>()

                        for (todoCenter in todoListCenterSet) {
                                if (todoCenter.todoListCenterId!!.todoListCenterId != null && todoCenter.todoListCenterId!!.todoListCenterId.equals("")) {
                                        todoCenter.todoListCenterId = TodoListCenterId()
                                        newTodoListCenter.add(todoCenter)

                                }
                        }
                        var todoListCenterMap: Map<String, TodoListCenter> = todoListCenterSet.map { x -> x.todoListCenterId!!.todoListCenterId to x }.toMap()

                        //Update Todo-List-Center
                        this.todoListCenterList.filter { x -> todoListCenterMap.containsKey(x.todoListCenterId!!.todoListCenterId) }.toList().forEach { x ->
                                updateTodoListCenterIds.add(x.todoListCenterId!!.todoListCenterId)
                                x.updateTodoListCenter(todoListCenterMap.get(x.todoListCenterId!!.todoListCenterId)!!)
                        }

                        //Delete Todo-List-Apll
                        this.todoListCenterList = this.todoListCenterList.filter { x -> updateTodoListCenterIds.contains(x.todoListCenterId!!.todoListCenterId) }


                        if (newTodoListCenter != null && newTodoListCenter.size > 0) {


                                var todoCenterList = ArrayList(this.todoListCenterList)
                                todoCenterList.addAll(newTodoListCenter)
                                this.todoListCenterList = todoCenterList
                        }
                        return true

                }
                return null
        }


}

