package sample

class MainClass {
  companion object {
    @JvmStatic fun main(args: Array<String>) {
      println("The SERVICE_NAME environment variable is [${System.getenv("SERVICE_NAME")}]")
    }
  }
}
