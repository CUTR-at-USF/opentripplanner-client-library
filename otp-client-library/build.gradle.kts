
val ktorVersion = "1.6.5"
val coroutineVersion = "1.5.2"

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlinx-serialization")
    id("com.chromaticnoise.multiplatform-swiftpackage") version "2.0.3"
    id("maven-publish")
}

group = "edu.usf.cutr.otp"
version = "1.0.0-SNAPSHOT"

kotlin {
    android()
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    ios {
        binaries {
            framework {
                baseName = "OpenTripPlannerClientLibrary"
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib-common")
                implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion-native-mt") {
                    version {
                        strictly("$coroutineVersion-native-mt")
                    }
                }
                implementation(
                    "org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("androidx.core:core-ktx:1.7.0")
                implementation(
                    "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion"
                )
                implementation("io.ktor:ktor-client-android:$ktorVersion")
            }
        }

        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion-native-mt") {
                    version {
                        strictly("$coroutineVersion-native-mt")
                    }
                }
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-apache:1.6.2")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:$coroutineVersion")
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:$coroutineVersion")
            }
        }
    }
}

android {
    compileSdk = 31
    defaultConfig {
        minSdk = 21
        targetSdk = 29
    }
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}

val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val sdkName = System.getenv("SDK_NAME") ?: "iphonesimulator"
    val targetName = "ios" + if (sdkName.startsWith("iphoneos")) "Arm64" else "X64"
    val framework = kotlin.targets.getByName<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget>(targetName).binaries.getFramework(
        mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)
}
tasks.getByName("build").dependsOn(packForXcode)

multiplatformSwiftPackage {
    packageName("OpenTripPlannerClientLibrary")
    swiftToolsVersion("5.3")
    targetPlatforms {
        iOS { v("13") }
    }
}
