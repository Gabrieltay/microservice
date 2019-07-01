package dev.boilerplate.gabrieltay.microservice.model

open class Character(
        open val id: String,
        open val name: String,
        open var friends: MutableList<Character> = mutableListOf<Character>(),
        open val appearsIn: List<Episode>
) {
    fun addFriends(vararg friends: Character) {
        this.friends.addAll(friends)
    }
}

class Human(
        id: String,
        name: String,
        appearsIn: List<Episode>,
        val homePlanet: String?): Character(id = id, name = name, appearsIn = appearsIn)

class Droid(
        id: String,
        name: String,
        appearsIn: List<Episode>,
        val primaryFunction: String?): Character(id = id, name = name, appearsIn = appearsIn)

enum class Episode{
    NEWHOPE,
    EMPIRE,
    JEDI
}

