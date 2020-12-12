package com.app.splitbillapp.entities

import javax.persistence.*

@Entity
data class Bill(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Int,
    @Column
    val amount:Int,
    @OneToOne
    @JoinColumn( name = "userID")
    val paidBy:AppUser,
    @OneToMany
    @JoinColumn(name = "splitID")
    val splits:List<Split>
)
