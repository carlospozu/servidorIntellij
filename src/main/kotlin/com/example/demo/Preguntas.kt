package com.example.demo

import com.google.gson.Gson
data class Preguntas(var nombre: String, var respuesta1: Int,var respuesta2: Int,var respuesta3: Int,var respuesta4: Int,var respuestaCorrecta: Int,var id: Int) {
    override fun toString(): String {
        val gson = Gson()
        return gson.toJson(this)
    }
}