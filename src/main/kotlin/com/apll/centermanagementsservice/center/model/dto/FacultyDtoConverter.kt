package  com.apll.centermanagementsservice.center.model.dto

import com.apll.centermanagementsservice.center.model.FacultyId
import com.apll.centermanagementsservice.center.model.FacultyState
import kotlin.collections.HashSet


class FacultyDtoConverter{
    object Converter {

        fun modelToDtoConvert(state: FacultyState): FacultyStateDto {
            var dto = FacultyStateDto(
                    state.facultyId.facultyId,
                    state.facultyName,
                    state.qualification,
                    state.contactNumber,
                    state.technicalSpecialization,
                    state.experience,
                    state.emailId,
                    state.doorNo,
                    state.streetName,
                    state.areaName,
                    state.landmark,
                    state.city,
                    state.state,
                    state.zipCode
            )

            return dto
        }


        fun dtoToModelConvert(dto: FacultyStateDto): FacultyState {

            var id:String?=dto.facultyId
            if(id==null){ id="" }

            var state = FacultyState(
                    FacultyId(id),
                    dto.facultyName!!,
                    dto.qualification!!,
                    dto.contactNumber!!,
                    dto.doorNo!!,
                    dto.streetName!!,
                    dto.areaName!!,
                    dto.landmark!!,
                    dto.city!!,
                    dto.state!!,
                    dto.zipCode!!,
                    dto.technicalSpecialization!!,
                    dto.experience!!,
                    dto.emailId!!)
            return state
        }





        fun dtoToModelConvertSet(facultyStateDTOList:Set<FacultyStateDto>):Set<FacultyState>{

            var facultyStateList=HashSet<FacultyState>()

            for (dto in facultyStateDTOList){
                var facultyDto=dtoToModelConvert(dto)
                facultyStateList.add(facultyDto)
            }
            return facultyStateList
        }


        fun modelToDtoConvertSet(facultyStateList: Set<FacultyState>):Set<FacultyStateDto>{

            var facultyStateDtoList=HashSet<FacultyStateDto>()

            for (state in facultyStateList){
                facultyStateDtoList.add(modelToDtoConvert(state))
            }
            return facultyStateDtoList
        }
    }
}