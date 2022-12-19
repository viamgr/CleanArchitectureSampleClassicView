import com.android.build.api.dsl.TestOptions
import com.android.build.gradle.AppExtension
import com.android.build.gradle.TestedExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.gradle.kotlin.dsl.project

object TestEnvironment {
    const val testInstrumentationRunner = "com.cleansample.android_test_shared.CustomTestRunner"
    const val testBuildType = "staging"
}

val Project.android: TestedExtension
    get() =
        (this as ExtensionAware).extensions.getByName("android") as TestedExtension


fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

fun DependencyHandler.stagingImplementation(dependencyNotation: Any) =
    add("stagingImplementation", dependencyNotation)


fun TestedExtension.commonTestOptions(action: (TestOptions.() -> Unit) = {}) {
    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
            it.testLogging {
                events(TestLogEvent.PASSED,
                    TestLogEvent.SKIPPED,
                    TestLogEvent.FAILED,
                    TestLogEvent.STANDARD_OUT,
                    TestLogEvent.STANDARD_ERROR)

                showStandardStreams = true
                showCauses = true
                showExceptions = true
                showStackTraces = true
                exceptionFormat = TestExceptionFormat.FULL
            }
        }
        action()
    }
}


fun Project.enableUnitTest() {

    afterEvaluate {
        android.apply {
            commonTestOptions()
        }
        dependencies.apply {

            testImplementation(project(BuildModules.Base.TEST_SHARED))
            stagingImplementation(project(BuildModules.BaseUi.ANDROID_TEST_SHARED))
            androidTestImplementation(project(BuildModules.BaseUi.ANDROID_TEST_SHARED))
        }
    }
}