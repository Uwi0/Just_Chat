plugins {
    id("kakapo.android.library")
    id("kakapo.android.hilt")
}

android {
    namespace = "com.kakapo.datastore_test"
}

dependencies {

    api(project(":core:datastore"))
    api(libs.androidx.dataStore.core)

    implementation(project(":core:common"))
    implementation(project(":core:testing"))
}