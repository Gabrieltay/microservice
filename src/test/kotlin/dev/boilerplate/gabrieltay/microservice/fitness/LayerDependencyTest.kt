package dev.boilerplate.gabrieltay.microservice.fitness

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import com.tngtech.archunit.library.Architectures.layeredArchitecture

@AnalyzeClasses(packages = ["dev.boilerplate.gabrieltay.microservice"],
        importOptions = [ImportOption.DoNotIncludeTests::class])
class LayerDependencyTest {

    @ArchTest
    val layerDepdenciesRule = layeredArchitecture()
        .layer("Dto").definedBy("dev.boilerplate.gabrieltay.microservice.controller.dto")
        .layer("Configuration").definedBy("dev.boilerplate.gabrieltay.microservice.configuration")
        .layer("Controller").definedBy("dev.boilerplate.gabrieltay.microservice.controller")
        .layer("Model").definedBy("dev.boilerplate.gabrieltay.microservice.model")
        .layer("Repository").definedBy("dev.boilerplate.gabrieltay.microservice.repository")
        .layer("Service").definedBy("dev.boilerplate.gabrieltay.microservice.service")

        .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
        .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
        .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service")

    @ArchTest
    fun `dto should only be accessed by controller and service`(importedClasses: JavaClasses) {
        val rule = ArchRuleDefinition.classes().that().resideInAPackage("dev.boilerplate.gabrieltay.microservice.dto")
            .should().onlyBeAccessed().byAnyPackage(
                "dev.boilerplate.gabrieltay.microservice.controller",
                "dev.boilerplate.gabrieltay.microservice.service")
        rule.check(importedClasses)
    }
}
