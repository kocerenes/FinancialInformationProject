<p align="center">
 <img src="https://github.com/kocerenes/FinancialInformationProject/blob/master/app/src/main/res/mipmap-xxxhdpi/ic_launcher.png?raw=true"  alt="drawing" />
</p>
<p align="center">  
📰 Hek News is a sample Android project using TMDB API based on MVVM architecture
<br/>
<p align="center">For API Documentation: <a href="https://newsapi.org/docs">Click here</a></p>
</p>

<p align="center">
<a href='https://play.google.com/store/apps/details?id=com.ekheek.financialinformationproject'><img width="200px" alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png'/></a>
</p>

### App Screenshots

| home screen | news detail | read later |
|:-:|:-:|:-:|
| <img src="https://github.com/kocerenes/FinancialInformationProject/blob/master/arts/ss.png?raw=true" alt="drawing" width="250"/> | <img src="https://github.com/kocerenes/FinancialInformationProject/blob/master/arts/ss2.png?raw=true" alt="drawing" width="250"/> | <img src="https://github.com/kocerenes/FinancialInformationProject/blob/master/arts/ss3.png?raw=true" alt="drawing" width="250"/> 

## App Gifs

| splash screen | list-detail-read later |
|:-:|:-:|
| <img src="https://github.com/kocerenes/FinancialInformationProject/blob/master/arts/gif1.gif?raw=true"  width="250"/> | <img src="https://github.com/kocerenes/FinancialInformationProject/blob/master/arts/gif3.gif?raw=true"  width="250"/> |

## How to build on your environment
Add your [News API](https://newsapi.org/)'s API key in local.properties file.
```xml
NEWS_API_KEY=add_your_api_key
```
and run the app.

## Tech stack
* ✅ MVVM with Clean Architecture
* ✅ [Kotlin Flow][33] - In coroutines, a flow is a type that can emit multiple values sequentially, as opposed to suspend functions that return only a single value.
* ✅ [LiveData][31] - is an observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services.
* ✅ [Coroutines][51] - A concurrency design pattern that you can use on Android to simplify code that executes asynchronously.
* ✅ [Navigation Component][24] - Handle everything needed for in-app navigation. asynchronous tasks for optimal execution.
* ✅ [Safe-Args][25] - For passing data between destinations
* ✅ [Dagger-Hilt][93] - A dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
* ✅ [Room][94] - The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
* ✅ [ViewModel][17] - Easily schedule asynchronous tasks for optimal execution.
* ✅ [Retrofit][90] - Retrofit is a REST client for Java/ Kotlin and Android by Square inc under Apache 2.0 license. Its a simple network library that is used for network transactions. By using this library we can seamlessly capture JSON response from web service/web API.
* ✅ [OkHttp][23] - Doing HTTP efficiently makes your stuff load faster and saves bandwidth.
* ✅ [View Binding][11] - a feature that allows you to more easily write code that interacts with views.
* ✅ [Lifecycle][22] - As a user navigates through, out of, and back to your app, the Activity instances in your app transition through different states in their lifecycle.
* ✅ [Glide][27] - for image loading framework for Android that wraps media decoding, memory and disk caching, and resource pooling into a simple and easy to use interface.
* ✅ [Firebase Auth][91] - You can use Firebase Authentication to let your users authenticate with Firebase using their email addresses and passwords, and to manage your app's password-based accounts.
* ✅ [Lottie][45] - Lottie is an open-source animation file format that’s tiny, high quality, scriptable, interactive, and can be manipulated at runtime
* ✅ [Coil][21] - An image loading library for Android backed by Kotlin Coroutines

[11]: https://developer.android.com/topic/libraries/view-binding
[92]: https://coil-kt.github.io/coil/
[93]: https://developer.android.com/training/dependency-injection/hilt-android
[51]: https://developer.android.com/kotlin/coroutines
[90]: https://square.github.io/retrofit/
[33]: https://developer.android.com/kotlin/flow
[22]: https://developer.android.com/guide/components/activities/activity-lifecycle
[17]: https://developer.android.com/topic/libraries/architecture/viewmodel?gclid=Cj0KCQiA4uCcBhDdARIsAH5jyUlE1HL0TNxXu5b4pw6DEMOlRccWdVnqiRcLji7OHsDN6trNOKa-sdgaAr6rEALw_wcB&gclsrc=aw.ds
[23]: https://square.github.io/okhttp/
[24]: https://developer.android.com/guide/navigation/navigation-getting-started
[25]: https://developer.android.com/guide/navigation/navigation-pass-data
[31]: https://developer.android.com/topic/libraries/architecture/livedata
[27]: https://github.com/bumptech/glide
[85]: https://developer.android.com/topic/libraries/architecture/paging/v3-overview
[86]: https://developer.android.com/topic/libraries/data-binding
[95]: https://github.com/square/moshi
[9]: https://github.com/JakeWharton/timber
[91]: https://firebase.google.com/docs/auth/android/password-auth
[94]: https://developer.android.com/training/data-storage/room
[45]: https://lottiefiles.com/
[21]: https://coil-kt.github.io/coil/
