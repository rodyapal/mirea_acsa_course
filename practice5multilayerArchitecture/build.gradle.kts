val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val ktormVersion: String by project

plugins {
	application
	kotlin("jvm") version "1.7.20"
	id("io.ktor.plugin") version "2.1.2"
	id("org.jetbrains.kotlin.plugin.serialization") version "1.7.20"
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
	// Ktor
	implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-host-common-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-websockets-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktorVersion")
	implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktorVersion")
	implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.8.0")
	implementation("io.ktor:ktor-server-html-builder-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")
	implementation("ch.qos.logback:logback-classic:$logbackVersion")

	// Ktorm
	implementation("org.ktorm:ktorm-core:$ktormVersion")

	// Jdbc
	implementation("org.postgresql:postgresql:42.5.0")
//	runtimeOnly("org.postgresql:postgresql")

	testImplementation("io.ktor:ktor-server-tests-jvm:$ktorVersion")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
}