package dev.boilerplate.gabrieltay.microservice.resolver

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import dev.boilerplate.gabrieltay.microservice.model.Character
import dev.boilerplate.gabrieltay.microservice.model.Droid
import dev.boilerplate.gabrieltay.microservice.model.Episode
import dev.boilerplate.gabrieltay.microservice.model.Human
import dev.boilerplate.gabrieltay.microservice.repository.CharacterRepository
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component

@Component
class Query(private val characterRepository: CharacterRepository): GraphQLQueryResolver {

    fun hero(episode: Episode?): Character? {
        return episode?.let {
            characterRepository.heroes?.get(episode)
        } ?:  characterRepository.characters?.get("1000")
    }

    fun character(id: String): Character? = characterRepository.characters?.get(id)

    fun human(id: String, env: DataFetchingEnvironment): Human? =
            characterRepository.characters?.filter {
                it.value is Human && it.key == id
            }?.values?.firstOrNull() as Human

    fun droid(id: String): Droid? =
            characterRepository.characters?.filter {
                it.value is Droid && it.key == id
            }?.values?.firstOrNull() as Droid
}
