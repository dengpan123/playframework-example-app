name := """example-app"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava,PlayEbean)
//lazy val einstein = (project in file("einstein")).enablePlugins(PlayJava, PlayEbean)
 // .dependsOn(commons % "compile->compile;test->test")

scalaVersion := "2.11.0"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  filters,
  "com.google.protobuf" % "protobuf-java" % "3.3.0",
  "org.codehaus.jackson" % "jackson-mapper-asl" % "1.8.5",
  "dom4j" % "dom4j" % "1.6.1",
  "mysql" % "mysql-connector-java" % "5.1.18"
)

libraryDependencies += "com.adrianhurt" %% "play-bootstrap" % "1.0-P25-B3"



// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

enablePlugins(DebianPlugin)

maintainer := "Edward Viaene <ward@in4it.io>"

packageSummary := "My custom package"

packageDescription := "Package"

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v", "-q")

