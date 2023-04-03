plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")

}

android {
    namespace = "tour.donnees.catalog"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    // Modules
    implementation(project(":data:tvmave"))
    implementation(project(":domain:tvmaze"))
    testImplementation(project(":data:tvmave"))

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