package com.app.splitbillapp.repo

import com.app.splitbillapp.entities.AppUser
import com.app.splitbillapp.entities.BalanceSheet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BalanceSheetRepository:JpaRepository<BalanceSheet,Int> {
    @Query("SELECT b from BalanceSheet b where lender=?1 and borrower=?2")
    fun findEntry(owner:AppUser,otherUser:AppUser):BalanceSheet

    @Query("SELECT * from balance_sheet b where b.lender = ?1", nativeQuery = true)
    fun findAllDues(id:Int) : Collection<BalanceSheet>

    @Query("SELECT sum(b.amount) from balance_sheet b where borrower = ?1 AND amount<0" ,  nativeQuery = true)
    fun findTotalDue(id:Int) : Collection<Int>

    @Query("SELECT * from balance_sheet b where b.lender = ?1 AND b.borrower = ?2", nativeQuery = true)
    fun findDuesBetweenUser(lenderID:Int,borrowerID:Int) : Collection<BalanceSheet>
}