import com.mooncloak.kodetools.kenv.Kenv
import com.mooncloak.kodetools.kenv.dotenv
import com.mooncloak.kodetools.kenv.properties
import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class BuildVariablesPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        projectBuildVariables[target.name] = BuildVariables(
            kenv = Kenv {
                properties(file = target.rootProject.layout.projectDirectory.file("library.properties").asFile)

                val dotEnvFile = target.rootProject.layout.projectDirectory.file(".env").asFile
                if (dotEnvFile.exists()) {
                    dotenv(file = dotEnvFile)
                }

                system()
            }
        )
    }
}

class BuildVariables internal constructor(
    private val kenv: Kenv
) {

    val group: String
        get() = kenv["group"].value

    val version: String
        get() = kenv["version"].value

    val versionCode: Int
        get() = TODO("Use git commit count.")

    val mavenCentralUsername: String
        get() = kenv["maven_central_username"].value

    val mavenCentralPassphrase: String
        get() = kenv["maven_central_password"].value

    // The following values are for signing the artifacts to publish to Maven Central.
    // These values MUST be located in the ~/.gradle/gradle.properties file. They are
    // provided here for future use, of the in-memory approach, not yet supported.
    // TODO: Support in-memory signing keys.
    val signingKeyId: String
        get() = kenv["signing.keyId"].value
    val signingPassword: String
        get() = kenv["signing.password"].value
    val signingKey: String
        get() = kenv["signing.secretKeyRingFile"].value
}

val Project.buildVariables: BuildVariables
    get() {
        var variables = projectBuildVariables[this.name]

        if (variables == null) {
            this.logger.warn("The '${BuildVariablesPlugin::class.simpleName}' was not applied to project with name '${this.name}'. Attempting to load root project build variables.")
        }

        if (this != this.rootProject) {
            variables = projectBuildVariables[this.rootProject.name]
        }

        return variables
            ?: error("Failed to load required build variables. Make sure the '${BuildVariablesPlugin::class.simpleName}' is applied to the project.")
    }

private val projectBuildVariables = mutableMapOf<String, BuildVariables>()
