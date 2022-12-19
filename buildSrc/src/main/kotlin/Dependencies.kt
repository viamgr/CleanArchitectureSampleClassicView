interface Dependencies {
    fun getDependencies(): List<Dependency>
}

internal object AllDependencies {
    val coreKtx = Dependency(
        dependency = "androidx.core:core-ktx",
        dependencyVersion = DependencyVersions.coreKtxVersion,
        dependencyType = DependencyType.IMPLEMENTATION,
    )
    val espresso = Dependency(
        dependency = "androidx.test.espresso:espresso-core",
        dependencyVersion = DependencyVersions.espresso,
        dependencyType = DependencyType.API,
    )
    val fragmentTesting = Dependency(
        dependency = "androidx.fragment:fragment-testing",
        dependencyVersion = DependencyVersions.fragmentKtx,
        dependencyType = DependencyType.API,
    )
    val appcompat = Dependency(
        dependency = "androidx.appcompat:appcompat",
        dependencyVersion = DependencyVersions.appcompatVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )

    val material = Dependency(
        dependency = "com.google.android.material:material",
        dependencyVersion = DependencyVersions.materialVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )
    val constraintLayout = Dependency(
        dependency = "androidx.constraintlayout:constraintlayout",
        dependencyVersion = DependencyVersions.constraintLayoutVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )
    val zoomImage = Dependency(
        dependency = "com.jsibbold:zoomage",
        dependencyVersion = DependencyVersions.zoomImage,
        dependencyType = DependencyType.IMPLEMENTATION
    )
    val coroutinesAndroid = Dependency(
        dependency = "org.jetbrains.kotlinx:kotlinx-coroutines-android",
        dependencyVersion = DependencyVersions.coroutinesAndroidVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )

    val coroutinesCore = Dependency(
        dependency = "org.jetbrains.kotlinx:kotlinx-coroutines-core",
        dependencyVersion = DependencyVersions.coroutinesCoreVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )
    val hiltAndroid = Dependency(
        dependency = "com.google.dagger:hilt-android",
        dependencyVersion = DependencyVersions.hiltVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )

    val coil = Dependency(
        dependency = "io.coil-kt:coil",
        dependencyVersion = DependencyVersions.coilVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )

    val kotlinxSerializationJson = Dependency(
        dependency = "org.jetbrains.kotlinx:kotlinx-serialization-json",
        dependencyVersion = DependencyVersions.serializationJsonVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )

    val mockkAndroidApi = Dependency(
        dependency = "io.mockk:mockk-android",
        dependencyVersion = DependencyVersions.mockkVersion,
        dependencyType = DependencyType.API
    )
    val mockkApi = Dependency(
        dependency = "io.mockk:mockk",
        dependencyVersion = DependencyVersions.mockkVersion,
        dependencyType = DependencyType.API
    )

    val mockkAgentApi = Dependency(
        dependency = "io.mockk:mockk-agent-jvm",
        dependencyVersion = DependencyVersions.mockkVersion,
        dependencyType = DependencyType.API
    )

    val coroutineTestApi = Dependency(
        dependency = "org.jetbrains.kotlinx:kotlinx-coroutines-test",
        dependencyVersion = DependencyVersions.coroutinesAndroidVersion,
        dependencyType = DependencyType.API
    )

    val kotestApi = Dependency(
        dependency = "io.kotest:kotest-runner-junit5-jvm",
        dependencyVersion = DependencyVersions.kotestJUnit,
        dependencyType = DependencyType.API
    )
    val kotlinReflectApi = Dependency(
        dependency = "org.jetbrains.kotlin:kotlin-reflect",
        dependencyVersion = DependencyVersions.kotlinReflect,
        dependencyType = DependencyType.API
    )

    val kotestAssertions = Dependency(
        dependency = "io.kotest:kotest-assertions-core-jvm",
        dependencyVersion = DependencyVersions.kotestJUnit,
        dependencyType = DependencyType.API
    )

    val hiltCompiler = Dependency(
        dependency = "com.google.dagger:hilt-android-compiler",
        dependencyVersion = DependencyVersions.hiltAndroidCompilerVersion,
        dependencyType = DependencyType.KAPT
    )

    val hiltCompilerAndroidTest = Dependency(
        dependency = "com.google.dagger:hilt-android-compiler",
        dependencyVersion = DependencyVersions.hiltAndroidCompilerVersion,
        dependencyType = DependencyType.KAPT_ANDROID_TEST
    )

