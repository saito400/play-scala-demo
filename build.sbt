name := """play-scala-demo"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "com.typesafe.slick" %% "slick" % "2.1.0",
  "com.typesafe.slick" %% "slick-codegen" % "2.1.0",
  "com.typesafe.play" %% "play-slick" % "0.8.0",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "org.specs2" %% "specs2-core" % "2.4.15" % "test",
  "org.specs2" %% "specs2-junit" % "2.4.15" % "test",
  "org.postgresql"  %  "postgresql"  %  "9.3-1102-jdbc41"
)
