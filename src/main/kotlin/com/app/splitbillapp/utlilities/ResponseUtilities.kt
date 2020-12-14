package com.app.splitbillapp.utlilities

import org.json.JSONObject
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

object ResponseUtilities {
    fun createApiResponseEntity(status:String,reason:String?,httpStatus: HttpStatus): ResponseEntity<String>
    {
        val responseJSON:JSONObject = createApiResponseJSON(status,reason)
            return ResponseEntity(responseJSON.toString(),HttpStatus.OK)
    }
    fun createApiResponseJSON(status:String,reason:String?) : JSONObject
    {
        val responseJSON:JSONObject = JSONObject()
        responseJSON.put("status",status)
        if(status.equals(MyConstants.FAILED))
            responseJSON.put("reason",reason)
        return responseJSON
    }

    fun createApiFailureResponseJSON(reason:String) : JSONObject = createApiResponseJSON(MyConstants.FAILED,reason)


}