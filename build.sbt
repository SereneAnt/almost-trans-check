name := "almost-trans-check"

version := "0.1.0"

scalaVersion := "2.13.2"

val zioVersion = "1.0.0-RC20"

libraryDependencies ++= Seq(
  "edu.mines.jtk" % "edu-mines-jtk" % "1.1.0",

  "dev.zio" %% "zio"               % zioVersion,
  "dev.zio" %% "zio-streams"       % zioVersion,
  "dev.zio" %% "zio-test"          % zioVersion % "test",
  "dev.zio" %% "zio-test-sbt"      % zioVersion % "test",
  "dev.zio" %% "zio-test-magnolia" % zioVersion % "test" // optional
)

testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
