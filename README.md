# Gradle Example: Shared Library

[Deutsche Version](README_ger.md)

This repository is the second of three Gradle stages. Compared with `gradle_01_wizard`, it adds the Android library module `Shared`. The installable `app` module can now use shared Kotlin code and Android resources from this library.

The changes are explained in [docs/Gradle.md](docs/Gradle.md). A [German version](docs/Gradle_ger.md) is also available. The [first project](https://github.com/berndRog/gradle_01_wizard/blob/master/docs/Gradle.md) explains the Gradle files of the wizard project.

## The three stages

| Project | Contents | Focus |
|---|---|---|
| [`gradle_01_wizard`](https://github.com/berndRog/gradle_01_wizard) | one `app` module | Gradle files of a wizard project |
| [`gradle_02_shared`](https://github.com/berndRog/gradle_02_shared) | `app` and `Shared` | application and library modules |
| [`gradle_03_modules`](https://github.com/berndRog/gradle_03_modules) | centralized configuration for `app` and `Shared` | extracting common settings from module build files |

The Gradle guide compares the changed files with the initial project.
