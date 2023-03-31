plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}


android {
    namespace = "tour.donnees.tvmaze"
    compileSdk = 33

    defaultConfig {
        applicationId = "tour.donnees.tvmaze"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }
        getByName("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }

    packagingOptions {

        pickFirst ("META-INF/LICENSE.md")
        pickFirst ("**/libfbjni.so")
    }

}

dependencies {

    //AndroidX
    implementation(libs.androidx.navigationfragment)
    implementation(libs.androidx.navigationui)
    testImplementation(libs.androidx.navigationtesting)

    implementation(libs.androidx.legacy)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.corektx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraint)
    testImplementation(libs.androidx.coretesting)

    androidTestImplementation(libs.androidx.espressocore)
    androidTestImplementation(libs.androidx.testrunner)
    androidTestImplementation(libs.androidx.testrules)
    androidTestImplementation(libs.androidx.junit)

    // Koin
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    testImplementation(libs.koin.test)
    testImplementation(libs.koin.junit)

    // Coroutines
    implementation(libs.coroutines.core)
    testImplementation(libs.coroutines.test)

    // Material
    implementation(libs.material)

    // Coil
    implementation(libs.coil)

    // OkHttp
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.convertergson)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
}