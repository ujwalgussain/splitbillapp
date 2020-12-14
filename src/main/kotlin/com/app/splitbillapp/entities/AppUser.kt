package com.app.splitbillapp.entities

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class AppUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Int = 0,

    @Column
    val userName: String = "",

    @Column
    val createdAt:LocalDateTime = LocalDateTime.now(),

    @Column
    val firstName:String = "",

    @Column
    val lastName:String = "",

   @Column(unique = true)
    val email:String = "",

    @Column(unique = true)
    val contactNo:String = "",

    @OneToMany
    @JoinColumn(name = "balancesheetid")
    val balanceSheetEntries: List<BalanceSheet>?= null

){

}
/*
data class Group(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Int,

    @Column
    val groupName: String,

    @Column
    val createdAt:LocalDateTime,

    @OneToMany(cascade = [CascadeType.ALL])
    val members:List<MemberDetails>,

    @OneToMany(cascade = [CascadeType.ALL])
    val bills:List<Bill>
)

data class MemberDetails(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Int,
    @OneToOne(cascade = [CascadeType.PERSIST])
    val user:AppUser,
    @Column
    val owe:Int
)

data class Bill(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Int,

    @Column
    val billName: String,

    @Column
    val billAmount:Int
)*/

