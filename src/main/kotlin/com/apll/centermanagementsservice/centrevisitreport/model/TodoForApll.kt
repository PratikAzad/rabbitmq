package com.apll.centermanagementsservice.centrevisitreport.model



import com.apll.centermanagementsservice.centrevisitreport.model.dto.TodoListApllResolvedDto
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import javax.persistence.*

@Entity
@Table(name="todo_for_apll")
data class TodoForApll(
        @EmbeddedId
        @Column(name="todo_for_apll_id")
        var todoForApllId: TodoForApllId,



        @OrderColumn(name="todo_for_apll_order")
        @OneToMany(cascade = arrayOf(CascadeType.ALL),fetch=FetchType.EAGER)
        @Fetch(value = FetchMode.SUBSELECT)
        @JoinTable(name="todo_for_apll_todo_list_apll",
               joinColumns = arrayOf(JoinColumn(name = "todo_for_apll_id")),
               inverseJoinColumns = arrayOf(JoinColumn(name = "todo_list_apll_id")))

        var todoListApllList:List<TodoListApll>)

{
        fun idInitializer() {
            this.todoForApllId = TodoForApllId()
            this.todoListApllList.forEach {
                it.todoListApllId = TodoListApllId()


            }
        }
        fun updateIdinitilizer(){

                this.todoListApllList.forEach {
                    it.todoListApllId = TodoListApllId()
                }
        }


    fun resolvedTodoListApll(dto:TodoListApllResolvedDto){
        this.todoListApllList.stream().filter { x->x.todoListApllId!!.todoListApllId.equals(dto.todoListApllId) }.findAny().get().resolveTodoListApll(dto)

    }


    fun resolvedTodoListAplls(dtos:List<TodoListApllResolvedDto>){
        this.todoListApllList.forEach {
            var dtoapll=dtos.stream().filter { x->x.todoListApllId.equals(it.todoListApllId!!.todoListApllId) }.findAny()
            if(dtoapll.isPresent){
                it.resolveTodoListApll(dtoapll.get())
            }
        }
    }


    fun updateTodoApll(todoForApll: TodoForApll?){

        this.todoListApllUpdate(todoForApll?.todoListApllList)




      /*  if(this.todoForApllId.todoForApllId==null || this.todoForApllId.todoForApllId.equals("")){
            this.todoForApllId=TodoForApllId()
        }else {
            this.todoListApllUpdate(todoForApll?.todoListApllList)
        }*/

    }


    fun todoListApllUpdate(todoListApllSet:List<TodoListApll>?):Boolean?{
        if(todoListApllSet!=null) {

            var updateTodoListApllIds = HashSet<String>()

            //Create Todo-List-Apll
            var newTodoListApll = ArrayList<TodoListApll>()

            for (todoApll in todoListApllSet) {
                if (todoApll.todoListApllId!!.todoListApllId != null && todoApll.todoListApllId!!.todoListApllId.equals("")) {
                    todoApll.todoListApllId = TodoListApllId()
                    newTodoListApll.add(todoApll)

                }
            }
            var todoListApllMap: Map<String, TodoListApll> = todoListApllSet.map { x -> x.todoListApllId!!.todoListApllId to x }.toMap()

            //Update Todo-List-Apll
            this.todoListApllList.filter { x -> todoListApllMap.containsKey(x.todoListApllId!!.todoListApllId) }.toList().forEach { x ->
                updateTodoListApllIds.add(x.todoListApllId!!.todoListApllId)
                x.updateTodoListApll(todoListApllMap.get(x.todoListApllId!!.todoListApllId)!!)
            }

            //Delete Todo-List-Apll
            this.todoListApllList = this.todoListApllList.filter { x -> updateTodoListApllIds.contains(x.todoListApllId!!.todoListApllId) }


            if (newTodoListApll != null && newTodoListApll.size > 0) {


                var todoApllList = ArrayList(this.todoListApllList)
                todoApllList.addAll(newTodoListApll)
                this.todoListApllList = todoApllList
            }
            return true

        }
        return null

    }


}


