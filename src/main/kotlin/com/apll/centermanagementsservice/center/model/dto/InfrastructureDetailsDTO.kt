package com.apll.centermanagementsservice.center.model.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.Range
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@ApiModel(value = "InfrastructureDetails", description = "Class representing InfrastructureDetails", parent = Any::class)
data class InfrastructureDetailsDTO (


        @ApiModelProperty(name = "infrastructureDetailsId", notes = "infrastructureDetailsId-Id of a Center")
        var infrastructureDetailsId: String?,

        @ApiModelProperty(name = "coveredArea", notes = "covered-Area of a Center")
        @get:NotNull(message = "covered-Area should not be null.")
        @get:Range(min=100,max = 30000,message ="covered-Area should min=100 and max=30000.")
        var coveredArea: Float?,

        @ApiModelProperty(name = "classRooms", notes = "class-Rooms of a Center")
        @get:NotNull(message = "class-Rooms should not be null.")
        @get:Range(min=1,max = 1000,message = "class-Rooms should min=1,max=1000")
        var classRooms: Int?,

        @ApiModelProperty(name = "sittingCapacityPerClass", notes = "sitting-Capacity-Per-class of a Center")
        @get:NotNull(message = "sitting-Capacity-Per-class should not be null.")
        @get:Range(min=1,max = 1000,message = "sitting-Capacity-Per-class should min=1,max=1000")
        var sittingCapacityPerClass: Int?,

        @ApiModelProperty(name = " noOfMachines", notes = "no-Of-Machines of a Center")
        @get:NotNull(message = " no-Of-Machines should not be null.")
        @get:Range(min=1,max = 1000,message = "noOfMachines should min=1,max=1000")
        var noOfMachines: Int?,

        @ApiModelProperty(name = " sittingCapacityPerLabs", notes = "sitting-Capacity-Per-Labs of a Center")
        @get:NotNull(message = " sitting-Capacity-Per-Labs should not be null.")
        @get:Range(min=1,max = 1000,message = "sittingCapacityPerLabs should min=1,max=1000")
        var sittingCapacityPerLabs: Int?,

        @ApiModelProperty(name = " noOfLab", notes = "noOfLab of a Center")
        @get:NotNull(message = " noOfLab should not be null.")
        @get:Range(min=1,max = 1000,message = "noOfLab should min=1,max=1000")
        var noOfLab:Int?
)
