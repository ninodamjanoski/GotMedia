# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-dontpreverify
-repackageclasses *
-allowaccessmodification
-optimizations !code/simplification/arithmetic
-optimizationpasses 5

-verbose

-keep class android.support.** { *; }
-keep interface android.support.** { *; }

-keep class com.google.android.gms.common.** { *; }

# OkHttp
-dontwarn okio.**
-dontwarn com.squareup.okhttp.**
-dontwarn java.nio.file.*
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

-keepnames @com.squareup.annotation.KeepName class *
-keepclassmembernames class * {
    @com.squareup.annotation.KeepName *;
}
-keepnames @okio.annotation.KeepName class *
-keepclassmembernames class * {
    @okio.annotation.KeepName *;
}

# Butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

# RxJava
# https://github.com/artem-zinnatullin/RxJavaProGuardRules
-dontwarn rx.**
-keep class rx.** { *; }
-keepclassmembers class rx.** { *; }

## Retrolambda specific rules ##
# as per official recommendation: https://github.com/evant/gradle-retrolambda#proguard
-dontwarn java.lang.invoke.*

# streamsupport
-keep class java8.**
-dontwarn java8.**

# glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}

# Gson
-keep class com.google.gson.** { *; }
-keep class com.google.inject.** { *; }

-keep class com.google.common.** { *; }
-keep class org.apache.** { *; }
-keep class org.htmlcleaner.** { *; }


# OkHttp3
-keepattributes Signature
-keepattributes *Annotation*
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**

# Rerofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
