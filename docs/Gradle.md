# Gradle with a Shared Module

[Deutsche Version](Gradle_ger.md)

## Stage 2: Adding an Android library

This project builds on `gradle_01_wizard`. The [description of the basic Gradle files](https://github.com/berndRog/gradle_01_wizard/blob/master/docs/Gradle.md) still applies. New elements are the `Shared` module, its registration in `settings.gradle.kts`, the Android library plugin, and the dependency from `app` to `Shared`.

```text
gradle_02_shared/
├── app/
│   └── build.gradle.kts
├── Shared/
│   ├── build.gradle.kts
│   └── consumer-rules.pro
├── gradle/
│   └── libs.versions.toml
├── build.gradle.kts
└── settings.gradle.kts
```

## Change in `settings.gradle.kts`

A directory does not become a Gradle module merely because it contains a build file. The module must also be registered in the project:

```kotlin
include(":app")
include(":Shared")
```

The leading colon denotes a module path starting at the root project. Gradle therefore knows two subprojects at this stage.

## Making the Android library plugin available

The version catalog contains the plugin alias:

```toml
android-library = {
   id = "com.android.library",
   version.ref = "agp"
}
```

The root `build.gradle.kts` makes the plugin available without applying it to the root project:

```kotlin
alias(libs.plugins.android.library) apply false
```

Only `Shared/build.gradle.kts` applies it:

```kotlin
plugins {
   alias(libs.plugins.android.library)
   alias(libs.plugins.kotlin.compose)
   alias(libs.plugins.kotlin.serialization)
   alias(libs.plugins.google.devtools.ksp)
}
```

## Application and library modules

`app` uses `com.android.application`; `Shared` uses `com.android.library`.

| Characteristic | `app` | `Shared` |
|---|---|---|
| Result | installable APK/AAB | Android Archive (AAR) |
| `applicationId` | required | not present |
| `versionCode` / `versionName` | app version | not required |
| `namespace` | `de.rogallab.mobile` | `de.rogallab.mobile.shared` |
| Launchable | yes | no, can only be used through an app |

Both modules have their own `android` block because the library is also compiled against an Android SDK, supports a minimum Android version, and may contain Android resources. Every Android namespace must be unique.

`consumerProguardFiles("consumer-rules.pro")` specifies rules that are later passed to a consuming app together with the AAR. These differ from rules that affect only the app's own build.

## Dependency from `app` to `Shared`

The `dependencies` block of the `app` module contains:

```kotlin
implementation(project(":Shared"))
```

`project(":Shared")` refers to a module in the same Gradle build, not to an external Maven library. This allows `app` to use public Kotlin classes, composables, and Android resources from `Shared`.

The dependency deliberately points in only one direction:

```text
app  ──uses──>  Shared
```

`Shared` must not depend on `app` in the opposite direction. That would create a cyclic dependency and eliminate the library's reusability.

## Dependencies are module-specific

A declaration in `app/build.gradle.kts` does not automatically make an external library available to source code in `Shared`. If code in `Shared` needs Room or Compose, for example, `Shared` has to declare that dependency itself.

For this reason, `app/build.gradle.kts` and `Shared/build.gradle.kts` contain many similar entries at this learning stage. The repetition is useful for teaching: it makes clear that each module is initially configured independently.

Later, a distinction can be made between `implementation` and `api`. `implementation` generally keeps a dependency as an implementation detail of the module. `api` exposes it to consuming modules and should only be used if types from that library are actually part of the public interface of `Shared`.

## What changes compared with `gradle_01_wizard`?

| File | Change |
|---|---|
| `settings.gradle.kts` | `include(":Shared")` registers the new module. |
| Root `build.gradle.kts` | makes the Android library plugin available. |
| `Shared/build.gradle.kts` | configures the new Android library. |
| `app/build.gradle.kts` | includes `Shared` with `implementation(project(":Shared"))`. |
| `gradle/libs.versions.toml` | contains the alias for the library plugin. |

Useful commands include:

```bash
./gradlew projects
./gradlew :app:assembleDebug
./gradlew :Shared:test
```

`projects` lists the registered modules. The module path prefixed to the other commands runs a task for a specific module.

The next development stage is the [`gradle_03_modules`](https://github.com/berndRog/gradle_03_modules) project. It centralizes the common settings from the two module build files.
