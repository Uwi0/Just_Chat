plugins {
    id("kakapo.android.library")
    id("kakapo.android.hilt")
}
android {
    namespace = "com.kakapo.data_test"
}

dependencies {
    api(project(":core:data"))
    implementation(project(":core:testing"))
}