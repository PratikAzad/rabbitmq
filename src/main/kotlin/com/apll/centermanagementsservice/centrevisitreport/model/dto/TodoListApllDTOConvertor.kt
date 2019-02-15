package com.apll.centermanagementsservice.centrevisitreport.model.dto

import com.apll.centermanagementsservice.centrevisitreport.model.TodoListApll
import com.apll.centermanagementsservice.centrevisitreport.model.TodoListApllId

class TodoListApllDTOConvertor {
    object Convertor{
        fun  modelToDtoConvertor(todoListApll:TodoListApll):TodoListApllDTO{
            var todoListApllDTO=TodoListApllDTO(todoListApll.todoListApllId!!.todoListApllId,
                    todoListApll.issueName,
                    todoListApll.raiseDate,
                    todoListApll.issueOfStatus,
                    todoListApll.resolvedBy,
                    todoListApll.resolverId,
                    todoListApll.resolvedDate,
                    todoListApll.howToResolved)
            return todoListApllDTO
        }

        fun dtoToModelConvertor(todoListApllDTO: TodoListApllDTO):TodoListApll{
            var todoapllId=TodoListApllId()
            todoapllId.todoListApllId=todoListApllDTO.todoListApllId!!
            var todoListApll=TodoListApll(
                    todoapllId,
                    todoListApllDTO.issueName,
                    todoListApllDTO.raiseDate,
                    todoListApllDTO.issueOfStatus,
                    todoListApllDTO.resolvedBy,
                    todoListApllDTO.resolverId,
                    todoListApllDTO.resolvedDate,
                    todoListApllDTO.howToResolved)

            return todoListApll
        }

        fun modelListToDtoList(todoListapllList:List<TodoListApll>):List<TodoListApllDTO>{
            var todoListApllDtoList=ArrayList<TodoListApllDTO>()
            for(todolistapll in todoListapllList){
                if(todolistapll!=null) {
                    var dto = modelToDtoConvertor(todolistapll)
                    todoListApllDtoList.add(dto)
                }
            }
            return todoListApllDtoList
        }
        fun dtoListToModelList(todoListApllDtoList:List<TodoListApllDTO>):List<TodoListApll>{
            var todolistApllList=ArrayList<TodoListApll>()
            for(dto in todoListApllDtoList){
                var todoListApll= dtoToModelConvertor(dto)
                todolistApllList.add(todoListApll)
            }
            return todolistApllList
        }
    }
}
