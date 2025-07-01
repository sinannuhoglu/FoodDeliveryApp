# FoodDeliveryApp

FoodDeliveryApp is a modern food ordering application built with **Jetpack Compose** and follows a clean **MVVM architecture**. Users can browse meals, add items to their cart, mark favorites and log in/register using Firebase Authentication.

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
├── ui/             # Screens + ViewModels (MVVM)
│   ├── basket/    
│   ├── detail/    
│   ├── favorite/  
│   ├── home/      
│   ├── login/     
│   ├── register/  
│   └── welcome/   
├── navigation/      # Compose navigation graph
├── di/              # Hilt dependency injection setup
├── util/            # Utility files (Constants, UserSession, etc.)
└── MainActivity.kt  # Entry point & Navigation host
```

---

## Technologies Used

| Technology           | Purpose                          |
|----------------------|----------------------------------|
| **Kotlin**           | Main programming language        |
| **Jetpack Compose**  | Declarative UI                   |
| **MVVM**             | Architecture pattern            |
| **Hilt**             | Dependency injection           |
| **Retrofit**         | REST API communication          |
| **OkHttp**           | HTTP client                     |
| **Firebase Auth**    | Authentication                  |
| **Coil**             | Image loading                   |
| **Coroutines**       | Asynchronous operations        |
| **Navigation Compose**| Navigation system            |
| **Material3**        | Modern UI components          |

---

## Features

- Search food items easily
- Add/remove favorites with one tap
- Add to basket with quantity selection
- Firebase authentication (Sign in / Sign up)
- Gradient backgrounds and modern Compose UI
- Smooth navigation with bottom bar
- Toasts and loading indicators

---

## API

The app uses [Kasim Adalan's Food API](http://kasimadalan.pe.hu/yemekler/) for backend data.

- `tumYemekleriGetir.php`: Get food list
- `sepeteYemekEkle.php`: Add to basket
- `sepettenYemekSil.php`: Remove from basket
- `sepettekiYemekleriGetir.php`: Get basket items
