plugins {
	java
	id("org.springframework.boot") version "3.1.4"
	id("io.spring.dependency-management") version "1.1.3"
}

group = "com.castlabs"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("io.netty:netty-all:4.1.100.Final")
  implementation(platform("io.projectreactor:reactor-bom:2022.0.12"))
  implementation("io.projectreactor.netty:reactor-netty-core")
  implementation("io.projectreactor.netty:reactor-netty-http")

	testImplementation("org.springframework.boot:spring-boot-starter-test")  
}

tasks.withType<Test> {
	useJUnitPlatform()
}
