package com.example.farmerapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject
/*
Karar verilmesi gereken konular;
    Kullanıcılar şirkete ait mi olamlı yani bir kullanıcı iki farklı şirkete çalıştığı zaman oraya nasıl giriş yapılmalı
    bir kullanıcı iki çifçi için tekrar kayıt olmak zorunda mı
 */
data class Customer
@Inject constructor(
    val name: String,
    val sourName: String,
    val phone: String,
    val address: String,
    val phoneNumber: String
){
    val id: Int = 0
}
