plugins {
    kotlin("jvm") version "2.1.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "me.axiumyu.expensivedeathsk"
version = "1.0.0"

repositories {
    mavenLocal()
    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "papermc-repo"
    }
    maven("https://oss.sonatype.org/content/groups/public/") {
        name = "sonatype"
    }
    maven("https://jitpack.io")
    mavenCentral()
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compileOnly("com.github.YiC200333:XConomyAPI:2.25.1")
}

val targetJavaVersion = 21
kotlin {
    jvmToolchain(targetJavaVersion)
}

tasks.build {
    dependsOn("shadowJar")
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}