    val hiltAndroidTestingApi = Dependency(
        dependency = "com.google.dagger:hilt-android-testing",
        dependencyVersion = DependencyVersions.hiltAndroidCompilerVersion,
        dependencyType = DependencyType.API
    )

    val viewModel = Dependency(
        dependency = "androidx.lifecycle:lifecycle-viewmodel-ktx",
        dependencyVersion = DependencyVersions.lifecycleVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )

    val lifecycleRuntime = Dependency(
        dependency = "androidx.lifecycle:lifecycle-runtime-ktx",
        dependencyVersion = DependencyVersions.lifecycleVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )

    val activityKtx = Dependency(
        dependency = "androidx.activity:activity-ktx",
        dependencyVersion = DependencyVersions.activityKtx,
        dependencyType = DependencyType.IMPLEMENTATION
    )

    val fragmentKtx = Dependency(
        dependency = "androidx.fragment:fragment-ktx",
        dependencyVersion = DependencyVersions.fragmentKtx,
        dependencyType = DependencyType.IMPLEMENTATION
    )
    val navigationFragment = Dependency(
        dependency = "androidx.navigation:navigation-fragment-ktx",
        dependencyVersion = DependencyVersions.navVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )

    val navigationUi = Dependency(
        dependency = "androidx.navigation:navigation-ui-ktx",
        dependencyVersion = DependencyVersions.navVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )

    val retrofit = Dependency(
        dependency = "com.squareup.retrofit2:retrofit",
        dependencyVersion = DependencyVersions.retrofitVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )

    val okhttp3 = Dependency(
        dependency = "com.squareup.okhttp3:okhttp",
        dependencyVersion = DependencyVersions.okHttpVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )

    val okhttpLoggingInterceptor = Dependency(
        dependency = "com.squareup.okhttp3:logging-interceptor",
        dependencyVersion = DependencyVersions.okHttpVersion,
        dependencyType = DependencyType.DEBUG_IMPLEMENTATION
    )

    val retrofitSerialization = Dependency(
        dependency = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter",
        dependencyVersion = DependencyVersions.retrofitSerialization,
        dependencyType = DependencyType.IMPLEMENTATION
    )
    val orbit = Dependency(
        dependency = "org.orbit-mvi:orbit-viewmodel",
        dependencyVersion = DependencyVersions.orbitVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )

    val orbitTest = Dependency(
        dependency = "org.orbit-mvi:orbit-test",
        dependencyVersion = DependencyVersions.orbitVersion,
        dependencyType = DependencyType.API
    )

    val epoxy = Dependency(
        dependency = "com.airbnb.android:epoxy",
        dependencyVersion = DependencyVersions.epoxy,
        dependencyType = DependencyType.IMPLEMENTATION
    )
    val epoxyDataBinding = Dependency(
        dependency = "com.airbnb.android:epoxy-databinding",
        dependencyVersion = DependencyVersions.epoxy,
        dependencyType = DependencyType.IMPLEMENTATION
    )
    val epoxyKapt = Dependency(
        dependency = "com.airbnb.android:epoxy-processor",
        dependencyVersion = DependencyVersions.epoxy,
        dependencyType = DependencyType.KAPT
    )

    val timber = Dependency(
        dependency = "com.jakewharton.timber:timber",
        dependencyVersion = DependencyVersions.timberVersion,
        dependencyType = DependencyType.API
    )
    val leakCanary = Dependency(
        dependency = "com.squareup.leakcanary:leakcanary-android",
        dependencyVersion = DependencyVersions.leakCanary,
        dependencyType = DependencyType.DEBUG_IMPLEMENTATION,
        excludes = listOf(Exclude("group", "junit"), Exclude("module", "junit"))
    )

    val testRunnerImpl = Dependency(
        dependency = "androidx.test:runner",
        dependencyVersion = DependencyVersions.androidXTestVersion,
        dependencyType = DependencyType.IMPLEMENTATION
    )

    val junitApi = Dependency(
        dependency = "junit:junit",
        dependencyVersion = DependencyVersions.junitVersion,
        dependencyType = DependencyType.API
    )

    val testRules = Dependency(
        dependency = "androidx.test:rules",
        dependencyVersion = DependencyVersions.androidXTestVersion,
        dependencyType = DependencyType.API
    )

    val androidXJunitImpl = Dependency(
        dependency = "androidx.test.ext:junit",
        dependencyVersion = DependencyVersions.androidXJunit,
        dependencyType = DependencyType.API
    )
}


