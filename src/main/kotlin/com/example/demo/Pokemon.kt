package com.example.demo

import com.google.gson.Gson
import com.google.gson.JsonObject
import netscape.javascript.JSObject

data class Pokemon(var nombre: String, var nivel: Int) {
    override fun toString(): String {
        val gson = Gson()
        val json = gson.toJson(this)
        val jsonObject = JsonObject()
        jsonObject.
        return gson.toJson(this)
    }
}