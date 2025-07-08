# FoodDeliveryApp

FoodDeliveryApp is a modern food ordering application built with **Jetpack Compose**, designed with clean **MVVM** architecture. It allows users to browse food items, add to basket, favorite meals, set delivery addresses via live map location and complete orders using Firebase Realtime Database.

---

## Screenshots

| Home Screen | Detail Screen | Basket Screen | Address Screen |
|-------------|---------------|---------------|----------------|
| <img width="1194" height="2532" alt="Image" src="https://github.com/user-attachments/assets/20ad3c79-3c72-4e36-a763-995cd9c89b52" /> | <img width="1194" height="2532" alt="Image" src="https://github.com/user-attachments/assets/39c70c28-275b-4a24-aba3-6cec7374aada" /> | <img width="1194" height="2532" alt="Image" src="https://github.com/user-attachments/assets/73ad7be1-c09e-42ff-9019-f64249d5cc18" /> | <img width="1194" height="2532" alt="Image" src="https://github.com/user-attachments/assets/def866ec-7e59-4dbe-bf51-29b23f833bbe" /> |

| Welcome Screen | Sign In Screen | Sign Up Screen |
|----------------|----------------|----------------|
| <img width="1194" height="2532" alt="Image" src="https://github.com/user-attachments/assets/833cbaa5-23ed-44cf-bf92-1ed170118f0a" /> | <img width="1194" height="2532" alt="Image" src="https://github.com/user-attachments/assets/9498239b-42bf-44a0-8d75-26980ed42df9" /> | <img width="1194" height="2532" alt="Image" src="https://github.com/user-attachments/assets/41276cf6-227f-4a44-acae-dd9ef97f2891" /> |

---

## Project Structure

```plaintext
com.sinannuhoglu.fooddeliveryapp/
├── data
│   └── repository/         # Address repository for geocoding
├── di/
│   └── AppModule.kt        # Hilt dependency injection setup
├── model/                  # Data models (e.g., BasketItem, FoodItem)
├── navigation/
│   └── NavGraph.kt         # Compose navigation graph
├── network/
│   └── ApiService.kt       # Retrofit API service
├── ui/
│   ├── address/            # Address screen (map & address input)
│   ├── basket/             # Basket screen
│   ├── bottomnav/          # Bottom navigation bar
│   ├── components/         # Reusable UI components (buttons, fields, etc.)
│   ├── delivery/           # Delivery repository (Firebase orders)
│   ├── detail/             # Food detail screen
│   ├── favorite/           # Favorite screen
│   ├── home/               # Home screen with search and favorites
│   ├── login/              # Login screen
│   ├── register/           # Register screen
│   ├── theme/             # UI colors, typography, constants
│   └── welcome/           # Welcome screen
├── util/                   # Utility classes (constants, user session, error utils)
├── FoodDeliveryApp.kt      # Hilt application entry point
└── MainActivity.kt         # Main Compose activity and NavGraph host
```

---

## Features

- Search and browse meals
- Add/remove favorites (local state)
- Add to basket with quantity control
- Set delivery address using Google Maps (with live location and marker support)
- Address details & order confirmation
- Firebase authentication (sign in/register)
- Modern Compose UI with gradient backgrounds
- Toast and snackbar feedback
- Smooth navigation with bottom bar
- Realtime order saving in Firebase

---

## Technologies Used

| Technology           | Purpose                          |
|----------------------|----------------------------------|
| **Kotlin**           | Main programming language        |
| **Jetpack Compose**  | Declarative UI                   |
| **MVVM**             | Clean architecture pattern       |
| **Hilt**             | Dependency injection             |
| **Retrofit**         | REST API communication          |
| **OkHttp**           | HTTP client                     |
| **Firebase Auth**    | Authentication                  |
| **Firebase Realtime DB** | Stores order data along with address information in real-time |
| **Google Maps Compose** | Map view and marker logic     |
| **Coil**             | Image loading                   |
| **Coroutines**       | Asynchronous operations        |
| **Navigation Compose**| Navigation system            |
| **Material3**        | Modern UI components          |

---

## API

The app uses [Kasim Adalan's Food API](http://kasimadalan.pe.hu/yemekler/) for backend data.

- `tumYemekleriGetir.php`: Get food list
- `sepeteYemekEkle.php`: Add to basket
- `sepettenYemekSil.php`: Remove from basket
- `sepettekiYemekleriGetir.php`: Get basket items
