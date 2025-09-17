plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

val koinVersion = "4.1.0"

dependencies {
    implementation(project.dependencies.platform("io.insert-koin:koin-bom:$koinVersion"))
    implementation(libs.koin.core)
}