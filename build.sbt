import Dependencies._

lazy val root = (project in file(".")).
    settings(
        inThisBuild(List(
            organization := "org.dsa-base",
            scalaVersion := "2.12.4",
            version := "0.1.0-SNAPSHOT"
        )),
        name := "dsa-base",
        scalastyleConfig := file("scalastyle_config.xml"),
        libraryDependencies ++= Seq(
            scalaTest,
            scalaCheck
        )
    )
