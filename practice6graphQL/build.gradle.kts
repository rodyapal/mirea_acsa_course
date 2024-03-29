val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
	application
	kotlin("jvm") version "1.7.21"
	id("io.ktor.plugin") version "2.1.3"
}

group = "com.rodyapal"
version = "0.0.1"
application {
	mainClass.set("com.rodyapal.ApplicationKt")

	val isDevelopment: Boolean = project.ext.has("development")
	applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
	implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
	implementation("ch.qos.logback:logback-classic:$logback_version")

	// KGraphQl
	implementation("com.apurebase:kgraphql:0.18.0")
	implementation("com.apurebase:kgraphql-ktor:0.18.0")

	testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}