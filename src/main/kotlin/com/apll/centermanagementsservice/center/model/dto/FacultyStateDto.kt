package com.apll.centermanagementsservice.center.model.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.Range
import javax.validation.constraints.*

@ApiModel(value = "Faculty", description = "Class representing Faculty", parent = Any::class)
data class FacultyStateDto (

        @ApiModelProperty(name = "facultyId", notes = "faculty-Id of a Faculty")
        var facultyId: String?,

        @ApiModelProperty(name = "facultyName", notes = "faculty-Name of a Faculty")
        @get:NotNull(message = "faculty-Name should not be null.")
        @get:NotBlank(message = "faculty-Name should not be blank.")
        @get:Size(min =3,max = 40,message = "Faculty name should min=3,max=40")
        var facultyName: String?,

        @ApiModelProperty(name = " qualification", notes = " qualification of a Faculty")
        @get:NotNull(message = "qualification should not be null.")
        @get:NotBlank(message = "qualification should not be blank.")
        @get:Size(min =2,max = 40,message = "qualification  should min=2,max=40")
        var qualification: String?,


        @ApiModelProperty(name = "contactNumber", notes = "contact-Number of a Faculty")
        @get:NotNull(message = "contact-Number should not be null.")
        @get:NotBlank(message = "contact-Number should not be blank.")
        @get:Size(max = 10,min = 10,message = "Mobile Number must be 10 digit only.")
        @get:Pattern(regexp = "^[6-9]\\d{9}\$",message = "Please write Contact number in valid format.")
        var contactNumber: String?,

        @ApiModelProperty(name = "technicalSpecialization", notes = "technical Specialization of a Faculty")
        @get:NotNull(message = "technical Specialization should not be null.")
        @get:NotBlank(message = "technical Specialization should not be blank.")
        @get:Size(min =3,max = 40,message = "technical Specialization  should min=3,max=40")
        var technicalSpecialization: String?,

        @ApiModelProperty(name = "experience", notes = "experience of a Faculty")
        @get:NotNull(message = "experience  should not be null.")
        @get:Range(min = 0,max = 100,message = "experience should min=1,max=100")
        var experience: Float?,

        @ApiModelProperty(name = "emailId", notes = "email-Id of a Faculty")
        @get:NotNull(message = "emailId  not be null.")
        @get:NotBlank(message = "emailId not be blank.")
        @get:Pattern(regexp =  "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,6})$",
                message ="Please write Email Id in valid format" )
        var emailId: String?,

        @ApiModelProperty(name = "doorNo", notes = "door-No of a Faculty")
        @get:NotNull(message = "door-No  not be null.")
        @get:NotBlank(message = "door-No not be blank.")
        @get:Size(min =1,max = 40,message = "door No should min=1,max=40")
        var doorNo:String?,

        @ApiModelProperty(name = "streetName", notes = "street-Name of a Faculty")
        @get:NotNull(message = "street-Name  not be null.")
        @get:NotBlank(message = "street-Name not be blank.")
        @get:Size(min =3,max = 40,message = "street Name should min=3,max=40")
        var streetName:String?,

        @ApiModelProperty(name = "areaName", notes = "area-Name of a Faculty")
        @get:NotNull(message = "area-Name  not be null.")
        @get:NotBlank(message = "area-Name not be blank.")
        @get:Size(min =3,max = 40,message = "area Name should min=3,max=40")
        var areaName:String?,

        @ApiModelProperty(name = "landmark", notes = "landmark of a Faculty")
        @get:NotNull(message = "landmark not be null.")
        @get:NotBlank(message = "landmark not be blank.")
        @get:Size(min =3,max = 40,message = "landmark should min=3,max=40")
        var landmark:String?,

        @ApiModelProperty(name = "city", notes = "city of a Faculty")
        @get:NotNull(message = "city not be null.")
        @get:NotBlank(message = "city not be blank.")
        @get:Size(min =3,max = 40,message = "city should min=3,max=40")
        var city:String?,

        @ApiModelProperty(name = "state", notes = "state of aFaculty")
        @get:NotNull(message = "state not be null.")
        @get:NotBlank(message = "state not be blank.")
        @get:Size(min =3,max = 40,message = "state should min=3,max=40")
        var state:String?,

        @ApiModelProperty(name = "zip-code", notes = "zip-Code of a Faculty")
        @get:NotNull(message = "zip-Code not be null.")
        @get:NotBlank(message = "zip-Code not be blank.")
        @get:Size(max=6,min=6,message = "ZipCode must have 6 characters only.")
        var zipCode:String?

)