package com.example.farmerapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.farmerapp.model.Company

@Dao
interface CompanyDao {
    @Insert
    fun insertCompany(company: Company)

    @Update
    fun updateCompany(company: Company)

    @Query("Select * from Company where id=:companyId")
    fun selectCompanyWithCompanyId(companyId: Int): Company

}