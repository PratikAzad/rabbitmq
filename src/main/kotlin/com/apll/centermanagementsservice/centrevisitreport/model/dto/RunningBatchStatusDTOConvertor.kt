package com.apll.centermanagementsservice.centrevisitreport.model.dto

import com.apll.centermanagementsservice.centrevisitreport.model.RunningBatchStatus
import com.apll.centermanagementsservice.centrevisitreport.model.RunningBatchStatusId

class RunningBatchStatusDTOConvertor{

     object Convertor {
         fun modelToDtoConvertor(runningBatchStatus: RunningBatchStatus): RunningBatchStatusDTO {
             var statusdto = RunningBatchStatusDTO(runningBatchStatus.runningBatchStatusId!!.runningBatchStatusId,
                     runningBatchStatus.batchNo!!,
                     runningBatchStatus.days!!,
                     runningBatchStatus.time!!,
                     runningBatchStatus.startdate!!,
                     runningBatchStatus.facultyName!!,
                     runningBatchStatus.facultyMobNo!!,
                     runningBatchStatus.completedModule,
                     runningBatchStatus.currentModule!!,
                     runningBatchStatus.noOfStudsAtBeg!!
                     )

             return statusdto
         }

         fun dtoToModelConvertor(runningBatchStatusDTO: RunningBatchStatusDTO): RunningBatchStatus {
             var id = RunningBatchStatusId(runningBatchStatusDTO.runningBatchStatusId!!)

             var status = RunningBatchStatus(id,
                     runningBatchStatusDTO.batchNo,
                     runningBatchStatusDTO.days,
                     runningBatchStatusDTO.time,
                     runningBatchStatusDTO.startdate,
                     runningBatchStatusDTO.facultyName,
                     runningBatchStatusDTO.facultyMobNo,
                     runningBatchStatusDTO.completedModule,
                     runningBatchStatusDTO.currentModule,
                     runningBatchStatusDTO.noOfStudsAtBegng
                     )
             return status

         }


         fun dtoToModelConverterList(dtos:List<RunningBatchStatusDTO>):List<RunningBatchStatus>{
             var runningBatchStatusLst=ArrayList<RunningBatchStatus>()

             for(dto in dtos){
                 runningBatchStatusLst.add(dtoToModelConvertor(dto))
             }
             return runningBatchStatusLst
         }

         fun modelToDtoConverterList(runningBatchStatus:List<RunningBatchStatus>):List<RunningBatchStatusDTO>{
             var runningBatchStatusDtoLst=ArrayList<RunningBatchStatusDTO>()

             for(cbs in runningBatchStatus){
                 runningBatchStatusDtoLst.add(modelToDtoConvertor(cbs))
             }
             return runningBatchStatusDtoLst
         }

     }

}