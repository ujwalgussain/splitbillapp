package com.app.splitbillapp.service

import com.app.splitbillapp.MyController
import com.app.splitbillapp.entities.AppUser
import com.app.splitbillapp.repo.AppUserRepository
import com.app.splitbillapp.utlilities.MyConstants
import org.json.JSONException
import org.json.JSONObject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.client.HttpClientErrorException
import java.lang.RuntimeException
import java.util.*

@Service
class AppUserService(
    @Autowired private val userRepository: AppUserRepository,
    private val logger: Logger = LoggerFactory.getLogger(AppUserService::class.java)) {

    fun createUser(rq_json:JSONObject)
    {
        //save the User to DB
        var appUser:AppUser?=null
        try{
            appUser = AppUser(id = 0,
                userName = rq_json.getString(MyConstants.USER_NAME),
                firstName = rq_json.getString(MyConstants.FIRST_NAME),
                email = rq_json.getString(MyConstants.EMAIL),
                lastName = rq_json.getString(MyConstants.LAST_NAME),
                contactNo = rq_json.getString(MyConstants.CONTACT)
            )
        }catch (e:JSONException)
        {
            logger.error("User Creation Failed. Unable to Parse JSON")
            throw e
        }
          userRepository.save(AppUser(id = 0,
              userName = rq_json.getString(MyConstants.USER_NAME),
              firstName = rq_json.getString(MyConstants.FIRST_NAME),
              email = rq_json.getString(MyConstants.EMAIL),
              lastName = rq_json.getString(MyConstants.LAST_NAME),
              contactNo = rq_json.getString(MyConstants.CONTACT)
              ))
    }
    fun findAppUserById(id: Int) : AppUser = userRepository.findById(id).get()

    fun updateUser(rq_json: JSONObject)
    {
        val userID = rq_json.getInt(MyConstants.USER_ID)
        val record:Optional<AppUser> = userRepository.findById(userID)
        if(record.isEmpty)
            throw RuntimeException("Invalid User ID")
        val appUser:AppUser = record.get()
        val newValue:String = rq_json.getString(MyConstants.NEW_VALUE)
        when(rq_json.getString(MyConstants.FIELD))
        {
            MyConstants.USER_NAME -> {
                appUser.userName = newValue
            }
            MyConstants.FIRST_NAME -> {
                appUser.firstName = newValue
            }
            MyConstants.LAST_NAME ->
            {
                appUser.lastName = newValue
            }
            MyConstants.EMAIL -> {
                appUser.email = newValue
            }
            MyConstants.CONTACT ->
            {
                appUser.contactNo = newValue
            }
            else ->{
                val msg:String = "Invalid Field for Update"
                logger.info(msg)
                throw RuntimeException(msg)
            }
        }
        userRepository.save(appUser)
    }
    fun deleteUser(id:Int)
    {
        userRepository.deleteById(id)
    }

}