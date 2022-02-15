package com.example.demo

import com.google.gson.Gson
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Usuario (var nombre: String, var contrasena: String, var idrepe:ArrayList<Int>, var token: String){
    @Id
    @GeneratedValue
    var id = 0
    override fun toString(): String {
        val gson = Gson()
        return gson.toJson(this)
    }
}