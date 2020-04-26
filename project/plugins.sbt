// Get version information from SBT
addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.9.0")

// Code formatting
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.3.4")

// Package result
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.4.1")

// Set version from GIT
addSbtPlugin("com.dwijnand" % "sbt-dynver" % "4.0.0")

// Add licence headers
addSbtPlugin("de.heikoseeberger" % "sbt-header" % "5.2.0")

// Scala linter
addSbtPlugin("org.wartremover" % "sbt-wartremover" % "2.4.3")
