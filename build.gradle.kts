plugins {
    id("java")
		id("application")
}

group = "ru.nsu.fit.dskvl.gfx"
version = "1.0-SNAPSHOT"
application {
		mainClass = "ru.nsu.fit.dskvl.gfx.views.InitMainWindow"
}
repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.formdev:flatlaf:3.0")
}
java {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
}
tasks.test {
    useJUnitPlatform()
}
