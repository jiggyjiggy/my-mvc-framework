plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("ch.qos.logback:logback-classic:1.5.6")

    implementation("org.apache.tomcat.embed:tomcat-embed-core:11.0.0-M19")
    implementation("org.apache.tomcat.embed:tomcat-embed-jasper:11.0.0-M19")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}