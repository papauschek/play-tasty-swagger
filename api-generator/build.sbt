ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

lazy val root = (project in file("."))
  .settings(
    name := "api-generator",
    libraryDependencies ++= Seq(
      "org.playframework" %% "play-routes-compiler" % "3.0.0",
      "org.playframework" %% "play-json" % "3.0.1",
      "org.scala-lang" %% "scala3-tasty-inspector" % scalaVersion.value
    )
  )
