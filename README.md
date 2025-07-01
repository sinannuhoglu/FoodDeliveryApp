# FoodDeliveryApp

Food Delivery App is a modern food ordering application built with **Jetpack Compose**, designed to allow users to browse meals, add them to their cart, favorite them and log in/register using Firebase Authentication.

---

## Screenshots
| Welcome Screen | Sign In Screen | Sign Up Screen | Home Screen | Detail Screen | Basket Screen |
|----------------|----------------|----------------|-------------|---------------|---------------|
| ![Image](https://github.com/user-attachments/assets/9794ef81-535a-4ae6-8659-0c3709593f14) | ![Image](https://github.com/user-attachments/assets/c433cdf9-713a-46b2-b442-93347444fef9) | ![Image](https://github.com/user-attachments/assets/b12dd83d-8fdd-4519-a370-e7639063bf59) | ![Image](https://github.com/user-attachments/assets/7c52a3bc-0d5d-465d-9eb4-be643baf7865) | ![Image](https://github.com/user-attachments/assets/33747393-4f88-4c18-b3f8-549a4d8e5e61) | ![Image](https://github.com/user-attachments/assets/c2dbfb44-19ed-48a0-b227-00d2b2c41fad) |


---

## Project Structure

```text
com.sinannuhoglu.fooddeliveryapp/
├── model/          # Data models
├── network/        # Retrofit API client and service
├── ui/             # UI screens and ViewModels
│   ├── basket/    # Basket screen 
│   ├── detail/    # Food detail screen
│   ├── favorite/  # Favorites screen
│   ├── home/      # Home screen
│   ├── login/     # Login screen
│   ├── register/  # Register screen
│   └── welcome/   # Welcome screen
├── navigation/    # Navigation graph
├── di/            # Dependency Injection modules
├── util/          # Utility classes (Constants, UserSession, etc.)
└── MainActivity.kt  # Navigation host and Hilt entry point
```

---

## Technologies Used

| Technology         | Description                  |
|--------------------|------------------------------|
| **Kotlin**         | Main language                |
| **Jetpack Compose**| Declarative UI framework     |
| **Hilt**           | Dependency Injection        |
| **Retrofit**       | Networking library          |
| **OkHttp**         | HTTP client                 |
| **Coil**           | Image loading              |
| **Firebase Auth**  | Authentication             |
| **Navigation Compose** | Screen navigation     |
| **Coroutines**     | Asynchronous operations    |
| **Material3**      | UI components             |

---

## Features

- Browse and search food items
- Add to basket with quantity selection
- Mark items as favorites (local only)
- Firebase login/register
- Modern Compose UI with gradient backgrounds
- Loading indicators & toasts

---

## API

The app uses [Kasim Adalan's Food API](http://kasimadalan.pe.hu/yemekler/) for backend data.

- `tumYemekleriGetir.php`: Get food list
- `sepeteYemekEkle.php`: Add to basket
- `sepettenYemekSil.php`: Remove from basket
- `sepettekiYemekleriGetir.php`: Get basket items
