package com.example.farmerapp.domain.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.farmerapp.data.local.dto.CompanyDto

@Dao
interface CompanyDao {
    @Insert
    fun insertCompany(company: CompanyDto): Long

    @Update
    fun updateCompany(company: CompanyDto): Int

    @Query("Select * from Company where apiId=:companyApiId")
    fun selectCompanyByCompanyApiId(companyApiId: String): CompanyDto?

    @Query("Select * from Company where phone like '%' || :phoneNumber and name like '%'|| :name")
    fun selectCompanyByPhoneNumberAndName(phoneNumber: String, name: String): CompanyDto?

}