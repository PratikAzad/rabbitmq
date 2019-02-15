package com.apll.centermanagementsservice.businessdevelopmentactivity.model.image

import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name="bda_visit_image")
data class BDAVisitImage(
        @EmbeddedId
        @Column(name="bda_visit_image_id")
        var bdaVisitImageId: BDAVisitImageId,

        @Column(name="bda_visit_image_view")
        var bdaVisitImageView:String?
) {
      /*  fun imginitilizer(){
                this.bdaVisitImageId=BDAVisitImageId()
        }*/

        fun updateBDAVisitImage(bdaVisitImage: BDAVisitImage){
                this.bdaVisitImageId=bdaVisitImage.bdaVisitImageId
                this.bdaVisitImageView=bdaVisitImage.bdaVisitImageView

        }

}