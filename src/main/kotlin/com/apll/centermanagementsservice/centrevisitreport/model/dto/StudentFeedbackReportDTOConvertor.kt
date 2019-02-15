/*
package com.apll.centermanagementsservice.centrevisitreport.model.dto

import com.apll.centermanagementsservice.centrevisitreport.model.StudentFeedbackReport
import com.apll.centermanagementsservice.centrevisitreport.model.StudentFeedbackReportId

class StudentFeedbackReportDTOConvertor {


    object convertor{

        fun dtoToModelConvert(studentFeedbackReportDTO: StudentFeedbackReportDTO): StudentFeedbackReport {

            var sfrId= StudentFeedbackReportId(studentFeedbackReportDTO.studentFeedbackReportId!!)

            var studentFeedbackReport= StudentFeedbackReport(
                    sfrId,
                    studentFeedbackReportDTO.nameOfStudent,
                    studentFeedbackReportDTO.id,
                    studentFeedbackReportDTO.batchNo,
                    studentFeedbackReportDTO.feedbackDetails
            )
            return studentFeedbackReport
        }


       fun modelToDtoConvert(studentFeedbackReport: StudentFeedbackReport): StudentFeedbackReportDTO {


           var studentFeedbackReportDTO= StudentFeedbackReportDTO(
                   studentFeedbackReport.studentFeedbackReportId!!.studentFeedbackReportId,
                   studentFeedbackReport.nameOfStudent!!,
                   studentFeedbackReport.id!!,
                   studentFeedbackReport.batchNo!!,
                   studentFeedbackReport.feedbackDetails!!)
           return studentFeedbackReportDTO
       }


        fun dtoToModelConverterList(dtos:List<StudentFeedbackReportDTO>):List<StudentFeedbackReport>{
            var studentFeedbackReportList=ArrayList<StudentFeedbackReport>()

            for(dto in dtos){
                studentFeedbackReportList.add(dtoToModelConvert(dto))
            }
            return studentFeedbackReportList
        }

        fun modelToDtoConverterList(studentFeedbackReport:List<StudentFeedbackReport>):List<StudentFeedbackReportDTO>{
            var studentFeedbackReportDTOList=ArrayList<StudentFeedbackReportDTO>()

            for(sfr in studentFeedbackReport){
                studentFeedbackReportDTOList.add(modelToDtoConvert(sfr))
            }
            return studentFeedbackReportDTOList
        }


    }
}*/
