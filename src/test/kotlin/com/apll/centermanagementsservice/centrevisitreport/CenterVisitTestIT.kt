package com.apll.centermanagementsservice.centrevisitreport

import com.apll.centermanagementsservice.centrevisitreport.model.*
import com.apll.centermanagementsservice.centrevisitreport.model.dto.CenterVisitReportDtoConverter
import com.apll.centermanagementsservice.centrevisitreport.model.dto.TodoListApllResolvedDto
import com.apll.centermanagementsservice.centrevisitreport.model.dto.TodoListCenterResolvedDto
import com.apll.centermanagementsservice.centrevisitreport.service.CenterVisitReportService
import com.apll.centermanagementsservice.config.CenterManagementsServiceApplication
import org.junit.Assert

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate
import java.time.LocalDateTime
import javax.validation.Validation
import javax.validation.ValidatorFactory

@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(CenterManagementsServiceApplication::class))
class CenterVisitTestIT {

    @Autowired
    lateinit var centerVisitReportService:CenterVisitReportService

   /* @Autowired
    lateinit var  todoservice: TodoForCenterService

*/
    @Test
    fun insertCVR(){

        var rBS=RunningBatchStatus(RunningBatchStatusId(),"101",12, LocalDateTime.now(), LocalDate.now(),"sai","7777999965","srk","crt",457)
        var todoCenterList=ArrayList<TodoListCenter>()
        var todocen=TodoListCenter(TodoListCenterId(),"center maintains...", LocalDate.now(),ReasonStatus.PROCESSING, ResolvedBy.HEAD_ADMIN,"78", LocalDate.of(2018,11,26),"solved so and so")
       todoCenterList.add(todocen)
        var todoForCenter=TodoForCenter(TodoForCenterId(),todoCenterList)
       var todoapllList=ArrayList<TodoListApll>()
       var todoapll=TodoListApll(TodoListApllId(),"center maintains...", LocalDate.now(),ReasonStatus.PROCESSING,ResolvedBy.HEAD_ADMIN,"78", LocalDate.of(2018,11,26),"solved so and so")
       todoapllList.add(todoapll)
        var todoForApll=TodoForApll(TodoForApllId(),todoapllList)

        var rBSSet=ArrayList<RunningBatchStatus>()

        rBSSet.add(rBS)
        var dOD=DropOutDetails(DropOutDetailsId(),"sri","786","102", LocalDate.now(),"Health issue")


        var dODSet=ArrayList<DropOutDetails>()
        dODSet.add(dOD)
        var cBS=CompletedBatchStatus(CompletedBatchStatusId(),"105",true, LocalDate.now(),459,352,"fine...")

        var cBSSet=ArrayList<CompletedBatchStatus>()
        cBSSet.add(cBS)




        var stock=StockStatus(StockStatusId(),"apllItem",18,50,"stock@gmail.com",41,false,"stock is very bad...")



        var stockSet=ArrayList<StockStatus>()

        stockSet.add(stock)
       var infraList=ArrayList<CvrInfrastructure>()
        var infrastructure=CvrInfrastructure(CvrInfrastructureId(),"labs",100.0F,false,10.0F)

       infraList.add(infrastructure)
    var cVR=CenterVisitReport(CenterVisitReportId(), "789","123","79", "8",LocalDate.of(2019,1,25),LocalDate.of(2018,8,25),dODSet,rBSSet,stockSet,cBSSet,todoForCenter,todoForApll,infraList,CenterVisitReportStatus.PROCESSING)

    var cvrdto=CenterVisitReportDtoConverter.converter.modelToDtoConverter(cVR)
       var  factory : ValidatorFactory = Validation.buildDefaultValidatorFactory();

       var validator = factory.getValidator();
       var violations = validator.validate( cvrdto);
       Assert.assertEquals(0,violations.size)

       // val result = centerVisitReportService.getMinimalReq()
        val re = centerVisitReportService.insertCenterVisitReport(cvrdto)
        println(re)
        Assert.assertEquals(true,re!=null)
    }

    @Test
    fun getByCenterVisitReportId(){
        var id="a5cea952-24a6-41e3-9fc6-c0b627fe6764"
        var result=centerVisitReportService.findByCenterVisitReportId(id)
        println(result)
        Assert.assertEquals(true,result.isRight)






    }

    @Test
    fun getAllCenterVisitReportTest(){
        var result=centerVisitReportService.findAllCenterVisitReport()
        println(result)
        Assert.assertEquals(true,result.isRight)
    }

