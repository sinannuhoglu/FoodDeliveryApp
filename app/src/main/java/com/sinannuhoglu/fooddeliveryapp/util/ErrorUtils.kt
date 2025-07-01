package com.sinannuhoglu.fooddeliveryapp.util

/**
 * Firebase hatasına göre kullanıcı dostu bir hata mesajı döner.
 * Yaygın Firebase hatalarını daha anlaşılır Türkçe mesajlara çevirir.
 */
fun getFriendlyErrorMessage(errorMessage: String?): String {
    return when {
        errorMessage.isNullOrBlank() -> ""

        errorMessage.contains("The email address is badly formatted") ->
            "E-posta adresi hatalı formatta. (The email address is badly formatted)"

        errorMessage.contains("There is no user record corresponding") ->
            "Kullanıcı bulunamadı. (No user record found)"

        errorMessage.contains("The password is invalid") ->
            "Şifre hatalı. (Invalid password)"

        errorMessage.contains("A network error") ->
            "Ağ hatası oluştu. (Network error)"

        else -> "Bir hata oluştu. ($errorMessage)"
    }
}
