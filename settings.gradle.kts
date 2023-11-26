pluginManagement {
    repositories {
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "org.springframework.boot") {
                useModule("org.springframework.boot:spring-boot-gradle-plugin:${requested.version}")
            }
        }
    }
}
rootProject.name = "people"
include(":businessPeople")
include(":presentation")
include(":useCasePeople")
include(":persistence")
include(":quoteGarden")
include(":avatarsDicebear")
include("tests")
include("tests:e2e")
findProject(":tests:e2e")?.name = "e2e"
include("tests:performance")
findProject(":tests:performance")?.name = "performance"
include("tests:common")
findProject(":tests:common")?.name = "common"
