package com.apll.centermanagementsservice.regionAndsubRegion.model


import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import javax.persistence.*

@Entity
@Table(name = "region")
class Region {
    @EmbeddedId
    @Column(name = "region_id")
    lateinit var regionId: RegionId

    @Column(name = "region_name")
    var regionName: String = " "

    @Column(name = "state")
    var state: String = " "

    @OneToMany(cascade = arrayOf(CascadeType.ALL),fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "region_sub_region",
            joinColumns = arrayOf(JoinColumn(name = "region_id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "sub_region_id")))
     lateinit var subRegions:Set<SubRegion>


    constructor()

    constructor(regionId: RegionId, regionName: String, state: String, subRegions: Set<SubRegion>) {
        this.regionId = regionId
        this.regionName = regionName
        this.state = state
        this.subRegions = subRegions
    }


    fun regionUpdate(region: Region){
        this.state=region.state
        this.regionName=region.regionName
        this.subRegions.forEach{
            it.regionName=this.regionName
            it.regionId=this.regionId.regionId
        }

        region.subRegions.forEach{
            it.regionName=this.regionName
            it.regionId=this.regionId.regionId
        }

        var subRegionIds=ArrayList<String>()

        //New SubRegion
        var newSubRegion=region.subRegions.
                map { x->x.subRegionId.subRegionId to x }.toMap()


        //Add SubRegion
        val addSubRegion=region.subRegions.filter {x->x.subRegionId.subRegionId.equals("")}.
                map {x->
                    x.subRegionId=SubRegionId()
                    x
                }

        //Update SubRegion
        this.subRegions.filter { x->newSubRegion.containsKey(x.subRegionId.subRegionId) }.
                forEach{
                    subRegionIds.add(it.subRegionId.subRegionId)
                    it.updateSubRegion(newSubRegion.get(it.subRegionId.subRegionId)!!)
                }

        /*//Delete SubRegion
        this.subRegions=this.subRegions.
                filter {x->subRegionIds.contains(x.subRegionId.subRegionId)}.toSet()
*/


        var set=HashSet(this.subRegions)
        set.addAll(addSubRegion)
        this.subRegions=set
    }

    fun idInitializer(){
        this.regionId= RegionId()
        this.subRegions.forEach{
            it.subRegionId=SubRegionId()
            it.regionName=this.regionName
            it.regionId=this.regionId.regionId
        }
    }
}