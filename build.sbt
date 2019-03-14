name := """regression"""
organization := "com.gu"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.8"

resolvers += Resolver.bintrayRepo("cibotech", "public")
libraryDependencies += "com.cibo" %% "evilplot" % "0.6.3"
libraryDependencies += "com.cibo" %% "evilplot-repl" % "0.6.3"

libraryDependencies += guice
libraryDependencies += "org.specs2" %% "specs2-core" % "4.4.1" % Test

