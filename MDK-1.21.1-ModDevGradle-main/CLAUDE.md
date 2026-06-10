# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Overview

`umamusume` is a **NeoForge 1.21.1** Minecraft mod (Java 21) that adds Uma Musume horse-girl
mob entities with custom GeckoLib animations/models, spawn eggs, a creative tab, and a custom
"Hipódromo" (racetrack) worldgen structure. Comments and in-game strings are in Portuguese.

## Build & Run

Use the Gradle wrapper (`gradlew.bat` on Windows, `./gradlew` elsewhere). The NeoForge
ModDevGradle plugin defines the run configurations.

```bash
gradlew build                 # compile + package the mod jar into build/libs/
gradlew runClient             # launch a dev Minecraft client with the mod loaded
gradlew runServer             # launch a dev dedicated server (--nogui)
gradlew runGameTestServer     # run GameTests for the "umamusume" namespace
gradlew runData               # run data generation -> writes to src/generated/resources/
gradlew --refresh-dependencies   # rebuild the dependency cache if the IDE is missing libs
gradlew clean                 # reset build outputs (does not touch source)
```

There is no test suite beyond NeoForge's GameTest harness (no JUnit). CI (`.github/workflows/build.yml`)
runs `gradlew build` on push/PR.

## Configuration gotchas

- **`build.gradle` is the source of truth, not `gradle.properties`.** The version pins that
  actually take effect are hardcoded in `build.gradle`: NeoForge `21.1.35`, Parchment
  `2024.11.17` / `1.21.1`, GeckoLib `geckolib-neoforge-1.21.1:4.6.6`. `gradle.properties` still
  contains the unedited MDK template placeholders (`examplemod`, `neo_version=21.1.233`) — these
  are largely stale and unused. Change versions in `build.gradle`.
- The real mod id is **`umamusume`** (see `UmaMusumeMod.MOD_ID`). The `mod_id` used at runtime in
  `neoforge.mods.toml` comes from the hardcoded map in the `generateModMetadata` task in
  `build.gradle`, not from `gradle.properties`.
- `src/main/templates/META-INF/neoforge.mods.toml` is processed through `generateModMetadata`
  (property expansion via `expand`) before being placed in the final jar. Edit the template here,
  not a generated copy.
- `*.bbmodel` files (Blockbench sources) are excluded from the packaged resources.

## Architecture

Registration happens through NeoForge `DeferredRegister`s wired up in the `UmaMusumeMod(IEventBus)`
constructor (`UmaMusumeMod.java`). The registry holder classes:

- `ModEntities` — `EntityType`s for each Uma (all `MobCategory.CREATURE`, sized 0.6×1.8).
- `ModItems` — one `DeferredSpawnEggItem` per Uma.
- `ModCreativeTab` — the `uma_tab` creative tab listing every spawn egg.
- `ModStructures` — the Hipódromo structure type + piece type.
- `ClientSetup` — `@EventBusSubscriber(Dist.CLIENT)` that registers each entity's renderer on
  `EntityRenderersEvent.RegisterRenderers`.
- Entity attributes are registered separately via `EntityAttributeCreationEvent` in `UmaMusumeMod.registerAttributes`.

### Entities (the core repeated pattern)

Each Uma lives in its own package under `entity/<snake_case_name>/` and follows a fixed 4-part shape:

- `XxxEntity` extends `BaseUmaEntity` and implements GeckoLib's `GeoEntity`. It supplies an
  `AnimatableInstanceCache`, overrides `getUmaName()`, and `registerControllers()` to switch between
  `animation.<name>.run` (when moving) and `animation.<name>.idle`.
- `XxxModel` extends `GeoModel<XxxEntity>` and points at three resources under
  `assets/umamusume/`: `geo/<name>.geo.json`, `textures/entity/<name>.png`,
  `animations/<name>.animation.json`.
- `XxxRenderer` extends `GeoEntityRenderer<XxxEntity>`, wiring the model and texture.

