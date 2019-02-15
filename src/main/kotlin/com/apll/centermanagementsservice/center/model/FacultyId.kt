package com.apll.centermanagementsservice.center.model

import javax.persistence.Embeddable

import java.io.Serializable
import java.util.UUID

@Embeddable
class FacultyId : Serializable {

  var facultyId :String=UUID.randomUUID().toString()
  set(facultyId){
    field=facultyId
  }

  constructor()

  constructor(facultyId: String){
    this.facultyId = facultyId
  }

}
