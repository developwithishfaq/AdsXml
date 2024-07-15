
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("maven-publish")
    id("signing")

}

group = "com.github.jitpack"
version = "1.0"


android {
    namespace = "com.example.admobads"
    compileSdk = 34

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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
publishing {
    publications {
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.fb.shimmer)

}


afterEvaluate {
    publishing {
        publications {
            // Creates a Maven publication called "release".
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.developwithishfaq"
                artifactId = "ads_sdk"
                version = "1.0"
            }
        }
    }/*
    repositories {
        maven {
            url = uri("your-repository-url")
            credentials {
                username = project.findProperty("repoUser") as String? ?: "defaultUser"
                password = project.findProperty("repoPassword") as String? ?: "defaultPassword"
            }
        }
    }*/
}