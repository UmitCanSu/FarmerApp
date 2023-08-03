package com.example.farmerapp.domain.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.farmerapp.domain.model.Company

@Dao
interface CompanyDao {
    @Insert
    fun insertCompany(company: Company):Long

    @Update
    fun updateCompany(company: Company):Long

    @Query("Select * from Company where id=:companyId")
    fun selectCompanyWithCompanyId(companyId: Int): Company

}