ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

lazy val root = (project in file("."))
  .settings(
    sbtPlugin := true,
    sbtVersion := "1.9.7",
    name := "api-generator",
    organization := "com.journiapp",
    libraryDependencies ++= Seq(
      "org.playframework" %% "play-routes-compiler" % "3.0.0",
      /*
      "org.playframework" %% "play-routes-compiler" % "3.0.0" excludeAll(
        ExclusionRule(organization = "org.scala-lang.modules", name = "scala-xml"),
        ExclusionRule(organization = "org.scala-lang.modules", name = "scala-parser-combinators")), */
      "org.playframework" %% "play-json" % "3.0.1",
      "org.scala-lang" %% "scala3-tasty-inspector" % scalaVersion.value
    ),
    dependencyOverrides ++= Seq(
      "org.scala-lang.modules" %% "scala-xml" % "2.2.0",
      "org.scala-lang.modules" %% "scala-parser-combinators" % "2.3.0"
    )
  )

