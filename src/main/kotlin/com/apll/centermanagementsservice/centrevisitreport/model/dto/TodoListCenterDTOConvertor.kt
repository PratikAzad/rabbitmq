package com.apll.centermanagementsservice.centrevisitreport.model.dto

import com.apll.centermanagementsservice.centrevisitreport.model.TodoListCenter
import com.apll.centermanagementsservice.centrevisitreport.model.TodoListCenterId

class TodoListCenterDTOConvertor {

    object Convertor{
        fun  modelToDtoConvertor(todoListCenter: TodoListCenter):TodoListCenterDTO{

            var todoListCenterDto=TodoListCenterDTO(todoListCenter.todoListCenterId!!.todoListCenterId,
                    todoListCenter.issueName,
                    todoListCenter.raiseDate,
                    todoListCenter.issueOfStatus,
                    todoListCenter.resolvedBy,
                    todoListCenter.resolverId,
                    todoListCenter.resolvedDate,
                    todoListCenter.howToResolved
                   )
            return todoListCenterDto
        }

        fun dtoToModelConvertor(todoCenterDto:TodoListCenterDTO):TodoListCenter{
            var tid=TodoListCenterId()
            tid.todoListCenterId=todoCenterDto.todoListCenterId

            var todoListCenter=TodoListCenter(tid,
                    todoCenterDto.issueName,
                    todoCenterDto.raiseDate,
                    todoCenterDto.issueOfStatus,
                    todoCenterDto.resolvedBy,
                    todoCenterDto.resolverId,
                    todoCenterDto.resolvedDate,
                    todoCenterDto.howToResolved
                   )
            return todoListCenter
        }

        fun modelListToDtoList(todoListCenterList:List<TodoListCenter>):List<TodoListCenterDTO>{

            var  todoListCenterDtoList=ArrayList<TodoListCenterDTO>()
            for(reason in todoListCenterList){
                if(reason!=null) {
                    var todoListCenterDTO = modelToDtoConvertor(reason)
                    todoListCenterDtoList.add(todoListCenterDTO)
                }
            }
            return todoListCenterDtoList
        }
        fun dtoListToModelLiost(todoListCenterDto:List<TodoListCenterDTO>):List<TodoListCenter>{
            var todoListCenterList=ArrayList<TodoListCenter>()
            for(reasonDto in todoListCenterDto){
                var todoListCenter= dtoToModelConvertor(reasonDto)
                todoListCenterList.add(todoListCenter)
            }
            return todoListCenterList
        }



    }


}
