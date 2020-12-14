package com.app.splitbillapp.service

import com.app.splitbillapp.entities.AppUser
import com.app.splitbillapp.entities.Split
import org.json.JSONArray
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SplitService (@Autowired private val appUserService: AppUserService,
                    val logger: Logger = LoggerFactory.getLogger(BillService::class.java)){
    fun createSplits(arr:JSONArray, total:Int) : MutableList<Split>
    {
        val splitAmt:Int = total / arr.length()
        val splits:MutableList<Split> = mutableListOf()
        for(i in 0..arr.length()-1)
        {
            val userId:Int = arr.getInt(i)
            val appUser:AppUser = appUserService.findAppUserById(userId)
            if(appUser == null)
                throw Exception("Split Could not be Created. User Not Found")
            logger.info("Split User Found $appUser")
            splits.add(Split(id=0,splitAmt = splitAmt,appUser))
        }
        logger.info("Split Creation is Successfuk")
        return splits
    }
}