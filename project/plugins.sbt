lazy val root: Project = (project in file(".")).dependsOn(assemblyPlugin)

lazy val assemblyProjectRef: ProjectReference = RootProject(uri("git://github.com/kamon-io/kamon-sbt-umbrella.git"))

lazy val assemblyPlugin: ClasspathDep[ProjectReference] = ClasspathDependency(assemblyProjectRef, None)