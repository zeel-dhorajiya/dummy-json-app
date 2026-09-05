# Android Clean Architecture Portfolio 📱

A modern Android application designed from the ground up to showcase industry-standard Android development practices. If you are looking for an Android Engineer who understands modern architecture, scalable design, and robust API integration, this codebase serves as a direct demonstration of those skills.

## 🎯 Core Skills Demonstrated

*   **UI & Presentation:** 100% **Jetpack Compose** (Material 3, Navigation Compose, Lazy Layouts, State Management).
*   **Architecture Pattern:** **MVVM** (Model-View-ViewModel) paired with **Clean Architecture** (Data, Domain, UI layers).
*   **Dependency Injection:** **Koin 4.0** (Modules, ViewModel Injection).
*   **REST API Integration:** **Retrofit 2** & **OkHttp**.
*   **JSON Serialization:** **Kotlinx Serialization** for robust, type-safe JSON parsing.
*   **Asynchronous Programming:** **Kotlin Coroutines** & **Flows** (`StateFlow`, `collectAsState`).
*   **Authentication & Security:** JWT Token management via `SharedPreferences`.

## ⚙️ Project Structure Highlight

*   `data/api`: Contains the `Retrofit` setup and interface endpoints linking to a remote REST API.
*   `data/repository`: Concrete implementations of data fetching and caching logic.
*   `di/`: The Koin module declarations injecting Repositories, ViewModels, and Network clients.
*   `ui/`: Modularized Jetpack Compose screens (Auth, Products, Cart, Profile) built reactively using `StateFlow`.

*(For the complete technical specifications and API endpoints used in this demo, please see [API_DOC.md](API_DOC.md))*
