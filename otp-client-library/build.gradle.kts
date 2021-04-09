
val ktorVersion = "1.5.3"
val coroutineVersion = "1.4.3"

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlinx-serialization")
    id("kotlin-android-extensions")
    id("com.chromaticnoise.multiplatform-swiftpackage") version "2.0.3"
    id("maven-publish")
}

group = "edu.usf.cutr.otp"
version = "1.0.0"

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
                    "org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("androidx.core:core-ktx:1.3.2")
                implementation(
                    "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion")
                implementation("io.ktor:ktor-client-android:$ktorVersion")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
                implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion-native-mt") {
                    version {
                        strictly("$coroutineVersion-native-mt")
                    }
                }
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-java:$ktorVersion")
                implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-swing:$coroutineVersion")
            }
        }
    }
}

android {
    compileSdkVersion(29)
    defaultConfig {
        minSdkVersion(16)
        targetSdkVersion(29)
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

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            setUrl("https://maven.pkg.github.com/octocat/hello-world")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        filterIsInstance<MavenPublication>().forEach { publication ->
            publication.pom {
                name.set(project.name)
                description.set(project.description)
                packaging = "jar"
                url.set("https://github.com/serpro69/${project.name}")
                developers {
                    developer {
                        id.set("serpro69")
                        name.set("Sergii Prodan")
                        email.set("serpro@disroot.org")
                    }
                }
                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://github.com/serpro69/${project.name}/blob/master/LICENCE.md")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/serpro69/${project.name}.git")
                    developerConnection.set("scm:git:git@github.com:serpro69/${project.name}.git")
                    url.set("https://github.com/serpro69/${project.name}")
                }
            }
        }
    }
}