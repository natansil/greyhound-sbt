//Test / sourceDirectories := baseDirectory { base =>
//  Seq(
//    base / "src/test/scala"
//  )
//}.value


lazy val root = project
  .in(file("."))
  .settings(
    name := "greyhound-2.13-sbt",
    version := "0.1.0-SNAPSHOT",

//    scalaVersion := "2.13.8",
    scalaVersion := "2.12.12",

    libraryDependencies ++= Seq(
      "com.novocode" % "junit-interface" % "0.11" % "test",
      "org.specs2" %% "specs2-junit" %  "4.8.3" % "test",
      "dev.zio" %% "zio-test-junit" % "1.0.9" % "test",
      "org.specs2" %% "specs2-mock" % "4.8.3" % "test",

      "dev.zio" %% "zio" % "1.0.9",
      "dev.zio" %% "zio-streams" % "1.0.9",
      "org.apache.kafka" % "kafka-clients" % "2.4.1",
      "org.apache.kafka" %% "kafka" % "2.4.1",
      "org.apache.curator" % "curator-test" % "2.12.0",
      "com.h2database" % "h2" % "1.4.197"
    )
  )
