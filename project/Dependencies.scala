import sbt._

object Dependencies {

  // Unit and integration tests
  val scalatest: ModuleID = "org.scalatest" %% "scalatest" % "3.0.8"

  // Property based testing
  val scalacheck: ModuleID = "org.scalacheck" %% "scalacheck" % "1.14.0"

  // ScalaFX library
  val `scala-fx`: ModuleID = "org.scalafx" %% "scalafx" % "12.0.2-R18"

  // JavaFX libraries depending on the operating system. Needed for JDK 11 and above because they are no longer
  // included in the JRE.
  private lazy val osName = System.getProperty("os.name") match {
    case n if n.startsWith("Linux")   => "linux"
    case n if n.startsWith("Mac")     => "mac"
    case n if n.startsWith("Windows") => "win"
    case _                            => throw new Exception("Unknown platform!")
  }
  private lazy val javaFXModules = Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
  val `java-fx`: Seq[ModuleID]   = javaFXModules.map(m => "org.openjfx" % s"javafx-$m" % "12.0.2" classifier osName)

  // Circe JSON and YAML libraries
  val `circe-core`: ModuleID    = "io.circe" %% "circe-core"    % "0.13.0"
  val `circe-generic`: ModuleID = "io.circe" %% "circe-generic" % "0.13.0"
  val `circe-parser`: ModuleID  = "io.circe" %% "circe-parser"  % "0.13.0"
  val `circe-yaml`: ModuleID    = "io.circe" %% "circe-yaml"    % "0.13.0"
}
