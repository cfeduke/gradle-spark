# Spark/Gradle/IntelliJ Project Template

Spark version:		2.4.0
Scala version: 		2.11.8
Java runtime version: 	1.8

As of 05/2019 these are the same settings used in the Databricks cloud environment.

This template is based off of [an article by Faizan Ahemad](https://medium.com/@faizanahemad/apache-spark-setup-with-gradle-scala-and-intellij-2eeb9f30c02a) and
has been updated accordingly.

## Getting Started (from scratch)

For a completely new environment, install [SDKMAN!](https://sdkman.io/) and install Java 1.8.

```bash
$ sdk install java 8.0.212-zulu # 05/2019 current 1.8 version
$ sdk install gradle		# 05/2019 5.4.1
```

(or manually install these dependencies)

You could install a later JDK though YMMV as far as Spark goes on anything above JDK 1.8 with Spark 2.4.x.

## Getting Started

Clone this repo into a suitable directory.

Create your very own Gradle wrapper. (Its okay to check this into source control, I didn't here for convenience to support future users of this template.)

```bash
$ gradle wrapper
```

Execute the run task and verify things work properly by running a local Spark cluster and executing a simple CSV aggregation:

```bash
$ ./gradlew runSpark
```

Expected _approximate_ output:
```
Using Spark's default log4j profile: org/apache/spark/log4j-defaults.properties
19/05/02 10:25:53 INFO SparkContext: Running Spark version 2.4.0
19/05/02 10:25:53 WARN NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
⋮
19/05/02 10:58:50 INFO BlockManagerMaster: Registered BlockManager BlockManagerId(driver, c02y33c2jgh5.lan, 60239, None)
19/05/02 10:58:50 INFO BlockManager: Initialized BlockManager: BlockManagerId(driver, c02y33c2jgh5.lan, 60239, None)
Average Age: 23.14

BUILD SUCCESSFUL in 5s
3 actionable tasks: 1 executed, 2 up-to-date
```

You'll want to remove the git directory for this repository if you're using it as the basis for a new project:

```bash
$ rm -rf ./.git
```

Probably don't need the contents of the README anymore or the example files:

```bash
$ echo -n > ./README.md
$ find . -name "Main.scala" -exec rm {} \;
$ find . -name "*.csv" -exec rm {} \;
```

The `InitializeSpark.scala` file is left in place since it could be useful across local development projects.

Assuming you want source control don't forget to initialize the new repo - a reasonable `.gitignore` is included in this template.

```bash
$ git init
```

Finally you should be able to open your new project in IntelliJ (with the Scala plugin enabled) by Importing a Gradle project. Tip: select the `gradlew` option during import.
The first time the IDE executes its Build for the Gradle file it should take a while to resolve all the dependencies [on a new computer], this is normal.

### Shadow JAR

The purpose of the Shadow JAR is to upload a single JAR to a Spark cluster with all of the dependencies included. Looking at the configuration for this project there's a lot
of things trimmed out because its assumed they're present in the Spark environment itself. It helps when performing incremental debugging of a job at runtime if the JAR file
isn't 100MiB+.

### Why not {sbt, giter8}?

sbt and I don't get along.

[giter8](https://github.com/foundweekends/giter8) is a great concept but I always find the project templates I need are hopelessly out of date.

