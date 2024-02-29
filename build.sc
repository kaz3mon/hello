import mill._
import scalalib._
// support BSP
import mill.bsp._

val defaultScalaVersion = "2.13.12"
val chiselVersion       = "6.0.0"
val scalatestVersion    = "3.2.16"

val chiselIvy       = ivy"org.chipsalliance::chisel:$chiselVersion"
val chiselPluginIvy = ivy"org.chipsalliance:::chisel-plugin:$chiselVersion"
val scalatestIvy    = ivy"org.scalatest::scalatest::$scalatestVersion"

object %NAME% extends HasChisel with scalafmt.ScalafmtModule {
  override def millSourcePath = os.pwd / "hdl" / "chisel"
  override def moduleDeps = super.moduleDeps ++ Seq(
    // deps
  )
  object test extends ScalaTests with TestModule.ScalaTest {
    override def ivyDeps = super.ivyDeps() ++ Agg(scalatestIvy)
  }
}

trait HasChisel extends ScalaModule {
  override def scalaVersion = defaultScalaVersion

  override def scalacOptions = super.scalacOptions() ++ Seq(
    "-unchecked",
    "-deprecation",
    "-language:reflectiveCalls",
    "-feature",
    "-Xcheckinit",
    "-Xfatal-warnings",
    "-Ywarn-dead-code",
    "-Ywarn-unused",
    "-Ymacro-annotations"
  )

  override def ivyDeps = super.ivyDeps() ++ Agg(chiselIvy)

  override def scalacPluginIvyDeps = super.scalacPluginIvyDeps() ++ Agg(chiselPluginIvy)
}
