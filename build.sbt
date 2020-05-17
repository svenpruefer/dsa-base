import java.net.URL

lazy val quintilian = project
  .in(file("."))
  .settings(
    inThisBuild(
      List(
        organization := "de.musmehl",
        organizationName := "Musmehl",
        organizationHomepage := Some(
          new URL("https://www.musmehl.de")
        ),
        startYear := Some(2020),
        licenses += ("GPL-3.0", new URL(
          "https://www.gnu.org/licenses/gpl-3.0.en.html"
        )),
        scalaVersion := "2.13.2"
      )
    ),
    name := "quintilian",
    libraryDependencies ++= List(
      Dependencies.`scala-fx`,
      Dependencies.`circe-core`,
      Dependencies.`circe-generic`,
      Dependencies.`circe-parser`,
      Dependencies.`circe-yaml`,
      Dependencies.scalatest     % Test,
      Dependencies.scalacheck    % Test,
      Dependencies.scalatestplus % Test
    ) ++ Dependencies.`java-fx`
  )
  // Package as a normal Java app
  .enablePlugins(JavaAppPackaging)
  // Scala linting via WartRemover
  .settings(
    wartremoverErrors ++= Warts.allBut(
      // We use default arguments for case class constructors
      Wart.DefaultArguments
    )
  )
  // Integration tests
  .configs(IntegrationTest)
  .settings(
    Defaults.itSettings,
    libraryDependencies ++= List(
      Dependencies.scalatest % IntegrationTest
    )
  )
