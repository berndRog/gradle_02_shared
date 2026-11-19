# Gradle-Beispiel: Shared Library

[English version](README.md)

Dieses Repository ist die zweite von zwei Gradle-Stufen. Gegenüber `gradle_01_wizard` kommt das Android-Library-Modul `Shared` hinzu. Das installierbare `app`-Modul kann dadurch gemeinsam verwendbaren Code und Android-Ressourcen aus `Shared` nutzen.

Die Änderungen werden in [docs/Gradle_ger.md](docs/Gradle_ger.md) beschrieben. Zusätzlich gibt es eine [englische Version](docs/Gradle.md). Die Basisdateien des Wizard-Projekts erläutert der [erste Stand](https://github.com/berndRog/gradle_01_wizard/blob/master/docs/Gradle_ger.md).

## Die zwei Stufen

| Projekt | Inhalt | Schwerpunkt |
|---|---|---|
| [`gradle_01_wizard`](https://github.com/berndRog/gradle_01_wizard) | ein `app`-Modul | Gradle-Dateien eines Wizard-Projekts |
| [`gradle_02_shared`](https://github.com/berndRog/gradle_02_shared) | `app` und `Shared` | Application- und Library-Modul |

Die Änderungen gegenüber dem Ausgangsprojekt werden in der Gradle-Beschreibung dateibezogen gegenübergestellt.

## Quelle der Shared-Library und Prüfungen

Quellcode, Ressourcen und Tests der Library sind mit
[`MobileSystems/Shared`](https://github.com/berndRog/MobileSystems/tree/6fe77ce4bc6423f207025b669387f5f130f2539f/Shared)
abgeglichen. Die wiederverwendbaren Bausteine und gezielt auswählbaren Koin-Module
beschreibt [Shared/readme_ger.md](Shared/readme_ger.md). Dieses Repository behält
seinen eigenen Gradle-Build mit den Modulen `app` und `Shared`; öffne den
Repository-Root in Android Studio. Die Modul-Builddateien deklarieren ihre Plugins
und Abhängigkeiten direkt.

Der Build verwendet Java 21, Gradle 9.8.2, Android Gradle Plugin 9.5.1 und Android
SDK 37 mit Minor-API-Level 2. Installiere vor dem Build das passende SDK.

```bash
./gradlew :app:assembleDebug :Shared:assembleDebug
./gradlew :app:testDebugUnitTest :Shared:testDebugUnitTest
./gradlew :app:connectedDebugAndroidTest :Shared:connectedDebugAndroidTest
```

Der letzte Befehl benötigt einen verbundenen Emulator oder ein Android-Gerät.
