package com.app.splitbillapp.entities

import javax.persistence.*

@Entity
data class Bill(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Int = 0,
    @Column
    val amount:Int = 0,
    @Column
    val billDescription:String,
    @OneToOne()
    @JoinColumn( name = "userID")
    val paidBy:AppUser = AppUser(),
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "splitID")
    val splits:List<Split>? = null

)
