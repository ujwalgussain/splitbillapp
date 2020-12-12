package com.app.splitbillapp

import com.app.splitbillapp.service.AppUserService
import org.json.JSONObject
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.Autowired

@RestController
class MyController(
    @Autowired private val appUserService: AppUserService

) {

    /*MileStone 1*/
    @PostMapping("/user/create")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun createUser(@RequestBody requestBody: String)
    {
        /*TestCases
        Pass : AddUser with correct data
        Fail : AddUser with wrong data
         */
        appUserService.createUser(JSONObject(requestBody))
    }

    @PutMapping("/user/update")
    fun updateUser(@RequestBody requestBody: String)
    {
        //find record
        //replace
        //appUserService.createUser(JSONObject(requestBody))
    }

    @DeleteMapping("/user/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteUser(@PathVariable id:Int)
    {
        //We consider the userId will come from UI
        appUserService.deleteUser(id)
    }

    //createBill -
//      TestCases addBill +ve amt addBill -ve amt [A,B,C -> A pays 300 B pays 150, C pays 75] check final state
    /*{amt, paidBy, split_users[]}*/


}