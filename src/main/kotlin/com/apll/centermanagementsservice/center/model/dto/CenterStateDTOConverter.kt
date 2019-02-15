import com.apll.centermanagementsservice.center.model.CenterId
import com.apll.centermanagementsservice.center.model.CenterState
import com.apll.centermanagementsservice.center.model.dto.CenterStateDtoWithSR
import com.apll.centermanagementsservice.center.model.dto.CenterUpdateDTO
import com.apll.centermanagementsservice.center.model.dto.FacultyDtoConverter
import com.apll.centermanagementsservice.center.model.dto.InfrastructureDetailsDTOConvertor

import com.apll.centermanagementsservice.center.model.dto.CenterStateDTO
import com.apll.centermanagementsservice.regionAndsubRegion.controller.regiondto.SubRegionDTO


class   CenterStateDTOConverter {



    object converter {

       /* @Autowired
        lateinit var service: SubRegionService

*/
        fun dtoToModelConvert(centerStateDTO: CenterStateDTO): CenterState {


            var facultyStateList= FacultyDtoConverter.Converter.
                    dtoToModelConvertSet(centerStateDTO.facultyStateDtoList!!)

            var infraStrDetail= InfrastructureDetailsDTOConvertor.convertor.
                    dtoToModelConvert(centerStateDTO.infrastructureDetailsDTO!!)

           var id:String?=centerStateDTO.centerId
           if(id==null || id.equals("")){ id="" }
            var centerState=CenterState(
                    CenterId(id),
                    centerStateDTO.centerName!!,
                    centerStateDTO.nameOfOwner!!,
                    centerStateDTO.contactNumber!!,
                    centerStateDTO.alternateNumber!!,
                    centerStateDTO.houseNumber!!,
                    centerStateDTO.streetName!!,
                    centerStateDTO.areaName!!,
                    centerStateDTO.landmark!!,
                    centerStateDTO.city!!,
                    centerStateDTO.pinCode!!,
                    centerStateDTO.state!!,
                    centerStateDTO.courierService!!,
                    centerStateDTO.emailId!!,
                    centerStateDTO.panDetails!!,
                    centerStateDTO.centerEstablishYear!!,
                    centerStateDTO.serviceTaxNumber!!,
                    centerStateDTO.centerType!!,
                    centerStateDTO.centerStatus!!,
                    facultyStateList,
                    infraStrDetail,
                    centerStateDTO.subRegionId!!)
            return  centerState
        }




      fun  modelToDtoConvert(centerState: CenterState): CenterStateDtoWithSR {

          var facultyStateDtoList=FacultyDtoConverter.Converter.modelToDtoConvertSet(
                                 centerState.facultyList)

          var infraStrucDetail=InfrastructureDetailsDTOConvertor.convertor.modelToDtoConvert(
                                 centerState.infrastructureDetails)

          var centerStateDTO=CenterStateDtoWithSR(
                  centerState.centerId!!.centerId,
                  centerState.centerName,
                  centerState.nameOfOwner,
                  centerState.contactNumber,
                  centerState.alternateNumber,
                  centerState.houseNumber,
                  centerState.streetName,
                  centerState.areaName,
                  centerState.landmark,
                  centerState.city,
                  centerState.pinCode,
                  centerState.state,
                  centerState.courierService,
                  centerState.emailId,
                  centerState.panDetails,
                  centerState.centerEstablishYear,
                  centerState.serviceTaxNumber,
                  centerState.centerType,
                  centerState.centerStatus,
                  facultyStateDtoList,
                  infraStrucDetail,
                  SubRegionDTO()
          )
          return centerStateDTO
      }


