package com.example.demo

import com.google.gson.Gson
import org.springframework.web.bind.annotation.*
import kotlin.random.Random

@RestController
class PreguntaController(private val usuariosRepository: UsuariosRepository) {


    @GetMapping("getPregunta/{token}")
    fun enviarPregunta(@PathVariable token: String): String {
        var pregunta = ""
        val usuario = usuariosRepository.getById(encontrarUsuario(token))
        if (usuario.idrepe.size==0)
            return "No quedan preguntas"
        val numPregunta = usuario.idrepe[Random.nextInt(0, usuario.idrepe.size)]
        var i = 0
        var salir = false
        do {
            if (PreguntaRepository.listaPreguntas[i].id == numPregunta){
                salir = true
                usuario.idrepe.remove(numPregunta)
                usuariosRepository.save(usuario)
            } else
                i++
        } while (!salir && i < PreguntaRepository.listaPreguntas.size)

        val hola=(0 until PreguntaRepository.listaPreguntas.size).random()
        var gson = Gson()
        return gson.toJson(PreguntaRepository.listaPreguntas[hola])


/* "Pregunta: "+PreguntaRepository.listaPreguntas[hola].nombre+" Respuesta 1: "+PreguntaRepository.listaPreguntas[hola].respuesta1+" Respuesta 2: "+PreguntaRepository.listaPreguntas[hola].respuesta2+" Respuesta 3: "+PreguntaRepository.listaPreguntas[hola].respuesta3+" Respuesta 4: "+PreguntaRepository.listaPreguntas[hola].respuesta4+" id: "+PreguntaRepository.listaPreguntas[hola].id
    */
    }


    @GetMapping("getPregunta/{respuestaCorrecta}/{id}/{token}")
    fun responder(@PathVariable respuestaCorrecta: Int, @PathVariable id: Int, @PathVariable token: String) : String {
        val usuario = usuariosRepository.getById(encontrarUsuario(token))
        var i = 0
        var salir = false
        var respuesta = "ID incorrecto"
        do {
            if (PreguntaRepository.listaPreguntas[i].id == id) {
                salir = true
                respuesta = if (PreguntaRepository.listaPreguntas[i].respuestaCorrecta == respuestaCorrecta)
                    "Respuesta Correcta"
                else
                    "Respuesta Incorrecta"

                return if (PreguntaRepository.listaPreguntas[id].respuestaCorrecta == respuestaCorrecta)
                    "Respuesta Correcta"
                else
                    "Respuesta Incorrecta"
            } else
                i++
        } while (!salir && i < PreguntaRepository.listaPreguntas.size)
        return respuesta
    }

    @GetMapping("Registro/{usuario}/{contrasena}")
    fun iniciarSesion(@PathVariable usuario: String, @PathVariable contrasena: String): String {
        val token = generarToken()
        val idRespuestas = arrayListOf(0,1,2,3,4,5,6,7,8,9)
        val usuarioNuevo = Usuario(usuario,contrasena,idRespuestas, token,  )
        usuariosRepository.save(usuarioNuevo)
        return token     }


    @GetMapping("mostrarDatabase")
    fun mostrarDatabase():MutableList<Usuario>{
        return usuariosRepository.findAll()
    }


    fun generarToken():String{
        val alphabet = 'a'..'z'
        var generado =""
        for (i in 1..7){
            val randomLetter = alphabet.random()
            generado+=randomLetter         }
        return generado     }

    fun encontrarUsuario(token: String ): Int{
        var idUsuario = -1
        var lista = usuariosRepository.findAll()
        var i = 0
        var salir = false
        do{
        if (lista[i].token == token){
            println("Token $token encontrado. El del usuario es: ${lista[i].token}" )
            idUsuario = lista[i].id
            salir = true
        } else{
            println("Token $token no encontrado")
            i++
        }

    } while(!salir && i < lista.size)
    return idUsuario
    }
}







