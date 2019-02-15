package com.apll.centermanagementsservice.regionAndsubRegion.model


import javax.persistence.*


@Entity
@Table(name = "sub_region")
class SubRegion {

    @EmbeddedId
    @Column(name = "sub_region_id")
     lateinit var subRegionId: SubRegionId

    @Column(name = "region_id")
    var regionId: String = " "

    @Column(name = "region_name")
    var regionName: String = " "

    @Column(name = "sub_region_name")
    var subRegionName: String = " "

    @Column(name = "city")
    var city: String = " "


    /*@JsonIgnore
   @JoinColumn(name = "region_id" ,nullable = false)
   @ManyToOne(cascade = arrayOf(CascadeType.ALL), optional = false)
   @OnDelete(action = OnDeleteAction.CASCADE)
   var region: Region ? = null
       get() = field
       set(value) {
           field = value
       }*/

    constructor()
    constructor(subRegionId: SubRegionId, regionName: String, subRegionName: String, city: String) {
        this.subRegionId = subRegionId
        this.regionName = regionName
        this.subRegionName = subRegionName
        this.city = city
    }

    constructor(subRegionId: SubRegionId, subRegionName: String, city: String) {
        this.subRegionId = subRegionId
        this.subRegionName = subRegionName
        this.city = city
    }

    fun updateSubRegion(subRegion: SubRegion){
        this.subRegionName=subRegion.subRegionName
        this.city=subRegion.city
    }

}




