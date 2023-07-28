plugins {
    id("kakapo.android.library")
    id("kakapo.android.library.jacoco")
    kotlin("kapt")
}


android {
    namespace = "com.kakapo.domain"
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(libs.hilt.android)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)

    kapt(libs.hilt.compiler)

    testImplementation(project(":core:testing"))
}