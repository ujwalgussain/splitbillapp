package com.app.splitbillapp.entities

import javax.persistence.*

@Entity
@Table(uniqueConstraints = arrayOf(UniqueConstraint(columnNames = ["sheetOwner","owesUser"])))
data class BalanceSheet(
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    val id:Int = 0,
    @OneToOne
    @JoinColumn( name="sheetOwner")
    val sheetOwner:AppUser = AppUser(),
    @OneToOne
    @JoinColumn( name="owesUser")
    val owesUser:AppUser = AppUser(),
    @Column
    var amount:Int = 0 )
