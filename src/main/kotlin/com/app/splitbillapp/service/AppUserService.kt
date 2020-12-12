package com.app.splitbillapp.service

import com.app.splitbillapp.entities.AppUser
import com.app.splitbillapp.repo.AppUserRepository
import com.app.splitbillapp.utlilities.MyConstants
import org.json.JSONObject
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

@Service
class AppUserService(
    @Autowired private val userRepository: AppUserRepository) {

    fun createUser(rq_json:JSONObject)
    {
        //save the User to DB
          userRepository.save(AppUser(id = 0,
              userName = rq_json.getString(MyConstants.USER_NAME),
              firstName = rq_json.getString(MyConstants.FIRST_NAME),
              email = rq_json.getString(MyConstants.EMAIL),
              lastName = rq_json.getString(MyConstants.LAST_NAME),
              contactNo = rq_json.getString(MyConstants.CONTACT)
              ))
    }

    fun deleteUser(id:Int)
    {
        userRepository.deleteById(id)
    }
}