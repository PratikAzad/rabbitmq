package com.apll.centermanagementsservice.regionAndsubRegion.repository


import com.apll.centermanagementsservice.regionAndsubRegion.model.Region
import com.apll.centermanagementsservice.regionAndsubRegion.model.RegionId
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface RegionRepo: JpaRepository<Region, RegionId> {

    @EntityGraph(attributePaths = arrayOf("subRegions"))
    override fun findById(p0: RegionId): Optional<Region>

    @EntityGraph(attributePaths = arrayOf("subRegions"))
    override fun findAll(): List<Region>

    fun findByState(state: String): List<Region>



}