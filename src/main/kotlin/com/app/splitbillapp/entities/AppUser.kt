package com.app.splitbillapp.entities

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class AppUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Int = 0,

    @Column
    var userName: String = "",

    @Column
    var createdAt:LocalDateTime = LocalDateTime.now(),

    @Column
    var firstName:String = "",

    @Column
    var lastName:String = "",

   @Column(unique = true)
    var email:String = "",

    @Column(unique = true)
    var contactNo:String = "",

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

