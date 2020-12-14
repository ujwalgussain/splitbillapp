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
    fun createOrUpdateEntry(owner: AppUser, otherUser: AppUser, amt: Int) {
        if (owner.equals(otherUser))
            return
        var entry: BalanceSheet? = findEntry(owner, otherUser)
        if (entry == null) {
            //this is a new Entry
            entry = BalanceSheet(id = 0, lender = owner, borrower = otherUser, amount = 0)
        }
        entry.amount = entry.amount + amt
        balanceSheetRepository.save(entry)
    }

    fun findEntry(owner: AppUser, otherUser: AppUser): BalanceSheet? {
        try {
            return balanceSheetRepository.findEntry(owner, otherUser)
        } catch (e: Exception) {
            return null
        }

    }


    fun getAllDues(id: Int): JSONObject {
        fun createJSON(entry: BalanceSheet): JSONObject = JSONObject()
            .put(MyConstants.USER_ID, entry.borrower.id)
            .put(MyConstants.USER_NAME, entry.borrower.userName)
            .put(MyConstants.AMT, Math.abs(entry.amount))

        fun createRSJSON(
            willgiveArr: JSONArray,
            willgetArr: JSONArray,
            willgive_Total: Int,
            willget_Total: Int
        ): JSONObject {
            val responseJSON: JSONObject = JSONObject()
            val willgetJSON = JSONObject().put(MyConstants.TOTAL_AMOUNT, willget_Total)
                .put(MyConstants.PAY_DETAILS, willgetArr)

            val willgiveJSON = JSONObject().put(MyConstants.TOTAL_AMOUNT, willgive_Total)
                .put(MyConstants.PAY_DETAILS, willgiveArr)

            responseJSON.put(
                MyConstants.DUES, JSONObject()
                    .put(MyConstants.WILL_GET, willgetJSON)
                    .put(MyConstants.WILL_GIVE, willgiveJSON)
            )
            return responseJSON
        }

        val entries: Collection<BalanceSheet> = balanceSheetRepository.findAllDues(id)
        val willgiveArr: JSONArray = JSONArray()
        var willgive_Total: Int = 0
        var willget_Total: Int = 0
        var willgetArr: JSONArray = JSONArray()
        if (entries.isNotEmpty()) {
            for (entry in entries) {

                if (entry.amount > 0) {
                    //lender will get OR borrower owes lender
                    willgetArr.put(createJSON(entry))
                    willget_Total += (entry.amount)

                } else {
                    //lender will give OR lender owes borrower
                    willgiveArr.put(createJSON(entry))
                    willgive_Total += entry.amount
                }
                logger.info("Parsed Entry: $entry")
            }
        }
        return createRSJSON(willgiveArr, willgetArr, willgive_Total, willget_Total)
    }

    fun getDuesBetweenUser(rqJSONObject: JSONObject) :JSONObject
    {
        val lenderID:Int = rqJSONObject.getInt(MyConstants.LENDER)
        val borrowerID:Int = rqJSONObject.getInt(MyConstants.BORROWER)
        val entries:Collection<BalanceSheet> = balanceSheetRepository.findDuesBetweenUser(lenderID,borrowerID)
        var willget:Int=0
        var willgive:Int=0
        if(entries.size > 0)
        {
            val entry:BalanceSheet = entries.first()
            if(entry.amount>0)
                willget=entry.amount
            else
                willgive=Math.abs(entry.amount)
        }
        val rsJSONObject:JSONObject =JSONObject(rqJSONObject.toString())
            .put(MyConstants.WILL_GET,willget)
            .put(MyConstants.WILL_GIVE,willgive)
        return rsJSONObject

    }

}