name := "stroxy"

version := "0.1"

scalaVersion := "2.11.6"

resolvers += "Typesafe Snapshots Repository" at "http://repo.typesafe.com/typesafe/maven-snapshots/"

resolvers += Resolver.url("Typesafe Ivy Snapshots Repository", url("http://repo.typesafe.com/typesafe/ivy-snapshots"))(Resolver.ivyStylePatterns)

libraryDependencies += "org.bouncycastle" % "bcprov-jdk15on" % "1.51"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.9"

libraryDependencies += "com.typesafe.akka" %% "akka-stream-experimental" % "1.0-M4"

libraryDependencies += "com.typesafe.akka" %% "akka-http-experimental" % "1.0-M4"

libraryDependencies += "com.typesafe.slick" %% "slick" % "3.0.0-RC1"

libraryDependencies += "com.h2database" % "h2" % "1.4.186"

fork in run := true

javaOptions in run ++= List("-Xms768M", "-Xmx768M")

