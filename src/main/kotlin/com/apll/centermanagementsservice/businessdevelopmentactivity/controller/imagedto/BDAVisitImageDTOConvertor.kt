package com.apll.centermanagementsservice.businessdevelopmentactivity.controller.imagedto

import com.apll.centermanagementsservice.businessdevelopmentactivity.model.image.BDAVisitImage
import com.apll.centermanagementsservice.businessdevelopmentactivity.model.image.BDAVisitImageId

class BDAVisitImageDTOConvertor {

    object Convertor{
        fun  modelToDtoConvertor(bdaVisitImage: BDAVisitImage): BDAVisitImageDTO {
            var bdaVisitImageDto= BDAVisitImageDTO(
                    bdaVisitImage.bdaVisitImageId.bdaVisitImageId,
                    bdaVisitImage.bdaVisitImageView
            )
            return bdaVisitImageDto
        }

        fun dtoToModelConvertor(bdaVisitImageDto: BDAVisitImageDTO):BDAVisitImage{
            var id=BDAVisitImageId()
            if(!bdaVisitImageDto.bdaVisitImageId.equals("")||bdaVisitImageDto.bdaVisitImageId==null) {
                id.bdaVisitImageId = bdaVisitImageDto.bdaVisitImageId!!
            }
            var bdaVisitImage=BDAVisitImage(
                    id,
                    bdaVisitImageDto.bdaVisitImageView)
            return bdaVisitImage
        }

        fun modelListToDtoList(bdaVisitImageList: List<BDAVisitImage>):List<BDAVisitImageDTO>{
            var  bdaVisitImageDtoList=ArrayList<BDAVisitImageDTO>()
            for(bdaVisitImage in bdaVisitImageList)
            {
                var bdaVisitImageDto= modelToDtoConvertor(bdaVisitImage)
                bdaVisitImageDtoList.add(bdaVisitImageDto)
            }
            return bdaVisitImageDtoList
        }
        fun dtoListToModelList(bdaVisitImageDtoList: List<BDAVisitImageDTO>):List<BDAVisitImage>{
            var bdaVisitImageList=ArrayList<BDAVisitImage>()
            for(bdaVisitImageDto in bdaVisitImageDtoList){
                var bdaVisitImage= dtoToModelConvertor(bdaVisitImageDto)
                bdaVisitImageList.add(bdaVisitImage)
            }
            return bdaVisitImageList
        }




    }


}