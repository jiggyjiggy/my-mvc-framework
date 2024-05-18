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

    implementation("jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api:3.0.0")
    implementation("org.glassfish.web:jakarta.servlet.jsp.jstl:3.0.0")

    implementation("org.reflections:reflections:0.10.2")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testImplementation("org.assertj:assertj-core:3.25.3")
}

tasks.test {
    useJUnitPlatform()
}