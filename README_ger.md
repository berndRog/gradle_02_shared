# Gradle-Beispiel: Shared Library

[English version](README.md)

Dieses Repository ist die zweite von drei Gradle-Stufen. Gegenüber `gradle_01_wizard` kommt das Android-Library-Modul `Shared` hinzu. Das installierbare `app`-Modul kann dadurch gemeinsam verwendbaren Code und Android-Ressourcen aus `Shared` nutzen.

Die Änderungen werden in [docs/Gradle_ger.md](docs/Gradle_ger.md) beschrieben. Zusätzlich gibt es eine [englische Version](docs/Gradle.md). Die Basisdateien des Wizard-Projekts erläutert der [erste Stand](https://github.com/berndRog/gradle_01_wizard/blob/master/docs/Gradle_ger.md).

## Die drei Stufen

| Projekt | Inhalt | Schwerpunkt |
|---|---|---|
| [`gradle_01_wizard`](https://github.com/berndRog/gradle_01_wizard) | ein `app`-Modul | Gradle-Dateien eines Wizard-Projekts |
| [`gradle_02_shared`](https://github.com/berndRog/gradle_02_shared) | `app` und `Shared` | Application- und Library-Modul |
| [`gradle_03_modules`](https://github.com/berndRog/gradle_03_modules) | zentrale Konfiguration für `app` und `Shared` | gemeinsame Einstellungen aus Moduldateien herausziehen |

Die Änderungen gegenüber dem Ausgangsprojekt werden in der Gradle-Beschreibung dateibezogen gegenübergestellt.
