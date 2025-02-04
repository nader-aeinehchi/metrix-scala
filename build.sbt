val scala3Version = "3.5.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "smetrix",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "1.0.0" % Test,
    libraryDependencies += "org.scala-lang" %% "toolkit" % "0.1.7",
    Compile / unmanagedSourceDirectories += file(
      baseDirectory.value + "/src/mock/scala"
    ),
    Compile / unmanagedSourceDirectories += file(
      baseDirectory.value + "/src/sample/scala"
    )

  )
