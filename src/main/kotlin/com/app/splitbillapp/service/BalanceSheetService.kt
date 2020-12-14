package com.app.splitbillapp.service

import com.app.splitbillapp.MyController
import com.app.splitbillapp.entities.AppUser
import com.app.splitbillapp.entities.BalanceSheet
import com.app.splitbillapp.repo.BalanceSheetRepository
import com.app.splitbillapp.utlilities.MyConstants
import org.json.JSONArray
import org.json.JSONObject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BalanceSheetService(
    @Autowired private val balanceSheetRepository: BalanceSheetRepository,
    private val logger: Logger = LoggerFactory.getLogger(BalanceSheetService::class.java)
) {
    fun createOrUpdateEntry(owner:AppUser, otherUser:AppUser, amt:Int)
    {
        if(owner.equals(otherUser))
            return
        var entry:BalanceSheet ?= findEntry(owner,otherUser)
        if(entry==null)
        {
            //this is a new Entry
            entry = BalanceSheet(id = 0 , sheetOwner = owner, owesUser = otherUser, amount = 0)
        }
        entry.amount=entry.amount+amt
        balanceSheetRepository.save(entry)
    }
    fun findEntry(owner:AppUser, otherUser:AppUser) :BalanceSheet?
    {
        try{
            return balanceSheetRepository.findEntry(owner,otherUser)
        }
        catch (e:Exception)
        {
            return null
        }

    }


    fun getAllDues(id:Int): JSONObject
    {
        val entries:Collection<BalanceSheet> = balanceSheetRepository.findAllDues(id)
        val duesArr:JSONArray = JSONArray()
        if(entries.isNotEmpty()) {
            for (entry in entries) {
                val json:JSONObject = JSONObject()
                json.put("lender_user",JSONObject()
                        .put(MyConstants.USER_ID,entry.owesUser.id)
                        .put(MyConstants.USER_NAME,entry.owesUser.userName))
                json.put(MyConstants.AMT,entry.amount)

                duesArr.put(json)

                logger.info("${entry.sheetOwner.userName} owes ${entry.owesUser.userName} - Rs ${Math.abs(entry.amount)}")
            }
        }
        logger.info("dues array generated $duesArr")
        return JSONObject().put("dues",duesArr)
    }
}