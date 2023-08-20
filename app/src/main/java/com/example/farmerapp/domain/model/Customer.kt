package com.example.farmerapp.domain.model

import javax.inject.Inject

/*
Karar verilmesi gereken konular;
    Kullanıcılar şirkete ait mi olamlı yani bir kullanıcı iki farklı şirkete çalıştığı zaman oraya nasıl giriş yapılmalı
    bir kullanıcı iki çifçi için tekrar kayıt olmak zorunda mı
 */
data class Customer
@Inject constructor(
    val id: Int = 0,
    val name: String,
    val surName: String,
    val phone: String,
    val address: String,
) {
    constructor(
        name: String,
        surName: String,
        phone: String,
        address: String,
    ) : this(0, name, surName, phone, address)
}
