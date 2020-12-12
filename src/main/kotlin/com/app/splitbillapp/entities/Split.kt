package com.app.splitbillapp.entities

import javax.persistence.*

@Entity
data class Split(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Int,
    @Column
    val splitAmt:Int,
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID" )
    val appUser:AppUser
)
