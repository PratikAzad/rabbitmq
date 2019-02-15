package com.apll.centermanagementsservice.center.model.dto

import com.apll.centermanagementsservice.center.model.CenterStatus
import com.apll.centermanagementsservice.center.model.CenterType
import com.apll.centermanagementsservice.config.LocalDateDeSerializer
import com.apll.centermanagementsservice.config.LocalDateSerializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@ApiModel(value = "CenterUpdateDTO", description = "Class representing CenterUpdateDTO", parent = Any::class)
data class CenterUpdateDTO(

        @ApiModelProperty(name = "centerId", notes = "centerId of a Center")
        var centerId: String?,

        @ApiModelProperty(name = "centerName", notes = "center Name of a Center")
        @get:NotNull(message = "Center Name should not be null.")
        @get:NotBlank(message = "Center Name should not be Blank.")
        var centerName:String?,

        @ApiModelProperty(name = "contactNumber", notes = "contact Number of a Center")
        @get:NotNull(message = "Contact number should not be null.")
        @get:NotBlank(message = "Contact number should not be blank.")
        @get:Pattern(regexp = "^[6-9]\\d{9}\$",message = "Please write Contact number in valid format.")
        @get:Size(max = 10,min = 10,message = "Contact number must be 10 digit only.")
        var contactNumber:String?,

        @ApiModelProperty(name = "alternateNumber", notes = "alternate Number of a Center")
        @get:NotNull(message = "alternate Number should not be null.")
        @get:NotBlank(message = "alternate Number should not be blank.")
        @get:Pattern(regexp = "^[6-9]\\d{9}\$",message = "Please write alternate number in valid format.")
        @get:Size(max = 10,min = 10,message = "alternate Number must be 10 digit only.")
        var alternateNumber:String?,


        @ApiModelProperty(name = "houseNumber", notes = "house Number of a Center")
        @get:NotNull(message = "house Number should not be null.")
        @get:NotBlank(message = "house Number should not be blank.")
        var houseNumber:String?,

        @ApiModelProperty(name = "streetName", notes = "street Name of a Center")
        @get:NotNull(message = "street Name should not be null.")
        @get:NotBlank(message = "street Name should not be blank.")
        var streetName:String?,

        @ApiModelProperty(name = "areaName", notes = "area Name of a Center")
        @get:NotNull(message = "area Name should not be null.")
        @get:NotBlank(message = "area Name should not be blank.")
        var areaName:String?,

        @ApiModelProperty(name = "landmark", notes = "landmark of a Center")
        @get:NotNull(message = "landmark should not be null.")
        @get:NotBlank(message = "landmark should not be blank.")
        var landmark:String?,


        @ApiModelProperty(name = "city", notes = "city of a Center")
        @get:NotNull(message = "city should not be null.")
        @get:NotBlank(message = "city should not be blank.")
        var city :String?,

        @ApiModelProperty(name = "pinCode", notes = "pinCode of a Center")
        @get:NotNull(message = "pinCode should not be null.")
        @get:NotBlank(message = "pinCode should not be blank.")
        @get:Size(max = 6,min = 6,message = "pin Code must be 6 digit only")
        var pinCode:String?,

        @ApiModelProperty(name = "state", notes = "state of a Center")
        @get:NotNull(message = "state should not be null.")
        @get:NotBlank(message = "state should not be blank.")
        var state:String?,

        @ApiModelProperty(name = "courierService", notes = "courier Service of a Center")
        @get:NotNull(message = "courier Service not be null.")
        @get:NotBlank(message = "courier Service not be blank.")
        var courierService:String?,

        @ApiModelProperty(name = "emailId", notes = "emailId of a Center")
        @get:NotNull(message = "emailId  not be null.")
        @get:NotBlank(message = "emailId not be blank.")
        @get:Pattern(regexp =  "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,3})$",
                message = "  Invalid EmailId format")
        var emailId:String?,


        @ApiModelProperty(name = "centerEstablishYear", notes = "center Establish Year of a Center.")
        @JsonDeserialize(using = LocalDateDeSerializer::class)
        @JsonSerialize(using = LocalDateSerializer::class)
        @get:NotNull(message = "center Establish Year not be null.")
        var centerEstablishYear: LocalDate?,


        @ApiModelProperty(name = "centerType", notes = "center Type[enum] of a Center")
        @get:NotNull(message = "center Type[enum] should not be null.")
        var centerType: CenterType?,

        @ApiModelProperty(name = "centerStatus", notes = "center Status[enum] of a Center")
        @get:NotNull(message = "center Status[enum] should not be null.")
        var centerStatus: CenterStatus?,

        @ApiModelProperty(name = "facultyStateDtoList", notes = "faculty List of a Center")
        @get:NotNull(message = "faculty List should not be null.")
        @get:Valid
        var facultyStateDtoList: Set<FacultyStateDto>?,

        @ApiModelProperty(name = "infrastructureDetailsDTO", notes = "infrastructure Details  of a Center")
        @get:NotNull(message = "infrastructure Details should not be null.")
        @get:Valid
        var infrastructureDetailsDTO: InfrastructureDetailsDTO?
)