package com.apll.centermanagementsservice.regionAndsubRegion.repository


import com.apll.centermanagementsservice.regionAndsubRegion.model.SubRegion
import com.apll.centermanagementsservice.regionAndsubRegion.model.SubRegionId
import org.springframework.data.jpa.repository.JpaRepository


interface SubRegionRepo: JpaRepository<SubRegion, SubRegionId> {
}