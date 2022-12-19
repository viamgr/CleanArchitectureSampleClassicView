object AppModuleDependencies : Dependencies {

    override fun getDependencies(): List<Dependency> = listOf(
        AllDependencies.leakCanary,
        AllDependencies.coreKtx,
        AllDependencies.appcompat,
        AllDependencies.material,
        AllDependencies.hiltAndroid,
        AllDependencies.hiltCompiler,
        AllDependencies.activityKtx,
        AllDependencies.navigationFragment,
        AllDependencies.hiltCompilerAndroidTest,
        )
}

object UiThemeModuleDependencies : Dependencies {

    override fun getDependencies(): List<Dependency> = listOf(
        AllDependencies.appcompat,
        AllDependencies.material,
        AllDependencies.constraintLayout,
    )
}

object FeatureUiDependencies : Dependencies {
    override fun getDependencies(): List<Dependency> = listOf(
        AllDependencies.coroutinesCore,
        AllDependencies.coroutinesAndroid,
        AllDependencies.hiltCompiler,
        AllDependencies.hiltAndroid,
        AllDependencies.fragmentKtx,
        AllDependencies.navigationUi,
        AllDependencies.navigationFragment,
        AllDependencies.viewModel,
        AllDependencies.lifecycleRuntime,
        AllDependencies.orbit,
        AllDependencies.epoxy,
        AllDependencies.epoxyDataBinding,
        AllDependencies.epoxyKapt,
        AllDependencies.hiltCompilerAndroidTest,
    )
}

object FeaturePresentationDependencies : Dependencies {
    override fun getDependencies(): List<Dependency> = listOf(
        AllDependencies.coroutinesCore,
        AllDependencies.coroutinesAndroid,
        AllDependencies.hiltCompiler,
        AllDependencies.hiltAndroid,
        AllDependencies.fragmentKtx,
        AllDependencies.viewModel,
        AllDependencies.orbit,
    )
}

object CoreModuleDependencies : Dependencies {

    override fun getDependencies(): List<Dependency> = listOf(
        AllDependencies.timber,
        AllDependencies.hiltAndroid,
        AllDependencies.coroutinesAndroid,
        AllDependencies.coroutinesCore,
    )

}

object DataModuleDependencies : Dependencies {

    override fun getDependencies(): List<Dependency> = listOf(
        AllDependencies.hiltAndroid,
        AllDependencies.hiltCompiler,
    )

}

object DomainModuleDependencies : Dependencies {

    override fun getDependencies(): List<Dependency> = listOf(
        AllDependencies.hiltAndroid,
        AllDependencies.hiltCompiler,
        AllDependencies.coroutinesAndroid,
        AllDependencies.coroutinesCore,
    )
}

object UiCommonModuleDependencies : Dependencies {
    override fun getDependencies(): List<Dependency> = listOf(
        AllDependencies.material,
        AllDependencies.coil,
        AllDependencies.viewModel,
        AllDependencies.orbit,
        AllDependencies.epoxy,
        AllDependencies.navigationUi,
        AllDependencies.fragmentKtx,
        AllDependencies.navigationFragment,
        AllDependencies.zoomImage,
    )
}

object TestSharedModuleDependencies : Dependencies {
    override fun getDependencies(): List<Dependency> = listOf(
        AllDependencies.coroutinesCore,
        AllDependencies.orbitTest,
        AllDependencies.junitApi,
        AllDependencies.testRunnerImpl,
        AllDependencies.coroutineTestApi,
        AllDependencies.kotestApi,
        AllDependencies.kotlinReflectApi,
        AllDependencies.kotestAssertions,
    )
}

object AndroidTestSharedModuleDependencies : Dependencies {
    override fun getDependencies(): List<Dependency> = listOf(
        AllDependencies.coroutinesAndroid,
        AllDependencies.coroutinesCore,
        AllDependencies.orbitTest,
        AllDependencies.mockkAndroidApi,
        AllDependencies.hiltAndroid,
        AllDependencies.coroutineTestApi,
        AllDependencies.appcompat,
        AllDependencies.junitApi,
        AllDependencies.testRunnerImpl,
        AllDependencies.hiltCompiler,
        AllDependencies.testRules,
        AllDependencies.espresso,
        AllDependencies.fragmentTesting,
        AllDependencies.androidXJunitImpl,
        AllDependencies.hiltAndroidTestingApi,
    )
}

object RemoteModuleDependencies : Dependencies {

    override fun getDependencies(): List<Dependency> = listOf(
        AllDependencies.retrofit,
        AllDependencies.okhttp3,
        AllDependencies.okhttpLoggingInterceptor,
        AllDependencies.hiltAndroid,
        AllDependencies.hiltCompiler,
        AllDependencies.kotlinxSerializationJson,
        AllDependencies.retrofitSerialization,
    )
}

object ApiModuleDependencies : Dependencies {

    override fun getDependencies(): List<Dependency> = listOf(
        AllDependencies.hiltAndroid,
        AllDependencies.appcompat,
        AllDependencies.hiltCompiler,
    )
}
