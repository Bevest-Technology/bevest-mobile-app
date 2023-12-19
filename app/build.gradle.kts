plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("kotlin-kapt")
}

android {
    namespace = "com.bevesttech.bevest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bevesttech.bevest"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.17")
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    //firebase
    implementation(platform("com.google.firebase:firebase-bom:32.6.0"))
    implementation("com.google.firebase:firebase-auth:22.3.0")
    implementation("com.google.firebase:firebase-firestore:24.9.1")
    implementation("com.google.android.gms:play-services-auth:20.7.0")
    implementation("com.google.firebase:firebase-storage:20.3.0")


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //fragmentKTX
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    //viewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    //liveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    //glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

    //camerax
//    val camerax = "1.3.1"
//    implementation("androidx.camera:camera-core:${camerax}")
//    implementation("androidx.camera:camera-camera2:${camerax}")
//    implementation("androidx.camera:camera-lifecycle:${camerax}")
//    implementation("androidx.camera:camera-view:${camerax}")
//    implementation("androidx.camera:camera-extensions:${camerax}")
}