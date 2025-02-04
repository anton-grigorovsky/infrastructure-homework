import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.0"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    kotlin("jvm") version "1.6.20"
    kotlin("plugin.spring") version "1.6.20"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.6.20" apply false
    id("org.sonarqube") version "4.0.0.2929"
    id("com.github.ben-manes.versions") version "0.47.0"
    id("io.gitlab.arturbosch.detekt") version "1.21.0"
    id("info.solidsoft.pitest") version "1.7.0" apply false
}

allprojects {
    group = "com.stringconcat"

    repositories {
        mavenCentral()
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
    }

    apply(plugin = "io.gitlab.arturbosch.detekt")

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
            allWarningsAsErrors = true
        }
    }

    configurations.all {
        resolutionStrategy {
            eachDependency {
                val ver = requested.version
                val name = requested.name
                if (ver != null && ver.endsWith("-SNAPSHOT"))
                    throw GradleException("Found snapshot dependency: $name:$ver")
            }
        }
    }
}

subprojects {
    apply {
        plugin("java")
        plugin("info.solidsoft.pitest")
        plugin("java-test-fixtures")
        plugin("jacoco")
    }

    configure<info.solidsoft.gradle.pitest.PitestPluginExtension> {
        junit5PluginVersion.set("0.15")
        targetClasses.set(listOf("com.stringconcat.*"))
        threads.set(4)
        failWhenNoMutations.set(false)
        timestampedReports.set(false)
        outputFormats.set(listOf("HTML"))
        avoidCallsTo.set(setOf("kotlin.jvm.internal"))
    }

    tasks {
        val check = named<DefaultTask>("check")
        val jacocoTestReport = named<JacocoReport>("jacocoTestReport")
        val jacocoTestCoverageVerification = named<JacocoCoverageVerification>("jacocoTestCoverageVerification")

        check {
            finalizedBy(jacocoTestReport)
        }

        jacocoTestReport {
            dependsOn(check)
            finalizedBy(jacocoTestCoverageVerification)
        }

        jacocoTestCoverageVerification {
            dependsOn(jacocoTestReport)

            violationRules {

                rule {
                    excludes = listOf("e2e")
                    limit {
                        minimum = BigDecimal("0.8")
                    }
                }
            }
        }
    }
}

java.sourceCompatibility = JavaVersion.VERSION_11


dependencies {
    // spring modules
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-rest")

    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation(project(":presentation"))
    implementation(project(":persistence"))
    implementation(project(":useCasePeople"))
    implementation(project(":businessPeople"))
    implementation(project(":quoteGarden"))
    implementation(project(":avatarsDicebear"))

    // dev tools
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // tests
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("io.projectreactor:reactor-test")
}

tasks.test {
    useJUnitPlatform()
}