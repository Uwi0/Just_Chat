plugins {
    id("kakapo.android.library")
    id("kakapo.android.library.compose")
    id("kakapo.android.hilt")
}

android {
    namespace = "com.kakapo.analytics"
}

dependencies {
    implementation(platform(libs.firebase.bom))
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.core.ktx)
    implementation(libs.firebase.analytics)
    implementation(libs.kotlinx.coroutines.android)
}