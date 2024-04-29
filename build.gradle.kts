plugins {
  id("java-gradle-plugin")
  id("org.jetbrains.kotlin.jvm").version("1.9.22")
}

dependencies {
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
  testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.3")
  testImplementation(platform("io.kotest:kotest-bom:5.6.2"))
  testImplementation("io.kotest:kotest-assertions-core:5.6.2")
  testImplementation("io.kotest:kotest-assertions-shared:5.6.2")

  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.3")
}

tasks.withType<Test>().configureEach {
  useJUnitPlatform()
}