        fun dtoToModelConvertForUpdate(centerUpdateDTO: CenterUpdateDTO): CenterState {


            var facultyStateList= FacultyDtoConverter.Converter.
                    dtoToModelConvertSet(centerUpdateDTO.facultyStateDtoList!!)

            var infraStrDetail= InfrastructureDetailsDTOConvertor.convertor.
                    dtoToModelConvert(centerUpdateDTO.infrastructureDetailsDTO!!)

            var id:String?=centerUpdateDTO.centerId
            if(id==null){ id="" }
            var centerState=CenterState(
                    CenterId(id),
                    centerUpdateDTO.centerName!!,
                    "",
                    centerUpdateDTO.contactNumber!!,
                    centerUpdateDTO.alternateNumber!!,
                    centerUpdateDTO.houseNumber!!,
                    centerUpdateDTO.streetName!!,
                    centerUpdateDTO.areaName!!,
                    centerUpdateDTO.landmark!!,
                    centerUpdateDTO.city!!,
                    centerUpdateDTO.pinCode!!,
                    centerUpdateDTO.state!!,
                    centerUpdateDTO.courierService!!,
                    centerUpdateDTO.emailId!!,
                    "",
                    centerUpdateDTO.centerEstablishYear!!,
                    "",
                    centerUpdateDTO.centerType!!,
                    centerUpdateDTO.centerStatus!!,
                    facultyStateList,
                    infraStrDetail,
                    "")
            return  centerState
        }


        fun dtoToModelConvertWithSR(centerStateDtoWithSR: CenterStateDtoWithSR):CenterState{

            var centerId=CenterId()
            if (!centerStateDtoWithSR.centerId.equals("")){
                centerId.centerId= centerStateDtoWithSR.centerId!!
            }

            var facultyStateList=FacultyDtoConverter.Converter.
                    dtoToModelConvertSet(centerStateDtoWithSR.facultyStateDtoList)

            var infraStructureDetail=InfrastructureDetailsDTOConvertor.convertor.
                    dtoToModelConvert(centerStateDtoWithSR.infrastructureDetailsDTO)

            var centerState=CenterState(
                  centerId,
                  centerStateDtoWithSR.centerName,
                  centerStateDtoWithSR.nameOfOwner,
                  centerStateDtoWithSR.contactNumber,
                  centerStateDtoWithSR.alternateNumber,
                  centerStateDtoWithSR.houseNumber,
                  centerStateDtoWithSR.streetName,
                  centerStateDtoWithSR.areaName,
                  centerStateDtoWithSR.landmark,
                  centerStateDtoWithSR.city,
                  centerStateDtoWithSR.pinCode,
                  centerStateDtoWithSR.state,
                  centerStateDtoWithSR.courierService,
                  centerStateDtoWithSR.emailId,
                  centerStateDtoWithSR.panDetails,
                  centerStateDtoWithSR.centerEstablishYear,
                  centerStateDtoWithSR.serviceTaxNumber,
                  centerStateDtoWithSR.centerType,
                  centerStateDtoWithSR.centerStatus,
                  facultyStateList,
                    infraStructureDetail,
                    centerStateDtoWithSR.subRegionDTO.subRegionId!!
            )
            return centerState
        }



        fun modelToDtoConvertWithSR(centerState: CenterState):CenterStateDtoWithSR {

            var facultyStateList = FacultyDtoConverter.Converter.modelToDtoConvertSet(
                    centerState.facultyList)

            var infrastructureDetail = InfrastructureDetailsDTOConvertor.convertor.modelToDtoConvert(
                    centerState.infrastructureDetails)

            //var subRegionDto = service.getSubRegionById(centerState.subregionId)

            var centerStateDtoWithSR = CenterStateDtoWithSR(
                    centerState.centerId.centerId,
                    centerState.centerName,
                    centerState.nameOfOwner,
                    centerState.contactNumber,
                    centerState.alternateNumber,
                    centerState.houseNumber,
                    centerState.streetName,
                    centerState.areaName,
                    centerState.landmark,
                    centerState.city,
                    centerState.pinCode,
                    centerState.state,
                    centerState.courierService,
                    centerState.emailId,
                    centerState.panDetails,
                    centerState.centerEstablishYear,
                    centerState.serviceTaxNumber,
                    centerState.centerType,
                    centerState.centerStatus,
                    facultyStateList,
                    infrastructureDetail,
                    SubRegionDTO())
            return centerStateDtoWithSR
        }



        fun modelToDtoListConvert(centerState: List<CenterState>):List<CenterStateDtoWithSR>{

            var centerStateDTOList=ArrayList<CenterStateDtoWithSR>()

            for (state in centerState){
                var centerStateDTO=CenterStateDTOConverter.converter.modelToDtoConvertWithSR(state)
                centerStateDTOList.add(centerStateDTO)
            }
            return centerStateDTOList
        }

    }

}

