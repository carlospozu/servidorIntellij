package com.example.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class PokemonController {

    @GetMapping("prueba1")
    fun prueba1() {
        println("Me han llamado")
    }

    @GetMapping("prueba2")
    fun prueba2() : String {
        println("Han llamado a prueba2")
        return "Hello!"
    }

    @GetMapping("getPokemon")
    fun enviarPokemon() : Pokemon {
        return Pokemon("Pikachu", 15)
    }

    @GetMapping("getPokemonIniciales")
    fun getPokemonIniciales() : List<Pokemon> {
        return PokemonRepository.listaPokemon
    }

    @GetMapping("seleccionarPokemonFavorito/{id}")
    fun getPokemonFavorito(@PathVariable id: Int) : String {
        if (id < PokemonRepository.listaPokemon.size)
            return PokemonRepository.listaPokemon[id].toString()
        else
            return "Ese Pokemon no existe"
    }
}