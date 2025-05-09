plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.ldt.volley"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ldt.volley"
        minSdk = 33
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    //thu viện load image
    implementation (libs.glide)
    annotationProcessor(libs.compiler)
    //thu viện load dữ liệu API
    implementation (libs.volley)
    //thư viện circle images
    implementation (libs.circleimageview)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}