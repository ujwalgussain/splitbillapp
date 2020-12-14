package com.app.splitbillapp.repo

import com.app.splitbillapp.entities.AppUser
import com.app.splitbillapp.entities.BalanceSheet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BalanceSheetRepository:JpaRepository<BalanceSheet,Int> {
    @Query("SELECT b from BalanceSheet b where sheetOwner=?1 and owesUser=?2")
    fun findEntry(owner:AppUser,otherUser:AppUser):BalanceSheet

    @Query("SELECT b from BalanceSheet b where sheet_owner=?1 AND amount<0")
    fun findAllDues(id:Int) : Collection<BalanceSheet>
}