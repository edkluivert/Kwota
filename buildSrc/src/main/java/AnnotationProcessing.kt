package dependencies

object AnnotationProcessing {

    val hiltandroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt_android_version}"
    val roomPersistenceLibraryCompiler = "androidx.room:room-compiler:${Versions.room_version}"
    val lifecycle_compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle_version}"
    val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltViewModels}"

}