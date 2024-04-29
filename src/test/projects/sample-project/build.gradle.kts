plugins {
  id("application")
  id("org.jetbrains.kotlin.jvm") version "1.9.22"
}

application {
  mainClass.set("sample.MainClass")
}

val SERVICE_NAME = "SERVICE_NAME"
val serviceName = providers.environmentVariable(SERVICE_NAME)

tasks.named<org.gradle.api.tasks.JavaExec>("run").configure {
  if (serviceName.orNull.isNullOrEmpty()) {
    environment(SERVICE_NAME, "defaultServiceName")
  }
}
