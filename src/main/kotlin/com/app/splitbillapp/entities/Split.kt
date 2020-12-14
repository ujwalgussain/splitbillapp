package com.app.splitbillapp.entities

import javax.persistence.*

@Entity
data class Split(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Int = 0,
    @Column
    val splitAmt:Int = 0,
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID" )
    val appUser:AppUser = AppUser()
)
