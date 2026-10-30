HyperStorage is still under developement. 
# HyperStorage

HyperStorage is a Paper plugin for a Hypixel SkyBlock-inspired personal storage system. Players use `/storage` to open a menu of storage pages and access their stored items.

## Features

- A menu for opening personal storage pages.
- 45 item slots per page, with a maximum capacity of 405 slots (9 pages).
- Per-player storage data saved in JSON files keyed by player UUID.
- Online player data is autosaved every 10 seconds.
- An administrative command for changing a player's storage capacity.

## Requirements

- A Paper server compatible with the Paper API `26.3`.
- Java 25.

The Paper API and Gson are compile-time dependencies configured by Gradle.

## Build

From the project directory, run:

```bash
./gradlew build
```

The plugin JAR will be generated in `build/libs/`. Place it in the server's `plugins/` directory and restart the server.

## Commands

| Command | Description | Access |
| --- | --- | --- |
| `/storage` | Open your personal storage menu. | Players |
| `/storage help` | Show the storage command help. | Operator with `hyperstorage.admin` |
| `/storage setslots <player> <slots>` | Set an online player's capacity from 0 to 405 slots. | Operator with `hyperstorage.admin` |

The plugin currently requires both operator status and the `hyperstorage.admin` permission to enter the administrative `/storage` command path.

`/adminstorage` is declared by the plugin, but its administrative functionality is not complete yet.

## Player data

Storage data is written under: For now data is saved inside.dat in compressed form using GZIP lib.
The json inside .dat file contains storage level and 405 ItemStacks encoded in base64.
```text
plugins/HyperStorage/data/<player-uuid>.dat
```

Each file contains the allowed slot count and serialized item data. Back up this directory before removing or replacing the plugin.

## Project status

This project is under active development. The storage menu, page navigation, and periodic persistence are implemented; administrative tooling and edge-case handling may change as the plugin develops.
