name := """back"""
organization := "be.frol"

version := "1.0-SNAPSHOT"

scalaVersion := "2.13.3"

lazy val root = (project in file(".")).enablePlugins(PlayScala)
  .dependsOn(generated)
  .aggregate(generated)
  .settings(
    version := "1.0-SNAPSHOT",
    resolvers += ("com.e-iceblue" at "http://repo.e-iceblue.com/nexus/content/groups/public/").withAllowInsecureProtocol(true),
    libraryDependencies ++= Seq(
      guice,
      "com.typesafe.play" %% "play-slick" % "5.0.0",
      "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0",
      "com.typesafe.slick" %% "slick-codegen" % "3.3.2",
      "mysql" % "mysql-connector-java" % "5.1.44",
      "org.webjars" % "swagger-ui" % "3.36.2",
      "com.typesafe.play" %% "play-json" % "2.6.0",
      "commons-io" % "commons-io" % "2.8.0",
      "e-iceblue" % "spire.pdf.free" % "3.9.0",
    )

  )

val generatedApiPath = file("modules/generated")
val angular = "modules/generated-api"

lazy val generated = (project in generatedApiPath).enablePlugins(PlayScala)
  .enablePlugins(OpenAPIGeneratorPlugin)
  .settings(
    openapiApiNameSuffix := Some("Api"),
    openapiInputSpec := uri("../api.yaml"),
    openapiOutputDir := generatedApiPath,
    openapiGeneratorName := "scala-play-server",
    openapiAdditionalProperties := Map(
      "routesFileName" -> "be.frol.game.generated.routes"),
    openapiArtifactId := Some("generated-api"),
    openapiArtifactVersion := Some("1.0-SNAPSHOT"),
    openapiApiPackage := Some("be.frol.game.api"),
    openapiModelPackage := Some("be.frol.game.model"),
  )

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "5.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0",
  "com.typesafe.slick" %% "slick-codegen" % "3.3.2",
  "mysql" % "mysql-connector-java" % "5.1.44",
)


lazy val slick = TaskKey[Seq[File]]("gen-tables")

slick := {
  val outputDir = file("app").getPath
  val url = "jdbc:mysql://db:3306/db?allowPublicKeyRetrieval=true&useSSL=false"
  val jdbcDriver = "com.mysql.jdbc.Driver"
  val slickDriver = "slick.jdbc.MySQLProfile"
  val pkg = "be.frol.game.tables"
  val fname = outputDir + "tables/Tables.scala"
  val user = "user"
  val password = "pass"

  val cp = (dependencyClasspath in Compile).value
  val r = (runner in Compile).value
  val s = streams.value

  r.run("slick.codegen.SourceCodeGenerator", cp.files, Array(slickDriver, jdbcDriver, url, outputDir, pkg, user, password), s.log)
  Seq(file(fname))
}




scalaVersion := "2.12.6"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

lazy val generateAngularClient = (project in file("target/generateAngularCLient"))
  .enablePlugins(OpenAPIGeneratorPlugin)
  .settings(
    openapiApiNameSuffix := Some("Api"),
    openapiInputSpec := uri("../api.yaml"),
    openapiOutputDir := file("./../front/src/generated/api"),
    openapiGeneratorName := "typescript-angular",
    openapiArtifactId := Some("generated")
  )
