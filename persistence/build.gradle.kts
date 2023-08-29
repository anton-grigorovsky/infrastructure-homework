plugins {
    kotlin("jvm")
    id("io.spring.dependency-management")
    id("org.jetbrains.kotlin.plugin.jpa")
}

java.sourceCompatibility = JavaVersion.VERSION_11

dependencies {
    implementation(project(":businessPeople"))
    implementation(project(":useCasePeople"))

    implementation("javax.persistence:javax.persistence-api:2.2")
    implementation("org.apache.logging.log4j:log4j-core:2.17.2")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.7.0")

    //persistance
    implementation("org.postgresql:postgresql:42.3.6")
    implementation("org.liquibase:liquibase-core:4.11.0")

    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // tests
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.2")
    testImplementation("org.testcontainers:postgresql:1.16.2")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.0") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }

    testImplementation("org.assertj:assertj-core:3.24.2")

    testImplementation(testFixtures(project(":businessPeople")))
    testFixturesImplementation(testFixtures(project(":businessPeople")))

    testFixturesImplementation("org.testcontainers:postgresql:1.16.2")
    testFixturesImplementation("org.liquibase:liquibase-core:4.6.1")
    testFixturesImplementation("org.springframework.boot:spring-boot-starter-data-jpa:2.7.0")
}

tasks.test {
    useJUnitPlatform()
}