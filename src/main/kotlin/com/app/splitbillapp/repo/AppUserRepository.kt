package com.app.splitbillapp.repo

import com.app.splitbillapp.entities.AppUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AppUserRepository : JpaRepository<AppUser,Int> {

}