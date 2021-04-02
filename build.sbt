name := "trueaccord_proj"

version := "0.1"

scalaVersion := "2.13.5"

//libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2"



val circeVersion = "0.13.0"
libraryDependencies ++= Seq(
  "com.lihaoyi" %% "requests" % "0.6.5",
  "com.lihaoyi" %% "upickle" % "1.3.8",
 // "io.circe"  %% "circe-core"     % circeVersion,
 // "io.circe"  %% "circe-generic"  % circeVersion,
 // "io.circe"  %% "circe-parser"   % circeVersion,
  "net.liftweb" %% "lift-json" % "3.4.3",
  "org.scalactic" %% "scalactic" % "3.2.5",
  "org.scalatest" %% "scalatest" % "3.2.5" % "test",
  //"org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
  //"com.typesafe.play" %% "play-json" % "2.9.2",
  "org.json4s" %% "json4s-native" % "3.7.0-M15",
  "org.json4s" %% "json4s-jackson" % "3.7.0-M15"
)

//addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)