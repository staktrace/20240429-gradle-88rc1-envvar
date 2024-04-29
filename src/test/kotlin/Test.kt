import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldContain
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.gradle.util.GradleVersion
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.io.File

internal class Test {
  @ParameterizedTest(name = "{0}")
  @MethodSource("gradleVersions")
  fun `doesn't override any existing SERVICE_NAME envar`(gradleVersion: GradleVersion) {
    val result = GradleRunner.create()
      .withProjectDir(File("src/test/projects/sample-project"))
      .withGradleVersion(gradleVersion.version)
      .withEnvironment(mapOf("SERVICE_NAME" to "testServiceName"))
      .withArguments("clean", "run")
      .build()

    assertSoftly {
      val task = result.task(":run")
      task shouldNotBe null
      task?.outcome shouldBe TaskOutcome.SUCCESS
      result.output shouldContain "The SERVICE_NAME environment variable is [testServiceName]"
    }
  }

  protected companion object {
    @JvmStatic protected fun gradleVersions(): Set<GradleVersion> = setOf(
      GradleVersion.current(),
      GradleVersion.version("8.8-rc-1")
    )
  }
}
