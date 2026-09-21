# Gradle mit einem Shared-Modul

## Stufe 2: Android Library ergänzen

Dieses Projekt baut auf `gradle_01_wizard` auf. Die [Beschreibung der Gradle-Basisdateien](https://github.com/berndRog/gradle_01_wizard/blob/master/docs/Gradle.md) gilt weiterhin. Neu sind das Modul `Shared`, seine Registrierung in `settings.gradle.kts`, das Android-Library-Plugin und die Abhängigkeit von `app` auf `Shared`.

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

## Änderung in `settings.gradle.kts`

Ein Verzeichnis wird nicht allein dadurch zu einem Gradle-Modul, dass es eine Builddatei enthält. Das Modul muss zusätzlich im Projekt registriert werden:

```kotlin
include(":app")
include(":Shared")
```

Der führende Doppelpunkt bezeichnet einen Modulpfad vom Root-Projekt aus. Gradle kennt in diesem Stand damit zwei Subprojekte.

## Android-Library-Plugin bereitstellen

Der Version Catalog enthält den Plugin-Alias:

```toml
android-library = {
   id = "com.android.library",
   version.ref = "agp"
}
```

Das Root-`build.gradle.kts` stellt das Plugin bereit, ohne es auf das Root-Projekt anzuwenden:

```kotlin
alias(libs.plugins.android.library) apply false
```

Erst `Shared/build.gradle.kts` wendet es an:

```kotlin
plugins {
   alias(libs.plugins.android.library)
   alias(libs.plugins.kotlin.compose)
   alias(libs.plugins.kotlin.serialization)
   alias(libs.plugins.google.devtools.ksp)
}
```

## Application- und Library-Modul

`app` verwendet `com.android.application`; `Shared` verwendet `com.android.library`.

| Merkmal | `app` | `Shared` |
|---|---|---|
| Ergebnis | installierbare APK/AAB | Android Archive (AAR) |
| `applicationId` | erforderlich | nicht vorhanden |
| `versionCode` / `versionName` | App-Version | nicht erforderlich |
| `namespace` | `de.rogallab.mobile` | `de.rogallab.mobile.shared` |
| Startbar | ja | nein, nur über eine App nutzbar |

Beide Module besitzen einen eigenen `android`-Block, weil auch die Library gegen ein Android SDK kompiliert wird, eine minimale Android-Version unterstützt und Android-Ressourcen enthalten kann. Jeder Android-Namensraum muss eindeutig sein.

`consumerProguardFiles("consumer-rules.pro")` benennt Regeln, die später zusammen mit der AAR an eine verwendende App weitergegeben werden. Das unterscheidet sich von Regeln, die nur den eigenen App-Build betreffen.

## Abhängigkeit von `app` auf `Shared`

Im `dependencies`-Block des `app`-Moduls steht:

```kotlin
implementation(project(":Shared"))
```

`project(":Shared")` verweist auf ein Modul desselben Gradle-Builds und nicht auf eine externe Maven-Bibliothek. Dadurch kann `app` öffentliche Kotlin-Klassen, Composables und Android-Ressourcen aus `Shared` verwenden.

Die Abhängigkeitsrichtung ist bewusst nur:

```text
app  ──verwendet──>  Shared
```

`Shared` darf nicht umgekehrt von `app` abhängen. Das würde eine zyklische Abhängigkeit erzeugen und die Wiederverwendbarkeit der Library aufheben.

## Abhängigkeiten sind modulspezifisch

Eine Deklaration in `app/build.gradle.kts` macht eine externe Bibliothek nicht automatisch im Quellcode von `Shared` verfügbar. Benötigt Code in `Shared` beispielsweise Room oder Compose, muss `Shared` diese Abhängigkeit selbst deklarieren.

Deshalb besitzen `app/build.gradle.kts` und `Shared/build.gradle.kts` in diesem Lernstand viele ähnliche Einträge. Diese Wiederholung ist hier didaktisch nützlich: Sie macht sichtbar, dass jedes Modul zunächst eigenständig konfiguriert wird.

Später kann zwischen `implementation` und `api` unterschieden werden. `implementation` hält eine Abhängigkeit grundsätzlich als Implementierungsdetail des Moduls. `api` veröffentlicht sie an konsumierende Module und sollte nur verwendet werden, wenn Typen dieser Bibliothek tatsächlich Teil der öffentlichen Schnittstelle von `Shared` sind.

## Was ändert sich gegenüber `gradle_01_wizard`?

| Datei | Änderung |
|---|---|
| `settings.gradle.kts` | `include(":Shared")` registriert das neue Modul. |
| Root-`build.gradle.kts` | stellt das Android-Library-Plugin bereit. |
| `Shared/build.gradle.kts` | konfiguriert die neue Android Library. |
| `app/build.gradle.kts` | bindet `Shared` mit `implementation(project(":Shared"))` ein. |
| `gradle/libs.versions.toml` | enthält den Alias für das Library-Plugin. |

Nützliche Befehle sind:

```bash
./gradlew projects
./gradlew :app:assembleDebug
./gradlew :Shared:test
```

`projects` zeigt die registrierten Module. Der vorangestellte Modulpfad bei den anderen Befehlen führt gezielt eine Aufgabe eines bestimmten Moduls aus.

Die nächste Entwicklungsstufe zeigt das Projekt [`gradle_03_modules`](https://github.com/berndRog/gradle_03_modules). Dort werden die gemeinsamen Einstellungen der beiden Modul-Builddateien zentralisiert.
