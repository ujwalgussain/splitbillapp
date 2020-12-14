package com.app.splitbillapp.repo

import com.app.splitbillapp.entities.Bill
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BillRepository:JpaRepository<Bill,Int> {
}