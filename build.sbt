organization  := "org.change"

version       := "0.2"

scalaVersion  := "2.11.1"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

fork := true

libraryDependencies ++= {
  Seq(
    "org.antlr" % "antlr4" % "4.5.2",
    "commons-io" % "commons-io" % "2.4",
    "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test",
    "io.spray" %%  "spray-json" % "1.3.2"
  )
}

test in assembly := {}

lazy val p4control = taskKey[Unit]("P4 control function to SEFL")


//fullRunTask(p4control, Compile, "org.change.v2.runners.experiments.P4ControlRunner")
p4control := {
  val file = Option(System.getProperty("file")).getOrElse("/Users/localadmin/poli/symnet-paper/code/Symnetic/src/main/resources/p4_test_files/control.p4")
  val r = (runner in Compile).value
  //val cp: Seq[File] = (dependencyClasspath in Compile).value.files
  val cp = (fullClasspath in Compile).value.files
  toError(r.run("org.change.v2.runners.experiments.P4ControlRunner", cp, Seq(file), streams.value.log))
}

lazy val sample = taskKey[Unit]("Interpreting")

fullRunTask(sample, Compile, "org.change.v2.runners.experiments.SEFLRunner")

lazy val click = taskKey[Unit]("Symbolically running Template.click")

fullRunTask(click, Compile, "org.change.v2.runners.experiments.TemplateRunner")

lazy val symb = taskKey[Unit]("Symbolically running Template.click without validation")

fullRunTask(symb, Compile, "org.change.v2.runners.experiments.TemplateRunnerWithoutValidation")

lazy val mc = taskKey[Unit]("Running multiple VMs")

fullRunTask(mc, Compile, "org.change.v2.runners.experiments.MultipleVms")

lazy val switch_bench = taskKey[Unit]("Switch Bench")

fullRunTask(switch_bench, Compile, "org.change.v2.runners.experiments.ciscoswitchtest.CiscoSwitchTestBench")

lazy val policy = taskKey[Unit]("Policy testing")

fullRunTask(policy, Compile, "org.change.v2.verification.Test")

lazy val sefl = taskKey[Unit]("SEFL execution")

fullRunTask(sefl, Compile, "org.change.v2.runners.sefl.SEFLExecutor")

seq(Revolver.settings: _*)
