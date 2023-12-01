lazy val sampleapp = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := "sampleapp",
    organization := "com.example",
    version := "1.0-SNAPSHOT",
    scalaVersion := "3.3.1",
    libraryDependencies ++= Seq(
      guice
    )
  )
