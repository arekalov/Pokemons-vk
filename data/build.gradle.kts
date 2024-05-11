plugins {
    id("java-library")
    id("kotlin-kapt")
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
    implementation(libs.kotlinx.coroutines.android)

//    paging
    implementation(libs.androidx.paging.common.ktx)

//    dagger
    implementation(libs.dagger)
    kapt(libs.dagger)
}