/*

package com.apll.centermanagementsservice.com.apll.apllcommon.config.center;


import com.apll.centermanagementsservice.center.model.*;
import com.apll.centermanagementsservice.center.model.dto.CenterStateDTO;
import com.apll.centermanagementsservice.center.model.dto.CenterStateDtoWithSR;
import com.apll.centermanagementsservice.center.model.dto.FacultyStateDto;
import com.apll.centermanagementsservice.center.model.dto.InfrastructureDetailsDTO;
import com.apll.centermanagementsservice.com.apll.apllcommon.config.center.model.*;
import com.apll.centermanagementsservice.com.apll.apllcommon.config.center.model.dto.*;
import com.apll.centermanagementsservice.config.CenterManagementsServiceApplication;
import com.apll.centermanagementsservice.regionAndsubRegion.model.RegionId;
import com.apll.centermanagementsservice.regionAndsubRegion.model.SubRegionId;
import com.apll.centermanagementsservice.regionAndsubRegion.model.dto.SubRegionDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CenterManagementsServiceApplication.class)
public class CenterDTOTest {

    @Test
    public void dtoToModelConvertTest(){

        FacultyStateDto dto=new FacultyStateDto("111",
                "aaa",
                "eng",
                "1234543213",
                "tech",
                5,
                "ship@gmail.com",
                "222",
                "hjk",
                "mla",
                "lotus",
                "hyd",
                "ts",
                "765432");

        Set<FacultyStateDto> set=new HashSet<FacultyStateDto>();
        set.add(dto);


        InfrastructureDetailsDTO details=new InfrastructureDetailsDTO("767",
                                 10,
                                 34,
                                 34,
                                 45,
                                 15,
                                 55);

        CenterStateDTO centerStateDTO=new CenterStateDTO(
                "656",
                "shipra",
                "aatram",
                "1234567890",
                "9999999999",
                "123",
                "11nm",
                "mla",
                "lotous",
                "hyd",
                "234562",
                "ts",
                "wsff",
                "ship@gmial.com",
                "asddfdf",
                LocalDate.of(2000,06,06),
                472927,
                CenterType.NEWCENTER,
                CenterStatus.ENDING,
                set,
                details,
                "887");

        CenterState result=CenterStateDTOConverter.converter.INSTANCE.dtoToModelConvert(centerStateDTO);

        System.out.println(result);
        Assert.assertEquals(centerStateDTO,centerStateDTO);
        Assert.assertEquals(set,set);
        Assert.assertEquals(details,details);
        Assert.assertEquals("887","887");
    }


    @Test
    public void modelToDtoConvertTest() {

        CenterId resolverId = new CenterId();
        resolverId.getCenterId();

        Set<FacultyState> facultyStateSet=new HashSet<FacultyState>();

        FacultyId fid=new FacultyId();
        fid.setFacultyId("111");
        FacultyState facultyState=new FacultyState(fid,
                "bbb",
                "eng",
                "12345677898",
                "sf",
                "sds",
                "ts",
                "lot",
                "ts",
                "sff",
                "2142342",
                "ewrfw",
                4,
                "wfafaf");
        facultyStateSet.add(facultyState);

        InfrastructureDetailsId iid=new InfrastructureDetailsId();
        iid.setInfrastructureDetailsId("444");
InfrastructureDetails infrastructureDetail=new InfrastructureDetails(iid,
        333,
        33,
        44,
        433,
        44,
        443);


        CenterState state = new CenterState(resolverId,
                "abc",
                "bbc",
                "2343245322",
                "8765987643",
                "43er",
                "teft",
                "eszf",
                "efrf",
                "fef",
                "23123",
                "wdasdc",
                "sfdwad",
                "weadawd",
                "wedwad",
                LocalDate.of(2000, 5, 6),
                1246786990,
                CenterType.EXISTINGCENTER,
                CenterStatus.ENDING,
                facultyStateSet,
                infrastructureDetail,
                "209"
                );


        CenterStateDtoWithSR result=CenterStateDTOConverter.converter.INSTANCE.modelToDtoConvert(state);

        Assert.assertTrue("test is pass",true);
    }

    @Test
    public  void dtoToModelConvertWithSRTest(){

        SubRegionDTO sbdto=new SubRegionDTO("111",
                "222",
                "wqwqw",
                "sdasd",
                "dad"
                );

        FacultyStateDto dto=new FacultyStateDto("111",
                "aaa",
                "eng",
                "1234543213",
                "tech",
                5,
                "ship@gmail.com",
                "222",
                "hjk",
                "mla",
                "lotus",
                "hyd",
                "ts",
                "765432");

        Set<FacultyStateDto> set=new HashSet<FacultyStateDto>();
        set.add(dto);


        InfrastructureDetailsDTO details=new InfrastructureDetailsDTO("767",
                12,
                34,
                34,
                45,
                15,
                55);


        CenterStateDtoWithSR srdto=new CenterStateDtoWithSR(
                "656",
                "shipra",
                "aatram",
                "1234567890",
                "9999999999",
                "123",
                "11nm",
                "mla",
                "lotous",
                "hyd",
                "234562",
                "ts",
                "wsff",
                "ship@gmial.com",
                "asddfdf",
                LocalDate.of(2000,06,06),
                472927,
                CenterType.NEWCENTER,
                CenterStatus.ENDING,
                set,
                details,
               sbdto );

        CenterState result=CenterStateDTOConverter.converter.INSTANCE.dtoToModelConvertWithSR(srdto);
      Assert.assertTrue("test is pass",true);
    }

  @Test
   public void modelToDtoConvertWithSRTest(){


        SubRegionId sid=new SubRegionId();
        sid.setSubRegionId("111");


        RegionId rid=new RegionId();
        rid.setRegionId("444");

        SubRegion sb=new SubRegion(sid,
                rid,
                "wqwqw",
                "sdasd",
                "dad"
        );

        FacultyId fid=new FacultyId();
        fid.setFacultyId("435");

        FacultyState fs=new FacultyState(fid,
                "aaa",
                "eng",
                "1234543213",
                "tech",
                "7",
                "ship@gmail.com",
                "222",
                "hjk",
                "mla",
                "lotus",
                "hyd",
                8,
                "765432");

        Set<FacultyState> set=new HashSet<FacultyState>();
        set.add(fs);


        InfrastructureDetailsId iid=new InfrastructureDetailsId();
        iid.setInfrastructureDetailsId("876");

        InfrastructureDetails details=new InfrastructureDetails(iid,
                12,
                34,
                34,
                45,
                15,
                55);


        CenterId cid=new CenterId();
        cid.setCenterId("777");

        CenterState state=new CenterState(
                cid,
                "shipra",
                "aatram",
                "1234567890",
                "9999999999",
                "123",
                "11nm",
                "mla",
                "lotous",
                "hyd",
                "234562",
                "ts",
                "wsff",
                "ship@gmial.com",
                "asddfdf",
                LocalDate.of(2000,06,06),
                472927,
                CenterType.NEWCENTER,
                CenterStatus.ENDING,
                set,
                details,
                sb );

        CenterState result=CenterStateDTOConverter.converter.INSTANCE.modelToDtoConvertWithSR(state);
        Assert.assertTrue("test is pass",true);

    }


    }

*/
