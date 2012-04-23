import sbt._

import Keys._
import AndroidKeys._

object General {
  val settings = Defaults.defaultSettings ++ Seq (
    name := "texTumbl",
    version := "0.1",
    versionCode := 0,
    scalaVersion := "2.9.1",
    platformName in Android := "android-8"
  )

  val proguardSettings = Seq (
    useProguard in Android := true
  )

  lazy val fullAndroidSettings =
    General.settings ++
    AndroidProject.androidSettings ++
    TypedResources.settings ++
    proguardSettings ++
    AndroidManifestGenerator.settings ++
    AndroidMarketPublish.settings ++ Seq (
      keyalias in Android := "change-me",
      libraryDependencies += "org.scalatest" %% "scalatest" % "1.7.RC1" % "test"
    )
    

}

object AndroidBuild extends Build {
  lazy val main = Project (
    "texTumbl",
    file("."),
    settings = General.fullAndroidSettings
  )

  lazy val tests = Project (
    "tests",
    file("tests"),
    settings = General.settings ++
               AndroidTest.settings ++
               General.proguardSettings ++ 
               Seq (
      name := "texTumblTests"
    )
  ) dependsOn main
}
