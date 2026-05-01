📰 Android News App built with Jetpack Compose

A modern, fully functional News App that delivers real-time news with offline support, bookmarking, and category filtering.

## ✨ Features
- 🌐 Real-time news from NewsAPI.org
- 📂 6 Categories — General, Business, Technology, Sports, Health, Entertainment
- 🔖 Bookmark articles to read later
- 📡 Offline support with local caching
- 🌍 Full article reading via WebView
- ⚡ Smooth navigation between screens

## 🛠️ Tech Stack
| Technology | Purpose |
|---|---|
| Jetpack Compose | Modern UI |
| MVVM Architecture | Clean code structure |
| Hilt | Dependency Injection |
| Retrofit + OkHttp | REST API calls |
| Room Database | Local storage & caching |
| Kotlin Coroutines + Flow | Async operations |
| KSP | Annotation processing |
| Coil | Image loading |
| Navigation Compose | Screen navigation |

## 📁 Project Structure
```
NewsApp/
├── data/
│   ├── api/          # Retrofit API interface
│   ├── db/           # Room Database & DAO
│   ├── model/        # Data classes
│   └── repository/   # Data manager
├── di/               # Hilt modules
├── ui/
│   ├── screens/      # Composable screens
│   ├── components/   # Reusable UI components
│   └── navigation/   # Navigation setup
└── viewmodel/        # ViewModels
```

## 🚀 Getting Started

1. Clone the repository
```bash
git clone https://github.com/muddasirdev/NewsApp.git
```

2. Get your free API key from [NewsAPI.org](https://newsapi.org)

3. Add your API key in `NewsApi.kt`
```kotlin
@Query("apiKey") apiKey: String = "YOUR_API_KEY_HERE"
```

4. Build and run the project in Android Studio

## 📸 Screenshots

| Home Screen | Bookmarks | Article Detail |
|---|---|---|
| ![home](screenshots/home.png) | ![bookmarks](screenshots/bookmarks.png) | ![detail](screenshots/detail.png) |

## 📄 License
This project is open source and available under the [MIT License](LICENSE).

---
⭐ If you found this helpful, please give it a star!
