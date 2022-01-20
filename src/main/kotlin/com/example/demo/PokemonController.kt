package com.example.demo

import org.springframework.web.bind.annotation.*

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

    @GetMapping("pokemon/{id}")
    fun getPokemonFavorito(@PathVariable id: Int) : String {
        return if (id < PokemonRepository.listaPokemon.size)
            PokemonRepository.listaPokemon[id].toString()
        else
            "Ese Pokemon no existe"
    }

    @DeleteMapping("pokemon/{id}")
    fun deletePokemon(@PathVariable id: Int) : Boolean {
        return if (id < PokemonRepository.listaPokemon.size) {
            PokemonRepository.listaPokemon.removeAt(id)
            true
        } else
            false
    }

    @PatchMapping("pokemon/{nombre}/{nivel}")
    fun anadirPokemon(@PathVariable nombre: String, @PathVariable nivel: Int) {
        PokemonRepository.listaPokemon.add(Pokemon(nombre, nivel))
    }
}