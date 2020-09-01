
object Versions {
    const val room_version = "2.2.5"
    const val  lifecycle_version = "2.2.0"
    const val recyclerView_version = "1.1.0"
    const val viewPager_version = "1.0.0"
    const val google_material = "1.3.0-alpha02"
    const val cardview_version = "1.0.0"
    const val fragment_ktx_versions = "2.3.0"
    const val navigation_ui_ktx_versions = "2.3.0"
    const val intuit_sdp_version = "1.0.6"
    const val intuit_ssp_version = "1.0.6"
    const val hilt_android_version = "2.28-alpha"
    const val hilt_compiler_version = "2.28-alpha"
    const val google_gson_version = "2.8.6"
    const val retrofit_version = "2.9.0"
    const val retrofit_gson_converter_version = "2.9.0"
    const val logging_interceptor_version = "4.6.0"
    const val kotlin_jdk = "1.3.72"
    const val kotlin_coroutines_version = "1.3.8"



}

object Dependencies {
    val roomPersistenceLibraryRuntime = "androidx.room:room-runtime:${Versions.room_version}"
    val roomPersistenceLibraryCompiler = "androidx.room:room-compiler:${Versions.room_version}"
    val lifecyleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"
    val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_version}"
    val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"
    val lifecycleCommon = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle_version}"
    val cardView = "androidx.cardview:cardview:${Versions.cardview_version}"
    val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView_version}"
    val viewPager = "androidx.viewpager2:viewpager2:${Versions.viewPager_version}"
    val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.fragment_ktx_versions}"
    val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation_ui_ktx_versions}"
    val intuitsdp = "com.intuit.sdp:sdp-android:${Versions.intuit_sdp_version}"
    val intuitssp = "com.intuit.ssp:ssp-android:${Versions.intuit_ssp_version}"
    val googleMaterial = "com.google.android.material:material:${Versions.google_material}"
    val googleGson = "com.google.code.gson:gson:${Versions.google_gson_version}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_gson_converter_version}"
    val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.logging_interceptor_version}"
    val daggerHilt = "com.google.dagger:hilt-android:${Versions.hilt_android_version}"
    val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt_compiler_version}"
    val room = "androidx.room:room-ktx:${Versions.room_version}"
    val roomTesting = "androidx.room:room-testing:${Versions.room_version}"
    val kotlinJdk = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin_jdk}"
    val kotlin_coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlin_coroutines_version}"
    val kotlin_coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlin_coroutines_version}"



}