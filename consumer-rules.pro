# Keep all public classes and methods in the library
-keep public class * {
    public protected *;
}

# Keep all classes with specific annotations (useful for JSON or DI)
-keepclasseswithmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# Prevent R8 from removing classes used via reflection
-keepclassmembers class * {
    public <init>(...);
}

# Keep model classes (optional - adjust your package)
-keep class dev.myallworks.native_orm_db.model.** { *; }

# If you're using annotations (like Room or Dagger)
-keep @interface dev.myallworks.native_orm_db.annotations.**

# If using Kotlin data classes or Jetpack libraries
-keepclassmembers class ** {
    kotlin.Metadata;
}

# Keep your entry point (if needed)
-keep class dev.myallworks.native_orm_db.MainClass { *; }
