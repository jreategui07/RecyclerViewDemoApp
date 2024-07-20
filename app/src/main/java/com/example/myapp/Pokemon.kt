package com.example.myapp

import kotlin.random.Random

class Pokemon {
    var name:String
    var type:String
    var description:String
    var imageUrl:String
    var attackPower:Int

    constructor(
        name: String,
        type: String,
        description: String,
        imageUrl: String
    ) {
        this.name = name
        this.type = type
        this.description = description
        this.imageUrl = imageUrl
        // randomly generated number between 30-100
        this.attackPower = Random.nextInt(30, 100)
    }
}

// example pokemon

// var list:MutableList<Pokemon> = mutableListOf(
//    Pokemon("Pikachu", "electric", "When it is angered, it immediately discharges the energy stored in the pouches in its cheeks.", "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/025.png"),
//    Pokemon("Porygon", "normal", "It is an artificial Pokémon. Since it doesn’t breathe, people are excited by its potential to be useful in any environment.", "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/137.png"),
//    Pokemon("Charmander", "fire", "The flame on its tail shows the strength of its life-force. If Charmander is weak, the flame also burns weakly", "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/004.png"),
//    Pokemon("Skitty", "normal", "Skitty has the habit of becoming fascinated by moving objects and chasing them around. This Pokémon is known to chase after its own tail and become dizzy.", "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/300.png"),
//    Pokemon("Fuecoco", "fire", "It lies on warm rocks and uses the heat absorbed by its square-shaped scales to create fire energy.", "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/909.png"),
//    Pokemon("Tapu Koko", "electric", "Although it’s called a guardian deity, if a person or Pokémon puts it in a bad mood, it will become a malevolent deity and attack.", "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/785.png")
//)
