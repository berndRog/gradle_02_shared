# Gradle Example: Shared Library

[Deutsche Version](README_ger.md)

This repository is the second of two Gradle stages. Compared with `gradle_01_wizard`, it adds the Android library module `Shared`. The installable `app` module can now use shared Kotlin code and Android resources from this library.

The changes are explained in [docs/Gradle.md](docs/Gradle.md). A [German version](docs/Gradle_ger.md) is also available. The [first project](https://github.com/berndRog/gradle_01_wizard/blob/master/docs/Gradle.md) explains the Gradle files of the wizard project.

## The two stages

| Project | Contents | Focus |
|---|---|---|
| [`gradle_01_wizard`](https://github.com/berndRog/gradle_01_wizard) | one `app` module | Gradle files of a wizard project |
| [`gradle_02_shared`](https://github.com/berndRog/gradle_02_shared) | `app` and `Shared` | application and library modules |

The Gradle guide compares the changed files with the initial project.

## Shared library source and checks

The library's source code, resources, and tests are synchronized with
[`MobileSystems/Shared`](https://github.com/berndRog/MobileSystems/tree/6fe77ce4bc6423f207025b669387f5f130f2539f/Shared).
Its reusable components and opt-in Koin modules are described in
[Shared/readme.md](Shared/readme.md). This repository keeps its own Gradle build
with the two modules `app` and `Shared`; open the repository root in Android Studio.
The module build files declare their plugins and dependencies directly.

The build uses Java 21, Gradle 9.8.2, Android Gradle Plugin 9.5.1, and Android SDK
37 with minor API level 2. Install the matching SDK before building.

```bash
./gradlew :app:assembleDebug :Shared:assembleDebug
./gradlew :app:testDebugUnitTest :Shared:testDebugUnitTest
./gradlew :app:connectedDebugAndroidTest :Shared:connectedDebugAndroidTest
```

The last command requires a connected emulator or Android device.