`BaseUmaEntity` (`entity/BaseUmaEntity.java`) is the shared `PathfinderMob` base: default AI goals
(float, look-at-player, stroll, look-around), shared attributes (20 HP, 0.38 speed), and a
shift-click "follow me" toggle implemented in `mobInteract` + `tick`.

**To add a new Uma:** create the 4 classes in a new `entity/<name>/` package, add the three asset
files (geo/texture/animation JSON, named `animation.<name>.run` / `.idle`), then register it in
five places: `ModEntities` (EntityType), `ModItems` (spawn egg), `ModCreativeTab` (display item),
`ClientSetup` (renderer), and `UmaMusumeMod.registerAttributes` (attributes). Missing any one of
these is the usual cause of a crash or invisible/un-spawnable mob.

### Worldgen — Hipódromo structure

The racetrack is a single logical structure assembled from **four NBT template quadrants**
(`hipodromoid/ie/sd/se` under `data/umamusume/structure/`), stitched together in code rather than
via a jigsaw. `HipodromoStructure.findGenerationPoint` rejects ocean/cherry-grove biomes and
ice/water columns before placing; `HipodromoPiece.postProcess` clears air above, lays a dirt
foundation below, places the four quadrants at fixed offsets, strips snow, and spawns one of each
Uma at the center (in a centered 4×2 grid, not a straight line). Placement rules live in
`data/umamusume/worldgen/structure/hipodromo.json` and `structure_set/hipodromo_set.json`.

> **Gotcha (1.21.1):** the NBT template folder MUST be `structure/` (singular), not `structures/`.
> In 1.21 `StructureTemplateManager`'s resource lister is `new FileToIdConverter("structure", ".nbt")`,
> so templates load from `data/<ns>/structure/*.nbt`. If they sit in the old 1.20.1 `structures/`
> (plural) folder, `templateManager.getOrCreate(...)` silently returns an **empty** template (no error
> logged) and `placeInWorld` places nothing — but the manual terrain-clearing and Uma-spawning in
> `postProcess` still run, so the symptom is "ground cleared + Umas spawned but no racetrack built".

`HipodromoStructure.CODEC` is a `MapCodec` (not a plain `Codec`) because `StructureType.codec()`
returns `MapCodec` in 1.21.1, and `HipodromoStructure.HIPODROMO_TYPE`/`HIPODROMO_PIECE` are
registered as `DeferredHolder`s like the rest of the mod.

## Porting more code from the Forge 1.20.1 source

This project was migrated from a Forge 1.20.1 version (kept at `../../umamusume/forge-1.20.1-47.4.20-mdk/`,
outside this repo — read-only reference). The core/entity/worldgen classes are already ported and the
project compiles (`gradlew build`). If you copy further code from that Forge source, apply these
recurring 1.20.1→1.21.1 + Forge→NeoForge conversions (all of which were needed here):

- **Registries:** `net.minecraftforge.registries.DeferredRegister` / `RegistryObject` /
  `ForgeRegistries.*` → `net.neoforged.neoforge.registries.DeferredRegister` / `DeferredHolder` and
  `BuiltInRegistries.*` (or `net.minecraft.core.registries.Registries.*` keys).
- **Mod entrypoint:** `FMLJavaModLoadingContext.get().getModEventBus()` → inject `IEventBus` via the
  `@Mod` constructor. `GeckoLib.initialize()` is no longer called.
- **Client events:** `@Mod.EventBusSubscriber` → `net.neoforged.fml.common.EventBusSubscriber`.
- **`ResourceLocation`:** the `new ResourceLocation(ns, path)` constructor is private in 1.21.1 — use
  `ResourceLocation.fromNamespaceAndPath(ns, path)`.
- **Structures:** `StructureType`/`Structure.simpleCodec(...)` now use `MapCodec`, not `Codec`. Prefer
  `holder.is(Biomes.SOME_BIOME)` over looking biomes up by `ResourceLocation`.
- **GeckoLib (4.6.6):** the `core` infix was dropped — `software.bernie.geckolib.core.animation.*` →
  `software.bernie.geckolib.animation.*`, and `software.bernie.geckolib.core.animatable.instance.*` →
  `software.bernie.geckolib.animatable.instance.*`.
