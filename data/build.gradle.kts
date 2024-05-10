plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
//    retrofit
    implementation(libs.converter.gson)
    implementation(libs.retrofit)

//    coroutines
    runtimeOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation(libs.kotlinx.coroutines.android)
}