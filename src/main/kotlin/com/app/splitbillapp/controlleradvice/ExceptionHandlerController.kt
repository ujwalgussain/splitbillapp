package com.app.splitbillapp.controlleradvice

import com.app.splitbillapp.utlilities.MyConstants
import com.app.splitbillapp.utlilities.ResponseUtilities
import org.json.JSONException
import org.json.JSONObject
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.RuntimeException

@ControllerAdvice
class ExceptionHandlerController {
   /* @ExceptionHandler(JSONException::class)
    fun handleException(exception: RuntimeException): ResponseEntity<JSONObject> = ResponseUtilities.createApiResponse(
        MyConstants.FAILED,exception.message,
        HttpStatus.BAD_REQUEST)*/

    /*@ExceptionHandler(JSONException::class)
    fun handleException(exception: RuntimeException): ResponseEntity<JSONObject> = ResponseUtilities.createApiResponseEntity(
        MyConstants.FAILED,exception.message,
        HttpStatus.BAD_REQUEST)*/


}