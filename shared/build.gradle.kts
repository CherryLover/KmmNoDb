plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("kotlin-parcelize")
//    id("com.codingfeline.buildkonfig")
}

kotlin {
    androidTarget()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)


                val decomposeVersion = "2.1.0-compose-experimental-alpha-07"
                val decomposeRouterVersion = "0.4.0"
//                decompose = { module = "com.arkivanov.decompose:decompose", version.ref = "decompose" }
//                decompose-router = { module = "io.github.xxfast:decompose-router", version.ref = "decompose-router" }
//                decompose-router-wear = { module = "io.github.xxfast:decompose-router-wear", version.ref = "decompose-router" }
//                decompose-compose-multiplatform = { module = "com.arkivanov.decompose:extensions-compose-jetbrains", version.ref = "decompose" }
                implementation("com.arkivanov.decompose:decompose:$decomposeVersion")
                implementation("io.github.xxfast:decompose-router:$decomposeRouterVersion")
//                implementation("io.github.xxfast:decompose-router-wear:$decomposeRouterVersion")
                implementation("com.arkivanov.decompose:extensions-compose-jetbrains:$decomposeVersion")
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.activity:activity-compose:1.7.2")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.10.1")

                implementation(libs.androidx.compose.windowsizeclass)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.cherry.kmm.common"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
}

//buildkonfig {
//    packageName = "com.cherry.kmm.common"

//    defaultConfigs {
//        val apiKey: String = com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(rootDir).getProperty("apiKey")
//
//        require(apiKey.isNotEmpty()) {
//            "Register your api key from developer.nytimes.com and place it in local.properties as `apiKey`"
//        }
//
//        buildConfigField(STRING, "API_KEY", apiKey)
//    }
//}
