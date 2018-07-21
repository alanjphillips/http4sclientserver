val CatsEffectVersion = "1.0.0-RC2"
val CirceVersion = "0.10.0-M1"
val Http4sVersion = "0.19.0-M1"
val Specs2Version = "4.2.0"
val LogbackVersion = "1.2.3"

lazy val commonSettings = Seq(
  organization := "com.alaphi",
  name := "http4sserver",
  version := "0.0.1-SNAPSHOT",
  scalaVersion := "2.12.6",
  libraryDependencies ++= Seq(
    "org.typelevel"   %% "cats-effect"         % CatsEffectVersion,
    "org.http4s"      %% "http4s-blaze-server" % Http4sVersion,
    "org.http4s"      %% "http4s-blaze-client" % Http4sVersion,
    "org.http4s"      %% "http4s-circe"        % Http4sVersion,
    "org.http4s"      %% "http4s-dsl"          % Http4sVersion,
    "ch.qos.logback"  %  "logback-classic"     % LogbackVersion,
    "io.circe"        %% "circe-core"          % CirceVersion,
    "io.circe"        %% "circe-generic"       % CirceVersion,
    "io.circe"        %% "circe-parser"        % CirceVersion
  )
)

lazy val dockerSettings = Seq(
  dockerBaseImage := "openjdk:jre-alpine"
)

lazy val root = (project in file("."))
  .aggregate(common, server, client)

lazy val common = (project in file("common"))
  .settings(commonSettings)

lazy val server = (project in file("http4sserver"))
  .enablePlugins(JavaAppPackaging, DockerPlugin, AshScriptPlugin)
  .dependsOn(common)
  .settings(dockerSettings)

lazy val client = (project in file("http4sclient"))
  .dependsOn(common)




