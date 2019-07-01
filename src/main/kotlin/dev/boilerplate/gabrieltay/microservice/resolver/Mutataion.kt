package dev.boilerplate.gabrieltay.microservice.resolver

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import dev.boilerplate.gabrieltay.microservice.model.Human
import org.springframework.stereotype.Component
import java.util.*

@Component
class Mutataion: GraphQLMutationResolver {

    fun createHuman(createHumanInput: Map<String, String>): Human {
        var name: String? = null

        if (createHumanInput.containsKey("name")) {
            name = createHumanInput.get("name")
        }

        var homePlanet: String? = "Jakku"

        if (createHumanInput.containsKey("homePlanet")) {
            homePlanet = createHumanInput.get("homePlanet")
        }

        return Human(UUID.randomUUID().toString(), name!!, emptyList(), homePlanet )
    }
}