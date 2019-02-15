package com.apll.centermanagementsservice.businessdevelopmentactivity.controller.imagedto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@ApiModel("BDAVisitImage" ,description = "Visit Report  for BDAVisitImage",parent = Any::class)
data class BDAVisitImageDTO (
        @ApiModelProperty(name=" bda_visit_image_id",notes=" bda_visit_image_id for Business Development Activity ")
        var bdaVisitImageId: String?,

        @get:NotNull(message = " bda image  can not be NULL")
        @get:NotBlank(message = "bda image must not be blank")
        @get:Size(min=1,message = "bda image  should have Atleast one Image")
        @ApiModelProperty(name="bda_visit_image_view",notes="bda_visit_image_view for Business Development Activity")
        var bdaVisitImageView:String?){
}