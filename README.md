# Geometric Expansion

Academic Java game project developed collaboratively by **Alexandre Selani, Cristiano Marques, and Pedro Francischeto**.

This repository is being reorganized from the original university-project version so that the source code is easier to understand, build, and present as a portfolio project.

## Project overview

Geometric Expansion is a desktop game built with **Java Swing**. The code includes:

- a start screen and level-selection interface;
- three playable levels;
- player movement and jumping;
- obstacle, platform, and collision handling;
- custom images and font resources;
- audio support for each level;
- timers and threads used by the game loop and collision checks.

## Project structure

```text
geometric-expansion/
├── src/
│   └── main/
│       ├── java/          # Java source files
│       └── resources/
│           └── Assets/    # Images, font, and local audio resources
├── docs/                  # Cleanup notes and project trees
├── build.ps1              # Windows build helper
├── run.ps1                # Windows run helper
└── .gitignore
```

## Requirements

- JDK 17 or newer
- Windows PowerShell for the included helper scripts

The cleaned source has been compilation-tested with **JDK 21**.

## Build

From PowerShell, in the project folder:

```powershell
.\build.ps1
```

The script compiles the sources to `build/classes` and copies the resources to the classpath.

## Run

After building:

```powershell
.\run.ps1
```

The application starts from `TelaInicial`.

## Important note about assets

The original project contains image, font, and audio assets. Their origin and redistribution rights should be reviewed before the repository is made public.

The three WAV files are intentionally listed in `.gitignore` so they are not added to Git by accident. The local copies are kept in this cleanup package only so the original project can still be tested while the asset review is pending.

See [`ASSET_REVIEW.md`](ASSET_REVIEW.md) before publishing the repository.

## Authors

- Alexandre Selani
- Cristiano Marques
- Pedro Francischeto

## Status

This is the **first cleanup stage**. The game rules and level design were intentionally left unchanged. The current changes focus on project structure and portable resource loading. Code-style cleanup, documentation of individual classes, build tooling, and further refactoring can be handled in later stages.