        @Test
        fun updateCenterVisitReportTest(){

            var id=CenterVisitReportId()

            id.centerVisitReportId="a5cea952-24a6-41e3-9fc6-c0b627fe6764"

            var rid=RunningBatchStatusId()
            rid.runningBatchStatusId="59171b44-7f14-4095-bc02-1688ee162eee"

            var comid=CompletedBatchStatusId()
            comid.completedBatchStatusId="c3907155-7279-48fd-a139-daeeaaed5ec4"

            var dId=DropOutDetailsId()
            dId.dropOutDetailsId="c26479be-b624-4c22-b9da-ed177d13b7fa"


            var stockId=StockStatusId()
            stockId.stockStatusId="5a1875c0-ba54-40a4-a577-9bbef3e304e4"

            var todoCenterList=ArrayList<TodoListCenter>()
            var todocen=TodoListCenter(TodoListCenterId(),"center maintains...", LocalDate.now(),ReasonStatus.PROCESSING,ResolvedBy.HEAD_ADMIN,"78", LocalDate.of(2018,11,26),"solved so and so")
            todoCenterList.add(todocen)
            var todoForCenter=TodoForCenter(TodoForCenterId(),todoCenterList)
            var todoapllList=ArrayList<TodoListApll>()
            var todoapll=TodoListApll(TodoListApllId(),"center maintains...", LocalDate.now(),ReasonStatus.PROCESSING,ResolvedBy.HEAD_ADMIN,"78", LocalDate.of(2018,11,26),"solved so and so")
            todoapllList.add(todoapll)
            var todoForApll=TodoForApll(TodoForApllId(),todoapllList)



            var rBS=RunningBatchStatus(rid,"101",12, LocalDateTime.now(), LocalDate.now(),"sai","7844444444","srk","crt",457)



            var rBSSet=ArrayList<RunningBatchStatus>()

            rBSSet.add(rBS)
            var dOD=DropOutDetails(dId,"sri","786","102", LocalDate.now(),"Health issue")


            var dODSet=ArrayList<DropOutDetails>()
            dODSet.add(dOD)
            var cBS=CompletedBatchStatus(comid,"105",true, LocalDate.now(),459,352,"fine...")

            var cBSSet=ArrayList<CompletedBatchStatus>()
            cBSSet.add(cBS)


            var stock=StockStatus(stockId,"apll1Item",16,50,"stock@gmail.com",41,false,"stock is very bad...")



            var stockSet=ArrayList<StockStatus>()

            stockSet.add(stock)

            var infraList=ArrayList<CvrInfrastructure>()
            var infrastructure=CvrInfrastructure(CvrInfrastructureId(),"labs",1000.0F,false,10.0F)

            infraList.add(infrastructure)

            var cVR=CenterVisitReport(id, dODSet,rBSSet,stockSet,cBSSet,todoForCenter,todoForApll,infraList,CenterVisitReportStatus.PROCESSING)


            var cvrdto=CenterVisitReportDtoConverter.converter.modelToDtoForUpdate(cVR)
            var  factory : ValidatorFactory = Validation.buildDefaultValidatorFactory();

            var validator = factory.getValidator();
            var violations = validator.validate( cvrdto);
            Assert.assertEquals(0,violations.size)



            var resultdto=centerVisitReportService.updateCVR(cvrdto)

            println(resultdto)

            Assert.assertEquals(true,resultdto.isRight)


        }


    @Test
    fun getCenterVisitReportByFrontId(){
        var id="string"
    var resultlist=centerVisitReportService.getCenterVisitReportByFrontEndId(id)
        println(resultlist)
        Assert.assertEquals(true,resultlist.isRight)
}

    @Test
    fun getChangeCenterVisitReportStatus(){
        var id="8e88c204-6ded-477e-8f35-b1d67e225799"
        var status=CenterVisitReportStatus.APPROVED
        var result=centerVisitReportService.changeStatus(status,id)
        println(result)
        Assert.assertEquals(true,result.isRight)

    }
    @Test
    fun getCenterVisitReportByYearMonth(){
        var ym="11-2018"
        var fid="string"


        var result=centerVisitReportService.getMonthWiseCenterVisitReportByYearMonthAndFrontEndId(ym,fid)
        println(result)
        Assert.assertEquals(true, result.isRight)
    }

    /*@Test
    fun changeStatusToTodoListIdparticulartodoId(){
        var todocenterid="68f2335f-dbc8-4ab3-af84-d8bbba77fa64"
        var todolistid="7eb55a84-ee5c-4b30-a3b3-473ba6a67849"
        var status=ReasonStatus.RESOLVED
        var result=todoservice.changeReasonStatus(todocenterid,todocenterid,status)
        println(result)
        Assert.assertEquals(true,result.isRight)
    }
*/
    /*@Test
    fun changeReasonStatusForTodoForCenter(){
        var todoId="ddb13fd5-d2bd-43e1-bee2-b25af2d22439"
        var cid="52a95bf0-4b6c-40f5-9599-c52266f101d4"
        var status=ReasonStatus.NOT_RESOLVED
        var result=centerVisitReportService.changeReasonStatusForTodoForCenter(cid,todoId,status)
        println(result)
        Assert.assertEquals(true,result.isRight)
    }*/

    @Test
    fun resolvedTodoForCenterByCvrId(){
        var cvrid="ecb8ed87-1766-41f8-b018-3d865bd922cd"
        var dto=TodoListCenterResolvedDto("9bab2857-a98f-4146-bb2e-905a33b7d408",ResolvedBy.HEAD_ADMIN,"10001", LocalDate.of(2018,11,29),"solved so and so",ReasonStatus.PROCESSING)
        var dto2=TodoListCenterResolvedDto("b13e45a5-cc65-40d1-8623-f17ef195d6f7",ResolvedBy.HEAD_ADMIN,"564", LocalDate.of(2018,12,26),"solved so and so",ReasonStatus.PROCESSING)
        var list=ArrayList<TodoListCenterResolvedDto>()
        list.add(dto)
        list.add(dto2)

        var result=centerVisitReportService.resolvedTodoForCenterByCvrId(cvrid,list)
        println(result)
        Assert.assertEquals(true,result.isRight)
    }

    @Test
    fun resolvedTodoForApllByCvrId(){
        var cvrid="ecb8ed87-1766-41f8-b018-3d865bd922cd"
        var aplldto=TodoListApllResolvedDto("570abaf8-ec17-4d32-bae8-a5a30daf45bf",ResolvedBy.HEAD_ADMIN,"78", LocalDate.of(2018,11,26),"solved so and so",ReasonStatus.PROCESSING)
        var apllList=ArrayList<TodoListApllResolvedDto>()
        apllList.add(aplldto)
        var result=centerVisitReportService.resolvedTodoForApllByCvrId(cvrid,apllList)
        println(result)
        Assert.assertEquals(true,result.isRight)

    }

}
