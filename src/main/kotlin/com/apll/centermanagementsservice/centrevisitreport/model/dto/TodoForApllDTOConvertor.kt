package com.apll.centermanagementsservice.centrevisitreport.model.dto

import com.apll.centermanagementsservice.centrevisitreport.model.TodoForApll
import com.apll.centermanagementsservice.centrevisitreport.model.TodoForApllId

class TodoForApllDTOConvertor {
    object Convertor{
        fun modelToDTOConvertor(todoForApll: TodoForApll?):TodoForApllDTO?{
            if(todoForApll!=null) {

                var todoForApllDTO = TodoForApllDTO(todoForApll.todoForApllId.todoForApllId,
                        TodoListApllDTOConvertor.Convertor.modelListToDtoList(todoForApll.todoListApllList))

                return todoForApllDTO
            }
            return null

        }
        fun dtoToModelConvertor(todoForApllDTO: TodoForApllDTO?): TodoForApll? {
            if (todoForApllDTO != null) {
                var id = TodoForApllId()
                id.todoForApllId = todoForApllDTO.todoForApllId!!


                var todoForApll = TodoForApll(
                        id,
                        TodoListApllDTOConvertor.Convertor.dtoListToModelList(todoForApllDTO.todoListApll!!)
                )
                return todoForApll
            }
            return null
        }

        fun modelListToDTOListConvertorList(todoForApllList:List<TodoForApll>):List<TodoForApllDTO>{
            var todoForApllDTOList=ArrayList<TodoForApllDTO>()
           for(todoForApll in todoForApllList){
               var dto= modelToDTOConvertor(todoForApll)
               todoForApllDTOList.add(dto!!)
           }

            return todoForApllDTOList

        }
        fun dtoListToModelListConvertorList(todoForApllDTOList: List<TodoForApllDTO>): List<TodoForApll>{

            var  todoForApllList=ArrayList<TodoForApll>()
                  for(todoForApllDto in todoForApllDTOList){
                      var todoForApll= dtoToModelConvertor(todoForApllDto)
                      todoForApllList.add(todoForApll!!)
                  }
            return todoForApllList
        }



    }
}
