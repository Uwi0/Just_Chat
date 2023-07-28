plugins {
    id("kotlin")
}

kotlin {
    jvmToolchain(11)
}

dependencies {
    implementation(libs.kotlinx.datetime)
    implementation(libs.google.gson)
    api(libs.serpro69.faker)
}