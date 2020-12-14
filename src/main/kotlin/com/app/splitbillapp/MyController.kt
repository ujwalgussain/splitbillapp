package com.app.splitbillapp

import com.app.splitbillapp.entities.AppUser
import com.app.splitbillapp.service.AppUserService
import com.app.splitbillapp.service.BalanceSheetService
import com.app.splitbillapp.service.BillService
import com.app.splitbillapp.utlilities.MyConstants
import com.app.splitbillapp.utlilities.ResponseUtilities
import org.json.HTTP
import org.json.JSONException
import org.json.JSONObject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import java.lang.Exception
import java.lang.RuntimeException

@RestController
class MyController(
    @Autowired private val appUserService: AppUserService,
    @Autowired private val billService: BillService,
    @Autowired private val balanceSheetService: BalanceSheetService,
    private val logger:Logger = LoggerFactory.getLogger(MyController::class.java)

) {

    /*MileStone 1*/
    @PostMapping("/user/create")
    fun createUser(@RequestBody requestBody: String) : ResponseEntity<String>
    {
        /*TestCases
        Pass : AddUser with correct data
        Fail : AddUser with wrong data
         */
        logger.info("Recieved RQ for User Creation-$requestBody")
        appUserService.createUser(JSONObject(requestBody))
        logger.info(String.format("User Added Successfully"))
        return ResponseUtilities.createApiResponseEntity(MyConstants.SUCESS,null,HttpStatus.OK)
    }

    @PutMapping("/user/update")
    fun updateUser(@RequestBody requestBody: String)  : ResponseEntity<String>
    {
        //find record
        //replace
        //appUserService.createUser(JSONObject(requestBody))
        appUserService.updateUser(JSONObject(requestBody))
        return ResponseUtilities.createApiResponseEntity(MyConstants.SUCESS,null,HttpStatus.OK)
    }

    @DeleteMapping("/user/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteUser(@PathVariable id:Int) :  ResponseEntity<String>
    {
        //We consider the userId will come from UI
        appUserService.deleteUser(id)
        return ResponseUtilities.createApiResponseEntity(MyConstants.SUCESS,null,HttpStatus.OK)
    }

    //createBill -
//      TestCases addBill +ve amt addBill -ve amt [A,B,C -> A pays 300 B pays 150, C pays 75] check final state
    /*{amt, paidBy, split_users[]}*/

    @PostMapping("/bill/create")
    fun createBill(@RequestBody requestBody: String) :  ResponseEntity<String>
    {
        logger.info(String.format("Recieved RQ for Bill Creation - %s",requestBody))
        billService.addBill(JSONObject(requestBody).getJSONObject(MyConstants.BILL_DETAILS))
        logger.info("Bill Added Successfully")
        return ResponseUtilities.createApiResponseEntity(MyConstants.SUCESS,null,HttpStatus.OK)
    }

    fun softDeleteBill() {TODO()}


    @GetMapping("/bill/get/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getBill(@PathVariable id:Int) :ResponseEntity<String>
    {
        /*Assumes the A valid bill id would be given from ui*/
        logger.info("Recieved RQ for get bill with ID with id $id")
        val responseJSONStr:String = billService.getBill(id)
        logger.info("Response Generated $responseJSONStr")
        return ResponseEntity(responseJSONStr,HttpStatus.OK)
    }

    @GetMapping("/user/getAllDues/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllDues(@PathVariable id:String) :ResponseEntity<String>
    {
        logger.info("Recieved RQ for findAllDues for User with id $id")
        val responseJSON:JSONObject = balanceSheetService.getAllDues(Integer.parseInt(id))
        logger.info("Response Generated $responseJSON")
        return ResponseEntity(responseJSON.toString(),HttpStatus.OK)
    }

    @PostMapping("/user/getDuesforUsers")
    fun getDuesBetweenUser(@RequestBody requestBody: String) :ResponseEntity<String>
    {
        val responseJSON = balanceSheetService.getDuesBetweenUser(JSONObject(requestBody))
        return ResponseEntity(responseJSON.toString(),HttpStatus.OK)

    }
    @PostMapping("/user/settle")
    fun settleDue(@RequestBody requestBody: String) :ResponseEntity<String>
    {
        //rq structure will be same except for the fact we just double the amt and split users are the lender and borrower
        logger.info("Recieved RQ - $requestBody")
        billService.settleDue(JSONObject(requestBody))
        return ResponseEntity<String>(ResponseUtilities.createApiSuccessResponseJSON().toString(),HttpStatus.OK)
    }

}