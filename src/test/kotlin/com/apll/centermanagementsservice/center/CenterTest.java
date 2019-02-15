package com.apll.centermanagementsservice.center;


import com.apll.centermanagementsservice.center.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashSet;

public class CenterTest {


    @Test
    public void updateFaculty(){

        FacultyState state1=new FacultyState(new FacultyId("1"),
                "f1",
                "btech",
                "2222222222",
                "2",
                "s1",
                "a1",
                "l1",
                "c1",
                "s1",
                "z1",
                "ts1",
                1,
                "email1");



        FacultyState state2=new FacultyState(new FacultyId("2"),
                "f2",
                "btech2",
                "3333333333",
                "3",
                "s2",
                "a2",
                "l2",
                "c2",
                "s2",
                "z2",
                "ts2",
                2,
                "email2");


        FacultyState state3=new FacultyState(new FacultyId("3"),
                "f3",
                "btech3",
                "4444444444",
                "4",
                "s4",
                "a4",
                "l4",
                "c4",
                "s4",
                "z4",
                "ts4",
                4,
                "email4");

        HashSet<FacultyState> facultyStateSet=new HashSet<>();
        facultyStateSet.add(state1);
        facultyStateSet.add(state2);
        facultyStateSet.add(state3);

        InfrastructureDetailsId fid=new InfrastructureDetailsId();
        fid.setInfrastructureDetailsId("22");

        InfrastructureDetails infrastructureDetails=new InfrastructureDetails(fid,
                333,
                44,
                433,
                44,
                443,
                6);

        CenterId centerId=new CenterId();
        centerId.setCenterId("11");

        CenterState centerState=new CenterState(centerId,
                "www",
                "dee",
                "3518653786",
                "9875434566",
                "hoa7",
                "gha",
                "jad",
                "hkasd",
                "ksd",
                "86754",
                "dged",
                "dfdf",
                "sfhsflj",
                "fsdf",
                LocalDate.of(1997,9,4),
                "vvn",
                CenterType.EXISTINGCENTER,
                CenterStatus.ENDING,
                facultyStateSet,
                infrastructureDetails,
                "dbfa45cb-1b47-4534-a4bf-292772518d66");

       Center center=new Center(centerState);


        FacultyState state4=new FacultyState(new FacultyId("1"),
                "f1-update",
                "btech",
                "2222222222",
                "2",
                "s1",
                "a1",
                "l1",
                "c1",
                "s1",
                "z1",
                "ts1",
                1,
                "email1");



        FacultyState state5=new FacultyState(new FacultyId("2"),
                "f2-update",
                "btech2",
                "3333333333",
                "3",
                "s2",
                "a2",
                "l2",
                "c2",
                "s2",
                "z2",
                "ts2",
                2,
                "email2");


        FacultyState state6=new FacultyState(new FacultyId(""),
                "f3-new",
                "btech3",
                "4444444444",
                "4",
                "s4",
                "a4",
                "l4",
                "c4",
                "s4",
                "z4",
                "ts4",
                4,
                "email4");

        HashSet<FacultyState> facultyStateSetUpdate=new HashSet<>();
        facultyStateSetUpdate.add(state4);
        facultyStateSetUpdate.add(state5);
        facultyStateSetUpdate.add(state6);



        InfrastructureDetails infrastructureDetailsUpdate=new InfrastructureDetails(
                new InfrastructureDetailsId("22"),
                444,
                50,
                500,
                15,
                443,
                66);



        CenterState centerStateUpdate=new CenterState(new CenterId("11"),
                "www",
                "dee",
                "3518653786",
                "9875434566",
                "hoa7",
                "gha",
                "jad",
                "hkasd",
                "ksd",
                "86754",
                "dged",
                "dfdf",
                "sfhsflj",
                "fsdf",
                LocalDate.of(1997,9,4),
                "nhj",
                CenterType.EXISTINGCENTER,
                CenterStatus.ENDING,
                facultyStateSetUpdate,
                infrastructureDetailsUpdate,
                "dbfa45cb-1b47-4534-a4bf-292772518d66");

        Center center1=new Center(centerStateUpdate);
        //center1.updateFaculty(center.getCenterState().getFacultyList());

        center.updateCenter(center1.getCenterState());

        Assert.assertTrue("pass"+center,true);
    }
}
