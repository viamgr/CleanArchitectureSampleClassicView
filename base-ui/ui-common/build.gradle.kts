plugins {
    id(BuildPlugins.Apply.androidLibrary)
    id(BuildPlugins.Apply.kotlinAndroid)
    id(BuildPlugins.Apply.kotlinKapt)
    id(BuildPlugins.Apply.safeargs)
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
    generateStubs = true
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
    }

    testBuildType = TestEnvironment.testBuildType
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

        create(testBuildType) {
            initWith(getByName("debug"))
        }
    }
    compileOptions {
        sourceCompatibility = ConfigData.jvmTarget
        targetCompatibility = ConfigData.jvmTarget
    }

    // For Kotlin projects
    kotlinOptions {
        jvmTarget = ConfigData.jvmTarget.toString()

    }

    buildFeatures {
        dataBinding = true
    }

}

dependencies {
    implementation(project(BuildModules.Base.CORE))
    implementation(project(BuildModules.BaseUi.THEME_UI))
    addDependencies(UiCommonModuleDependencies.getDependencies())
}
