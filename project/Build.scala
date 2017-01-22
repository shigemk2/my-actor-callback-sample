import sbt._
import Keys._

object MyActorCallbackSampleProject extends Build {
  import Resolvers._
  lazy val root = Project("MyActorCallbackSample", file(".")) settings(coreSettings : _*)

  lazy val commonSettings: Seq[Setting[_]] = Seq(
    organization := "com.example",
    version := "1.0",
    scalaVersion := "2.11.8"
  )

  lazy val coreSettings = commonSettings ++ Seq(
    name := """my-actor-callback-sample""",
    libraryDependencies := Seq(
      "com.typesafe.akka" %% "akka-actor" % "2.4.10",
      "org.scalatest" %% "scalatest" % "3.0.1" % "test"
    )
  )
}
