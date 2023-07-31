plugins {
    id("kakapo.android.library")
}

android {
    namespace = "com.kakapo.logger"
}

dependencies{
    implementation(libs.timber.logging)
}
