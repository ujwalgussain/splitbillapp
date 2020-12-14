package com.app.splitbillapp.entities

import javax.persistence.*

@Entity
@Table(uniqueConstraints = arrayOf(UniqueConstraint(columnNames = ["lender","borrower"])))
data class BalanceSheet(
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    val id:Int = 0,
    @OneToOne
    @JoinColumn( name="lender")
    val lender:AppUser = AppUser(),
    @OneToOne
    @JoinColumn( name="borrower")
    val borrower:AppUser = AppUser(),
    @Column
    var amount:Int = 0
//OneToOne groupid
)



