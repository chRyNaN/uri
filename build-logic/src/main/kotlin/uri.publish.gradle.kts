plugins {
    `maven-publish`
    // FIXME: Signingsigning
}

version = rootProject.version
group = rootProject.group

afterEvaluate {
    publishing {
        repositories {
            maven {
                url = uri("https://repo.repsy.io/mvn/chrynan/public")

                credentials {
                    username = (project.findProperty("repsyUsername")
                        ?: System.getenv("repsyUsername")) as? String
                    password = (project.findProperty("repsyPassword")
                        ?: System.getenv("repsyPassword")) as? String
                }
            }
        }

        if (plugins.hasPlugin("org.jetbrains.kotlin.multiplatform")) {
            // already has publications, just need to add javadoc task
            val javadocJar by tasks.creating(Jar::class) {
                from("javadoc")
                archiveClassifier.set("javadoc")
            }

            publications.all {
                if (this is MavenPublication) {
                    artifact(javadocJar)
                    mavenCentralPom()
                }
            }

            // create task to publish all apple (macos, ios, tvos, watchos) artifacts
            val publishApple by tasks.registering {
                publications.all {
                    if (name.contains(Regex("macos|ios|tvos|watchos"))) {
                        val publicationNameForTask = name.replaceFirstChar(Char::uppercase)
                        dependsOn("publish${publicationNameForTask}PublicationToSonatypeRepository")
                    }
                }
            }
        } else {
            // Need to create source, javadoc & publication
            val java = extensions.getByType<JavaPluginExtension>()

            java.withSourcesJar()
            java.withJavadocJar()

            publications {
                create<MavenPublication>("lib") {
                    from(components["java"])
                    mavenCentralPom()
                }
            }
        }
    }
}

fun MavenPublication.mavenCentralPom() {
    pom {
        name.set("uri")
        description.set("A Kotlin Multiplatform Utility Library for Uniform Resource Identifiers (URIs)")
        url.set("https://github.com/chRyNaN/uri")

        issueManagement {
            url.set("https://github.com/chRyNaN/uri/issues")
            system.set("Github Issues")
        }

        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }

        developers {
            developer {
                id.set("ckeenan")
                name.set("Chris Keenan")
                url.set("https://github.com/chRyNaN")
                roles.set(setOf("Primary uri developer. 💪"))
            }
        }

        scm {
            connection.set("https://github.com/chRyNaN/uri.git")
            developerConnection.set("https://github.com/chRyNaN/uri.git")
            url.set("https://github.com/chRyNaN/uri")
        }
    }
}

// Signs the artifacts that are published.
// NOTE: This is MANDATORY for publishing to Maven Central.
// This requires values in your home gradle.properties file.
// https://docs.gradle.org/current/userguide/signing_plugin.html
/* FIXME: Signing
signing {
isRequired = true
useGpgCmd()

publishing.publications.all {
    sign(this)
}
}

// TODO: remove after https://youtrack.jetbrains.com/issue/KT-46466 is fixed
project.tasks.withType(AbstractPublishToMaven::class.java).configureEach {
dependsOn(project.tasks.withType(Sign::class.java))
}
*/
