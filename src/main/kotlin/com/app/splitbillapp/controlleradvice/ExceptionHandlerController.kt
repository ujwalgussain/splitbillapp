package com.app.splitbillapp.controlleradvice

import com.app.splitbillapp.utlilities.MyConstants
import com.app.splitbillapp.utlilities.ResponseUtilities
import org.json.JSONException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.Exception
import java.lang.RuntimeException

@ControllerAdvice
class ExceptionHandlerController: ResponseEntityExceptionHandler(){
   @ExceptionHandler(value = [RuntimeException::class])
    fun handleException(ex: RuntimeException,request: WebRequest): ResponseEntity<String> =
            ResponseUtilities.createApiResponseEntity(
        MyConstants.FAILED,ex.message,
        HttpStatus.BAD_REQUEST)

    @ExceptionHandler(value =[JSONException::class])
    fun handleJSONException(ex: RuntimeException,request: WebRequest): ResponseEntity<String> =
        ResponseUtilities.createApiResponseEntity(
        MyConstants.FAILED,ex.message,
        HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value =[Exception::class])
    fun handleJSONException(ex: Exception,request: WebRequest): ResponseEntity<String> =
        ResponseUtilities.createApiResponseEntity(
            MyConstants.FAILED,ex.message,
            HttpStatus.BAD_REQUEST)


}