import Dependencies._

lazy val dsabase = (project in file(".")).
    settings(
        organization := "org.dsa-base",
        scalaVersion := "2.12.4",
        version := "0.0.1-SNAPSHOT",
        name := "dsa-base",
        scalastyleConfig := file("scalastyle_config.xml"),
        libraryDependencies ++= Seq(
            scalaTest % "test",
            scalaCheck % "test"
        )
    )
