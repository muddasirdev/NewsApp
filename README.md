# NewsApp

An Android news reader sample built with Kotlin and Jetpack Compose. It demonstrates a small article browsing flow with category navigation, local persistence, and bookmarks.

## What it currently does

- Requests top headlines for General, Business, Technology, Sports, Health, and Entertainment.
- Shows article lists and article details.
- Stores article records in Room and lets a reader toggle bookmarks.
- Loads saved articles from the local database when network requests fail.
- Opens the article URL in an in-app WebView.

The API client currently contains the placeholder `YOUR_API_KEY_HERE` in `NewsApi.kt`. Requests will not return real headlines until a valid NewsAPI key is configured. The app does not currently read the key from an untracked local secrets file; do not put a personal key in a public commit.

## Technology

- Kotlin, Jetpack Compose, Material 3
- Retrofit and OkHttp
- Room
- Hilt dependency injection
- Coroutines and Flow
- Navigation Compose and Coil
- Android Gradle Plugin / Kotlin versions are managed in the Gradle version catalog

## Project layout

- `app/src/main/java` — application, data, dependency injection, navigation, screens, and view models
- `app/src/main/res` — Android resources and layouts
- `app/src/test` and `app/src/androidTest` — test source sets
- `gradle/libs.versions.toml` — centralized dependency versions
- `screenshots/` — sample app screenshots

The data flow is organized around API and database data sources, a news repository, and screen view models. The UI is written in Compose.

## Requirements

- Android Studio with a JDK 17 runtime
- Android SDK 36 installed
- Android device or emulator running Android 8.0 / API 26 or newer
- A NewsAPI key to load live headlines

The project configures compile/target SDK 36 and minimum SDK 26. Gradle plugin and Kotlin versions are pinned by the repository; use a current Android Studio compatible with those versions.

## Build and run

Open the repository in Android Studio, allow Gradle sync to finish, then run the `app` configuration on an API 26+ device or emulator.

From a shell with the Android SDK configured:

```sh
./gradlew assembleDebug
```

On Windows:

```bat
gradlew.bat assembleDebug
```

The debug APK is produced under `app/build/outputs/apk/debug/`. A successful build has not been asserted by this README; dependency and SDK compatibility should be checked in the developer's environment.

## Screenshots

| Headlines | Bookmarks | Article |
| --- | --- | --- |
| ![Headlines screen](screenshots/home.jpg) | ![Bookmarks screen](screenshots/bookmarks.jpg) | ![Article screen](screenshots/detail.jpg) |

## Limitations

This is a sample app, not a hosted news service. Live data depends on a valid API key, network connectivity, and NewsAPI plan/region limits. Offline access is based on locally cached database records; it does not guarantee that every article body is available offline. No license file is included, so contact the owner before reusing or distributing the code.
