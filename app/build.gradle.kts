plugins {
    id("com.android.application")
}


android {
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mart.cart_activity"
        minSdk = 24
        targetSdk = 34
        versionCode = 8
        versionName = "8.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    namespace= "com.mart.cart_Activity"
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

//    buildFeatures {
//        dataBinding = true
//    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.android.support:support-annotations:28.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.github.bumptech.glide:glide:4.12.0")
    implementation("com.google.code.gson:gson:2.10")
    implementation("androidx.databinding:databinding-runtime:7.1.1")
//    implementation ("androidx.appcompat:appcompat:1.3.0")
//    implementation ("androidx.core:core-ktx:1.6.0")

//    implementation ("com.github.EudyContreras.Skeleton-Bones:v1.3")

//   image libary
    implementation ("com.squareup.picasso:picasso:2.71828")
//  Volley libarary
    implementation ("com.android.volley:volley:1.2.0")

//    GIF file image load timeuse
//    implementation ("com.github.bumptech.glide:glide:4.12.0")
//    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
    implementation ("pl.droidsonroids.gif:android-gif-drawable:1.2.28")

    implementation ("com.google.android.gms:play-services-auth:20.7.0")


//    room database
     implementation ("androidx.room:room-runtime:2.6.1")
     annotationProcessor ("androidx.room:room-compiler:2.6.1")

//    ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")

    // Retrofit and Gson
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")


    // ViewModel and LiveData
    implementation ("androidx.lifecycle:lifecycle-viewmodel:2.3.1")
    implementation ("androidx.lifecycle:lifecycle-livedata:2.3.1")
    implementation ("androidx.lifecycle:lifecycle-common-java8:2.3.1")


    // ViewModel Factory
    implementation ("androidx.lifecycle:lifecycle-viewmodel:2.3.1")
    implementation ("androidx.lifecycle:lifecycle-common-java8:2.3.1")


    // Retrofit Logging (optional)
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.1")
    implementation ("androidx.core:core:1.7.0")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

//    rezorepay integration related dependency
    implementation ("com.razorpay:checkout:1.6.33")



}
