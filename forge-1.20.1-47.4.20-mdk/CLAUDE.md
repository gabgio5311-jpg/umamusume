# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this is

A Minecraft **Forge 1.20.1** (Forge 47.4.x) mod, modid `umamusume`, that adds Uma Musume horse-girl mobs and a generated "Hipódromo" (racetrack) structure. Entity rendering/animation uses **GeckoLib 4.4.4**. Code is Java 17. Comments and in-game strings are in Portuguese.

Note: although `gradle.properties` still contains the unfilled MDK template values (`mod_id=examplemod`, etc.), the **real** values live in `build.gradle` under `tasks.named('processResources')` — `mod_id` is `umamusume`. Edit those, not `gradle.properties`.

## Commands

Use the Gradle wrapper (`./gradlew` on Unix, `gradlew.bat` on Windows). `org.gradle.daemon=false` is set, so every invocation is a cold start.

- `./gradlew build` — compile + reobfuscate the mod jar into `build/libs/`
- `./gradlew runClient` — launch a dev Minecraft client with the mod loaded (working dir `run/`)
- `./gradlew runServer` — launch a dev dedicated server (`--nogui`)
- `./gradlew genIntellijRuns` / `genEclipseRuns` — generate IDE run configs
- First-time setup after cloning or changing Forge/mappings: `./gradlew --refresh-dependencies` (re-decompiles Minecraft; needs ~3G heap, already configured)

There are no unit tests. `forge.enabledGameTestNamespaces=umamusume` is set on the run configs, but no GameTests exist yet.

## Architecture

### Mod bootstrap
`UmaMusumeMod` (`@Mod("umamusume")`) is the entry point. Its constructor takes an injected `FMLJavaModLoadingContext` (the mod event bus comes from `context.getModEventBus()`), calls `GeckoLib.initialize()`, and registers every `DeferredRegister` onto that bus: `ModEntities`, `ModItems`, `ModCreativeTab`, and the two registers in `ModStructures`. It also binds `registerAttributes` to `EntityAttributeCreationEvent` — **every new entity must be added there** or it crashes at attribute creation.

`ClientSetup` (`@Mod.EventBusSubscriber(..., value = Dist.CLIENT)`) registers the GeckoLib renderers, client-side only.

### API conventions (1.20.1, forward-compatible)
Forge marks two APIs deprecated-for-removal that this codebase avoids — keep new code consistent:
- Build `ResourceLocation`s with `ResourceLocation.fromNamespaceAndPath(ns, path)` (or `.parse(str)`), **not** the deprecated `new ResourceLocation(...)` constructor.
- Get the mod event bus from the **constructor-injected** `FMLJavaModLoadingContext` parameter, **not** the deprecated static `FMLJavaModLoadingContext.get()`.

### The per-Uma pattern
Each character lives in `entity/<name>/` as a trio (plus three registration touch-points):

- `XxxEntity` extends `BaseUmaEntity` and implements GeckoLib's `GeoEntity` — holds an `AnimatableInstanceCache`, overrides `getUmaName()`, and `registerControllers()` to switch between `<name>.animation.idle` / `.run` based on `state.isMoving()`.
- `XxxModel` extends `GeoModel` — points at `geo/<name>.geo.json`, `textures/entity/<name>.png`, `animations/<name>.animation.json`.
- `XxxRenderer` extends `GeoEntityRenderer` — wires the model and texture.

`BaseUmaEntity` (extends `PathfinderMob`) holds the shared behavior: base attributes (20 HP, 0.38 speed), wander/look AI goals, and a **shift-click follow toggle** (`mobInteract` flips a `following` flag; `tick()` paths toward the nearest player within 32 blocks when set).

**To add a new Uma**, mirror an existing folder (e.g. `silence_suzuka`) and register it in all of: `ModEntities` (EntityType, `.sized(0.6, 1.8)`), `ModItems` (spawn egg), `ModCreativeTab` (add to `displayItems`), `UmaMusumeMod.registerAttributes`, and `ClientSetup`. Then drop the matching `geo/`, `animations/`, `textures/entity/`, and `models/item/<name>_spawn_egg.json` assets, plus a `lang/en_us.json` entry. The resource file names must match the `ResourceLocation`s in the Model/Renderer exactly.

### Worldgen — the Hipódromo
A custom jigsaw-free structure assembled from four NBT template quadrants.

- `ModStructures` registers the `StructureType` (`hipodromo`) and `StructurePieceType` (`hipodromo_piece`) via codec.
- `HipodromoStructure` (`findGenerationPoint`) rejects sites over water/ice and ocean/cherry_grove biomes, then places a single `HipodromoPiece` on the surface.
- `HipodromoPiece.postProcess` does the heavy lifting: clears air above and lays a dirt foundation below across the footprint (53×8×71), then **manually stitches the four templates** `hipodromoid` / `hipodromoie` / `hipodromosd` / `hipodromose` at fixed 48-block offsets, strips snow, and spawns one of each Uma in a row at the center chunk.
- JSON config: `data/umamusume/worldgen/structure/hipodromo.json` (biome list + placement step) and `.../structure_set/hipodromo_set.json` (random_spread, spacing 20). NBT templates live in `data/umamusume/structures/`.

## Gotchas

- `ModItems.agnes_tachyon_spawn_egg` and `mejiro_mcqueen_spawn_egg`/`silence_suzuka_spawn_egg` were copy-pasted: `agnes_tachyon_spawn_egg` wrongly references `ModEntities.GOLD_SHIP`. Check the referenced `RegistryObject` and the spawn-egg colors when touching that file.
- `HipodromoPiece` spawns only 7 of the 9 Umas (the spawn list omits `mejiro_mcqueen` and `silence_suzuka`).
- Forge version is `1.20.1-47.4.20` in three places that must stay in sync: `gradle.properties` (`forge_version`), and `build.gradle`'s `dependencies` block and `processResources` replace map.
