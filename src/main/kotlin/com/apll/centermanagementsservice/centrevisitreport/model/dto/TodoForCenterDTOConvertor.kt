package com.apll.centermanagementsservice.centrevisitreport.model.dto

import com.apll.centermanagementsservice.centrevisitreport.model.TodoForCenter
import com.apll.centermanagementsservice.centrevisitreport.model.TodoForCenterId

class TodoForCenterDTOConvertor {

    object Convertor {

        fun modelToDTOConvertor(todoForCenter: TodoForCenter?): TodoForCenterDTO?{
            if(todoForCenter!=null) {
                var todocenterDto = TodoForCenterDTO(todoForCenter.todoForCenterId.todoForCenterId,
                        TodoListCenterDTOConvertor.Convertor.modelListToDtoList(todoForCenter.todoListCenterList))
                return todocenterDto

            }
            return null

        }


        fun dtoToModelConvertor(todoForCenterDTO: TodoForCenterDTO?): TodoForCenter? {
            if(todoForCenterDTO!=null) {
                var id = TodoForCenterId()
                id.todoForCenterId = todoForCenterDTO.todoForCenterId!!

                var todoForCenter = TodoForCenter(
                        id,
                        TodoListCenterDTOConvertor.Convertor.dtoListToModelLiost(todoForCenterDTO.todoListCenterList!!))
                return todoForCenter
            }
            return null
        }
        fun modelListToDTOListConvertorList(todoForCenterList:List< TodoForCenter>): List<TodoForCenterDTO> {
            var todocenterDtoList= ArrayList<TodoForCenterDTO>()
           for(todoForCenter in todoForCenterList){
               var todoForDto= modelToDTOConvertor(todoForCenter)
               todocenterDtoList.add(todoForDto!!)

           }
            return todocenterDtoList
        }

        fun dtoListToModelListConvertorList(todoForCenterDTOList: List<TodoForCenterDTO>): List<TodoForCenter> {
           var todoForCenterList=ArrayList<TodoForCenter>()
            for (dto in todoForCenterDTOList) {


                var todoForCenter = dtoToModelConvertor(dto)

                todoForCenterList.add(todoForCenter!!)
            }

            return todoForCenterList
        }

    }

}

