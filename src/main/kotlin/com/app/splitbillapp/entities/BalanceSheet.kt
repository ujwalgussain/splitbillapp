package com.app.splitbillapp.entities

import javax.persistence.*

@Entity
data class BalanceSheet(
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    val id:Int,
    @OneToOne
    @JoinColumn( name="owes_userID")
    val owes_user:AppUser,
    @Column
    val amount:Int)
