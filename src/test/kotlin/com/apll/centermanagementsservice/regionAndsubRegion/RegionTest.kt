package com.apll.centermanagementsservice.regionAndsubRegion

import com.apll.centermanagementsservice.regionAndsubRegion.model.Region
import com.apll.centermanagementsservice.regionAndsubRegion.model.RegionId
import com.apll.centermanagementsservice.regionAndsubRegion.model.SubRegion
import com.apll.centermanagementsservice.regionAndsubRegion.model.SubRegionId
import org.junit.Assert
import org.junit.Test


class RegionTest {


    @Test
    fun regionUpdateTest() {

        var subRegions = HashSet<SubRegion>()
        var subRegion1=SubRegion(SubRegionId("1"),
                "sr1",
                "c1")

        var subRegion2=SubRegion(SubRegionId("2"),
                "sr2",
                "c2")

        var subRegion3=SubRegion(SubRegionId("3"),
                "sr3",
                "c3")

        subRegions.add(subRegion1)
        subRegions.add(subRegion2)
        subRegions.add(subRegion3)

        var oldRegion =Region(RegionId("101"),"r1","TS",subRegions)


        var subRegionsNew = HashSet<SubRegion>()
        var subRegion4=SubRegion(SubRegionId(""),
                "new-sr1",
                "new-c1")

        var subRegion5=SubRegion(SubRegionId("2"),
                "update-sr2",
                "update-c2")

        var subRegion6=SubRegion(SubRegionId("3"),
                "update-sr3",
                "update-c3")

        subRegionsNew.add(subRegion4)
        subRegionsNew.add(subRegion5)
        subRegionsNew.add(subRegion6)

        var updateOldRegion =Region(RegionId("101"),"update-r1","AP",subRegionsNew)


        oldRegion.regionUpdate(updateOldRegion)

        Assert.assertTrue(subRegion2.city.equals("update-c2"))
        Assert.assertTrue(subRegion2.subRegionName.equals("update-sr2"))

        Assert.assertTrue(subRegion3.city.equals("update-c3"))
        Assert.assertTrue(subRegion3.subRegionName.equals("update-sr3"))

        Assert.assertTrue(oldRegion.regionName.equals("update-r1"))
        Assert.assertTrue(oldRegion.state.equals("AP"))
    }
}
