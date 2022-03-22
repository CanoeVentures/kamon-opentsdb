import sbt.ExclusionRule

/* =========================================================================================
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 * =========================================================================================
 */

val kamonCore = "io.kamon"     %%  "kamon-core" % "0.6.7"
val opentsdb  = "net.opentsdb" % "opentsdb"     % "2.4.0" excludeAll(
   ExclusionRule(organization = "ch.qos.logback"),
   ExclusionRule(organization = "com.google.gwt"),
   ExclusionRule(organization = "net.opentsdb", name = "opentsdb_gwt_theme"),
   ExclusionRule(organization = "org.jgrapht"),
   ExclusionRule(organization = "ch.qos.logback")
   )

val hbase = "org.hbase" % "asynchbase" % "1.8.2"
name := "kamon-opentsdb"
parallelExecution in Test in Global := false
crossScalaVersions := Seq("2.11.8", "2.12.1")

version := (version in ThisBuild).value

libraryDependencies ++=
    compileScope(kamonCore, slf4jApi, opentsdb, hbase) ++
    testScope(scalatest, akkaDependency("testkit").value, slf4jApi, slf4jnop,
       "org.mockito" % "mockito-all" % "1.10.19"
    )

resolvers += Resolver.bintrayRepo("kamon-io", "releases")

def akkaDependency(moduleName: String) = Def.setting {
   scalaBinaryVersion.value match {
      case "2.10" => "com.typesafe.akka" %% s"akka-$moduleName" % "2.3.16"
      case "2.11" | "2.12" => "com.typesafe.akka" %% s"akka-$moduleName" % "2.4.16"
   }
}