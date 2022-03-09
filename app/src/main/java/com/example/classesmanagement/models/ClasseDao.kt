package com.example.classesmanagement.models


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ClasseDao {
    @Query("SELECT * FROM Classe")
    fun getAll(): List<Classe>

    @Query("SELECT * FROM Classe WHERE cid ==:cid")
    fun loadById(cid: Int): Classe

    @Query("SELECT * FROM Classe WHERE name LIKE :name0")
    fun findName(name0: String): Classe

    @Insert
    fun insertClass(classe: Classe)

    @Delete
    fun delete(classe: Classe)
}