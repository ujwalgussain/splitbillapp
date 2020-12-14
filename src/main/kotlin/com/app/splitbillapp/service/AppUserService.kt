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

    fun deleteUser(id:Int)
    {
        userRepository.deleteById(id)
    }

}