# ForgeModTemplate

A template repository for creating Minecraft mods using [Forge](https://files.minecraftforge.net/net/minecraftforge/forge/).

---

## 🧱 Project Setup

Before developing your mod, configure the project metadata and structure.

### 1. Update Metadata

Most configuration values are located in `gradle.properties`:
- **`environment`** – Minecraft and Forge version.
- **`mappings`** – Parchment mapping version.
- **`properties`** – Basic mod information:
  - `modId`: A single lowercase word (e.g., `examplemod`).
  - `modGroup`: Must match your package path in `src/main/java`.
  - `modVersion`: Can be left as-is; CI/CD will update it on release.
- **`description`** – User-facing mod description.
- **`cicd`** - Repository publish link.
  - `modGithubRepoURL`: The Github repository of the mod. 

### 2. Rename Source Packages

Adjust the Java package structure under `src/main/java`:
- Replace `com/example/examplemod` with your `modGroup`.
- Rename `ExampleMod.java` to your main mod class name.

### 3. Mixins (Optional)

Mixins are included by default for advanced modification of Minecraft internals. You can choose to keep or remove them:

#### ✅ If keeping Mixins:
- Rename `examplemod.mixin.json` (in `src/main/resources`) to match your `modId`.
- Update the `package` in the mixin config file to match your `modGroup` (pointing to the mixin package).

#### ❌ If removing Mixins:
- Delete:  
  - `src/main/resources/examplemod.mixin.json`
- In `settings.gradle`, remove:
  
  ```groovy
  maven { url = 'https://repo.spongepowered.org/repository/maven-public/' }
  ```
- In build.gradle, remove:
  
  ```groovy
  id 'org.spongepowered.mixin' version '0.7-SNAPSHOT'               // Line 4
  arg "-mixin.config=${modId}.mixin.json"                           // Lines 21 & 26
  testAnnotationProcessor 'org.spongepowered:mixin:0.8.5:processor' // Line 38
  annotationProcessor 'org.spongepowered:mixin:0.8.5:processor'     // Line 39
  implementation 'org.spongepowered:mixin:0.8.5'                    // Line 40
  ```

---

## Continuous Integration & Deployment (CI/CD)

This template includes a GitHub Actions workflow for automated testing and deployment to [Modrinth](https://modrinth.com).

### Setup

#### 1. Modrinth API Token

- Go to **Modrinth > Settings > Developer > Personal Access Tokens**.
- Generate a token with these scopes:
  - `Create versions`
  - `Write projects`
- Copy the token for the next step.

#### 2. GitHub Secrets

- Go to **GitHub > Your Repository > Settings > Secrets and variables > Actions**.
- Add a new repository secret:
  - **Name**: `MODRINTH_TOKEN`
  - **Value**: *(Paste your Modrinth token)*

### Releasing Your Mod

Releases are handled through GitHub’s Releases page:

1. Click **Draft a new release**.
2. Use a tag name with the format: `<alpha_numeric>-<alpha|beta|release>`
   - Example: `1.0.0-beta`
3. Fill out any relevant release information and click **Publish release**.

Once published, the workflow will:
- ✅ Run tests (if any) on your mod  
- 🚀 If tests pass, upload and publish the mod to Modrinth
