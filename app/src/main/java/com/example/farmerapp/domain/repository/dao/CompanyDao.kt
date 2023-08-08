package com.example.farmerapp.domain.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.farmerapp.data.local.dto.CompanyDto
import com.example.farmerapp.domain.model.Company

@Dao
interface CompanyDao {
    @Insert
    fun insertCompany(company: CompanyDto):Long

    @Update
    fun updateCompany(company: CompanyDto):Int

    @Query("Select * from Company where id=:companyId")
    fun selectCompanyWithCompanyId(companyId: Int): CompanyDto

}