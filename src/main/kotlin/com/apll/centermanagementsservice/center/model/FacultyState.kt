package com.apll.centermanagementsservice.center.model

import javax.persistence.*


@Entity
@Table(name="faculty_state")
class FacultyState {
    @EmbeddedId
    @Column(name = "faculty_id")
    var facultyId = FacultyId()

    @Column(name = "faculty_name")
    var facultyName: String = ""

    @Column(name = "qualification")
    var qualification: String = ""

    @Column(name = "contact_number")
    var contactNumber: String = ""

    @Column(name = "door_no")
    var doorNo: String = ""

    @Column(name = "street_name")
    var streetName: String = ""

    @Column(name = "area_name")
    var areaName: String = ""

    @Column(name = "land_mark")
    var landmark: String = ""

    @Column(name = "city")
    var city: String = ""

    @Column(name = "state")
    var state: String = ""

    @Column(name = "zip_code")
    var zipCode: String = ""

    @Column(name = "technical_specialization")
    var technicalSpecialization: String = ""

    @Column(name = "experience")
    var experience: Float = 0.0f

    @Column(name = "email_id")
    var emailId: String = ""

   /* @JsonIgnore
    @JoinColumn(name = "center_id", nullable = false)
    @ManyToOne(cascade = arrayOf(CascadeType.ALL))
    var center: CenterState?= null
        get() = field

        // setter
        set(value) {
            field = value
        }
*/
    constructor(facultyId: FacultyId, facultyName: String,
                qualification: String, contactNumber: String,
                doorNo: String, streetName: String, areaName: String,
                landmark: String, city: String, state: String,
                zipCode: String, technicalSpecialization: String,
                experience: Float, emailId: String) {

        this.facultyId = facultyId
        this.facultyName = facultyName
        this.qualification = qualification
        this.contactNumber = contactNumber
        this.doorNo = doorNo
        this.streetName = streetName
        this.areaName = areaName
        this.landmark = landmark
        this.city = city
        this.state = state
        this.zipCode = zipCode
        this.technicalSpecialization = technicalSpecialization
        this.experience = experience
        this.emailId = emailId
    }

    constructor()


    fun updateFaculty(facultyState: FacultyState) {
        this.facultyName = facultyState.facultyName
        this.qualification = facultyState.qualification
        this.contactNumber = facultyState.contactNumber
        this.doorNo = facultyState.doorNo
        this.streetName = facultyState.streetName
        this.areaName = facultyState.areaName
        this.landmark = facultyState.landmark
        this.city = facultyState.city
        this.state = facultyState.state
        this.zipCode = facultyState.zipCode
        this.technicalSpecialization = facultyState.technicalSpecialization
        this.experience = facultyState.experience
        this.emailId = facultyState.emailId
    }

}


