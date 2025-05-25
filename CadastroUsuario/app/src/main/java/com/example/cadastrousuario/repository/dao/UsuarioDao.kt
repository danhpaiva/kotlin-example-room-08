package com.example.cadastrousuario.repository.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.cadastrousuario.model.Usuario

@Dao
interface UsuarioDao {
    @Insert
    fun insertUser(usuario: Usuario): Long
    @Update
    fun updateUser(usuario: Usuario): Int
    @Delete
    fun deleteUser(usuario: Usuario): Int
    @Query("SELECT * FROM Usuarios WHERE id = :id")
    fun get(id: Int): Usuario
    @Query("SELECT * FROM Usuarios")
    fun getAll(): List<Usuario>
}