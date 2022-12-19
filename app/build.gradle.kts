plugins {
    id(BuildPlugins.Apply.androidApplication)
    id(BuildPlugins.Apply.kotlinAndroid)
    id(BuildPlugins.Apply.kotlinKapt)
    id(BuildPlugins.Apply.daggerHiltPlugin)
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
    correctErrorTypes = true
}

enableUnitTest()

android {

    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        applicationId = ConfigData.appID
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName

        vectorDrawables {
            useSupportLibrary = true
        }
        testInstrumentationRunner = TestEnvironment.testInstrumentationRunner
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    testBuildType = TestEnvironment.testBuildType
    buildTypes {

        getByName("release") {
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            isMinifyEnabled = true
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

        freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    hilt {
        enableAggregatingTask = true
    }
}


dependencies {
    implementation(project(BuildModules.Base.CORE))
    testImplementation(project(BuildModules.Base.TEST_SHARED))

    implementation(project(BuildModules.BaseUi.COMMON_UI))
    implementation(project(BuildModules.BaseUi.THEME_UI))
    implementation(project(BuildModules.Feature.Sample.UI))
    stagingImplementation(project(BuildModules.BaseUi.ANDROID_TEST_SHARED))

    implementation(project(BuildModules.AndroidApi.Location.CONTRACT))
    implementation(project(BuildModules.AndroidApi.Location.GOOGLE))

    implementation(project(BuildModules.Data.REPOSITORY))
    implementation(project(BuildModules.Datasource.REMOTE))


    stagingImplementation(project(BuildModules.BaseUi.ANDROID_TEST_SHARED))
    androidTestImplementation(project(BuildModules.BaseUi.ANDROID_TEST_SHARED))

    addDependencies(AppModuleDependencies.getDependencies())
}
