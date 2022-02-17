ThisBuild / scalafixDependencies +=
  "org.scala-lang.modules" %% "scala-collection-compat" % "2.6.0"
ThisBuild / scalafixDependencies += "org.scala-lang.modules" %% "scala-collection-migrations" % "2.6.0"
addCompilerPlugin(scalafixSemanticdb)
scalacOptions ++= List("-Yrangepos", "-P:semanticdb:synthetics:on")

lazy val root = project
  .in(file("."))
  .settings(
    name := "greyhound-2.13-sbt",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := "2.13.8",
//    scalaVersion := "2.12.15",

    libraryDependencies ++= Seq(
      "org.scala-lang.modules" %% "scala-collection-compat" % "2.6.0",
      "com.novocode" % "junit-interface" % "0.11" % "test",
      "org.specs2" %% "specs2-junit" %  "4.8.3" % "test",
      "dev.zio" %% "zio-test-junit" % "1.0.13" % "test",
      "org.specs2" %% "specs2-mock" % "4.8.3" % "test",

      "dev.zio" %% "zio" % "1.0.13",
      "dev.zio" %% "zio-streams" % "1.0.13",
      "org.apache.kafka" % "kafka-clients" % "2.4.1",
      "org.apache.kafka" %% "kafka" % "2.4.1",
      "org.apache.curator" % "curator-test" % "2.12.0",
      "com.h2database" % "h2" % "1.4.197"
    )
  )
