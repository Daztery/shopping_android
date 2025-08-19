# Shopping Android

Monthly shopping application developed in **Kotlin** with **Jetpack Compose**, following **MVVM + Clean Architecture** and **DI with Hilt**. It allows adding, editing, and deleting products, managing quantities, viewing **subtotal per item** and **overall total**, and (optionally) setting a **spending limit** with remaining/over-limit indicators. Local persistence with **Room**.

> **Main stack:** Kotlin · Jetpack Compose · Hilt · Room · Coroutines/Flow · Gradle KTS · Version Catalog

---

## ✨ Features

- **Product CRUD**: add, edit, delete.
- **Quantity and unit price** → calculate **subtotal** per product.
- **Real-time total** of the shopping list.
- **Optional spending limit**: shows remaining and alerts if exceeded.
- **Local persistence** with Room.
- **100% Compose UI** with reactive state (State/Flow).
- **Clean Architecture** (domain/data/presentation) with **MVVM** and **Use Cases**.

---

## 🏗️ Architecture

```
app/
├─ data/
│  ├─ local/           # Room (DAO/Entities)
│  ├─ remote/          # Retrofit (if applicable)
│  ├─ mapper/          # Data <-> Domain mappers
│  └─ repository/      # Repository implementations
├─ domain/
│  ├─ model/           # Domain models
│  ├─ repository/      # Repository contracts
│  └─ usecase/         # Use cases (business logic)
├─ presentation/
│  ├─ navigation/      # Navigation graph and routes
│  ├─ screens/         # Compose screens + state
│  └─ viewmodel/       # ViewModels (Hilt)
├─ di/                 # Hilt modules (providers/binds)
├─ MyApp.kt            # Compose setup (MaterialTheme, NavHost)
└─ MainActivity.kt     # Activity host (HiltAndroidApp / AndroidEntryPoint)
```

**Key principles**

- **Single source of truth**: data flows from repository → use case → ViewModel → UI.
- **Side-effects** isolated in ViewModel (coroutines).
- **Layered separation**: UI does not know persistence/network details.

---

## 🧪 State & Logic (example)

- `PurchaseState` centralizes:
  - `items: List<Purchase>`
  - `spendingLimit: Double?`
  - `total`, `isOverLimit`, `remaining`
- ViewModels expose `StateFlow` for Compose.
- Use cases encapsulate actions: `AddProduct`, `EditProduct`, `DeleteProduct`, `GetProducts`, etc.

---

## 📦 Requirements

- **Android Studio** (Koala or newer recommended)
- **JDK 17**
- **Gradle KTS** with **Version Catalog** (`libs.versions.toml`)
- **AGP 8.x** (recommended)

> The project uses **KSP for Room** and **Hilt** for DI (with either `kapt` or `ksp` depending on configuration).

---

## 🚀 How to run the project

1. **Clone**
   ```bash
   git clone https://github.com/Daztery/shopping_android.git
   cd shopping_android
   ```

2. **Open in Android Studio**  
   *File → Open…* and select the project folder.

3. **Sync Gradle**  
   Android Studio will do it automatically.

4. **Run**  
   Select the **Run configuration** for the `app` module and press ▶️.

> Command line:
```bash
./gradlew clean :app:assembleDebug
./gradlew :app:installDebug
```

---

## 🧰 Technical Setup

### Hilt (DI)
- Annotations on `Application` (`@HiltAndroidApp`) and `Activity`/`ViewModel` (`@AndroidEntryPoint`, `@HiltViewModel`).
- Modules in `di/` provide `RoomDatabase`, `Dao`, `Repository`, `Retrofit` (if applicable).

### Room (Persistence)
- `@Entity`, `@Dao`, `@Database`.
- Processing with **KSP** (or `kapt` if configured).
- Repository in `data/repository` implements interfaces from `domain/repository`.

### Compose (UI)
- Screens in `presentation/screens`.
- Navigation handled with `NavHost`.
- State derived from ViewModels via `collectAsState()`.
  
---

## 🗺️ Roadmap

- [ ] Swipe-to-delete with animation.
- [ ] Product search/filter.
- [ ] Export/Import list.
- [ ] Multiple lists (e.g., by month).
- [ ] Cloud sync (optional).
