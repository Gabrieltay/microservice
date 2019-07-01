package dev.boilerplate.gabrieltay.microservice.repository

import dev.boilerplate.gabrieltay.microservice.model.Character
import dev.boilerplate.gabrieltay.microservice.model.Episode
import org.springframework.stereotype.Component
import dev.boilerplate.gabrieltay.microservice.model.Droid
import dev.boilerplate.gabrieltay.microservice.model.Human
import java.util.*


@Component
class CharacterRepository {
    var characters: Map<String, Character>? = null
    var heroes: Map<Episode, Character>? = null

    init {
        val lukeSkywalker = Human("1000", "Luke Skywalker", listOf(Episode.NEWHOPE, Episode.JEDI, Episode.EMPIRE), "Tatooine")
        val darthVader = Human("1001", "Darth Vader", Arrays.asList(Episode.NEWHOPE, Episode.JEDI, Episode.EMPIRE), "Tatooine")
        val hanSolo = Human("1002", "Han Solo", Arrays.asList(Episode.NEWHOPE, Episode.JEDI, Episode.EMPIRE), null)
        val leiaOrgana = Human("1003", "Leia Organa", Arrays.asList(Episode.NEWHOPE, Episode.JEDI, Episode.EMPIRE), "Alderaan")
        val wilhuffTarkin = Human("1004", "Wilhuff Tarkin", Collections.singletonList(Episode.NEWHOPE), null)

        val c3po = Droid("2000", "C-3PO", Arrays.asList(Episode.NEWHOPE, Episode.JEDI, Episode.EMPIRE), "Protocol")
        val aretoo = Droid("2001", "R2-D2", Arrays.asList(Episode.NEWHOPE, Episode.JEDI, Episode.EMPIRE), "Astromech")

        lukeSkywalker.addFriends(hanSolo, leiaOrgana, c3po, aretoo)
        darthVader.addFriends(wilhuffTarkin)
        hanSolo.addFriends(lukeSkywalker, leiaOrgana, aretoo)
        leiaOrgana.addFriends(lukeSkywalker, hanSolo, c3po, aretoo)
        wilhuffTarkin.addFriends(darthVader)

        c3po.addFriends(lukeSkywalker, hanSolo, leiaOrgana, aretoo)
        aretoo.addFriends(lukeSkywalker, hanSolo, leiaOrgana)

        characters = listOf(lukeSkywalker, darthVader, hanSolo, leiaOrgana, wilhuffTarkin, c3po, aretoo).map {
            it.id to it
        }.toMap()

        heroes = mapOf(Episode.NEWHOPE to lukeSkywalker, Episode.EMPIRE to aretoo, Episode.JEDI to darthVader)
    }
}