package com.app.splitbillapp.service

import com.app.splitbillapp.entities.AppUser
import com.app.splitbillapp.entities.Bill
import com.app.splitbillapp.entities.Split
import com.app.splitbillapp.repo.AppUserRepository
import com.app.splitbillapp.repo.BillRepository
import com.app.splitbillapp.repo.SplitRepository
import com.app.splitbillapp.utlilities.MyConstants
import com.app.splitbillapp.utlilities.ResponseUtilities
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.util.ClassUtil
import org.json.JSONArray
import org.json.JSONObject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import java.nio.charset.Charset
import java.util.*
import javax.transaction.Transactional


@Service
class BillService(
    @Autowired private val billRepository: BillRepository,
    @Autowired private val splitService: SplitService,
    @Autowired private val userService:AppUserService,
    @Autowired private val balanceSheetService:BalanceSheetService,
    @Autowired private val objectMapper: ObjectMapper,
    val logger:Logger = LoggerFactory.getLogger(BillService::class.java)
) {
    @Transactional
    fun addBill(rqJSON:JSONObject)
    {
        val paidByUser:AppUser = userService.findAppUserById(rqJSON.getInt(MyConstants.PAID_BY))
        val amt:Int = rqJSON.getInt(MyConstants.AMT)
        if(paidByUser == null)
            throw HttpClientErrorException(HttpStatus.BAD_REQUEST,"Cannot Create Bill. User Not Found.")
        logger.info("Paid By User Found $paidByUser")
        val arr:JSONArray=rqJSON.getJSONArray(MyConstants.SPLIT_USER)
        logger.info("Started Split Creation")
        val splits = splitService.createSplits(arr,amt)
        val bill:Bill = Bill(amount = amt,
                        paidBy = paidByUser,
                        splits = splits,
                        id =0
                        )
        for(split in splits)
        {
            balanceSheetService.createOrUpdateEntry(paidByUser,split.appUser,split.splitAmt)
            logger.info(String.format("Entry Added for ${paidByUser.userName} -> ${split.appUser.userName}"))
            balanceSheetService.createOrUpdateEntry(split.appUser,paidByUser,-split.splitAmt)
            logger.info(String.format("Entry Added for ${split.appUser.userName} -> ${paidByUser.userName}"))

        }
        billRepository.save(bill)
    }

    fun getBill(id:Int) : String
    {
        val bill:Optional<Bill>
                = billRepository.findById(id)
        if(bill.isEmpty)
            throw HttpClientErrorException(HttpStatus.BAD_REQUEST,"bill not found",
                ResponseUtilities.createApiFailureResponseJSON("bill Not found in the DB").toString().toByteArray(),
                Charset.defaultCharset())
        return objectMapper.writeValueAsString(bill)
    }
}