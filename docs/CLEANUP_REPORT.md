# Stage 1 cleanup report

## Goal

Prepare the original university project for version control without redesigning the game or changing its level rules.

## Original package

The uploaded package contained:

- **18 Java source files**;
- **27 compiled `.class` files**;
- **13 asset files**;
- source files, compiled files, and assets mixed inside the old `java/classes` layout.

## What was removed from the repository draft

- 27 compiled `.class` files.

Compiled output should be generated locally and should not be committed to Git.

## What was kept

- all 18 `.java` source files;
- all 13 original assets in the local cleanup package;
- original class names and game/level logic.

## What was moved

| Original location | New location |
| --- | --- |
| `java/*.java` | `src/main/java/*.java` |
| `java/classes/Assets/*` | `src/main/resources/Assets/*` |
| `java/classes/*.class` | removed; generated later in `build/classes` |

## Minimal source changes made in Stage 1

Only resource-loading code was adjusted so the reorganized directory structure can be compiled and run more portably:

- resource paths now use classpath paths such as `/Assets/Chao.png` instead of Windows-only `Assets\\Chao.png`;
- the player image is loaded from the classpath instead of a working-directory `File` path;
- audio is loaded from the classpath;
- if a WAV file is absent (for example, because it is intentionally excluded from a public repository), the game continues without music rather than failing immediately.

No obstacle placement, movement constants, level data, collision rules, interface dimensions, or gameplay timing values were intentionally changed.

## Files added for project hygiene

- `.gitignore`
- `README.md`
- `CONTRIBUTORS.md`
- `ASSET_REVIEW.md`
- `build.ps1`
- `run.ps1`
- `docs/ORIGINAL_TREE.txt`
- `docs/CURRENT_TREE.txt`

## Deferred to later stages

- removal of debugging `System.out.println` calls;
- cleanup of informal comments and naming conventions;
- renaming `painelDeJogo` to Java-standard class naming;
- package declarations;
- Maven/Gradle configuration;
- automated tests;
- screenshots/GIF for the README;
- final asset licensing decisions;
- selection of an open-source license.
