package com.ekheek.financialinformationproject

import Versions

object Libs {

    object Gradle {
        const val androidGradlePlugin = "com.android.tools.build:gradle:7.0.0"
    }

    object AndroidX {
        const val core = "androidx.core:core-ktx:" + Versions.coreKtx
        const val appCompat = "androidx.appcompat:appcompat:" + Versions.appCompat
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:" + Versions.constraint
        const val vmLifeCycle = "androidx.lifecycle:lifecycle-viewmodel-ktx:" + Versions.viewModel
        const val navigation = "androidx.navigation:navigation-fragment-ktx:" + Versions.navigation
        const val navigationUI = "androidx.navigation:navigation-ui-ktx:" + Versions.navigation
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:" + Versions.livedata
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:" + Versions.runtime
        const val extensions = "androidx.lifecycle:lifecycle-extensions:" + Versions.extensions
        const val safeArgs =
            "androidx.navigation:navigation-safe-args-gradle-plugin:" + Versions.safeArgs
        const val swipeRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:" + Versions.swipe_refresh
    }

    object Google {
        const val material = "com.google.android.material:material:" + Versions.material
        const val firebase_auth = "com.google.firebase:firebase-auth-ktx:" + Versions.firebase_auth
        const val google_services = "com.google.gms:google-services:" + Versions.google_services
    }

    object Hilt {
        const val android = "com.google.dagger:hilt-android:" + Versions.hilt
        const val compiler = "com.google.dagger:hilt-android-compiler:" + Versions.hiltCompiler
        const val plugin = "com.google.dagger:hilt-android-gradle-plugin:" + Versions.hilt
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:" + Versions.retrofit
        const val converter = "com.squareup.retrofit2:converter-gson:" + Versions.retrofit
        const val okhttp = "com.squareup.okhttp3:okhttp:" + Versions.ok_http
        const val interceptor = "com.squareup.okhttp3:logging-interceptor:" + Versions.ok_http
        const val gson: String = "com.google.code.gson:gson:" + Versions.gson
    }

    object Room {
        const val ktx = "androidx.room:room-ktx:" + Versions.room
        const val runtime = "androidx.room:room-runtime:" + Versions.room
        const val compiler = "androidx.room:room-compiler:" + Versions.room
    }

    object Image {
        const val glide: String = "com.github.bumptech.glide:glide:" + Versions.glide
        const val compiler = "com.github.bumptech.glide:compiler:" + Versions.glide
        const val coil = "io.coil-kt:coil:" + Versions.coil
    }

    object Test {
        const val junit = "junit:junit:" + Versions.junit
        const val testExt = "androidx.test.ext:junit:" + Versions.testExt
        const val espresso = "androidx.test.espresso:espresso-core:" + Versions.espresso
    }

    object View {
        const val lottie = "com.airbnb.android:lottie:" + Versions.lottie
    }
}