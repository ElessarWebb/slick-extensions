
name := "slick-extensions"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "2.0.2",
  "joda-time" % "joda-time" % "2.3",
  "com.github.tototoshi" %% "slick-joda-mapper" % "1.1.0",
  "org.scalatest" %% "scalatest" % "2.2.0" % "test",
  "com.h2database" % "h2" % "1.4.181" % "test",
  "org.scalaz" %% "scalaz-core" % "7.1.0"
)
