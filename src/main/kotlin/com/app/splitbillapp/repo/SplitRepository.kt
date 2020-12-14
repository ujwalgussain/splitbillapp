package com.app.splitbillapp.repo

import com.app.splitbillapp.entities.Split
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SplitRepository:JpaRepository<Split,Int> {
}