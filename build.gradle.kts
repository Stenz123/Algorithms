import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType

plugins {
    kotlin("jvm") version "1.9.22"
    `java-library`
    `maven-publish`
    signing
}

group = "dev.stenz.algorithms"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed")
    }
}

kotlin {
    jvmToolchain(21)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "basic-algorithms"
            from(components["java"])
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name = "Competitive Programming Algorithms"
                description = "Basic algorithms for competitive programming"
                url = "https://github.com/Stenz123/algorithms/"
                //properties = mapOf(
                //    "myProp" to "value",
                //    "prop.with.dots" to "anotherValue"
                //)
                licenses {
                    license {
                        name = "The Apache License, Version 2.0"
                        url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                    }
                }
                developers {
                    developer {
                        id = "mStenz"
                        name = "Michael Stenz"
                        email = "michael.stenz2006@gmail.com"
                    }
                }
                scm {
                    connection = "scm:git:git://github.com/Stenz123/algorithms/"
                    developerConnection = "scm:git:ssh://github.com/Stenz123/algorithms/"
                    url = "https://github.com/Stenz123/algorithms/"
                }
            }
        }
    }
    repositories {
        if (version.toString().endsWith("SNAPSHOT")) {
            maven("https://s01.oss.sonatype.org/content/repositories/snapshots/") {
                name = "sonatypeReleaseRepository"
                credentials(PasswordCredentials::class)
            }
        } else {
            maven("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/") {
                name = "sonatypeSnapshotRepository"
                credentials(PasswordCredentials::class)
            }
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}