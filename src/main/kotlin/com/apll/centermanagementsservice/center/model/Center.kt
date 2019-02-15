package com.apll.centermanagementsservice.center.model


class
Center {

  var centerState : CenterState

  constructor(centerState: CenterState) {
    this.centerState = centerState
  }


  fun updateCenter(centerState: CenterState){

    //Update CvrInfrastructure.
    this.centerState.infrastructureDetails.updateInfrastructureDetails(centerState.infrastructureDetails)

    //update center state
    this.centerState.updateCenterState(centerState)

    //update facultyList
    var updateFid=HashSet<String>()

    //add faculty
    var newlyAddedFac=centerState.facultyList.filter { x->x.facultyId.facultyId.equals("") }
            .map { x->x.facultyId=FacultyId()
            x}

    var newFaculty:Map<String,FacultyState> = centerState.facultyList
            .map {x->x.facultyId.facultyId to x  }.toMap()

    //update faculty
    this.centerState.facultyList.
            filter { x->newFaculty.containsKey(x.facultyId.facultyId) }.
            forEach { x->updateFid.add(x.facultyId.facultyId)
                       x.updateFaculty(newFaculty.get(x.facultyId.facultyId)!!)}

    //delete faculty
    this.centerState.facultyList=this.centerState.facultyList.
            filter { x->updateFid.contains(x.facultyId.facultyId)}.toSet()

    var list=HashSet(this.centerState.facultyList)
    list.addAll(newlyAddedFac)
    this.centerState.facultyList=list
  }
}

